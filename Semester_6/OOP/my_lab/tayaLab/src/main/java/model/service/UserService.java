package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.Optional;

public class UserService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> getUserByUsername(String username) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
