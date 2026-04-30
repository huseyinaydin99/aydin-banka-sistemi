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
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
        Map<String, Double> rates = new HashMap<>();
        
        if (response != null && response.get("rates") != null) {
            Map<String, Object> apiRates = (Map<String, Object>) response.get("rates");
            rates.put("USD", Double.valueOf(apiRates.get("USD").toString()));
            rates.put("EUR", Double.valueOf(apiRates.get("EUR").toString()));
            rates.put("GBP", Double.valueOf(apiRates.get("GBP").toString()));
        }
        
        return rates;
    }
}
