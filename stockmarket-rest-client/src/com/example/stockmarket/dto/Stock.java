package com.example.stockmarket.dto;

public class Stock {
	private String symbol;
	private String description;
	private String company;
	private double price;

	public Stock() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String comapny) {
		this.company = comapny;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", description=" + description + ", company=" + company + ", price=" + price
				+ "]";
	}

}
