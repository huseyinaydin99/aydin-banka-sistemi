package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.repository.AppUserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/confirm-mail")
@RequiredArgsConstructor
public class ConfirmMailController {
    private final AppUserRepository appUserRepository;

    @GetMapping
    public String index() {
        return "confirm-mail/index";
    }

    @PostMapping
    public String confirm(@RequestParam String username, @RequestParam Integer code) {
        Optional<AppUser> userOptional = appUserRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            if (user.getConfirmCode().equals(code)) {
                return "redirect:/login";
            }
        }
        return "redirect:/confirm-mail?error";
    }
}
