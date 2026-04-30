package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CreditCardDto;
import java.util.List;

public interface CreditCardService {
    List<CreditCardDto> getCardsByUserId(Integer userId);
    void applyForCard(CreditCardDto creditCardDto);
}