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
        Map<String, Double> rates = currencyService.getExchangeRates();
        model.addAttribute("rates", rates);
        model.addAttribute("usdToTry", rates.getOrDefault("TRY", 0.0));
        model.addAttribute("eurToTry", rates.getOrDefault("TRY", 0.0) / rates.getOrDefault("EUR", 1.0));
        model.addAttribute("gbpToTry", rates.getOrDefault("TRY", 0.0) / rates.getOrDefault("GBP", 1.0));
        model.addAttribute("usdToEur", rates.getOrDefault("EUR", 0.0));
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return "dashboard/index";
    }

    @GetMapping("/Dashboard/Chart")
    @ResponseBody
    public Map<String, Object> getChartData() {
        Map<String, Double> rates = currencyService.getExchangeRates();
        Map<String, Object> response = new HashMap<>();
        List<String> keys = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        rates.forEach((k, v) -> {
            if (List.of("TRY", "EUR", "GBP", "CHF", "CAD").contains(k)) {
                keys.add(k);
                values.add(v);
            }
        });

        response.put("exchangeKey", keys);
        response.put("exchangeValue", values);
        return response;
    }
}
