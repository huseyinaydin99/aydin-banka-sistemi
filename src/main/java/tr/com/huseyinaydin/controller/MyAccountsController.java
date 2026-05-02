package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.dto.AppUserUpdateDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.AppUserService;

@Controller
@RequestMapping("/my-accounts")
@RequiredArgsConstructor
public class MyAccountsController {
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    @GetMapping
    public String index(Model model, Authentication authentication) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        AppUserUpdateDto dto = new AppUserUpdateDto();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setCity(user.getCity());
        dto.setDistrict(user.getDistrict());
        dto.setImageUrl(user.getImageUrl());
        model.addAttribute("appUserUpdateDto", dto);
        return "my-accounts/index";
    }

    @PostMapping
    public String index(AppUserUpdateDto dto, Authentication authentication) {
        if (dto.getPassword() != null && dto.getPassword().equals(dto.getConfirmPassword())) {
            appUserService.updateProfile(authentication.getName(), dto);
            return "redirect:/login";
        }
        return "my-accounts/index";
    }
}
