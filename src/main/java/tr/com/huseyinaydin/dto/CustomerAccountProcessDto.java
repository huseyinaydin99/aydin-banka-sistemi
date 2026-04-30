package tr.com.huseyinaydin.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CustomerAccountProcessDto {
    private Integer id;
    private String processType;
    private BigDecimal amount;
    private LocalDateTime processDate;
    private String description;
    private Integer senderId;
    private Integer receiverId;
    private String receiverAccountNumber;
}
