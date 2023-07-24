package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class RWMedicine {
	private final File fu;
	private ArrayList<Medicine> med;
	
	public RWMedicine(){
		fu = new File("Medicine.data");
		med = new ArrayList<Medicine>();
		if(!fu.exists()){
			writeEmp();
		} else {
			med = readEmp();
		}
	}

	public ArrayList<Medicine> readEmp() {
		try {
			FileInputStream fis = new FileInputStream(fu);
			ObjectInputStream ois = new ObjectInputStream(fis);
			med = (ArrayList<Medicine>) ois.readObject();
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

	private void writeEmp() {
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
		readEmp();
	}
	
	public ArrayList<Medicine> getMedicine(){
		return med;
	}
	public void add(Medicine e){
		med.add(e);
		writeEmp();
	}
	public void remove(Medicine e){
		med.remove(e);
		writeEmp();
	}
	public void newQuantity(Medicine m, int n) {
		for(int i = 0 ; i< med.size(); i++) {
			if(med.get(i).getName().equals(m.getName()))
				med.get(i).setQuantity(n);
			writeEmp();
		}
		
	}
	public void changeQuantity(Medicine e, int quantity) {
		for(int i = 0 ; i < med.size(); i++) {
			if(med.get(i).getName().equals(e.getName())) {
				med.get(i).setQuantity(quantity);
				writeEmp();
			}
		}
	}
	
}
