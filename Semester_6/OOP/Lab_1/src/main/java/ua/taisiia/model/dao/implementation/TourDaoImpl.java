package ua.taisiia.model.dao.implementation;

import ua.taisiia.model.dao.DaoFactory;
import ua.taisiia.model.dao.TourDao;
import ua.taisiia.model.entity.Tour;
import ua.taisiia.model.entity.TourType;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TourDaoImpl implements TourDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public TourDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Tour> findAllRegularClients(Long tourId) {
        return Optional.empty();
    }

    @Override
    public List<Tour> findByTourType(TourType tourType) {
        return null;
    }

    @Override
    public List<Tour> findByCountry(String country) {
        return null;
    }

    @Override
    public List<Tour> findAllLastMinuteTour(Boolean lastMinuteTour) {
        return null;
    }

    @Override
    public List<Tour> findAllToursInCostDiapazone(BigDecimal minCost, BigDecimal maxCost) {
        return null;
    }

    @Override
    public void create(Tour entity)
            //throws DataExistsException
    {

    }

    @Override
    public void update(Tour entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Tour findById(Long id) {
        return null;
    }

    @Override
    public List<Tour> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
