//
//package com.kk.stock.scheduler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.kk.stock.service.RecommendationService;
//
//@Component
//public class SchedulerService {
//
//	@Autowired
//	RecommendationService stockService;
//
//	@Scheduled(cron = "0 0/1 * 1/1 * ?")
//	public void schedulerOnEvery5Minutes() {
//		System.out.println("Scheduler run ");
//		stockService.verifyDateAndUpdateStatus();
//
//	}
//
//}
