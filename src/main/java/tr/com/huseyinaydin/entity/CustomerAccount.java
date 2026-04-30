package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customer_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountNumber;
    private Integer cvc;
    private String expirationDate;
    private String currency;
    private BigDecimal balance;
    private String bankBranch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @OneToMany(mappedBy = "senderCustomer")
    private List<CustomerAccountProcess> sentProcesses;

    @OneToMany(mappedBy = "receiverCustomer")
    private List<CustomerAccountProcess> receivedProcesses;
}
