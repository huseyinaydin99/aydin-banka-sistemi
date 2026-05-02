package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import tr.com.huseyinaydin.dto.NotificationDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.service.CustomerAccountProcessService;
import tr.com.huseyinaydin.service.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SendMoneyController {
    private final CustomerAccountProcessService processService;
    private final CustomerAccountRepository accountRepository;
    private final AppUserRepository appUserRepository;
    private final NotificationService notificationService;

    @GetMapping("/send-money")
    public String index(@RequestParam(name = "currency", defaultValue = "TRY") String currency,
                        @RequestParam(name = "success", required = false) String success,
                        @RequestParam(name = "error", required = false) String error,
                        Authentication authentication, Model model) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        List<CustomerAccount> accounts = accountRepository.findByAppUserId(user.getId()).stream()
                .filter(a -> a.getCurrency().equalsIgnoreCase(currency))
                .collect(Collectors.toList());

        model.addAttribute("currency", currency);
        model.addAttribute("accounts", accounts);
        model.addAttribute("processDto", new CustomerAccountProcessDto());
        
        if (success != null) {
            model.addAttribute("successMessage", success);
        }
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        
        return "send-money/index";
    }

    @PostMapping("/send-money")
    public String sendMoney(CustomerAccountProcessDto processDto,
                            @RequestParam(name = "currency", defaultValue = "TRY") String currency,
                            Authentication authentication,
                            RedirectAttributes redirectAttributes) {
        try {
            processService.sendMoney(processDto);
            
            // Bildirim oluştur
            NotificationDto notification = new NotificationDto();
            notification.setTitle("Para Transferi Başarılı");
            notification.setContent(processDto.getAmount() + " " + currency + " tutarındaki transfer işleminiz başarıyla gerçekleşti.");
            notificationService.createNotification(notification);
            
            redirectAttributes.addAttribute("currency", currency);
            redirectAttributes.addAttribute("success", "Para transferi başarıyla gerçekleşti.");
            return "redirect:/send-money";
        } catch (Exception e) {
            // Hata bildirimi oluştur
            NotificationDto notification = new NotificationDto();
            notification.setTitle("Para Transferi Başarısız");
            notification.setContent("Transfer işlemi gerçekleştirilemedi. Hata: " + e.getMessage());
            notificationService.createNotification(notification);

            redirectAttributes.addAttribute("currency", currency);
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/send-money";
        }
    }
}
