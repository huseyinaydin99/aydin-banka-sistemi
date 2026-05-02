package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import tr.com.huseyinaydin.dto.NotificationDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.NotificationService;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final AppUserRepository appUserRepository;
    private final NotificationService notificationService;

    @ModelAttribute("loginUserName")
    public String loginUserName(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
            if (user != null) {
                return user.getName() + " " + user.getSurname();
            }
        }
        return "Misafir";
    }

    @ModelAttribute("loginUserImageUrl")
    public String loginUserImageUrl(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            AppUser user = appUserRepository.findByUsername(authentication.getName()).orElse(null);
            if (user != null && user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                return user.getImageUrl();
            }
        }
        return "/user.png";
    }

    @ModelAttribute("unreadNotifications")
    public List<NotificationDto> unreadNotifications(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            try {
                return notificationService.getUnreadNotifications();
            } catch (Exception e) {
                System.err.println("Bildirimler alınamadı: " + e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Banka Sistemi";
    }
}
