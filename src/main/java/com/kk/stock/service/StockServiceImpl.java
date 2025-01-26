package com.kk.stock.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kk.stock.entity.Stock;
import com.kk.stock.entity.StockList;
import com.kk.stock.entity.StockPrice;
import com.kk.stock.exception.DuplicateStockException;
import com.kk.stock.exception.StockNotFoundException;
import com.kk.stock.model.StockPriceDetails;
import com.kk.stock.model.Symbols;
import com.kk.stock.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	RestTemplate restTmplate;
	
	@Value("${api.market.symbol.prices}")
	String marketApiPriceUrl;

	@Override
	public List<Stock> getAllStockDetails() {
		return stockRepo.findAll();
	}

	public List<String> getAllStocksymbols() {
		return stockRepo.findSymbols();
	}

	@Override
	public Stock getStockBySymbol(String symbol) {
		Optional<Stock> stock = stockRepo.findBySymbol(symbol);
		if(stock.isPresent()) {
			System.out.println(symbol + ": Stock Details fetched Successfully");
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
	public StockList saveAllStockData(StockList stockList) {		
		try {
			List<Stock> stocks = stockRepo.saveAll(stockList.getStockList());
			return new StockList(stocks);
		}catch (Exception e) {
			System.out.println("Exception while saving stock data: "+e.getMessage()+e.getClass());
			throw new DuplicateStockException("Stock already existing");
		}
		
	}

	@Override
	public String updateStockPrices(Symbols symbols) {
		
		
		List<String> errSym = new ArrayList<>();
		List<String> updSym = new ArrayList<>();
		List<String> extSym = new ArrayList<>();
		
		for(String symbol : symbols.getSymbols()) {
			try {
				// Fetch Stock Details from DB
				Stock stock = getStockBySymbol(symbol);
				System.out.println(symbol + ": Stock details fetched from DB");
				Optional<StockPrice> latestPriceObj = stock.getStockPrices().stream().max(Comparator.comparing(StockPrice::getDate));
				if(latestPriceObj.isPresent()) {
					StockPrice latestPrice = latestPriceObj.get();
					LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS);
					System.out.println("###Yesterday"+yesterday);
					System.out.println("###LastPrice"+latestPrice.getDate());
					if(latestPrice.getDate().compareTo(yesterday) < 0) {
						updateLatestStockFromMarket(updSym, symbol, stock);
					} else {
						extSym.add(symbol);
						System.out.println(symbol + ": Latest Stock Price existing in DB");
					}
				} else {
					updateLatestStockFromMarket(updSym, symbol, stock);
				}
				
			}catch (RestClientException ex) {
				System.out.println("Exception occured while fetching prices for stock:"+symbol);
				System.out.println(ex.getLocalizedMessage());
				errSym.add(symbol);
			}
		}
		
		return "Stock Update Status: Errors" +errSym +" LatestPresent"+ extSym +" Updated"+updSym;
	}

	private void updateLatestStockFromMarket(List<String> updSym, String symbol, Stock stock) {
		ResponseEntity<StockPriceDetails> response = restTmplate.getForEntity(marketApiPriceUrl, StockPriceDetails.class, symbol);
		System.out.println(symbol + ": Stock price fetched from Market");
		StockPriceDetails stockPriceDetails = response.getBody();	
		stock.getStockPrices().addAll(stockPriceDetails.getStockPriceList());
		updSym.add(symbol);
		stockRepo.save(stock);
	}

	@Override
	public Stock getStockById(Long id) {
		Optional<Stock> stock = stockRepo.findById(id);
		if(stock.isPresent()) {
			System.out.println(id + ": Stock Details fetched Successfully");
			return stock.get();			
		}
		throw new StockNotFoundException("Stock not found for stockId: "+id);
	}

}
