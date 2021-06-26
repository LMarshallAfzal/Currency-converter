package application;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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
    public static String sendApiGetRequest(String startCurrency, String endCurrency, double amount) throws IOException {
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

            System.out.println(amount/exchangeRate + endCurrency);
            resultingAmount = amount/exchangeRate + endCurrency;
            return amount/exchangeRate + endCurrency;
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
