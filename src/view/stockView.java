package view;

import java.util.ArrayList;

import Model.Birthday;
import Model.Medicine;
import Model.RWMedicine;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class stockView {
	
	public void show(Stage stage) {
		
		stage.setTitle("Magazine");
		
		TableView table = new TableView();
		table.setEditable(true);
		table.setPrefSize(400, 500);
		
		RWMedicine rwm = new RWMedicine();
		ArrayList<Medicine> med = new ArrayList<>();
		med =rwm.readEmp();
		
		TableColumn name = new TableColumn("Medicine");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setMinWidth(90);
		
		TableColumn quantity = new TableColumn("Quantity");
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		quantity.setMinWidth(90);
		
		TableColumn bought_price = new TableColumn("Bought Price");
		bought_price.setCellValueFactory(new PropertyValueFactory<>("bought_price"));
		bought_price.setMinWidth(90);
		
		TableColumn sold_price = new TableColumn("Sold Price");
		sold_price.setCellValueFactory(new PropertyValueFactory<>("sold_price"));
		sold_price.setMinWidth(90);
		
		TableColumn bdt = new TableColumn("Expiry Date");
		bdt.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
		bdt.setMinWidth(90);
		
		TableColumn b = new TableColumn("Bought date");
		b.setCellValueFactory(new PropertyValueFactory<>("purchased_date"));
		b.setMinWidth(90);
		
		table.setItems(FXCollections.observableArrayList(med));
		table.getColumns().addAll(name,sold_price,quantity,bought_price,b,bdt);
		
		
		Button close = new Button("Close");
		close.setPrefSize(70, 30);
		close.setAlignment(Pos.CENTER);
		close.setOnAction(e -> {
			stage.close();
		});
		
		VBox vbox = new VBox(table,close);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(30);
		stage.setScene(new Scene(vbox,600,600));
		stage.show();
	}

}
