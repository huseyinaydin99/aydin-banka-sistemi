package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.com.huseyinaydin.dto.CreditCardDto;
import tr.com.huseyinaydin.dto.NotificationDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.CreditCardService;
import tr.com.huseyinaydin.service.NotificationService;

import java.util.List;

@Controller
@RequestMapping("/credit-card")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;
    private final AppUserRepository appUserRepository;
    private final NotificationService notificationService;

    @GetMapping
    public String index(@RequestParam(name = "success", required = false) String success,
                        @RequestParam(name = "error", required = false) String error,
                        Authentication authentication, Model model) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        List<CreditCardDto> cards = creditCardService.getCardsByUserId(user.getId());
        model.addAttribute("cards", cards);
        
        if (success != null) {
            model.addAttribute("successMessage", success);
        }
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        
        return "credit-card/index";
    }

    @GetMapping("/apply")
    public String applyPage(Model model) {
        model.addAttribute("creditCardDto", new CreditCardDto());
        return "credit-card/apply";
    }

    @PostMapping("/apply")
    public String apply(CreditCardDto creditCardDto, Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
            creditCardDto.setUserId(user.getId());
            creditCardDto.setCardHolderName(user.getName() + " " + user.getSurname());
            creditCardService.applyForCard(creditCardDto);
            
            // Bildirim
            NotificationDto notification = new NotificationDto();
            notification.setTitle("Kredi Kartı Başvurusu Alındı");
            notification.setContent("Yeni kredi kartı başvurunuz başarıyla alındı.");
            notificationService.createNotification(notification);
            
            redirectAttributes.addAttribute("success", "Kredi kartı başvurunuz başarıyla alındı.");
            return "redirect:/credit-card";
        } catch (Exception e) {
            // Hata bildirimi
            NotificationDto notification = new NotificationDto();
            notification.setTitle("Kredi Kartı Başvurusu Başarısız");
            notification.setContent("Kredi kartı başvurusu sırasında bir hata oluştu: " + e.getMessage());
            notificationService.createNotification(notification);

            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/credit-card";
        }
    }
}
