# -*- coding: utf-8 -*-

import json

class stock_util:
    def __init__(self, stock_num, stock_start, stock_end, stock_description=None) -> None:
        self._stock_num = stock_num
        self._stock_start = stock_start
        self._stock_end = stock_end
        self._stock_description = stock_description

    @property
    def get_stock_number(self):
        return self._stock_num

    @property
    def get_stock_start(self):
        return self._stock_start

    @property
    def get_stock_end(self):
        return self._stock_end

    @property
    def get_stock_description(self):
        return self._stock_description


if __name__ == "__main__":
    with open(file='/Users/linjk/Documents/code/java/Jarvis/python/stock_data/config/stock.json', mode='r') as f:
        config = json.load(f)
        for stock in config:
            stock_config = stock_util(stock_num=stock['num'], stock_start=stock['start'], stock_end=stock['end'])
            print(f'stock_config: {stock_config.get_stock_number}')