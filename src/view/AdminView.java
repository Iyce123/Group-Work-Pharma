package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.Employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AdminView {
	private Employee admin;
	
	public AdminView(Employee admin) {
		this.admin = admin;
	}
	
	public void show(Stage stage) throws FileNotFoundException {
		
		stage.setTitle("Administrator");
		
		Label welcome = new Label("Welcome " + admin.getName() + " " +admin.getSurname());
		welcome.setFont(Font.font("Verdana",FontWeight.BOLD, 30)); //change the font theme and size
		welcome.setMinHeight(80);
		welcome.setAlignment(Pos.CENTER);
		welcome.setTextFill(Color.GREEN);
		
		//setting up the background
				Image image = new Image("images/userbg.jpg");
				BackgroundImage bg = new BackgroundImage(image,  
		                BackgroundRepeat.NO_REPEAT,  
		                BackgroundRepeat.NO_REPEAT,  
		                BackgroundPosition.DEFAULT,  
		                   BackgroundSize.DEFAULT);
				Background background = new Background(bg);
				
		
		Button manage = new Button("Manage Employees");
		manage.setPrefSize(160, 40);
		manage.setStyle(""
				
				+ "-fx-font-size: 14px;");
		manage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				new viewEmployee().show(new Stage());
			}
			
		});
		
		Button bis = new Button("Manage Pharmacy");
		bis.setStyle(""
				
				+ "-fx-font-size: 14px;");
		bis.setPrefSize(160, 40);
		bis.setOnAction(e -> {
			
			new statView().show(new Stage());
		});
		
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(welcome,manage,bis);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		
		MenuBar menu = new MenuBar();
		
		Menu emp = new Menu("Employee");
		MenuItem register = new MenuItem("Register Employee");
		register.setOnAction(e -> {
			new createUserView().create(new Stage());
		});
		
		MenuItem view = new MenuItem("View Employees");
		view.setOnAction(e ->{
			new viewEmployee().show(new Stage());
		});
		
		MenuItem salary = new MenuItem("Manage salary");
		salary.setOnAction(e ->{
			new editSalary().show(new Stage());
		});
		
		Menu stats = new Menu("Statistics");
		MenuItem salary_stat = new MenuItem("Salary");
		salary_stat.setOnAction(e -> {
			new salaryStat().show(new Stage());
		});
		
		MenuItem income_stat = new MenuItem("Income");
		income_stat.setOnAction(e ->{
			new pieChart().show(new Stage());
		});
		stats.getItems().addAll(salary_stat, income_stat);
		
		emp.getItems().addAll(register,view,salary,stats);
		
		Menu user = new Menu("User");
		MenuItem create_user = new MenuItem("Add User");
		create_user.setOnAction(e -> {
			new createUserView().create(new Stage());
		});
		MenuItem manage_user = new MenuItem("Manage User");
		manage_user.setOnAction(e -> {
			new viewEmployee().show(new Stage());
		});
		user.getItems().addAll(create_user, manage_user);
		
		Menu setting = new Menu("Settings");
		MenuItem logout = new MenuItem("Log out");
		logout.setOnAction(e->{
			try {
				(new LogIn()).start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			stage.show();
		});
		MenuItem changepw = new MenuItem("Change password");
		changepw.setOnAction(e -> {
			new changePWView().show(new Stage(), admin);
		});
		
		setting.getItems().addAll(changepw,logout);
		
		
		menu.getMenus().addAll(emp,user,setting);
		
		
		
		BorderPane layout = new BorderPane();
		
		
		layout.setTop(menu);
		layout.setCenter(vbox);
		layout.setBackground(background);
		BorderPane.setMargin(welcome, new Insets(10));
		
		Scene sc = new Scene(layout,500,500);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
		stage.setScene(sc);
		stage.show();
	}
}
