package Model;
import java.io.Serializable;

public abstract class Account implements Serializable {

	private int ID;
	public static int n = 0;
	private String  username,password;
	protected Level level;
	

	Account(String username,String password){
		ID = n++;
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public Level getLevel() {
		return level;
	}
	public abstract void setLevel(Level level);
}
