package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class RWAccounts {
	private final File fu;
	private ArrayList<Employee> emp;
	
	public RWAccounts(){
		fu=new File("Employee.data");
		emp=new ArrayList<Employee>();
		if(!fu.exists()){
			writeEmp();
		} else {
			emp=readEmp();
		}
	}

	public ArrayList<Employee> readEmp() {
		try {
			FileInputStream fis=new FileInputStream(fu);
			ObjectInputStream ois=new ObjectInputStream(fis);
			emp=(ArrayList<Employee>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
		} catch (IOException e) {
			System.err.println("File not accessable!!!");
		}
		return emp;
	}

	private void writeEmp() {
		try {
			FileOutputStream fos = new FileOutputStream(fu);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(emp);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (IOException e) {
			System.err.println("File not Writable!!!");
		}
		readEmp();
	}
	public Employee checkEmp(String username,String password){
		for(Employee x:emp){
			if(username.equals(x.getUsername()) && x.getPassword().equals(password)){
				return x;
			} 
		}
		return null;
	}
	public void changePassword(Employee e,String pass) {
		for(int i = 0 ; i < emp.size(); i++) {
			if(emp.get(i).getUsername().equals(e.getUsername())) {
				emp.get(i).setPassword(pass);
				writeEmp();
			}
		}
	}
	
	public void reWrite() {
		writeEmp();
	}
	
	public void setSalary( Employee e, double quantity) {
		for(int i = 0  ; i < emp.size(); i++) {
			if(emp.get(i).getUsername().equals(e.getUsername())) {
				emp.get(i).setSalary(quantity);
				
			}
		}
		writeEmp();
	}
	
	public ArrayList<Employee> getEmployee(){
		return emp;
	}
	public void add(Employee e){
		emp.add(e);
		writeEmp();
	}
	public void remove(Employee e){
		emp.remove(e);
		writeEmp();
	}
}
