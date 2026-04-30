package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.dto.ContactDto;
import tr.com.huseyinaydin.service.ContactService;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public String index() {
        return "contact/index";
    }

    @PostMapping
    public String index(ContactDto contactDto) {
        contactService.sendMessage(contactDto);
        return "redirect:/";
    }
}
