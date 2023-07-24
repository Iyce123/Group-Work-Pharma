package view;

import java.awt.Insets;

import Model.Employee;
import Model.RWAccounts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class editSalary {
	
	

	int check = -1;
	
	public void show(Stage stage) {
		
		stage.setTitle("Change salary");
		
		RWAccounts rwa = new RWAccounts();
		
		Text error = new Text("User does not exist");
		error.setVisible(false);
		error.setFill(Color.DARKBLUE);
		error.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		
		
		Text old_salary = new Text();
		old_salary.setFill(Color.DARKBLUE);	
		old_salary.setVisible(false);
		old_salary.setFont(Font.font("Arial", FontWeight.BOLD, 14));	
		
		Label title = new Label("Change Salary");
		title.setFont(Font.font("Arial", FontWeight.BOLD,17));
		
		Label name_label = new Label("Enter employee");
		name_label.setFont(Font.font(15));
		
		TextField name = new TextField();
		name.setPromptText("Enter username");
		name.setMinSize(120, 30);
		name.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				check = -1;
				for(int i = 0 ; i < rwa.getEmployee().size(); i++) {
					if(!(rwa.getEmployee().get(i).getUsername().equals(name.getText()))) {
						error.setVisible(true);
						old_salary.setVisible(false);
					}else {
						check = i;					
					}
				}
				if(check != -1) {
					error.setVisible(false);
					old_salary.setText("Old salary: "+Double.toString(rwa.getEmployee().get(check).getSalary()));
					old_salary.setVisible(true);
				}
			}
			
		});
		
		HBox name_layout = new HBox(name_label, name);
		name_layout.setSpacing(10);
		name_layout.setAlignment(Pos.CENTER);
		
		
		Label salary_label = new Label("Enter salary");
		salary_label.setFont(Font.font(15));
		
		TextField salary = new TextField();
		salary.setMinSize(120, 30);
		salary.setPromptText("Salary");
		
	
		Button change = new Button("Change salary");
		change.setPrefSize(120, 30);
		change.setOnAction(e ->{
			if(error.isVisible() || name.getText().isEmpty()) {
				Alert a = new Alert(AlertType.ERROR, "Username not found!");
				a.show();
			}else {
				if(salary.getText().matches("\\d+")) {
					rwa.setSalary(rwa.getEmployee().get(check), Double.parseDouble(salary.getText()));
					Alert a = new Alert(AlertType.INFORMATION, "Salary changed successfully");
					a.show();
					stage.close();
				}else {
					Alert a = new Alert(AlertType.ERROR, "Salary must be a number!");
					a.show();
				}
			}
		});
		
		
		GridPane pane = new GridPane();
		pane.addColumn(0, name_label,error ,salary_label);
		pane.addColumn(1, name, old_salary,salary);
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.setHgap(20);
		
		VBox vbox = new VBox(title,pane,change);
		vbox.setSpacing(30);
		vbox.setAlignment(Pos.CENTER);
		
		Scene sc = new Scene(vbox,500,450);
		stage.setScene(sc);
		stage.showAndWait();
		
	}
}
