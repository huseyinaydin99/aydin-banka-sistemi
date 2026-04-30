package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String district;
    private String city;
    private String imageUrl;
    private Integer confirmCode;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<CustomerAccount> customerAccounts;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<CreditCard> creditCards;
}
