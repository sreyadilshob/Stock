package com.kk.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kk.stock.entity.Stock;
import com.kk.stock.exception.DuplicateStockException;
import com.kk.stock.exception.StockNotFoundException;
import com.kk.stock.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockRepository stockRepo;

	@Override
	public List<Stock> getAllStockDetails() {
		return stockRepo.findAll();
	}

	@Override
	public Stock getStockBySymbol(String symbol) {
		Optional<Stock> stock = stockRepo.findBySymbol(symbol);
		if(stock.isPresent()) {
			return stock.get();
		}
		throw new StockNotFoundException("Stock not found for symbol: "+symbol);
	}

	
	@Override
	public Stock saveStockData(Stock stock) {		
		try {
			return stockRepo.save(stock);
		}catch (Exception e) {
			
			System.out.println("Exception while saving stock data: "+e.getMessage()+e.getClass());
			throw new DuplicateStockException("Stock already existing");
		}
		
	}

}
