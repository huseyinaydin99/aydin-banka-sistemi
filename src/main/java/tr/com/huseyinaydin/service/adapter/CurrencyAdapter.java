package tr.com.huseyinaydin.service.adapter;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tr.com.huseyinaydin.service.CurrencyService;

import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyAdapter implements CurrencyService {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/TRY";

    @Override
    public Map<String, Double> getExchangeRates() {
        Map<String, Double> rates = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
            
            if (response != null && response.get("rates") != null) {
                Map<String, Object> apiRates = (Map<String, Object>) response.get("rates");
                if (apiRates.containsKey("USD")) rates.put("USD", Double.valueOf(apiRates.get("USD").toString()));
                if (apiRates.containsKey("EUR")) rates.put("EUR", Double.valueOf(apiRates.get("EUR").toString()));
                if (apiRates.containsKey("GBP")) rates.put("GBP", Double.valueOf(apiRates.get("GBP").toString()));
                if (apiRates.containsKey("CHF")) rates.put("CHF", Double.valueOf(apiRates.get("CHF").toString()));
                if (apiRates.containsKey("CAD")) rates.put("CAD", Double.valueOf(apiRates.get("CAD").toString()));
                if (apiRates.containsKey("JPY")) rates.put("JPY", Double.valueOf(apiRates.get("JPY").toString()));
                if (apiRates.containsKey("AUD")) rates.put("AUD", Double.valueOf(apiRates.get("AUD").toString()));
                if (apiRates.containsKey("TRY")) rates.put("TRY", Double.valueOf(apiRates.get("TRY").toString()));
            }
        } catch (Exception e) {
            System.err.println("API Hatası: " + e.getMessage());
            // Varsayılan değerler
            rates.put("USD", 0.03);
            rates.put("EUR", 0.028);
            rates.put("GBP", 0.024);
            rates.put("TRY", 1.0);
        }
        
        return rates;
    }
}
