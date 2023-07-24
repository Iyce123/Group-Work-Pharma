package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class programClose {
	
	static boolean answer ;
	
	public static boolean request(){
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Exiting panel");
		stage.setMinWidth(350);
		//stage.initStyle(StageStyle.UNDECORATED);
	
		
		Label label = new Label("Are you sure you want to exit?");
		label.setFont(new Font("Times New Roman", 20));
		
		
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		//actions for the yes button
		yesButton.setPrefSize(60, 30);
		yesButton.setOnAction(e -> {
			answer = true;
			stage.close();
		});
		
		//actions for the no button
		 noButton.setPrefSize(60, 30);
		 noButton.setOnAction( e -> {
			 answer = false;
			 stage.close();
		 });
		 
		 //create a border pane layout
		 BorderPane layout = new BorderPane();
		 
		 //create a horizontal layout ---- the items are 15 pixels away from each other
		 HBox hbox = new HBox(15);
		 hbox.setAlignment(Pos.CENTER);
		 hbox.getChildren().addAll(yesButton, noButton);
		 
		 //set the label on top of the border pane layout
		 layout.setTop(label);
		 //set the horizontal box in the center of the border pane 
		 layout.setCenter(hbox);
		 
		 layout.setPadding(new Insets(30));
		 
		 BorderPane.setMargin(hbox, new Insets(30));
		 BorderPane.setAlignment(label, Pos.CENTER);
		 
		 //put the layout into the scene
		 Scene scene = new Scene(layout,200,150);
		 //put the scene into the stage
		 stage.setScene(scene);
		 //show the stage and no other action can be done until its closed
		 stage.showAndWait();
		
		
		return answer;
	}

}
