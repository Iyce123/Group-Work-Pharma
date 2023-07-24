package Model;

public class Admin extends Employee{

	public Admin(String name, String surname, String username, String password,double salary, Birthday bdt) {
		super(name, surname, username, password, salary, bdt);
		setLevel(Level.Admin);
	}

	@Override
	public String toString() {
		return "Administrator:  " + super.getName() + "  " + super.getSurname();
	}
}
