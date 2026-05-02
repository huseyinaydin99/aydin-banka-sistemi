package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.CustomerAccountService;

@Controller
@RequestMapping("/account-list-for-copy")
@RequiredArgsConstructor
public class AccountListForCopyController {
    private final AppUserRepository appUserRepository;
    private final CustomerAccountService customerAccountService;

    @GetMapping
    public String index(Model model, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        model.addAttribute("accounts", customerAccountService.getCustomerAccountsList(user.getId()));
        return "account-list-for-copy/index";
    }
}
