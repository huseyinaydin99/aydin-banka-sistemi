package tr.com.huseyinaydin.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CustomerAccountDto {
    private Integer id;
    private String accountNumber;
    private Integer cvc;
    private String expirationDate;
    private String currency;
    private BigDecimal balance;
    private String bankBranch;
    private Integer userId;
}
