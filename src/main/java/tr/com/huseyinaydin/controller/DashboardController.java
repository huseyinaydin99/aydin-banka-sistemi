package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tr.com.huseyinaydin.service.CurrencyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final CurrencyService currencyService;

    @GetMapping("/dashboard")
    public String index(Model model) {
        Map<String, Double> rates = new HashMap<>();
        try {
            rates = currencyService.getExchangeRates();
        } catch (Exception e) {
            System.err.println("Döviz kurları alınamadı: " + e.getMessage());
        }
        
        double tryRate = rates.getOrDefault("TRY", 1.0);
        double usdRate = rates.getOrDefault("USD", 1.0);
        double eurRate = rates.getOrDefault("EUR", 1.0);
        double gbpRate = rates.getOrDefault("GBP", 1.0);

        model.addAttribute("rates", rates);
        model.addAttribute("usdToTry", 1.0 / (usdRate != 0 ? usdRate : 1.0));
        model.addAttribute("eurToTry", 1.0 / (eurRate != 0 ? eurRate : 1.0));
        model.addAttribute("gbpToTry", 1.0 / (gbpRate != 0 ? gbpRate : 1.0));
        model.addAttribute("usdToEur", eurRate / (usdRate != 0 ? usdRate : 1.0));
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        model.addAttribute("pageTitle", "Dashboard");
        return "dashboard/index";
    }

    @GetMapping("/Dashboard/Chart")
    @ResponseBody
    public Map<String, Object> getChartData() {
        Map<String, Double> rates = new HashMap<>();
        try {
            rates = currencyService.getExchangeRates();
        } catch (Exception e) {
            System.err.println("Grafik verileri için döviz kurları alınamadı: " + e.getMessage());
        }
        
        Map<String, Object> response = new HashMap<>();
        List<String> keys = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        rates.forEach((k, v) -> {
            if (List.of("TRY", "EUR", "GBP", "CHF", "CAD").contains(k)) {
                keys.add(k);
                values.add(v);
            }
        });

        if (keys.isEmpty()) {
            keys.addAll(List.of("TRY", "EUR", "GBP"));
            values.addAll(List.of(1.0, 0.028, 0.024));
        }

        response.put("exchangeKey", keys);
        response.put("exchangeValue", values);
        return response;
    }
}
