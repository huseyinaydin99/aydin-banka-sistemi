package tr.com.huseyinaydin.business.rules;

import tr.com.huseyinaydin.entity.Contact;
import tr.com.huseyinaydin.exception.ValidationException;

public class ContactRules {
    public static void checkContactExists(Contact contact) {
        if (contact == null) {
            throw new ValidationException("Mesaj bulunamadı");
        }
    }
}
