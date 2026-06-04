package in.sp.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.entity.Recharge;

@Repository
public interface RechargeRepository
        extends JpaRepository<Recharge, Long> {

    List<Recharge> findByCustomerId(Long customerId);

    List<Recharge> findByServiceId(Long serviceId);
}