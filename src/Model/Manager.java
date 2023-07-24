package Model;

public class Manager extends Employee{

	public Manager(String name, String surname, String username, String password,double salary, Birthday bdt) {
		super(name, surname, username, password, salary, bdt);
		setLevel(Level.Manager);
	}
	@Override
	public String toString() {
		return "Manager:  " + super.getName() + "  " + super.getSurname();
	}

}
