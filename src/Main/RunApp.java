
/*********************************************************
* 
* @author Reni Koi 
* 
* Project   : Pharmacy project
*
**********************************************************/

package Main;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.LogIn;

public class RunApp extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		(new LogIn()).start(stage);
		
		stage.show();
		stage.getIcons().add(new Image("images/floopy.jpg"));
		
	}
	
}
