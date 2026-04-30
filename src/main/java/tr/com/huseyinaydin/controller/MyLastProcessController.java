package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.service.CustomerAccountProcessService;

@Controller
@RequestMapping("/my-last-process")
@RequiredArgsConstructor
public class MyLastProcessController {
    private final AppUserRepository appUserRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final CustomerAccountProcessService customerAccountProcessService;

    @GetMapping
    public String index(Model model, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        CustomerAccount tlAccount = customerAccountRepository.findByAppUserId(user.getId()).stream()
                .filter(x -> "TL".equalsIgnoreCase(x.getCurrency()))
                .findFirst()
                .orElse(null);
        
        if (tlAccount != null) {
            model.addAttribute("processes", customerAccountProcessService.getMyLastProcess(tlAccount.getId()));
        }
        
        return "my_last_process/index";
    }
}
