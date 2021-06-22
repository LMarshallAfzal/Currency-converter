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

    private final Converter converter = new Converter();

    /**
     * Initialises the GUI and sets the necessary elements.
     * @param url locates the necessary information
     * @param resourceBundle the location where the necessary information is located.
     */
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        populateComboBoxes();
    }

    /**
     * Populates the comboboxes with an observable list of currency codes.
     */
    public void populateComboBoxes()
    {
        ObservableList<String> list = FXCollections.observableArrayList("GBP", "EUR", "USD");
        startingCurrencyCmb.setItems(list);
        resultCurrencyCmb.setItems(list);


    }

    /**
     * Gets the starting amount that the user inputs into the textfield.
     */
    public void retrieveStartingAmount()
    {
        CharSequence cs = startingCurrencyTxt.getCharacters();
        //Instance variables
        double startingAmount = Double.parseDouble(cs.toString());
        System.out.println(startingAmount);
    }

    /**
     * The Action that occurs once the select button has been pressed.
     * @throws IOException
     */
    public void onSelectButtonClicked() throws IOException {
        retrieveStartingAmount();
        currencyConversion();
    }

    /**
     * Sends the information required by the currency converter API to the API call in the Converter class.
     * @throws IOException
     */
    public void currencyConversion() throws IOException {
        //converter.sendApiGetRequest(startingCurrencyCmb.getAccessibleText(), resultCurrencyCmb.getAccessibleText(), startingAmount);
        converter.sendApiGetRequest("USD", "GBP", 100.0); //For testing

    }

}

