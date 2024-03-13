package com.kk.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kk.stock.entity.Stock;
import com.kk.stock.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	StockService stockService;

	@GetMapping("/stocks")
	List<Stock> getAllStockDetails() {
		return stockService.getAllStockDetails();
	}

	@GetMapping("/symbol/{symbol}")
	Stock getStockBySymbol(@PathVariable String symbol) {
		return stockService.getStockBySymbol(symbol);
	}
	
	@PostMapping
	ResponseEntity<Stock> saveStockDetails(@RequestBody Stock stock) {
		stockService.saveStockData(stock);
		return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
		
	}


}
