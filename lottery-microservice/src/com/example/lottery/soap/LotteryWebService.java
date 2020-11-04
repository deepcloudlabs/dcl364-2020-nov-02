package com.example.lottery.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.example.lottery.service.LotteryService;
// Adapter
//@WebService
public class LotteryWebService {
	//@Inject // 1) Field Injection
	private LotteryService lotteryService;
	
	@WebMethod
	public List<List<Integer>> draw(
			@WebParam(name = "max") int max, 
			@WebParam(name = "size") int size, 
			@WebParam(name = "column") int column){
		return lotteryService.draw(max, size, column);
	}
}
