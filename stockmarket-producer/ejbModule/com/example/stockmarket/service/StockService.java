package com.example.stockmarket.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

import com.example.stockmarket.event.StockPriceChangedEvent;
import com.example.stockmarket.repository.StockRepository;

//standalone-full.xml:
//<subsystem xmlns="urn:jboss:domain:messaging-activemq:10.0">
//<server name="default">
//  . . .
//  <jms-queue name="stockQueue" entries="java:/jms/queue/stockQueue"/>
//  . . .
//</server>
//</subsystem>
@Stateless
public class StockService {
	@Inject
	private StockRepository stockRepo;
	@Inject
	private JMSContext context;
	@Resource(mappedName = "java:/jms/queue/stockQueue")
	private Queue stockQueue;

	@Schedule(second = "*/5", hour = "*", minute = "*")
	public void sendStockPriceChangedEvent() {
		stockRepo.findAll(0, 25).forEach(stock -> {
			var symbol = stock.getSymbol();
			var oldPrice = stock.getPrice();
			var changeRatio = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
			var newPrice = oldPrice * (1. + changeRatio);
			stock.setPrice(newPrice);
			stockRepo.update(stock);
			var event = new StockPriceChangedEvent(symbol, oldPrice, newPrice);
			System.err.println("Sending event to Queue " + event);
			context.createProducer().send(stockQueue, createJson(event));
		});
	}

	private String createJson(StockPriceChangedEvent event) {
		return Json.createObjectBuilder().add("symbol", event.getSymbol()).add("oldPrice", event.getOldPrice())
				.add("newPrice", event.getNewPrice()).build().toString();
	}
}
