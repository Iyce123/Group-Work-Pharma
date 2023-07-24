package view;

import Model.RWSupplier;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class supplierView {

	public void show(Stage st) {
		HBox suppliers = new HBox();
		
		RWSupplier rws = new RWSupplier();
		
		for(int i = 0 ; i < rws.getSupplier().size(); i++) {
			VBox s = new VBox();
			Label suppliername = new Label(rws.getSupplier().get(i).getSup_name());
			suppliername.setFont(Font.font("Times New Roman",FontWeight.BOLD, 15));
			suppliername.setMinHeight(30);
			s.getChildren().add(suppliername);
			
			for(int j = 1 ; j <= rws.getSupplier().get(i).getMedicine().size(); j++) {
				HBox medicine = new HBox();
				Label num = new Label(Integer.toString(j) + ".");
				num.setFont(Font.font("Times New Roman",FontWeight.BOLD, 13));
				Label med_name = new Label(rws.getSupplier().get(i).getMedicine().get(j-1));
				Label price = new Label(Double.toString(rws.getSupplier().get(i).getPr().get(j-1)));
				medicine.getChildren().addAll(num,med_name,price);
				medicine.setSpacing(10);
				s.getChildren().add(medicine);			
				}
			suppliers.getChildren().add(s);
			suppliers.setSpacing(50);
		}
		
		st.setScene(new Scene(suppliers,1000,400));
		st.show();
		
	}
}
