package com.kk.stock.entity;
import java.util.List;

public class StockList {
	
	private List<Stock> stockList;

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	public StockList() {}
	public StockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
 
	
}
