package customer.repository;

import customer.entity.CustomerVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerVerificationRepository extends JpaRepository<CustomerVerificationEntity,Integer> {
}
