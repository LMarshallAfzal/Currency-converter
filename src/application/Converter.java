package application;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Currency;

/**
 * This is where the main function of that application takes place. The Converter class contains the API call function
 * which converts a certain amount of one currency into a resulting currency using an online currency conversion API.
 *
 * @author Leonard Marshall Afzal
 * @version 26/06/2021
 */
public class Converter {
    //Instance variables
    private static String resultingAmount;
    /**
     * The API call for the currency conversion API. It converts a a certain amount of one currency into a second currency.
     * @param startCurrency The currency code that the API will convert from.
     * @param endCurrency The currency code that the APi will convert to.
     * @param amount The amount that is being converted.
     * @throws IOException
     */
    public String sendApiGetRequest(String startCurrency, String endCurrency, double amount) throws IOException {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(Currency.getInstance(endCurrency));

        String GET_URL = "https://api.exchangeratesapi.io/v1/convert?access_key=531da44fa06ca35c43f81ba58f6def74&from=" + startCurrency + "&to=" + endCurrency + "&amount=" + amount;
        URL url = new URL(GET_URL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            input.close();

            JSONObject object = new JSONObject(response.toString());
            Double exchangeRate = object.getJSONObject("info").getDouble("rate");

            resultingAmount = formatter.format(amount*exchangeRate);
            return resultingAmount;
        }
        else {
            System.out.println("GET request failed!");
            return null;
        }
    }

    /**
     * Gets the amount of resulting currency.
     * @return the amount of resulting currency after the exchange rate has been applied.
     */
    public String getResultingAmount() {
        return resultingAmount;
    }
}
