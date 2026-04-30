package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.business.rules.CustomerAccountProcessRules;
import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.entity.CustomerAccountProcess;
import tr.com.huseyinaydin.mapper.CustomerAccountProcessMapper;
import tr.com.huseyinaydin.repository.CustomerAccountProcessRepository;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.service.CustomerAccountProcessService;
import tr.com.huseyinaydin.validation.CustomerAccountProcessValidator;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerAccountProcessServiceImpl implements CustomerAccountProcessService {
    private final CustomerAccountProcessRepository processRepository;
    private final CustomerAccountRepository accountRepository;
    private final CustomerAccountProcessMapper processMapper;

    @Override
    @Transactional
    public void sendMoney(CustomerAccountProcessDto processDto) {
        CustomerAccountProcessValidator.validate(processDto);

        CustomerAccount sender = accountRepository.findById(processDto.getSenderId()).orElse(null);
        CustomerAccountProcessRules.checkAccountExists(sender, "Gönderen hesap bulunamadı");

        CustomerAccount receiver;
        if (processDto.getReceiverId() != null) {
            receiver = accountRepository.findById(processDto.getReceiverId()).orElse(null);
            CustomerAccountProcessRules.checkAccountExists(receiver, "Alıcı hesap bulunamadı");
        } else if (processDto.getReceiverAccountNumber() != null) {
            receiver = accountRepository.findByAccountNumber(processDto.getReceiverAccountNumber()).orElse(null);
            CustomerAccountProcessRules.checkAccountExists(receiver, "Alıcı hesap numarası bulunamadı");
        } else {
            CustomerAccountProcessRules.checkAccountExists(null, "Alıcı bilgisi eksik");
            return;
        }

        CustomerAccountProcessRules.checkAccountsAreDifferent(sender.getId(), receiver.getId());
        CustomerAccountProcessRules.checkBalanceIsSufficient(sender.getBalance(), processDto.getAmount());

        sender.setBalance(sender.getBalance().subtract(processDto.getAmount()));
        receiver.setBalance(receiver.getBalance().add(processDto.getAmount()));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        CustomerAccountProcess process = processMapper.toEntity(processDto);
        process.setSenderCustomer(sender);
        process.setReceiverCustomer(receiver);
        process.setProcessDate(LocalDateTime.now());
        process.setProcessType("Havale/EFT");
        processRepository.save(process);
    }
}
