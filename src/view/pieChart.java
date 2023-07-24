package view;

import java.util.ArrayList;

import Model.RWStatistics;
import Model.Statistics;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class pieChart {

	
	public void show(Stage stage) {
		
		stage.setTitle("Pie Chart");
		

		double income = 0;
		RWStatistics rws = new RWStatistics();
		ArrayList<Statistics> st = new ArrayList<>();	
		st = rws.readStat();
		ObservableList<PieChart.Data> piechart = FXCollections.observableArrayList(); 
		
		for(int i = 0 ; i < st.size(); i++) {
			piechart.addAll(new PieChart.Data(st.get(i).getMed(),st.get(i).getQuantity()));
			income += rws.getSupplier().get(i).getIncome();
		}
		piechart.forEach(data ->
        data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), " ", data.pieValueProperty(), " Medicaments"
                		)
        		)
		);
		
		final PieChart chart  = new PieChart(piechart);
		chart.setTitle("Quantity sold");
		
		String temp = String.format("%.2f",income);
		
		Text income_text = new Text();
		income_text.setText("Total income: " + temp +" $");
		
		
		FlowPane pane = new FlowPane();
		pane.getChildren().addAll(chart,income_text);
		pane.setAlignment(Pos.CENTER);
		
		stage.setScene(new Scene(pane,500,500));
		stage.show();
		
	}
	
}
