package com.example.stockmarket.service;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.example.stockmarket.dto.Stock;

@Stateless
public class StockmarketServiceClient {

	@Schedule(second = "*/5", hour = "*", minute = "*")
	public void callRestApi() {
		var stock = ClientBuilder.newClient()
		             .target("http://localhost:8080/stockmarket/api/v1/stocks")
		             .path("orcl")
		             .request(MediaType.APPLICATION_JSON)
		             .get(Stock.class);
		System.err.println(stock);
		var stocks = ClientBuilder.newClient()
				.target("http://localhost:8080/stockmarket/api/v1/stocks")
				.queryParam("page", 0)
				.queryParam("size", 25)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Stock>>() { });
		System.err.println(stocks);
	}
}
