package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.business.rules.ElectricBillRules;
import tr.com.huseyinaydin.dto.ElectricBillDto;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.entity.ElectricBill;
import tr.com.huseyinaydin.mapper.ElectricBillMapper;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.repository.ElectricBillRepository;
import tr.com.huseyinaydin.service.ElectricBillService;

@Service
@RequiredArgsConstructor
public class ElectricBillServiceImpl implements ElectricBillService {
    private final ElectricBillRepository billRepository;
    private final CustomerAccountRepository accountRepository;
    private final ElectricBillMapper billMapper;

    @Override
    public ElectricBillDto getBillDetails(String billNumber) {
        ElectricBill bill = billRepository.findByBillNumber(billNumber).orElse(null);
        ElectricBillRules.checkBillExists(bill);
        return billMapper.toDto(bill);
    }

    @Override
    @Transactional
    public void payBill(String billNumber, Integer accountId) {
        ElectricBill bill = billRepository.findByBillNumber(billNumber).orElse(null);
        ElectricBillRules.checkBillExists(bill);
        ElectricBillRules.checkBillIsNotPaid(bill);

        CustomerAccount account = accountRepository.findById(accountId).orElse(null);
        ElectricBillRules.checkAccountExists(account);
        ElectricBillRules.checkBalanceIsSufficient(account.getBalance(), bill.getAmount());

        account.setBalance(account.getBalance().subtract(bill.getAmount()));
        bill.setIsPaid(true);

        accountRepository.save(account);
        billRepository.save(bill);
    }
}