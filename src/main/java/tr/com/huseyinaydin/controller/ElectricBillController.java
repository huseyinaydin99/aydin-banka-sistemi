package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.com.huseyinaydin.dto.ElectricBillDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.service.ElectricBillService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/electric-bill")
@RequiredArgsConstructor
@Slf4j
public class ElectricBillController {
    private final ElectricBillService billService;
    private final CustomerAccountRepository accountRepository;
    private final AppUserRepository appUserRepository;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        AppUser user = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        List<CustomerAccount> accounts = accountRepository.findByAppUserId(user.getId());
        model.addAttribute("accounts", accounts);
        return "electric-bill/index";
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> queryDebt(@RequestParam String billNumber) {
        log.info("Fatura sorgulanıyor: {}", billNumber);
        Map<String, Object> response = new HashMap<>();
        try {
            ElectricBillDto bill = billService.getBillDetails(billNumber != null ? billNumber.trim() : "");
            log.info("Fatura bulundu: {}, Tutar: {}", bill.getBillNumber(), bill.getAmount());
            response.put("success", true);
            response.put("debtAmount", bill.getAmount());
            response.put("customerName", bill.getCustomerName());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Fatura bulunamadı veya borç yok.");
        }
        return response;
    }

    @PostMapping("/pay")
    @ResponseBody
    public Map<String, Object> payBill(@RequestParam String billNumber, @RequestParam Integer accountId) {
        Map<String, Object> response = new HashMap<>();
        try {
            billService.payBill(billNumber, accountId);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
