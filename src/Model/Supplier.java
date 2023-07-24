package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.util.Pair;

public class Supplier implements Serializable{

	private String sup_name;
	private String medName;
	private double price;
	private ArrayList<String> med = new ArrayList<>();
	private ArrayList<Double> pr = new ArrayList<>();
	
	public Supplier(String name) {
		this.sup_name = name;
	}
	
	public void addMedicine(String m, double p) {
		this.medName = m;
		this.price = p;
		med.add(m);
		pr.add(p);
		new RWSupplier().add(new Supplier(sup_name));
	}

	public String getSup_name() {
		return sup_name;
	}

	public String getMedName() {
		return medName;
	}

	public double getPrice() {
		return price;
	}

	public ArrayList<String> getMedicine() {
		return med;
	}

	public ArrayList<Double> getPr() {
		return pr;
	}

	@Override
	public String toString() {
		return "Supplier [sup_name=" + sup_name + ", med=" + med + ", pr=" + pr + "]";
	}
}
