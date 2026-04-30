package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.business.rules.CreditCardRules;
import tr.com.huseyinaydin.dto.CreditCardDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.entity.CreditCard;
import tr.com.huseyinaydin.mapper.CreditCardMapper;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.repository.CreditCardRepository;
import tr.com.huseyinaydin.service.CreditCardService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final AppUserRepository appUserRepository;
    private final CreditCardMapper creditCardMapper;

    @Override
    public List<CreditCardDto> getCardsByUserId(Integer userId) {
        return creditCardRepository.findByAppUserId(userId).stream()
                .map(creditCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void applyForCard(CreditCardDto creditCardDto) {
        AppUser user = appUserRepository.findById(creditCardDto.getUserId()).orElse(null);
        CreditCardRules.checkUserExists(user);

        CreditCard card = creditCardMapper.toEntity(creditCardDto);
        card.setAppUser(user);
        
        if (card.getCardNumber() == null || card.getCardNumber().isEmpty()) {
            card.setCardNumber(generateRandomCardNumber());
        }
        if (card.getCvc() == null || card.getCvc().isEmpty()) {
            card.setCvc(String.valueOf(new Random().nextInt(900) + 100));
        }
        if (card.getExpirationDate() == null || card.getExpirationDate().isEmpty()) {
            card.setExpirationDate("12/27");
        }

        creditCardRepository.save(card);
    }

    private String generateRandomCardNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(9000) + 1000);
            if (i < 3) sb.append(" ");
        }
        return sb.toString();
    }
}