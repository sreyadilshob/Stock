//
//package com.kk.stock.scheduler;
//
//import com.kk.stock.service.StockService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class SchedulerService {
//
//	@Autowired
//    StockService stockService;
//
//	@Scheduled(cron = "0 0/5 * 1/1 * ?")
//	public void schedulerOnEvery5Minutes() {
//		System.out.println("Scheduler run ");
//		stockService.updateStockPrices();
//
//	}
//
//}
