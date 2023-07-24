package view;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LogIn{
	
	public void start(Stage stage) throws Exception {
		
		Label title = new Label("Pharmacy Project");
		title.setFont(Font.font("Times New Roman",FontWeight.BOLD, 35)); //change the font theme and size
		title.setMinHeight(80);
		
		Button login = new Button("Log In");
		login.setPrefSize(70, 30);
		
		Button cancel = new Button("Exit");
		cancel.setPrefSize(70, 30);
		
		Button create = new Button("Create new account");
		create.setPrefSize(160, 30);
		
		
		TextField username = new TextField();
		username.setId("text");
		username.setPromptText("Your Username");
		username.setMinSize(90,40);
		
		PasswordField password = new PasswordField();
		password.setId("text");
		password.setPromptText("Your Password");
		password.setMinSize(90, 40);
		
		
		//setting up the logo
		Image logo = new Image(("images/logo1.png"));
		ImageView viewlogo = new ImageView(logo);
		viewlogo.setFitHeight(120);
		viewlogo.setFitWidth(120);
		
		//setting up the background
		Image image = new Image("images/wave.jpg");
		BackgroundImage bg = new BackgroundImage(image,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                   BackgroundSize.DEFAULT);
		Background background = new Background(bg);
		
		HBox top = new HBox();
		top.getChildren().addAll(title,viewlogo);
		top.setAlignment(Pos.CENTER);
		top.setSpacing(20);
		
		VBox center = new VBox();
		center.getChildren().addAll(username,password);
		center.setSpacing(20);
		center.setPadding(new Insets(80));
		
		//contains the login and exit button
		HBox choices = new HBox();
		choices.getChildren().addAll(login,cancel);
		choices.setSpacing(20);
		choices.setAlignment(Pos.CENTER);
		choices.setPadding(new Insets(20));
		
		VBox bottom = new VBox();
		bottom.getChildren().addAll(choices,create);
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(20));
		
		
		BorderPane layout = new BorderPane();
		layout.setBackground(background);
		layout.setTop(top);
		layout.setCenter(center);
		layout.setBottom(bottom);
		layout.setLeft(new Label("                                            " ));
		layout.setRight(new Label( "                                           "));
		
		stage.setTitle("Log In");
		stage.setResizable(false);
		Scene sc = new Scene(layout,630,530);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
		stage.setScene(sc);
		stage.show();
		
		
		
		//TODO Actions
		
		stage.setOnCloseRequest(e ->{
			e.consume();
			if(programClose.request()) stage.close();
		});
		
		cancel.setOnAction(e -> {
			if(programClose.request()) stage.close(); 
		});
		
		create.setOnAction(e -> {
			new createAccount().create(new Stage());
		});
		
		login.setOnAction(e ->{
			RWAccounts rwa = new RWAccounts();
			Employee temp = rwa.checkEmp(username.getText(), password.getText());//loggin in
			
			
			if(temp == null) {
				Alert al = new Alert(AlertType.ERROR, "User not found");
				al.show();
			}else {
				if(temp.getLevel() == Level.Admin)
					//enterin admin view
					try {
						new AdminView(temp).show(stage);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//entering manager view
				
				else if (temp.getLevel() == Level.Manager)
					try {
						new ManagerView(temp).show(stage);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				//enter pharmacist view
				else if (temp.getLevel() == Level.Pharm)
					try {
						new PharmView(temp).show(stage);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			//cheking username and password
			
		});
		
		setGlobalEventHandler(layout,login);
		
	}
	
	private void setGlobalEventHandler(Node root, Button b) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           b.fire();
	           ev.consume(); 
	        }
	    });
	}

}
