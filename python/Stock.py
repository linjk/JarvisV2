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

stocks = []

def init_stocks():
    with open(file='/Users/linjk/Documents/code/java/Jarvis/python/stock_data/config/stock.json', mode='r') as f:
        for stock in json.load(f):
            stocks.append(stock_util.stock_util(stock_num=stock['num'], stock_start=stock['start'], stock_end=stock['end'], stock_description=stock['description']))

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

    plt.figure(figsize = (20, 5))
    stock_raw_data['close'].plot(linewidth=1, color='k', grid=True)

    plt.show()