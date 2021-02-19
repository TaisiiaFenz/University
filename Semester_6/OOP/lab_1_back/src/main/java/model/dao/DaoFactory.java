package model.dao;


import model.dao.implementation.DaoFactoryImpl;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ClientDao createClientDao();
    public abstract UserDao createUserDao();
    public abstract UserRoleDao createUserRoleDao();
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
