package Model;

import java.io.Serializable;

public class Statistics implements Serializable{

	private String med;
	private double price;
	private int quantity;
	private double tot_price;
	private double income;
	
	
	public Statistics(String med, double price, int quantity,double tot_price, double income) {
		super();
		this.med = med;
		this.price = price;
		this.quantity = quantity;
		this.tot_price = tot_price;
		this.income = income;
	}
	
	public String getMed() {
		return med;
	}
	
	public void setMed(String med) {
		this.med = med;
	}
	
	public double getPrice() {
		
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getIncome() {
		return income;
	}
	
	public void setIncome(double income) {
		this.income = income;
	}
	
	public double getTot_price() {
		return tot_price;
	}
	
	public void setTot_price(double tot_price) {
		this.tot_price = tot_price;
	}
	
	
	
}
