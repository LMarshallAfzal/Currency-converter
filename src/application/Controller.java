package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.ResourceBundle;

/**
 * The Controller class controls all the actions that take place in the GUI. It contains all the functions that occur
 * after doing an action such as pressing a button or selecting a currency. It also passes the information that the user
 * gives to the Converter class so that that the currency conversion can be completed.
 *
 * @author Leonard Marshall Afzal
 * @version 26/06/2021
 */
public class Controller implements Initializable {
    //FXML instance variables
    @FXML
    private TextField startingCurrencyTxt;

    @FXML
    private Label startingAmountLbl;

    @FXML
    private Label endAmountLbl;

    @FXML
    private Label arrowLbl;

    @FXML
    private Label errorLbl;

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
        arrowLbl.setVisible(false);
    }

    /**
     * Populates the comboboxes with an observable list of currency codes.
     */
    public void populateComboBoxes()
    {
        ObservableList<String> list = FXCollections.observableArrayList("GBP", "EUR", "USD", "AUD", "JPY", "CAD", "CHF", "CNY", "HKD", "NZD", "SEK", "MXN", "SGD", "NOK", "KRW", "TRY", "INR", "RUB", "BRL", "ZAR", "DKK", "PLN");
        startingCurrencyCmb.setItems(list);
        resultCurrencyCmb.setItems(list);


    }

    /**
     * Gets the starting amount that the user inputs into the textfield.
     */
    public double retrieveStartingAmount()
    {
        CharSequence cs = startingCurrencyTxt.getCharacters();
        double startingAmount = Double.parseDouble(cs.toString());
        return startingAmount;
    }

    /**
     * The Action that occurs once the select button has been pressed.
     * @throws IOException
     */
    public void onSelectButtonClicked() throws IOException {
        try {
            //Checks that the text is in a numeric format.
            if(validateText(startingCurrencyTxt.getText())) {
                retrieveStartingAmount();
                currencyConversion(retrieveStartingAmount());
                populateConversionFields();
                errorLbl.setText("");
            }
            else {
                errorLbl.setText("ERROR: Amount must be a number.");
            }
        }
        catch (Exception e) {
                errorLbl.setText("ERROR: Be sure to select an amount and a currency.");
        }
    }

    /**
     * Sends the information required by the currency converter API to the API call in the Converter class.
     * @param amount amount of currency to be converted.
     * @throws IOException
     */
    public void currencyConversion(double amount) throws IOException {
        converter.sendApiGetRequest(startingCurrencyCmb.getValue(), resultCurrencyCmb.getValue(), amount);
    }

    /**
     * Populates the labels that show the conversion with the correct values.
     */
    public void populateConversionFields() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(Currency.getInstance(startingCurrencyCmb.getValue()));

        String start = String.valueOf(formatter.format(retrieveStartingAmount()));
        startingAmountLbl.setText(start);
        try {
            endAmountLbl.setText(converter.getResultingAmount());
            arrowLbl.setVisible(true);
        }
        catch (Exception e) {
            //If API server is offline for some reason.
            errorLbl.setText("ERROR: Server is offline.");
        }
    }

    /**
     * Checks that the amount is a number and not a word.
     * @param text The text inputted by the user.
     * @return a boolean value of true or false depending on whether the text is in numeric format.
     */
    public boolean validateText(String text) {
        return text.matches("[-]?[0-9]*(\\.[0-9]*)?");
    }
}

