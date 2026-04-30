package tr.com.huseyinaydin.business.rules;

import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.exception.ValidationException;

public class AppUserRules {
    public static void checkUserExists(AppUser user) {
        if (user == null) {
            throw new ValidationException("Kullanıcı bulunamadı");
        }
    }

    public static void checkEmailIsUnique(boolean isExists) {
        if (isExists) {
            throw new ValidationException("Bu e-posta adresi zaten kullanımda");
        }
    }
}
