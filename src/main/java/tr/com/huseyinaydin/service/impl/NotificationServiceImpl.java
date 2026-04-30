package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.business.rules.NotificationRules;
import tr.com.huseyinaydin.dto.NotificationDto;
import tr.com.huseyinaydin.entity.Notification;
import tr.com.huseyinaydin.mapper.NotificationMapper;
import tr.com.huseyinaydin.repository.NotificationRepository;
import tr.com.huseyinaydin.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationDto> getAll() {
        return notificationMapper.toDtoList(notificationRepository.findAll());
    }

    @Override
    public NotificationDto getById(Integer id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        NotificationRules.checkNotificationExists(notification);
        return notificationMapper.toDto(notification);
    }

    @Override
    public List<NotificationDto> getUnreadNotifications() {
        return notificationMapper.toDtoList(notificationRepository.findByIsReadFalse());
    }

    @Override
    public void createNotification(NotificationDto notificationDto) {
        Notification notification = notificationMapper.toEntity(notificationDto);
        notification.setDate(LocalDateTime.now());
        notification.setIsRead(false);
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void markAsRead(Integer id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        NotificationRules.checkNotificationExists(notification);
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
}
