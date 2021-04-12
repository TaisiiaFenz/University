package ua.taya.tayalab_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taya.tayalab_boot.entity.Client;
import ua.taya.tayalab_boot.entity.User;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
}
