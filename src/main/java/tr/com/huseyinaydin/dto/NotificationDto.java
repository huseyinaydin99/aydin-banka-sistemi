package tr.com.huseyinaydin.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Boolean isRead;
}
