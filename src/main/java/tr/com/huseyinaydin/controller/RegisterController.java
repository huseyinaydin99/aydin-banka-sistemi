package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tr.com.huseyinaydin.dto.AppUserRegisterDto;
import tr.com.huseyinaydin.service.AppUserService;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final AppUserService appUserService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("appUserRegisterDto", new AppUserRegisterDto());
        return "register/index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute AppUserRegisterDto appUserRegisterDto) {
        appUserService.register(appUserRegisterDto);
        return "redirect:/login";
    }
}
