package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.NotificationDto;
import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAll();
    NotificationDto getById(Integer id);
    List<NotificationDto> getUnreadNotifications();
    void createNotification(NotificationDto notificationDto);
    void markAsRead(Integer id);
}
