package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //FXML instance variables
    @FXML
    private TextField startingCurrencyTxt;

    @FXML
    private TextField resultingCurrencyTxt;

    @FXML
    private Button selectBtn;

    @FXML
    private ComboBox<String> startingCurrencyCmb;

    @FXML
    private ComboBox<String> resultCurrencyCmb;

    //Instance variables
    private double startingAmount;
    private Converter converter = new Converter();

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        populateComboBoxes();
    }

    public void populateComboBoxes()
    {
        ObservableList<String> list = FXCollections.observableArrayList("GBP", "EUR", "USD");
        startingCurrencyCmb.setItems(list);
        resultCurrencyCmb.setItems(list);


    }

    public void retrieveStartingAmount()
    {
        CharSequence cs = startingCurrencyTxt.getCharacters();
        startingAmount = Double.parseDouble(cs.toString());
        System.out.println(startingAmount);
    }

    public void onSelectButtonClicked() throws IOException {
        retrieveStartingAmount();
        currencyConversion();
    }

    public void currencyConversion() throws IOException {
        converter.sendApiGetRequest(startingCurrencyCmb.getAccessibleText(), resultCurrencyCmb.getAccessibleText(), startingAmount);

    }

}

