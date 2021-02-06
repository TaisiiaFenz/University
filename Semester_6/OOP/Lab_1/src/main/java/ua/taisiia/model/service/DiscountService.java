package ua.taisiia.model.service;

import ua.taisiia.model.dao.ClientDao;
import ua.taisiia.model.dao.DaoFactory;
import ua.taisiia.model.dao.DiscountDao;
import ua.taisiia.model.entity.Client;
import ua.taisiia.model.entity.Discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscountService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveDiscount() {

    }

//    public Optional<Discount> getClientByName(Long discountId) {
//        try (DiscountDao discountDao = DaoFactory.createDiscountDao()) {
//            return discountDao.findByDiscountId(discountId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    public List<Client> getAllReservations(Boolean regularClients) {
//        try (ClientDao clientDao = DaoFactory.createClientDao()) {
//            return clientDao.findAllRegularClients(regularClients);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
}
