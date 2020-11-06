package com.example.stockmarket.service.business;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.Json;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.example.stockmarket.event.StockPriceChangedEvent;

@Singleton
@ServerEndpoint("/changes")
public class StockmarketWebsocketService {
	private Map<String, Session> sessions = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpenSession(Session session) {
		sessions.put(session.getId(), session);
	}

	@OnClose
	public void onCloseSession(Session session) {
		sessions.remove(session.getId());
	}

	public void listenStockPriceChanges(@Observes StockPriceChangedEvent event) {
		System.err.println("Event received: " + event);
		sessions.forEach((sessionId, session) -> this.sendEvent(sessionId, session, event));
	}
	
	private void sendEvent(String sessionId, Session session,StockPriceChangedEvent event) {
		var jsonEvent = Json.createObjectBuilder()
			    .add("symbol", event.getSymbol())
			    .add("oldPrice", event.getOldPrice())
			    .add("newPrice", event.getNewPrice())
			    .build()
			    .toString();
		try {
			session.getBasicRemote().sendText(jsonEvent);
		} catch (IOException e) {
			System.err.println("Error when sending message over websocket: "+e.getMessage());
		}
	}
}
