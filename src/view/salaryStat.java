package view;

import java.util.ArrayList;

import Model.Employee;
import Model.Pharmacist;
import Model.RWAccounts;
import Model.RWStatistics;
import Model.Statistics;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class salaryStat {

	
	public void show(Stage stage) {
		
		stage.setTitle("Pie Chart");
	
		RWAccounts rwa = new RWAccounts();
		ArrayList<Employee> st = new ArrayList<>();	
		st = rwa.readEmp();
		ObservableList<PieChart.Data> piechart1 = FXCollections.observableArrayList(); 
		
		for(int i = 0 ; i < st.size(); i++) {
			piechart1.addAll(new PieChart.Data(st.get(i).getUsername(),st.get(i).getSalary()));	
		}
		piechart1.forEach(data ->
        data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), " ", data.pieValueProperty(), "  $"
                		)
        		)
		);
		final PieChart chart1  = new PieChart(piechart1);
		chart1.setTitle("Salary");
		
		
		
		ObservableList<PieChart.Data> piechart2 = FXCollections.observableArrayList(); 
		for(int i = 0 ; i < st.size(); i++) {
			if(st.get(i) instanceof Pharmacist)
				piechart2.addAll(new PieChart.Data(st.get(i).getUsername(),((Pharmacist) st.get(i)).getBills()));	
		}
		piechart2.forEach(data ->
        data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), " ", data.pieValueProperty(), "  bills"
                		)
        		)
		);
		
		final PieChart chart2  = new PieChart(piechart2);
		chart2.setTitle("Bills");
		
		HBox chart = new HBox(chart1,chart2);
		
		FlowPane pane = new FlowPane();
		pane.getChildren().addAll(chart);
		pane.setAlignment(Pos.CENTER);
		
		stage.setScene(new Scene(pane,850,450));
		stage.show();
		
	}
	
}
