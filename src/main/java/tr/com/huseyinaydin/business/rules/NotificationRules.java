package tr.com.huseyinaydin.business.rules;

import tr.com.huseyinaydin.entity.Notification;
import tr.com.huseyinaydin.exception.ValidationException;

public class NotificationRules {
    public static void checkNotificationExists(Notification notification) {
        if (notification == null) {
            throw new ValidationException("Bildirim bulunamadı");
        }
    }
}
