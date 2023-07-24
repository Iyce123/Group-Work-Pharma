package view;

import Model.Employee;
import Model.RWAccounts;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class changePWView {

	public void show(Stage stage, Employee emp) {
		
		stage.setTitle("Password change");
		stage.initModality(Modality.APPLICATION_MODAL);
		
		PasswordField oldpw = new PasswordField();
		oldpw.setPromptText("Enter old password");
		oldpw.setPrefSize(70, 30);
		
		PasswordField newpw = new PasswordField();
		newpw.setPromptText("Enter new password");
		newpw.setPrefSize(70, 30);
		
		PasswordField reenter = new PasswordField();
		reenter.setPromptText("Re-enter password");
		reenter.setPrefSize(70, 30);
		
		Button confirm = new Button("Confirm");
		confirm.setPrefSize(70, 30);
		confirm.setOnAction(e -> {
			if(oldpw.getText().equals(emp.getPassword())) {
				if(newpw.getText().equals(reenter.getText())) {
					new RWAccounts().changePassword(emp, newpw.getText());
					Alert al = new Alert(AlertType.INFORMATION, "Password changed successfuly");
					al.setTitle("Sign Up");
					al.setHeaderText(null);
					al.setGraphic(null);
					al.show();
					stage.close();
				}
				else {
					Alert al = new Alert(AlertType.ERROR, "Passwords do not match!");
					al.show();
				}
			}else {
				Alert al = new Alert(AlertType.ERROR, "Your password is incorrect");
				al.show();
			}
				
		});
		
		Button close = new Button("Close");
		close.setOnAction(e -> {
			stage.close();
		});
		
		VBox layout = new VBox();
		layout.getChildren().addAll(oldpw,newpw,reenter,confirm,close);
		layout.setSpacing(15);
		layout.setAlignment(Pos.CENTER);
		
		stage.setScene(new Scene(layout,250,250));
		stage.showAndWait();
		
	}
}
