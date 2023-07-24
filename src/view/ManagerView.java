package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.Employee;
import Model.RWMedicine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

public class ManagerView {
	private Employee manager;
	
	public ManagerView(Employee manager) {
		this.manager = manager;
	}
	
	public void show(Stage stage) throws FileNotFoundException {
		
		stage.setTitle("Manager");
		
		RWMedicine rwm = new RWMedicine();
		for(int i = 0 ; i < rwm.getMedicine().size(); i++) {
			if(rwm.getMedicine().get(i).getQuantity() < 5) {
				Alert a = new Alert(AlertType.INFORMATION, "Medicine : " + rwm.getMedicine().get(i).getName() +
						" is getting out of stock. Only " + rwm.getMedicine().get(i).getQuantity() + " left");
				a.show();
			}
				
		}
		
		Label welcome = new Label("Welcome " + manager.getName() + " " +manager.getSurname());
		welcome.setFont(Font.font("Verdana",FontWeight.BOLD, 30)); //change the font theme and size
		welcome.setMinHeight(80);
		welcome.setTextFill(Color.GREEN);
		welcome.setStyle("-fx-border-color: white;");
		
		
		Image image = new Image("images/userbg.jpg");
		BackgroundImage bg = new BackgroundImage(image,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                   BackgroundSize.DEFAULT);
		Background background = new Background(bg);
		
		Button supply = new Button("Supply");
		supply.setPrefSize(140, 30);
		supply.setOnAction(e -> {
			new addSupplier().show(manager,stage);
		});
		
		Button viewPharm = new Button("Check Pharmacists");
		viewPharm.setPrefSize(140, 30);
		viewPharm.setOnAction(e -> {
			new viewPharmacist().show(new Stage());
		});
		
		Button stat = new Button("Statistics");
		stat.setPrefSize(140, 30);
		stat.setOnAction(e -> {
			new pieChart().show(new Stage());
		});
		
		Button logout = new Button("Log Out");
		stat.setPrefSize(120, 30);
		logout.setPadding(new Insets(10));
		logout.setOnAction(e->{
			try {
				(new LogIn()).start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			stage.show();
		});
		
		Button medicine = new Button("View Magazine");
		medicine.setPrefSize(140, 30);
		medicine.setOnAction(e -> {
			new stockView().show(new Stage());
		});
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(supply,viewPharm,stat,medicine);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(welcome);
		layout.setCenter(vbox);
		layout.setBottom(logout);
		layout.setBackground(background);
		BorderPane.setAlignment(welcome, Pos.CENTER);
		BorderPane.setAlignment(logout, Pos.CENTER);
		BorderPane.setMargin(welcome, new Insets(10));
		BorderPane.setMargin(logout, new Insets(10));
		Scene sc = new Scene(layout,500,500);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
		stage.setScene(sc);
		
	}
}
