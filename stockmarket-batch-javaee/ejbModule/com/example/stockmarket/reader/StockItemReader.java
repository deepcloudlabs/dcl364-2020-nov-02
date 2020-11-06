package com.example.stockmarket.reader;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.stockmarket.dao.StockDao;
import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named("stockItemReader")
public class StockItemReader extends AbstractItemReader {
	@Inject private StockDao stockDao;
	private List<Stock> stocks;
	private Iterator<Stock> stockIterator;
	
	@Override
	public void open(Serializable checkpoint) throws Exception {
		stocks = stockDao.findAll();
		stockIterator = stocks.iterator();
	}

	@Override
	public Object readItem() throws Exception {
		if (stockIterator.hasNext())
			return stockIterator.next();
		return null;
	}

}
