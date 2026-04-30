package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import tr.com.huseyinaydin.dto.NotificationDto;
import tr.com.huseyinaydin.entity.Notification;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toEntity(NotificationDto dto);
    NotificationDto toDto(Notification entity);
    List<NotificationDto> toDtoList(List<Notification> entities);
}
