package in.sp.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.sp.main.entity.Customer;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {
    @Query("SELECT c.name FROM Customer c")
    List<String> findAllCustomerNames();

    Optional<Customer> findByMobile(String mobile);

    Optional<Customer> findByEmail(String email);

    boolean existsByMobile(String mobile);

    boolean existsByEmail(String email);
}