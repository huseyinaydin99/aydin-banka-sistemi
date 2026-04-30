package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.dto.CustomerAccountDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.CustomerAccountService;

@Controller
@RequestMapping("/customer-account")
@RequiredArgsConstructor
public class CustomerAccountController {
    private final CustomerAccountService customerAccountService;
    private final AppUserRepository appUserRepository;

    @GetMapping
    public String index(Model model, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        model.addAttribute("accounts", customerAccountService.getCustomerAccountsList(user.getId()));
        return "customer_account/index";
    }

    @GetMapping("/create")
    public String createAccount(Model model, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        model.addAttribute("nameSurname", user.getName() + " " + user.getSurname());
        return "customer_account/create";
    }

    @PostMapping("/create")
    public String createAccount(CustomerAccountDto customerAccountDto, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        customerAccountDto.setUserId(user.getId());
        customerAccountService.createAccount(customerAccountDto);
        return "redirect:/dashboard";
    }

    @GetMapping("/usd-list")
    public String getCustomerUSDAccountsList(Model model, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        model.addAttribute("accounts", customerAccountService.getCustomerUSDAccountsList(user.getId()));
        return "customer_account/usd_list";
    }

    @GetMapping("/eur-list")
    public String getCustomerEURAccountsList(Model model, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        model.addAttribute("accounts", customerAccountService.getCustomerEURAccountsList(user.getId()));
        return "customer_account/eur_list";
    }
}
