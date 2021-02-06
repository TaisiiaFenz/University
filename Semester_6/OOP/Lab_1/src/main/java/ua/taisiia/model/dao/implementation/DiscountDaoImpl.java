package ua.taisiia.model.dao.implementation;

import ua.taisiia.model.dao.DaoFactory;
import ua.taisiia.model.dao.DiscountDao;
import ua.taisiia.model.entity.Discount;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DiscountDaoImpl implements DiscountDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public DiscountDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Discount> findByDiscountId(Long discountId) {
        return Optional.empty();
    }

    @Override
    public List<Discount> findAllDiscountsBiggerThanDiscountPercentage(float discountPercentage) {
        return null;
    }

    @Override
    public void create(Discount entity)
            //throws DataExistsException
    {

    }

    @Override
    public void update(Discount entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Discount findById(Long id) {
        return null;
    }

    @Override
    public List<Discount> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
