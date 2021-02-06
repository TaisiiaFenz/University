package ua.taisiia.model.dao.implementation;

import ua.taisiia.model.dao.ClientDao;
import ua.taisiia.model.dao.DaoFactory;
import ua.taisiia.model.entity.Client;
import ua.taisiia.model.entity.DiscountType;
import ua.taisiia.model.entity.TourType;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ClientDaoImpl implements ClientDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    //private static final Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class);

    public ClientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Client> findByClientName(String lastName, String firstName) {
        return Optional.empty();
    }

    @Override
    public List<Client> findAllRegularClients(Boolean regularClients) {
        return null;
    }

    @Override
    public List<Client> findClientsByDiscountType(DiscountType discountType) {
        return null;
    }

    @Override
    public List<Client> findClientsByTourType(TourType tourType) {
        return null;
    }

    @Override
    public void create(Client entity)
            //throws DataExistsException
    {

    }

    @Override
    public void update(Client entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
