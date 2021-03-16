package model.dao;


import model.entity.Client;
import model.entity.DiscountType;
import model.entity.TourType;

import java.util.List;
import java.util.Optional;

public interface ClientDao extends Dao<Client> {
    Optional<Client> findByUserId(Long userId);
}
