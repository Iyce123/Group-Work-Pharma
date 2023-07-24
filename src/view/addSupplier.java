package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.Employee;
import Model.Manager;
import Model.Medicine;
import Model.RWSupplier;
import Model.Supplier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class addSupplier {
	
	public void show(Employee manager, Stage stage) {
		
	
		stage.setTitle("Supply");
		
		Button addProd = new Button("Add medicine");
		addProd.setPrefSize(120, 30);
		addProd.setOnAction( e -> {
			new createMedicine().show(manager, stage);
		});
		
		Button supp = new Button("See Suppliers");
		supp.setPrefSize(120,30);
		supp.setOnAction(e ->{
			
//			RWSupplier s = new RWSupplier();
//			Supplier a = new Supplier("Roche");
//			a.addMedicine("Bevacizumab", 150);
//			a.addMedicine("Trastuzumab", 132.1);
//			a.addMedicine("Rituximab",138.4);
//			a.addMedicine("Rosuvastatin", 185.7);
//			s.add(a);
//			
//			Supplier b = new Supplier("AbbVie Inc.");
//			b.addMedicine("Oki", 50.0);
//			b.addMedicine("Etanercept", 175.4);
//			b.addMedicine("Infliximab", 235.1);
//			s.add(b);
//			
//			Supplier c = new Supplier("Generic");
//			c.addMedicine("Nexium", 500.1);
//			c.addMedicine("Avastin",250.3);
//			c.addMedicine("Abilify",300.1);
//			c.addMedicine("Januvia",52.54);
//			c.addMedicine("Levemir",96.54);
//			s.add(c);
//			
//			Supplier d = new Supplier("Bayer");
//			d.addMedicine("Xarelto", 500.1);
//			d.addMedicine("Ritalin",250.3);
//			d.addMedicine("Alimta",300.1);
//			s.add(d);
			
			
			
			new supplierView().show(new Stage());
			
		});
		
		Button done = new Button("Exit");
		done.setPrefSize(120,30);
		done.setOnAction(e -> {
			try {
				new ManagerView(manager).show(stage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	
		HBox buttons = new HBox(addProd,supp,done);
		buttons.setSpacing(20);
		buttons.setPadding(new Insets(10));
		buttons.setAlignment(Pos.CENTER);
		
		BorderPane layout = new BorderPane();
		layout.setCenter(buttons);
		BorderPane.setAlignment(buttons, Pos.CENTER);
		
		stage.setScene(new Scene(layout,350,350));
		stage.show();
		
		
	}

}
