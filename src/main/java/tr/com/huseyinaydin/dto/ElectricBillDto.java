package tr.com.huseyinaydin.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ElectricBillDto {
    private Integer id;
    private String billNumber;
    private String customerName;
    private BigDecimal amount;
    private Boolean isPaid;
}