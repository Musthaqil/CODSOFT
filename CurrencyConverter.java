package task4;

import java.util.*;
import java.io.*;
import java.net.*;

public class CurrencyConverter {

    public static void main(String[] args) {
            // Get user input for currency conversion
    	try {
    		
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the base currency code: ");
            String baseCurrency = sc.nextLine().toUpperCase();

            System.out.print("Enter the target currency code: ");
            String targetCurrency = sc.nextLine().toUpperCase();

            System.out.print("Enter the amount to convert: ");
            double amount = sc.nextDouble();
            
            sc.close();

            // Perform the currency conversion
            double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);

            // Display the result
            System.out.printf("%f %s is equal to %f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}

    }
    
    //api key for the currency converter
    private static final String API_KEY = "463c6adb4c96ca71b99a7ea24a65010e";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
    	//setting up the url to fetch the real time currency rate of respective countries.
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + API_KEY;
        
        //appending the apiURL to the URL class for HTTPURL Connection
        URL url = new URL(apiUrl);
        //open the url connection using HttpURL Connection class
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        //setting the request method from httpurl connection class
        //get method
        connection.setRequestMethod("GET");
        
        //getting the request into the buffered reader class to set as response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        //reading each line of response and build into the String Builder class
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        // Parse the JSON response
        String jsonResponse = response.toString();
        int start = jsonResponse.indexOf(targetCurrency) + 5; // Assuming the format is "USD": X.XX,
        int end = jsonResponse.indexOf(",", start);
        String rateString = jsonResponse.substring(start, end);
        //the respective targetCurrency rate for the basecurrency is stored in rateString object
        return Double.parseDouble(rateString);
    }

    public static double convertCurrency(double amount, String baseCurrency, String targetCurrency) throws IOException {
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        return amount * exchangeRate;
    }

    
}
