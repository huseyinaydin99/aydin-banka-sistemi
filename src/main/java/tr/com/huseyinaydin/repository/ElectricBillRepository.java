package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.huseyinaydin.entity.ElectricBill;

import java.util.Optional;

@Repository
public interface ElectricBillRepository extends JpaRepository<ElectricBill, Integer> {
    Optional<ElectricBill> findByBillNumber(String billNumber);
}
