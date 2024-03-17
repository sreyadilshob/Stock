package com.kk.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.stock.entity.Stock;
import com.kk.stock.entity.StockPrice;
import com.kk.stock.exception.DuplicateStockException;
import com.kk.stock.exception.StockNotFoundException;
import com.kk.stock.model.StockPriceDetails;
import com.kk.stock.model.Symbols;
import com.kk.stock.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	RestTemplate restTmplate;
	
	@Value("${url.market.api}")
	String marketApiUrl;

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

	@Override
	public String updateStockPrices(Symbols symbols) {
		
		for(String symbol : symbols.getSymbols()) {
			ResponseEntity<StockPriceDetails> response = restTmplate.getForEntity(marketApiUrl, StockPriceDetails.class, symbol);
			System.out.println("stock price fetched");
			
			if(!response.getStatusCode().is2xxSuccessful()) {
				
			}
			
			Stock stock = getStockBySymbol(symbol);
			System.out.println("stock fetched");
			StockPriceDetails stockPriceDetails = response.getBody();	
			stock.getStockPrices().addAll(stockPriceDetails.getStockPriceList());
			stockRepo.save(stock);				
		}
		
		return "stock updated successfully";
	}

	@Override
	public Stock getStockById(Long id) {
		Optional<Stock> stock = stockRepo.findById(id);
		if(stock.isPresent()) {
			return stock.get();			
			}
		throw new StockNotFoundException("Stock not found for stockId: "+id);
	}

}
