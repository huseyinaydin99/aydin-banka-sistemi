package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CustomerAccountDto;
import java.util.List;

public interface CustomerAccountService {
    void createAccount(CustomerAccountDto customerAccountDto);
    List<CustomerAccountDto> getCustomerAccountsList(Integer userId);
    List<CustomerAccountDto> getCustomerUSDAccountsList(Integer userId);
    List<CustomerAccountDto> getCustomerEURAccountsList(Integer userId);
    List<CustomerAccountDto> getCustomerTLAccountsList(Integer userId);
}
