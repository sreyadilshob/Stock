package com.kk.stock.service;

import java.util.List;

import com.kk.stock.entity.Stock;

public interface StockService {

	List<Stock> getAllStockDetails();

	Stock getStockBySymbol(String symbol);

	Stock saveStockData(Stock stock);

}
