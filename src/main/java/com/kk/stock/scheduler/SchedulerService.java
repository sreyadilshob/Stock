package com.kk.stock.scheduler;

import com.kk.stock.model.Symbols;
import com.kk.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SchedulerService {

	@Autowired
    StockService stockService;

	//second, minute, hour, day of month, month, day(s) of week
	@Scheduled(cron = "0 30 2 * * *")
	public void schedulerOnEvery5Minutes() {
		System.out.println("Scheduler run ");
		Symbols symbols = new Symbols();
		symbols.setSymbols(stockService.getAllStocksymbols());

		stockService.updateStockPrices(symbols);

	}

}
