package com.example.observer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class MarketApiWebsocketClient {
	private static final String URL = 
			"wss://stream.binance.com:9443/ws/btcusdt@trade";

	public static void main(String[] args) throws InterruptedException {
		Listener listener = new MarketWebSocketListener();
		HttpClient.newHttpClient()
		          .newWebSocketBuilder()
		          .buildAsync(URI.create(URL ), listener);
		TimeUnit.DAYS.sleep(365);
	}
}

class MarketWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the server.");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(Thread.currentThread().getName()+": "+data);
		webSocket.request(1);
		return null;
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the server.");
		return null;
	}
	
}