package ua.taya.tayalab_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taya.tayalab_boot.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
