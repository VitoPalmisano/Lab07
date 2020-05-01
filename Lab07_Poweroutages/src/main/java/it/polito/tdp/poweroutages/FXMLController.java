package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> cmbNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnAnalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalysis(ActionEvent event) {
    	
    	txtResult.clear();
    	try {
    		int anni = Integer.parseInt(txtYears.getText());
    		int ore = Integer.parseInt(txtHours.getText());
    		if(cmbNerc.getValue() == null)
    			txtResult.setText("Selezionare prima un Nerc dalla lista");
    		else
    			txtResult.setText(model.trovaBlackouts(cmbNerc.getValue(), anni, ore));
    	}
    	catch(NumberFormatException nfe) {
    		txtResult.setText("Inserire nei camppi 'Max years' e 'Max hours' esclusivamente valori numerici interi");
    	}
    	
    }

    @FXML
    void initialize() {
        assert cmbNerc != null : "fx:id=\"cmbNERC\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }

	public void setModel(Model model) {
		this.model = model;
		
		cmbNerc.getItems().addAll(model.getNercList());
	}
}
