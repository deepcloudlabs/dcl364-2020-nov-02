package com.example.stockmarket.writer;

import java.util.List;

import javax.annotation.Resource;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named("stockItemWriter")
public class StockItemWriter extends AbstractItemWriter {
	@Inject
	private JMSContext context;
	@Resource(mappedName = "java:jboss/jms/stockQueue")
	private Queue stockQueue;

	@Override
	public void writeItems(List<Object> list) throws Exception {
		list.stream().filter(o -> o instanceof Stock).map(Stock.class::cast)
				.forEach(stock -> context.createProducer().send(stockQueue, createJson(stock)));
	}

	private String createJson(Stock stock) {
		return Json.createObjectBuilder().add("symbol", stock.getSymbol()).add("price", stock.getPrice()).build()
				.toString();
	}

}
