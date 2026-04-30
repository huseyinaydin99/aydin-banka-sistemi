package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tr.com.huseyinaydin.service.CurrencyService;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final CurrencyService currencyService;

    @GetMapping("/dashboard")
    public String index(Model model) {
        model.addAttribute("rates", currencyService.getExchangeRates());
        return "dashboard/index";
    }
}
