	package com.kk.stock.model;

import java.util.List;

import com.kk.stock.entity.StockPrice;

public class StockPriceDetails {
	
	private String symbol;
	
	private List<StockPrice> stockPriceList;

	public List<StockPrice> getStockPriceList() {
		return stockPriceList;
	}

	public void setStockPriceList(List<StockPrice> stockPriceList) {
		this.stockPriceList = stockPriceList;
	}
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


}
