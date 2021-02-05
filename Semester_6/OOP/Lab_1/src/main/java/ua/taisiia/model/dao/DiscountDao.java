package ua.taisiia.model.dao;

import ua.taisiia.model.entity.Discount;
import ua.taisiia.model.entity.DiscountType;
import ua.taisiia.model.entity.Tour;
import ua.taisiia.model.entity.TourType;

import java.util.List;
import java.util.Optional;

public interface DiscountDao extends Dao<Discount> {
    Optional<Discount> findByDiscountId(Long discountId);
    List<Discount> findAllDiscountsBiggerThanDiscountPercentage(float discountPercentage);
}
