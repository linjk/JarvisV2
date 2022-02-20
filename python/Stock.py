# -*- coding: utf-8 -*-

import os
import json
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

if __name__ == "__main__":
    api_key = os.getenv('TIINGO_API_KEY')
    if api_key is None:
        print('You should config env `TIINGO_API_KEY` first')
        exit(-1)

    init_stocks()
    print(stocks[0].get_stock_description)