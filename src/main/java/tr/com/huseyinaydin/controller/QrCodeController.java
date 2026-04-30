package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.service.QrCodeService;

import java.util.Base64;

@Controller
@RequestMapping("/qrcode")
@RequiredArgsConstructor
public class QrCodeController {
    private final QrCodeService qrCodeService;
    private final AppUserRepository appUserRepository;
    private final CustomerAccountRepository accountRepository;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        CustomerAccount account = accountRepository.findByAppUserId(user.getId()).stream().findFirst().orElse(null);
        if (account != null) {
            model.addAttribute("accountNumber", account.getAccountNumber());
        }
        return "qrcode/index";
    }

    @PostMapping
    public String generate(@RequestParam String text, Model model) {
        byte[] qrImage = qrCodeService.generateQrCode(text, 300, 300);
        String base64Image = Base64.getEncoder().encodeToString(qrImage);
        model.addAttribute("qrCodeImage", "data:image/png;base64," + base64Image);
        model.addAttribute("accountNumber", text);
        return "qrcode/index";
    }
}
