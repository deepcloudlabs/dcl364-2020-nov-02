package com.example.stockmarket.batch;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Stateless
public class StockMarketBatch {
	@Schedule(second = "*/5", minute = "*", hour = "*")
	public void runBatch() {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		jobOperator.start("job1", new Properties());
	}
}
