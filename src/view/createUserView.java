package view;
import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class createUserView{

	RWAccounts rwa = new RWAccounts();
	
	public void create(Stage accountStage) {
		
		
		accountStage.initModality(Modality.APPLICATION_MODAL);
		
		Label title = new Label("Create your new account");
		title.setTextFill(Color.GREEN); //changes color of the text
		title.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));
		
		Label name_label = new Label("Name");
		Label surname_label = new Label("Surname");
		Label password_label = new Label("Password");
		Label level_label = new Label("Level");
		Label username_label = new Label("Username");
		Label birthday_label = new Label("Birthday");
		Label salary_label = new Label("Salary");
		
		//handling exceptions
		Text[] err = {
				new Text("Name must not contain numbers"), //name	//0
				new Text("Surname must not contain numbers"),//surname  //1
				new Text("Username already in use"),//username //2
				new Text("Choose your position"),//level //3
				new Text("Enter birthday"),//birthday //4
				new Text("Numbers only"), //salary  //5
				new Text("4 characters or more")//password //6
			};
		
		for(int  i = 0 ; i < err.length ; i++) {
			err[i].setFill(Color.RED);
			err[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
			err[i].setVisible(false);
			err[i].resize(15, 15);
		}
		
		
		TextField name = new TextField();
		name.setPromptText("Write your name");
		name.setOnKeyReleased(new EventHandler<KeyEvent>() {
			//showing error if the person inputs numbers in the name
			@Override
			public void handle(KeyEvent event) {
				if(!name.getText().matches(("^[a-zA-Z]+$"))) {
					err[0].setVisible(true);
				}else {
					err[0].setVisible(false);
				}	
			}
		});
		
		TextField surname = new TextField();
		surname.setPromptText("Write your surname");
		surname.setOnKeyReleased(new EventHandler<KeyEvent>() {
			//showing error if the person inputs numbers in the name
			@Override
			public void handle(KeyEvent event) {
				if(!surname.getText().matches(("^[a-zA-Z]+$"))) {
					err[1].setVisible(true);
				}else {
					err[1].setVisible(false);
				}	
			}
		});
				
		TextField username = new TextField();
		username.setPromptText("Write your username");
		username.setOnKeyReleased(new EventHandler<KeyEvent>() {
			//showing error if the person inputs numbers in the name
			@Override
			public void handle(KeyEvent event) {
				int index = -1;
				
				for(int i = 0 ; i < rwa.getEmployee().size(); i++) {
					if(rwa.getEmployee().get(i).getUsername().equals(username.getText()))
						index = i;
				}
				if(index != -1) {
					err[2].setVisible(true);
				}else err[2].setVisible(false);
			}
		});
		
		TextField salary = new TextField();
		salary.setPromptText("Enter salary");
		salary.setOnKeyReleased(new EventHandler<KeyEvent>() {
			//showing error if the person inputs numbers in the name
			@Override
			public void handle(KeyEvent event) {
				if(!salary.getText().matches(("\\d+"))) {
					err[5].setVisible(true);
				}else {
					err[5].setVisible(false);
				}	
			}
		});
		
		DatePicker date = new DatePicker();
		date.getEditor().setDisable(true);
		
		
		//radio button for level of user
		RadioButton pharm = new RadioButton("Pharmacist");
		RadioButton manager = new RadioButton("Manager");
		ToggleGroup level = new ToggleGroup();
		pharm.setToggleGroup(level); manager.setToggleGroup(level); 
		
		
		
		HBox hbox2 = new HBox(pharm,manager);
		hbox2.setSpacing(15);
		
		PasswordField pass = new PasswordField();
		pass.setPromptText("Write your password");
		pass.setOnKeyReleased(new EventHandler<KeyEvent>() {
			//showing error if the person inputs numbers in the name
			@Override
			public void handle(KeyEvent event) {
				if(pass.getText().length() < 4)
					err[6].setVisible(true);
				else err[6].setVisible(false);
			}
		});
		
		PasswordField verify = new PasswordField();
		verify.setPromptText("Confirm password");
		
		
		HBox password = new HBox(pass,verify);
		password.setSpacing(20);
		
		Button close = new Button("Close");
		close.setPrefSize(100, 35);
		close.setOnAction(e -> {
			accountStage.close();
		});
		
		Button button = new Button("Create Account");
		button.setPrefSize(100,35);
		button.setOnAction( e -> {
		
			
			//this is done for differentiating the level enum
			
			
			if(name.getText()!=null && pass.getText()!=null
					&& name.getText()!=null&&surname.getText()!=null && 
					(pharm.isSelected()||manager.isSelected())) {
				if(!err[0].isVisible()) {
					if(!err[1].isVisible()) {
						if(!err[2].isVisible()) {
							if((!pharm.isSelected() || !manager.isSelected())) {
								if (!(date.getValue() == null)) {
									if(!err[5].isVisible()) {
										if(!err[6].isVisible()) {
											if(pass.getText().equals(verify.getText())) {
												if(pharm.isSelected()) {
													Pharmacist temp = new Pharmacist(name.getText(), surname.getText(), username.getText(), pass.getText(),
															Double.parseDouble(salary.getText()),
															new Birthday(date.getValue().toString().replace("-","/")));
													rwa.add(temp);	
												}else if(manager.isSelected()) {
													Manager temp = new Manager(name.getText(), surname.getText(), username.getText(), pass.getText(),
															Double.parseDouble(salary.getText()),
															new Birthday(date.getValue().toString().replace("-","/")));
													rwa.add(temp);
													
												}
												 Alert al = new Alert(AlertType.INFORMATION, "Account created successfuly");
												al.setTitle("Sign Up");
												al.setHeaderText(null);
												al.setGraphic(null);
												al.show();
												accountStage.close();
											}else {
												Alert matchpass = new Alert(AlertType.ERROR, "Passwords do not match");
												matchpass.show();
											}										 
										}else {
											Alert err6 = new Alert(AlertType.ERROR, "Password is less than 4 letters");
											err6.show();
										}										
									}else {
										Alert err5 = new Alert(AlertType.ERROR, "Salary must conatin only numbers");
										err5.show();
									}
								}else {
									Alert err4 = new Alert(AlertType.ERROR, "Choose your birthday");
									err4.show();
								}
							}else {
								Alert err3 = new Alert(AlertType.ERROR, "Choose your position");
								err3.show();
							}
						}else {
							Alert err2 = new Alert(AlertType.ERROR, "Username already in use");
							err2.show();
						}
					}else {
						Alert err1 = new Alert(AlertType.ERROR, "Surname must not contain numbers");
						err1.show();
					}
				}else {
					Alert err0 = new Alert(AlertType.ERROR, "Name must no contain numbers");
					err0.show();
				}
				
			
			 
			}else {
				Alert al = new Alert(AlertType.ERROR, "Dont leave anything blank");
				al.setTitle("Error");
				al.setHeaderText(null);
				al.setGraphic(null);
				al.show();
			}
			  
			
			
		});
		
		// TODO verify password 
		
		
		
		GridPane layout = new GridPane();
		layout.addColumn(1, name_label,surname_label, username_label,level_label, birthday_label,salary_label,password_label);
		layout.addColumn(2, name,surname,username,hbox2,date,salary,password);
		for(int  i = 0 ; i < err.length ; i++) {
			layout.addColumn(3, err[i]);
		}
		layout.setVgap(20);
		layout.setHgap(20);
		layout.setAlignment(Pos.CENTER);
		
		//creating a hbox for the buttons
		HBox buttons = new HBox(button,close);
		buttons.setSpacing(20);
		buttons.setAlignment(Pos.CENTER);
		
		BorderPane pane = new BorderPane();
		pane.setTop(title);
		pane.setCenter(layout);
		pane.setBottom(buttons);
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(buttons, Pos.CENTER);
		BorderPane.setMargin(buttons, new Insets(20));
		BorderPane.setMargin(title, new Insets(20));
		
		
		accountStage.setScene(new Scene(pane,750,550));
		accountStage.showAndWait();	
	}
	
	public static boolean verifyPass(String p, String v) {
		if( p .equals(v)) return true;
		return false;
		
	}
	
}
