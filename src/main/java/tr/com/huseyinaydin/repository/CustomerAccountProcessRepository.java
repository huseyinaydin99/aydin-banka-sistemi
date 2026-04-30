package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.huseyinaydin.entity.CustomerAccountProcess;

import java.util.List;

@Repository
public interface CustomerAccountProcessRepository extends JpaRepository<CustomerAccountProcess, Integer> {
    List<CustomerAccountProcess> findBySenderCustomerId(Integer customerAccountId);
    List<CustomerAccountProcess> findByReceiverCustomerId(Integer customerAccountId);
}
