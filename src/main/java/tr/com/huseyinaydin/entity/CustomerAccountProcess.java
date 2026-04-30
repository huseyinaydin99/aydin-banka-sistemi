package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_account_processes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String processType;
    private BigDecimal amount;
    private LocalDateTime processDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private CustomerAccount senderCustomer;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private CustomerAccount receiverCustomer;
}
