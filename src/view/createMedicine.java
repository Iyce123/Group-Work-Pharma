package view;

import Model.Birthday;
import Model.Employee;
import Model.Manager;
import Model.Medicine;
import Model.RWMedicine;
import Model.RWSupplier;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class createMedicine {
	
	int index_i = -1; 
	int index_j = -1;

	public void show(Employee manager,Stage stage) {
		
		TextField supp = new TextField();
		supp.setId("text");
		supp.setPromptText("Enter name of the supplier");
		supp.setMaxSize(300, 300);
		
		TextField name = new TextField();
		name.setId("text");
		name.setPromptText("Enter name of the medicine");
		name.setMaxSize(300, 300);
		
		TextField sold_price = new TextField();
		sold_price.setId("text");
		sold_price.setPromptText("Enter sold price");
		sold_price.setMaxSize(300, 300);
		
		TextField quantity = new TextField();
		quantity.setId("text");
		quantity.setPromptText("Enter quantity to be bought");
		quantity.setMaxSize(300, 300);
		
		HBox transaction = new HBox(sold_price,quantity);
		
		transaction.setSpacing(40);
		transaction.setAlignment(Pos.CENTER);
		
		DatePicker exp = new DatePicker();
		//exp.setId("text");
		exp.getEditor().setDisable(true);
		Label explabel = new Label("Enter expiry date");
		VBox expdate = new VBox(explabel,exp);
		expdate.setSpacing(10);
		
		DatePicker pr = new DatePicker();
		//pr.setId("text");
		pr.getEditor().setDisable(true);
		Label prlabel = new Label("Enter purchased date");
		VBox prdate = new VBox(prlabel,pr);
		prdate.setSpacing(10);
		
		Button add = new Button("Add");
		add.setPrefSize(70, 30);
		add.setOnAction(e -> {

			RWSupplier rws = new RWSupplier();
			if(supp.getText().trim().isEmpty() || name.getText().trim().isEmpty() || sold_price.getText().trim().isEmpty()
					|| quantity.getText().trim().isEmpty() || exp.getValue()==null || pr.getValue() == null) {
				Alert a = new Alert(AlertType.ERROR, "Do not leave anything blank");
				a.show();
			}else {
				for(int i = 0 ; i < rws.getSupplier().size(); i++) {
					//testing if there is the supplier entered
					if(rws.getSupplier().get(i).getSup_name().equals(supp.getText())) {
						index_i = i;
						for(int j = 0 ; j < rws.getSupplier().get(i).getMedicine().size();j++) {
							//checking if the medicine is offered by the supplier
							if(rws.getSupplier().get(i).getMedicine().get(j).equals(name.getText())) {
								index_j = j;
							}
						}
					}
				}
			}
			
			//adding the medicine into the bill
			if(index_i != -1) {
				if(index_j != -1) {
					if(quantity.getText().matches(("\\d+"))) {
						if(sold_price.getText().matches(("\\d+"))) {
							
							RWMedicine rwm = new RWMedicine();
							Medicine temp;
							int check = -1;
							for(int i = 0 ; i < rwm.getMedicine().size(); i++) {
								if(rwm.getMedicine().get(i).getName().equals(name.getText()))
									check = i;
							}
							if(check != -1) /*if medicine already exists*/ {
								rwm.changeQuantity(rwm.getMedicine().get(check)
										, rwm.getMedicine().get(check).getQuantity() + Integer.parseInt(quantity.getText()));
							}else {
								 temp = new Medicine(name.getText(), Integer.parseInt(quantity.getText())
											,(Double)rws.getSupplier().get(index_i).getPr().get(index_j),Double.parseDouble(sold_price.getText())
											,new Birthday(exp.getValue().toString().replace("-","/"))
											,new Birthday(pr.getValue().toString().replace("-","/")));
								 rwm.add(temp);
							}
							Alert a = new Alert(AlertType.INFORMATION, "Medicine saved successfuly");
							a.show();
							(new addSupplier()).show(manager, stage);
							//TODO alerts dont work properly beacuse of loops
						}else {
							Alert a = new Alert(AlertType.ERROR, "Price must be a number!");
							a.show();
						}	
					}else {
						Alert a = new Alert(AlertType.ERROR, "Quantity must be a number!");
						a.show();
					}
				}else {
					Alert al = new Alert(AlertType.ERROR, "This supplier does not offer this medicine!");
					al.show();
				}
			}else {
				
				Alert b = new Alert(AlertType.ERROR, "There is no supplier with this name!");
				b.show();
			}
		});
		
		
		Button close = new Button("Close");
		close.setPrefSize(70,30);
		close.setOnAction(e -> {
			new addSupplier().show(manager, stage);
		});
		
		
		HBox hbox = new HBox(prdate, expdate);
		hbox.setSpacing(30);
		hbox.setAlignment(Pos.CENTER);
		
		HBox buttons = new HBox(add,close);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);
		
		VBox vbox = new VBox(supp, name, transaction, hbox, buttons);
		vbox.setSpacing(25);
		vbox.setAlignment(Pos.CENTER);
		Scene sc = new Scene(vbox,450,450);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
		stage.setScene(sc);

		stage.show();
		
	}
	
	//checking if the medicine is already in the stock
	public Medicine checkMedicine( String name) {
		RWMedicine rwm = new RWMedicine();
		for(int i = 0 ; i < rwm.getMedicine().size(); i++) {
			if(rwm.getMedicine().get(i).getName().equals(name))
				return rwm.getMedicine().get(i);
		}
		return null;
	}

}
