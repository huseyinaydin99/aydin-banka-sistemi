package tr.com.huseyinaydin.dto;

import lombok.Data;

@Data
public class CreditCardDto {
    private Integer id;
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvc;
    private Integer userId;
}