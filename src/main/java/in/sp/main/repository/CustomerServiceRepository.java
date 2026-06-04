package in.sp.main.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.entity.CustomerService;

@Repository
public interface CustomerServiceRepository
        extends JpaRepository<CustomerService, Long> {

    List<CustomerService> findByCustomerId(Long customerId);

    Optional<CustomerService> findByStbNumber(String stbNumber);

    Optional<CustomerService> findByVcNumber(String vcNumber);

    List<CustomerService>
    findByEndDateBetweenAndStatus(
            LocalDate startDate,
            LocalDate endDate,
            String status
    );
}