package com.example.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.example.model.LotteryModel;

@Path("/numbers")
public class LotteryController {
	@Inject private LotteryModel lotteryModel;
	
	// http://localhost:5100/multiple/api/v1/numbers
	@GET
	@Produces("application/json")
	public LotteryModel getLotteryNumbers(){
		return lotteryModel; 
	}
}
