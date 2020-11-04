package com.example.lottery.service;

import java.util.List;

import com.example.lottery.aop.Audit;
import com.example.lottery.aop.Profile;

public interface LotteryService {

	List<Integer> draw(int max, int size);

	List<List<Integer>> draw(int max, int size, int column);

}