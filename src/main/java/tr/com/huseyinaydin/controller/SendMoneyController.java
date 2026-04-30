package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.service.CustomerAccountProcessService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SendMoneyController {
    private final CustomerAccountProcessService processService;
    private final CustomerAccountRepository accountRepository;
    private final AppUserRepository appUserRepository;

    @GetMapping("/send-money")
    public String index(@RequestParam(name = "currency", defaultValue = "TL") String currency, Authentication authentication, Model model) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        List<CustomerAccount> accounts = accountRepository.findByAppUserId(user.getId()).stream()
                .filter(a -> a.getCurrency().equals(currency))
                .collect(Collectors.toList());

        model.addAttribute("currency", currency);
        model.addAttribute("accounts", accounts);
        model.addAttribute("processDto", new CustomerAccountProcessDto());
        return "send-money/index";
    }

    @PostMapping("/send-money")
    public String sendMoney(CustomerAccountProcessDto processDto, Model model) {
        try {
            processService.sendMoney(processDto);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "send-money/index";
        }
    }
}
