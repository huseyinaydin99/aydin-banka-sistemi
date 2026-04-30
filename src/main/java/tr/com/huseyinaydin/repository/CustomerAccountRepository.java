package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.huseyinaydin.entity.CustomerAccount;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {
    List<CustomerAccount> findByAppUserId(Integer userId);
    Optional<CustomerAccount> findByAccountNumber(String accountNumber);
}
