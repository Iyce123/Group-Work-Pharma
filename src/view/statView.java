package view;

import java.util.ArrayList;

import Model.Employee;
import Model.RWStatistics;
import Model.Statistics;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class statView {
	
	
	private double total = 0; //holds the total income
	ArrayList<Statistics>  stat ;
	Label income_label = new Label();//shows the total income
	
	public void show(Stage stage) {
		
		stage.setTitle("Statistics");
		
		TableView table = new TableView();
		table.setEditable(true);
		table.setPrefSize(400, 500);
		
		RWStatistics rws = new RWStatistics();
		stat = new ArrayList<>();
		stat = rws.readStat();
		
		TableColumn<String, Statistics> med = new TableColumn<String, Statistics>("Medicine");
		med.setCellValueFactory(new PropertyValueFactory<>("med"));
		med.setMinWidth(90);
		
		TableColumn<Double, Statistics> price = new TableColumn<Double, Statistics>("Price");
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		price.setMinWidth(90);
		
		TableColumn<Integer, Statistics> quantity = new TableColumn<Integer, Statistics>("Quantity");
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		quantity.setMinWidth(90);
		
		TableColumn<Double, Statistics> tot_price = new TableColumn<Double, Statistics>("Total price");
		tot_price.setCellValueFactory(new PropertyValueFactory<>("tot_price"));
		tot_price.setMinWidth(90);
		
		TableColumn<Double, Statistics> income = new TableColumn<Double, Statistics>("Income");
		income.setCellValueFactory(new PropertyValueFactory<>("income"));
		income.setMinWidth(90);
		
		table.setItems(FXCollections.observableArrayList(stat));
		table.getColumns().addAll(med,price,quantity,tot_price,income);
		
		//calculating total income
		for(int i = 0 ; i < stat.size(); i++)
			total += stat.get(i).getIncome();
		
		income_label.setVisible(false);
		Button tot_income = new Button("Show total income");
		tot_income.setPrefSize(120, 40);
		tot_income.setOnAction(e -> {
			income_label.setText("Total income: " + total);
			income_label.setVisible(true);	
		});
		
		HBox hbox = new HBox(tot_income, income_label);
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		
		VBox vbox = new VBox(table,hbox);
		vbox.setSpacing(20);
		vbox.setPadding(new Insets(10));
		
		stage.setScene(new Scene(vbox,550,550));
		stage.show();		
	}
}
