# -*- coding: utf-8 -*-

import os
import json
import redis
import pickle
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import pandas_datareader as web
import seaborn as sns

from stock_data import stock_util

raw_days = {}

stocks = []

def init_stocks():
    with open(file='/Users/linjk/Documents/code/java/Jarvis/python/stock_data/config/stock.json', mode='r') as f:
        for stock in json.load(f):
            stocks.append(stock_util.stock_util(stock_num=stock['num'], stock_start=stock['start'], stock_end=stock['end'], stock_description=stock['description']))

def map_index(i):
    return i[i.find('Timestamp')+11:].split(' ')[0]

# 获取原有日期序号
def init_raw_days(index_data):
    for (idx, dat) in enumerate(index_data):
        raw_days[dat] = idx

def data_process(stock_data_raw, col_name):
    days = []
    vals = []
    for (dat, val) in zip(stock_data_raw.index, stock_data_raw[col_name].values):
        day = dat
        #print(day + ' - ' + str(val))
        days.append(day)
        vals.append(val)
    return pd.Series(vals, index=days)    

def get_operation(stock_data_raw : pd.DataFrame, col_name, op_type):
    """
        @param stock_data_raw : stock data
        @param col_name : data column name
        @param op_type : 1 - throw  0 - buy
    """
    xs = []
    ys = []
    x = stock_data_raw[col_name].loc[stock_data_raw.signal==1]#.loc[stock_data_raw['signal']==1].index
    y = stock_data_raw[col_name]#.loc[stock_data_raw['signal']==op_type]
    print(x)
    # print('----')
    # print(y)

    for (dat, val) in zip(x, y.values):
        day = dat
        xs.append(day)
        ys.append(val)
    return xs,ys

if __name__ == "__main__":
    api_key = os.getenv('TIINGO_API_KEY')
    if api_key is None:
       print('You should config env `TIINGO_API_KEY` first')
       exit(-1)

    init_stocks()

    stock = stocks[0]
    stock_raw_data = None
    rd = redis.Redis(connection_pool=redis.ConnectionPool(host='localhost', port=6379))
    r_key = f'stock_{stock.get_stock_number}'
    r_value = rd.get(r_key)
    if r_value is None:
        print('get stock data from internet...')
        data = web.data.DataReader(stock.get_stock_number, 'tiingo', stock.get_stock_start, stock.get_stock_end, api_key=api_key)
        rd.set(r_key, str((pd.DataFrame(data)).to_json()))
        r_value = rd.get(r_key)

    stock_raw_data = pd.read_json(r_value)
    stock_raw_data['diff'] = stock_raw_data['close'].diff()
    stock_raw_data['signal'] = np.where(stock_raw_data['diff']>0, 1, 0)
    stock_raw_data = stock_raw_data.rename(index=map_index)
    print(stock_raw_data)

    plt.figure(figsize = (20, 5))

    init_raw_days(stock_raw_data.index)

    data_process(stock_raw_data, 'close').plot(linewidth=1, color='k', grid=True)

    # 股票上涨，标注卖出信号
    days, vals = get_operation(stock_raw_data, 'close', 1)
    # print(days)
    # print('----')
    # print(vals)
    # temp = []
    # for d in days:
    #     temp.append(raw_days[d])
    # x1 = pd.Series(temp, index=days)
    # y1 = pd.Series(vals, index=days)
    # # print(x1)
    # # print('----')
    # # print(y1)
    # plt.scatter(x=x1, y=y1, marker='v', s=50, c='g')

    # #x11 = pd.Series([0, 1, 2, 3],
    # #                 index=['2020-02-17',
    # #                        '2020-02-18',
    # #                        '2020-02-19',
    # #                        '2020-02-20'])
    # #y11 = pd.Series([73.03, 73.3, 75.23, 78.62],
    # #                 index=['2020-02-17',
    # #                        '2020-02-18',
    # #                        '2020-02-19',
    # #                        '2020-02-20'])

    # # 股票下跌，标注买入信号
    # days2, vals2 = get_operation(data, 'close', 0)
    # temp = []
    # for d in days2:
    #     temp.append(raw_days[d])
    # x2 = pd.Series(temp, index=days2)
    # y2 = pd.Series(vals2, index=days2)
    # plt.scatter(x=x2, y=y2, marker='^', s=50, c='r')

    plt.show()