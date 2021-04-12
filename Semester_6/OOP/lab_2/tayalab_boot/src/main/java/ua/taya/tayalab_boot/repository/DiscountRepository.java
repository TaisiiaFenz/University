package ua.taya.tayalab_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taya.tayalab_boot.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
