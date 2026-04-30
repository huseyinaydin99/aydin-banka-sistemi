package tr.com.huseyinaydin.business.rules;

import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.exception.ValidationException;

public class CreditCardRules {

    public static void checkUserExists(AppUser user) {
        if (user == null) {
            throw new ValidationException("Kullanıcı bulunamadı");
        }
    }
}
