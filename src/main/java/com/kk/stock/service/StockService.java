package com.kk.stock.service;

import java.util.List;

import com.kk.stock.entity.Stock;
import com.kk.stock.entity.StockList;
import com.kk.stock.model.Symbols;

public interface StockService {

	List<Stock> getAllStockDetails();

	Stock getStockBySymbol(String symbol);

	Stock saveStockData(Stock stock);

	String updateStockPrices(Symbols symbols);

	Stock getStockById(Long stockId);

	StockList saveAllStockData(StockList stocks);

}
