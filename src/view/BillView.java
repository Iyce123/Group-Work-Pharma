package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.Bill;
import Model.Employee;
import Model.Medicine;
import Model.Pharmacist;
import Model.RWAccounts;
import Model.RWMedicine;
import Model.RWStatistics;
import Model.Statistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BillView {
	private Employee pharm;
	
	public BillView(Employee pharm) {
		this.pharm = pharm;
	}
	


    private double p; //price of a medicine
    private int q; //quantity of a medicine
    private double total = 0; //total amount to be paid
    int bill_id = 0;
    RWStatistics rws = new RWStatistics();
    
	public void show(Stage st) {
	ArrayList<Statistics> stats = new ArrayList<>();
		
		//MODIFY THIS SHIT 
		//st.initModality(Modality.APPLICATION_MODAL);
		st.setTitle("Medicines");
	    st.setWidth(900);
	    st.setHeight(700);

	    
	    
	    TextField medicine = new TextField();
	    medicine.setPrefSize(30, 60);
	    medicine.setPromptText("Enter the medicine name");
	    
	    TextField quantity = new TextField();
	    quantity.setPrefSize(30,60);
	    quantity.setPromptText("Enter the quantity to be sold");
	   
	    
	   
	    ObservableList<Bill> bill  = FXCollections.observableArrayList();
	    //creating the bill table
	    TableView<Bill> table = new TableView<Bill>();
	    
	    TableColumn<Bill, String> name = new TableColumn<Bill, String>("Medicine");
	    name.setCellValueFactory(
                new PropertyValueFactory<>("medicine"));
	    
	    TableColumn<Bill, Double> price = new TableColumn<Bill, Double>("Price");
	    price.setCellValueFactory(
                new PropertyValueFactory<>("price"));
	    
	    TableColumn<Bill, Integer> quant = new TableColumn<Bill, Integer>("Quantity");
	    quant.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
	    
	    table.setItems(bill);
		table.getColumns().addAll(name, price ,quant);
		
	    
		RWMedicine rwm = new RWMedicine();
		
	    Button add = new Button("Add");
	    add.setPrefSize(70, 30);   
	    add.setOnAction(e -> {
	 		
		    	int index = -1;
		    	
		    	//checking if the pharmacy has the medicine and saving the index into a variable
			    for(int i = 0; i < rwm.getMedicine().size(); i++) {
			    	if(rwm.getMedicine().get(i).getName().equals(medicine.getText())) {
			    		index = i;
			    	}
			    }
			   
			    if(index != -1) {
			    	//checking if the quantity is a number
			    	if(quantity.getText().matches(("\\d+"))) {
			    		//adding medicine into the bill
				    	p = rwm.getMedicine().get(index).getSold_price();
				    	q = Integer.parseInt(quantity.getText());
				    	if(rwm.getMedicine().get(index).checkQuantity(q) == true) {
				    		Alert a = new Alert(AlertType.ERROR, "Pharmacy has only "+ rwm.getMedicine().get(index).getQuantity()+ " in stock");
				    		a.show();
				    	}else {
				    		int new_quantity = rwm.getMedicine().get(index).getQuantity() - q;
				    	   // Bill b = new Bill(medicine.getText(), p, q);
					    	rwm.newQuantity(rwm.getMedicine().get(index), new_quantity );//removing the sold quantity from the pharmacy
					    	total += (p * q);
					    	if(rwm.getMedicine().get(index).checkQuantity(q) == true) {
					    		Alert a = new Alert(AlertType.ERROR, "Pharmacy has only "+ rwm.getMedicine().get(index).getQuantity()+ " in stock");
					    		a.show();
					    	}else { 
					    	
					    		Bill temp;
					    		int check = -1;
					    		for(int j = 0 ; j < bill.size(); j++) {	
					    			if(bill.get(j).getMedicine().equals(medicine.getText()))
					    				check = j;
					    			
					    		}
					    		if(check != -1) {
					    			bill.get(check).setQuantity(bill.get(check).getQuantity() + q);
					    			temp = (bill.get(check));
					    			bill.set(check, temp);
					    			//TODO visually the medicine doesnt change quantity
					    		}else {
					    			temp = new Bill(medicine.getText(), p, q);
					    			bill.add(temp);
					    			
					    		}  	 
					    		//checking if the medicine is in the stats
					    		
					    		int check_stats = -1;
					    		for(int i = 0  ; i < rws.getSupplier().size(); i++) {
					    				if(rws.getSupplier().get(i).getMed().equals(temp.getMedicine()))
					    					check_stats = i;
					    			
					    		}
					    		if(check_stats != -1) {
					    			rws.changeQuantity(rws.getSupplier().get(check_stats), q + rws.getSupplier().get(check_stats).getQuantity()
					    					,(rwm.getMedicine().get(index).getSold_price() - rwm.getMedicine().get(index).getBought_price()));
					    		}else {
					    			Statistics stat = new Statistics(rwm.getMedicine().get(index).getName()
						    				,rwm.getMedicine().get(index).getSold_price()
						    				,q,total
						    				,/*income -> */  q *(rwm.getMedicine().get(index).getSold_price() - rwm.getMedicine().get(index).getBought_price())
						    				);
					    			rws.add(stat);
					    		}
					    	}
				    	}
			    	}else {
			    		Alert a = new Alert(AlertType.ERROR, "Quantity must be a number");
			    		a.show();
			    	}
			   }else {
	    		Alert a = new Alert(AlertType.ERROR, "There is no medicine with this name");
	    		a.show();
	    	}
	
	    });
	    
	    Button cancel = new Button("Exit");
	    cancel.setPrefSize(70, 30);
	    cancel.setOnAction(e -> {
	    	st.close();
	    });
	    
	    HBox hbox = new HBox(add,cancel);
	    hbox.setSpacing(20);
	    
	    Button showMed = new Button("Show Medicine");
	    showMed.setPrefSize(160, 30);
	    showMed.setOnAction(e -> {
	    	new stockView().show(new Stage());
	    });
	    
	    VBox b = new VBox(hbox,showMed);
	    b.setSpacing(20);
	    
	    
	    VBox vbox = new VBox(medicine,quantity,b);
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(10);
	    
	   
		Label total_amount = new Label();
		total_amount.setVisible(false);
		total_amount.setAlignment(Pos.CENTER);
		
		
		
				
		Button print = new Button("Print Bill");
		print.setDisable(true);
		print.setPrefSize(120, 30);
		//printing the bill into a text file
		print.setOnAction(e -> {
			PrintWriter pw = null; 
			try {
				 pw  = new PrintWriter(new FileOutputStream(new File("Bill"+pharm.getUsername()+((Pharmacist) pharm).getBills() +".txt"),true));
				Bill temp_bill;
				ArrayList<String> temp = new ArrayList<>();
				temp.add("                  Pharmacy                 ");
				temp.add("                                           ");
				temp.add("Pharmacist: " + pharm.getName() + " " + pharm.getSurname());
				temp.add("");
				temp.add("Date :  "+ getDate());
				temp.add("                                           ");
				temp.add("-------------------------------------------");
				temp.add("");
				temp.add("Medicine         Price         Quantity        Total price");
				temp.add("");
				for(int i = 0 ; i < table.getItems().size(); i++) {
					temp_bill = table.getItems().get(i);
					temp.add(temp_bill.getMedicine() + "\t\t " + temp_bill.getPrice() + "\t\t"+ temp_bill.getQuantity() + "\t\t" + temp_bill.getPrice() * temp_bill.getQuantity());
					
				}
				
				
				for(int i = 0 ; i < temp.size(); i++) {
					System.out.println(temp.get(i));
					pw.println(temp.get(i));
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				pw.close();
			}
			
			RWAccounts rwa = new RWAccounts();
			int index = -1;
			
			for(int i  = 0 ; i < rwa.getEmployee().size(); i++) {
				if(rwa.getEmployee().get(i).getUsername().equals(pharm.getUsername())) {
					index = i;
				}
			}
			if(index != -1) {
				((Pharmacist) rwa.getEmployee().get(index)).setBills(((Pharmacist) pharm).getBills() + 1);
				((Pharmacist) rwa.getEmployee().get(index)).setMoney(((Pharmacist) pharm).getMoney() + total);
				rwa.reWrite();
			}
		
			Alert a = new Alert(AlertType.INFORMATION, "Receipt printed");
			a.showAndWait();
			st.close();
			
			
		});
		
		Button create = new Button("Calculate");
		create.setPrefSize(120, 30);
		create.setOnAction(e -> {
			add.setDisable(true);
			total_amount.setText("Amount : " + total);
			total_amount.setFont(Font.font("Times New Roman",FontWeight.BOLD, 17));
			total_amount.setVisible(true);
			print.setDisable(false);
			//do statistics
			
			RWStatistics rws = new RWStatistics();
			for(int i = 0 ; i < stats.size(); i++) {
				rws.add(stats.get(i));
			}
			
		});
		HBox buttons = new HBox(create,print);
		buttons.setSpacing(30);
		buttons.setAlignment(Pos.CENTER);
		
		VBox vtable = new VBox();
		vtable.getChildren().addAll(table,buttons, total_amount);
		vtable.setSpacing(50);
		
		AnchorPane layout = new AnchorPane();
		AnchorPane.setTopAnchor(vbox, 30.0);
		AnchorPane.setLeftAnchor(vbox, 30.0);
		AnchorPane.setRightAnchor(vbox, 550.0);
		
		AnchorPane.setTopAnchor(vtable,	30.0);
		AnchorPane.setLeftAnchor(vtable, 400.0);
		AnchorPane.setRightAnchor(vtable, 30.0);
		AnchorPane.setBottomAnchor(vtable, 50.0);
		layout.getChildren().addAll(vbox,vtable);
		
		st.setScene(new Scene(layout));
		
		st.show();
	}
	
	
	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return (dtf.format(now));  
	}
}
