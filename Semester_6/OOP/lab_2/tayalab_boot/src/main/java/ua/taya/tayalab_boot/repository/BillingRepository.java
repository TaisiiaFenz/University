package ua.taya.tayalab_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taya.tayalab_boot.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
