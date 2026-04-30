package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;

public interface CustomerAccountProcessService {
    void sendMoney(CustomerAccountProcessDto processDto);
}
