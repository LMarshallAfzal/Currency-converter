package application;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Converter {
    public static void sendApiGetRequest(String startCurrency, String endCurrency, double amount) throws IOException {
        String GET_URL = "https://api.exchangeratesapi.io/v1/convert?access_key=531da44fa06ca35c43f81ba58f6def74&from=" + startCurrency + "&to=" + endCurrency + "&amount=" + amount;
        URL url = new URL(GET_URL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if(responseCode == connection.HTTP_OK) {
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            input.close();

            JSONObject object = new JSONObject(response.toString());
            Double exchangeRate = object.getJSONObject("rates").getDouble(startCurrency);
            System.out.println(object.getJSONObject("rates"));
            System.out.println(exchangeRate);
            System.out.println();
            System.out.println(amount + startCurrency + " = " + amount/exchangeRate + endCurrency);
        }
        else {
            System.out.println("GET request failed!");
        }
    }
    //public static void sendApiGetRequest(String startCurrency, String endCurrency, double amount) throws IOException {

    //}
}
