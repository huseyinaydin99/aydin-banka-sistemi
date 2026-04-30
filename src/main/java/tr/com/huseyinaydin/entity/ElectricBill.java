package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "electric_bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectricBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String billNumber;
    private String customerName;
    private BigDecimal amount;
    private Boolean isPaid;
}
