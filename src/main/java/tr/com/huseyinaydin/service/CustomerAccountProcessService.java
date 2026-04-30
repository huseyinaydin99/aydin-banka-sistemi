package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import java.util.List;

public interface CustomerAccountProcessService {
    void sendMoney(CustomerAccountProcessDto processDto);
    List<CustomerAccountProcessDto> getMyLastProcess(Integer accountId);
}
