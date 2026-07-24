package customer.repository;

import customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
