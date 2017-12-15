package rnd.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.auth.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findOneByUsername(String username);
    User findByUsername(String username);

}
