package tr.com.huseyinaydin.business.rules;

import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.exception.ValidationException;

import java.math.BigDecimal;

public class CustomerAccountProcessRules {

    public static void checkAccountsAreDifferent(Integer senderId, Integer receiverId) {
        if (senderId.equals(receiverId)) {
            throw new ValidationException("Kendi hesabınıza transfer yapamazsınız");
        }
    }

    public static void checkBalanceIsSufficient(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new ValidationException("Yetersiz bakiye");
        }
    }

    public static void checkAccountExists(CustomerAccount account, String message) {
        if (account == null) {
            throw new ValidationException(message);
        }
    }
}
