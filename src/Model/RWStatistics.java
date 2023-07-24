package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class RWStatistics {
	private final File fu;
	private ArrayList<Statistics> stat;
	
	public RWStatistics(){
		fu = new File("Statistics.data");
		stat = new ArrayList<Statistics>();
		if(!fu.exists()){
			writeStat();
		} else {
			stat = readStat();
		}
	}

	public ArrayList<Statistics> readStat() {
		try {
			FileInputStream fis = new FileInputStream(fu);
			ObjectInputStream ois = new ObjectInputStream(fis);
			stat = (ArrayList<Statistics>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
		} catch (IOException e) {
			System.err.println("Statistics File not accessable!!!");
		}
		return stat;
	}

	private void writeStat() {
		try {
			FileOutputStream fos = new FileOutputStream(fu);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(stat);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (IOException e) {
			System.err.println("Statistics File not Writable!!!");
		}
		readStat();
	}
	public Statistics checkEmp(String username,String password){
		for(Statistics x:stat){
//			if(username.equals(x.getUsername()) && x.getPassword().equals(password)){
//				return x;
//			} 
		}
		return null;
	}
	public void changeQuantity(Statistics e, int quantity, double income) {
		for(int i = 0 ; i < stat.size(); i++) {
			if(stat.get(i).getMed().equals(e.getMed())) {
				stat.get(i).setQuantity(quantity);
				stat.get(i).setIncome(income * quantity);
				writeStat();
				
			}
		}
	}
	
	
	public ArrayList<Statistics> getSupplier(){
		return stat;
	}
	public void add(Statistics e){
		stat.add(e);
		writeStat();
	}
	public void remove(Statistics e){
		stat.remove(e);
		writeStat();
	}
}
