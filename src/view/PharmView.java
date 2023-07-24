package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import Model.Employee;
import Model.Medicine;
import Model.RWMedicine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PharmView {
	private Employee pharm;
	
	public PharmView(Employee pharm) {
		this.pharm = pharm;
	}
	
	public void show(Stage stage) throws FileNotFoundException {
		
		stage.setTitle("Pharmacist");
		
		Label welcome = new Label("Welcome " + pharm.getName() + " " +pharm.getSurname());
		welcome.setFont(Font.font("Verdana",FontWeight.BOLD, 30)); //change the font theme and size
		welcome.setMinHeight(80);
		welcome.setTextFill(Color.GREEN);
		
		
		Image image = new Image("images/userbg.jpg");
		BackgroundImage bg = new BackgroundImage(image,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                   BackgroundSize.DEFAULT);
		Background background = new Background(bg);
		
		Button createBill = new Button("Create bill");
		createBill.setPrefSize(100,40);
		createBill.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new BillView(pharm).show(new Stage());
				
			}
		});
		
		
		Button logout = new Button("Log out");
		logout.setPrefSize(100, 40);
		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					(new LogIn()).start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.show();
				
			}
			
		});
		
		
		//putting the buttons horizontally
		HBox hbox = new HBox(createBill, logout);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		BorderPane pane = new BorderPane();
		pane.setTop(welcome);
		pane.setCenter(hbox);
		pane.setPadding(new Insets(20,20,20,20));
		pane.setBackground(background);
		BorderPane.setAlignment(welcome, Pos.CENTER);
		BorderPane.setMargin(welcome, new Insets(10));
		Scene sc = new Scene(pane,500,500);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
		stage.setScene(sc);
		stage.show();
	}
}
