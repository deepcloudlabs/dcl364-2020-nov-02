package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.LotteryService;
// @Path("/numbers")
@RestController
@RequestMapping("/numbers")
public class LotteryController {
	@Autowired private LotteryService lotteryService;
	
	// http://localhost:5100/multiple/api/v1/numbers?max=50&size=6&column=10
	@GetMapping
	public List<List<Integer>> getLotteryNumbers(
			 @RequestParam int max, 
			 @RequestParam int size,
			 @RequestParam int column){
		return lotteryService.draw(max, size, column);
	}
}
