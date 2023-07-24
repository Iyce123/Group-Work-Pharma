package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class RWSupplier {
	private final File fu;
	private ArrayList<Supplier> med;
	
	public RWSupplier(){
		fu = new File("Supplier.data");
		med = new ArrayList<Supplier>();
		if(!fu.exists()){
			writeSupp();
		} else {
			med = readSupp();
		}
	}

	public ArrayList<Supplier> readSupp() {
		try {
			FileInputStream fis = new FileInputStream(fu);
			ObjectInputStream ois = new ObjectInputStream(fis);
			med = (ArrayList<Supplier>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
		} catch (IOException e) {
			System.err.println("File not accessable!!!");
		}
		return med;
	}

	private void writeSupp() {
		try {
			FileOutputStream fos = new FileOutputStream(fu);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(med);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (IOException e) {
			System.err.println("File not Writable!!!");
		}
		readSupp();
	}
	public Supplier checkEmp(String username,String password){
		for(Supplier x:med){
//			if(username.equals(x.getUsername()) && x.getPassword().equals(password)){
//				return x;
//			} 
		}
		return null;
	}
	public ArrayList<Supplier> getSupplier(){
		return med;
	}
	public void add(Supplier e){
		med.add(e);
		writeSupp();
	}
	public void remove(Supplier e){
		med.remove(e);
		writeSupp();
	}
}
