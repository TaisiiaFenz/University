package ua.taisiia.model.dao;

import ua.taisiia.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface ClientDao extends Dao<Client> {
    Optional<Client> findByClientName(String lastName, String firstName);
    List<Client> findAllRegularClients(Boolean regularClients);
    //all clients with such sale
    List<Client> findClientsByDiscountType(DiscountType discountType);
    List<Client> findClientsByTourType(TourType tourType);
}
