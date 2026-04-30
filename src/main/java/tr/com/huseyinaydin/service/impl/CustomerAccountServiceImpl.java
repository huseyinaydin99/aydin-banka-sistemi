package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.dto.CustomerAccountDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.mapper.CustomerAccountMapper;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.repository.CustomerAccountRepository;
import tr.com.huseyinaydin.service.CustomerAccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerAccountServiceImpl implements CustomerAccountService {
    private final CustomerAccountRepository customerAccountRepository;
    private final AppUserRepository appUserRepository;
    private final CustomerAccountMapper customerAccountMapper;

    @Override
    public void createAccount(CustomerAccountDto customerAccountDto) {
        AppUser user = appUserRepository.findById(customerAccountDto.getUserId()).orElse(null);
        CustomerAccount account = customerAccountMapper.toEntity(customerAccountDto);
        account.setAppUser(user);
        
        Random random = new Random();
        if (account.getAccountNumber() == null) {
            account.setAccountNumber(String.valueOf(random.nextInt(90000000) + 10000000));
        }
        if (account.getCvc() == null) {
            account.setCvc(random.nextInt(900) + 100);
        }
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }
        
        customerAccountRepository.save(account);
    }

    @Override
    public List<CustomerAccountDto> getCustomerAccountsList(Integer userId) {
        return customerAccountRepository.findByAppUserId(userId).stream()
                .map(customerAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerAccountDto> getCustomerUSDAccountsList(Integer userId) {
        return customerAccountRepository.findByAppUserId(userId).stream()
                .filter(x -> "USD".equalsIgnoreCase(x.getCurrency()))
                .map(customerAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerAccountDto> getCustomerEURAccountsList(Integer userId) {
        return customerAccountRepository.findByAppUserId(userId).stream()
                .filter(x -> "EUR".equalsIgnoreCase(x.getCurrency()))
                .map(customerAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerAccountDto> getCustomerTLAccountsList(Integer userId) {
        return customerAccountRepository.findByAppUserId(userId).stream()
                .filter(x -> "TL".equalsIgnoreCase(x.getCurrency()))
                .map(customerAccountMapper::toDto)
                .collect(Collectors.toList());
    }
}
