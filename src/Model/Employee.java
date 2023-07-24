package Model;

import java.io.Serializable;

public class Employee extends Account implements Serializable{

	private String name;
	private String surname;
	private Birthday bdt;
	private double salary;
	
	public Employee(String name, String surname, String username, String password, double salary,Birthday bdt) {
		super(username, password);
		this.name = name;
		this.surname = surname;
		this.salary = salary;
		this.bdt = bdt;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Birthday getBdt() {
		return bdt;
	}


	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public void setLevel(Level level) {
		super.level = level;
	}
	
	

}
