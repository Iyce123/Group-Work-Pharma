package Model;

public class Bill {
	
	private String medicine;
	private double price;
	private int quantity;
	
	public Bill(String medicine,double price, int quantity) {
		this.medicine = medicine;
		this.price = price;
		this.quantity = quantity;
	}

	public String getMedicine() {
		return medicine;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
