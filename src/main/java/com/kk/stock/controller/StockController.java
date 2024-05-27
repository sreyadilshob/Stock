package com.kk.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kk.stock.entity.Stock;
import com.kk.stock.entity.StockList;
import com.kk.stock.model.Symbols;
import com.kk.stock.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	StockService stockService;

	@GetMapping("/stocks")
	StockList getAllStockDetails() {
		StockList stockList = new StockList();
		stockList.setStockList(stockService.getAllStockDetails());
		return stockList;
	}

	@GetMapping("/symbol/{symbol}")
	Stock getStockBySymbol(@PathVariable String symbol) {
		return stockService.getStockBySymbol(symbol);
	}
	
	@GetMapping("/id/{id}")
	Stock getStockById(@PathVariable Long id) {
		return stockService.getStockById(id);
	}
	
	@PutMapping("/prices")
	String saveStockPrices(@RequestBody Symbols symbols) {
		return stockService.updateStockPrices(symbols);
		
	}

	@PostMapping
	ResponseEntity<Stock> saveStockDetails(@RequestBody Stock stock) {
		return new ResponseEntity<>(stockService.saveStockData(stock), HttpStatus.CREATED);
	}
	
	@PostMapping("/all")
	ResponseEntity<StockList> saveAllStockDetails(@RequestBody StockList stocks) {
		return new ResponseEntity<>(stockService.saveAllStockData(stocks), HttpStatus.CREATED);
	}

}
