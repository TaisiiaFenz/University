package ua.taya.tayalab_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taya.tayalab_boot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
