package tr.com.huseyinaydin.validation;

import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import tr.com.huseyinaydin.exception.ValidationException;
import java.math.BigDecimal;

public class CustomerAccountProcessValidator {
    public static void validate(CustomerAccountProcessDto dto) {
        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Transfer miktarı sıfırdan büyük olmalıdır");
        }
        if (dto.getReceiverId() == null) {
            throw new ValidationException("Alıcı hesap seçilmelidir");
        }
        if (dto.getSenderId() != null && dto.getSenderId().equals(dto.getReceiverId())) {
            throw new ValidationException("Kendi hesabınıza transfer yapamazsınız");
        }
    }
}
