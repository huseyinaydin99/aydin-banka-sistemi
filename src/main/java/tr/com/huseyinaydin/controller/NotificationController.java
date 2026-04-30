package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.service.NotificationService;

@Controller
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("notifications", notificationService.getAll());
        return "notification/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("notification", notificationService.getById(id));
        return "notification/detail";
    }
}
