package ua.taisiia.model.service;

import ua.taisiia.model.dao.ClientDao;
import ua.taisiia.model.dao.implementation.TransactionalDaoFactory;
import ua.taisiia.model.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private TransactionalDaoFactory transactionalDaoFactory;

    public ClientService() {
        transactionalDaoFactory = new TransactionalDaoFactory();
    }

    public void saveNewClient() throws Exception {

    }

    public Optional<Client> getClientByName(String lastName, String firstName) {
        try (ClientDao clientDao = transactionalDaoFactory.createClientDao()) {
            return clientDao.findByClientName(lastName, firstName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Client> getAllReservations(Boolean regularClients) {
        try (ClientDao clientDao = transactionalDaoFactory.createClientDao()) {
            return clientDao.findAllRegularClients(regularClients);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
