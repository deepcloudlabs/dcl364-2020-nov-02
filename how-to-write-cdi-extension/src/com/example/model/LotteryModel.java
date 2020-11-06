package com.example.model;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.example.service.RandomNumber;
// JBoss SEAM -> Delta Spike (ASF) -> CDI Extension Library
// https://deltaspike.apache.org/
@Named
@RequestScoped
public class LotteryModel {
	@RandomNumber(min=1,max=500,size=12,sorted=true, distinct=true)
	private List<Integer> numbers;

	public LotteryModel() {
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
	
}
