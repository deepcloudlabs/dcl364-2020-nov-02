package com.example.stockmarket.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.stockmarket.event.StockPriceChangedEvent;

@Service
public class StockmarketWebsocketService {
	@Autowired
	private SimpMessagingTemplate msgTemplate;
	
	@EventListener
	public void listenStockPriceChanges(StockPriceChangedEvent event) {
		System.err.println("Event received: " + event);
		msgTemplate.convertAndSend("/topic/changes", event);
	}
	
}
