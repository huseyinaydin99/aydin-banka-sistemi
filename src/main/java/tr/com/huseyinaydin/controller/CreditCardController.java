package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.dto.CreditCardDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.CreditCardService;

import java.util.List;

@Controller
@RequestMapping("/credit-card")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;
    private final AppUserRepository appUserRepository;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        List<CreditCardDto> cards = creditCardService.getCardsByUserId(user.getId());
        model.addAttribute("cards", cards);
        return "credit-card/index";
    }

    @GetMapping("/apply")
    public String applyPage(Model model) {
        model.addAttribute("creditCardDto", new CreditCardDto());
        return "credit-card/apply";
    }

    @PostMapping("/apply")
    public String apply(CreditCardDto creditCardDto, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        creditCardDto.setUserId(user.getId());
        creditCardDto.setCardHolderName(user.getName() + " " + user.getSurname());
        creditCardService.applyForCard(creditCardDto);
        return "redirect:/credit-card";
    }
}
