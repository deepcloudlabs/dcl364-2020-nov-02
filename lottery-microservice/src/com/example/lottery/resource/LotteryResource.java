package com.example.lottery.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.example.lottery.service.LotteryService;

// Hexagonal Architecture
// Onion Architecture
// Adapters&Ports Architecture
// Domain Model --> Technology
// DDD: ACL (Anti-Corruption Layer
// Adapter
// localhost:8080/lottery-microservice/api/v1/numbers
@Path("/numbers")
// Rest over Http, Rest over Websocket, SOAP WS, SSE, ...
public class LotteryResource {
	@Inject // 1) Field Injection
	private LotteryService lotteryService;
	
	/*
	@Inject // 2) Setter Injection
    public void setLotteryService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Inject // 3) Constructor Injection
	public LotteryResource(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}
    */

	// GET http://localhost:8080/lottery-microservice/api/v1/numbers?max=50&size=6&column=10
	//  ?max=50&size=6&column=10 --> query parameters: max, size, column
	@GET
//	@POST
//	@PUT
//	@PATCH
//	@DELETE
//	@OPTIONS
	@Produces("application/json")
	public List<List<Integer>> getLotteryNumbers(
			@QueryParam("max") int max, 
			@QueryParam("size") int size,
			@QueryParam("column") int column){
		return lotteryService.draw(max, size, column);
	}
}
