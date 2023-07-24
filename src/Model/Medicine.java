
package Model;

import java.io.Serializable;

public class Medicine implements Serializable{
	
	private String name;
	private int quantity;
	private double bought_price;
	private double sold_price;
	private Birthday purchased_date;
	private Birthday expiry_date;
	
	public Medicine(String name, int quantity, double bought_price, double sold_price,Birthday pr, Birthday ex) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.bought_price = bought_price;
		this.sold_price = sold_price;
		purchased_date = pr;
		expiry_date = ex;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSold_price() {
		return sold_price;
	}

	public void setSold_price(double sold_price) {
		this.sold_price = sold_price;
	}

	public String getName() {
		return name;
	}

	public Birthday getPurchased_date() {
		return purchased_date;
	}

	public Birthday getExpiry_date() {
		return expiry_date;
	}

	public double getBought_price() {
		return bought_price;
	}
	
	public void changeQuantity(int n) {
		//changing the quantity after buying the medicine
		quantity -= n;
	}
	
	public double calculate() {
		//fitimi
		return sold_price - bought_price;
	}
	
	public boolean checkQuantity(int n) {
		if(quantity < n) {
			return true;
		}return false;
	}
}

