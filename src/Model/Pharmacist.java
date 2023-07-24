package Model;

public class Pharmacist extends Employee{

	private int bills = 0;
	private double money = 0;
	
	public Pharmacist(String name, String surname, String username, String password,double salary, Birthday bdt) {
		super(name, surname, username, password, salary, bdt);
		this.setLevel(Level.Pharm);
	}
	public int getBills() {
		return bills;
	}
	public void setBills(int bills) {
		this.bills = bills;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "Pharmacist:  " + super.getName() + "  " + super.getSurname();
	}

}
