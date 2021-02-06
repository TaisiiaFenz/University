package ua.taisiia.model.dao;

import ua.taisiia.model.dao.implementation.DaoFactoryImpl;
import ua.taisiia.model.entity.Discount;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ClientDao createClientDao();

    public abstract DiscountDao createDiscountDao();

    public abstract ReservationDao createReservationDao();

    public abstract TourDao createTourDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DaoFactoryImpl();
                }
            }
        }
        return daoFactory;
    }
}
