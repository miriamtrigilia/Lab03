/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;


import it.polito.tdp.spellchecker.model.Model;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class SpellCheckerController {
	
	private Model dic;
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="choiseBox"
    private ChoiceBox<String> choiseBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrors"
    private TextField txtErrors; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private TextField txtTime; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    		
    		this.txtArea.clear();
    		this.txtResult.setText("");
    		this.txtTime.clear();
    		this.txtErrors.clear();
    		dic.reset();
    		
    		
    	}
    
    

    @FXML
    void doSpellCheck(ActionEvent event) {
    		List<String> inputTextList = new ArrayList<String>();

    		this.txtResult.clear();
    		double time1 = System.nanoTime();
    
    		String lingua = choiseBox.getValue();
    		dic.loadDictionary(lingua);
    		String testo = txtArea.getText();
    		String parole = testo.replaceAll("[.?,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    		String array[] = parole.split(" ");
    		
    		for(int i=0; i<array.length; i++) {
    			inputTextList.add(array[i].toLowerCase());
    		}
    		
    		
    		dic.spellCheckTextDichotomicaaa(inputTextList);
    		//System.out.println(dic.getParoleErrate());
    		this.txtResult.setText(dic.getParoleErrate());
    		this.txtErrors.setText("Il testo contiene "+dic.numErrate()+" errori");
    		
    		double time2 = System.nanoTime();
    		
    		this.txtTime.setText("Spell check completato in "+(time2-time1)+" secondi");
    		
    		dic.paroleErrate.clear();
    	}
    
    public void setDictionary(Model model) {
		this.dic = model;
		choiseBox.getItems().addAll("Italian","English");
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert choiseBox != null : "fx:id=\"choiseBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
}

