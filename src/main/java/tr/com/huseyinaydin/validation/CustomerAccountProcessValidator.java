package tr.com.huseyinaydin.validation;

import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import tr.com.huseyinaydin.exception.ValidationException;
import java.math.BigDecimal;

public class CustomerAccountProcessValidator {
    public static void validate(CustomerAccountProcessDto dto) {
        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Transfer miktarı sıfırdan büyük olmalıdır");
        }
        if (dto.getSenderId() == null) {
            throw new ValidationException("Gönderen hesap bilgisi eksik");
        }
        if (dto.getReceiverId() == null && (dto.getReceiverAccountNumber() == null || dto.getReceiverAccountNumber().trim().isEmpty())) {
            throw new ValidationException("Alıcı hesap bilgisi eksik");
        }
    }
}
