package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.entity.CustomerAccountProcess;
import tr.com.huseyinaydin.exception.ValidationException;
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

        CustomerAccount sender = accountRepository.findById(processDto.getSenderId())
                .orElseThrow(() -> new ValidationException("Gönderen hesap bulunamadı"));

        CustomerAccount receiver = accountRepository.findById(processDto.getReceiverId())
                .orElseThrow(() -> new ValidationException("Alıcı hesap bulunamadı"));

        if (sender.getBalance().compareTo(processDto.getAmount()) < 0) {
            throw new ValidationException("Yetersiz bakiye");
        }

        sender.setBalance(sender.getBalance().subtract(processDto.getAmount()));
        receiver.setBalance(receiver.getBalance().add(processDto.getAmount()));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        CustomerAccountProcess process = processMapper.toEntity(processDto);
        process.setProcessDate(LocalDateTime.now());
        process.setProcessType("Havale/EFT");
        processRepository.save(process);
    }
}
