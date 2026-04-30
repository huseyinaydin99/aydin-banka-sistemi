package tr.com.huseyinaydin.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ContactDto {
    private Integer id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private LocalDateTime date;
}
