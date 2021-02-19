package model.service;

import com.google.gson.Gson;
import model.dao.ClientDao;
import model.dao.UserDao;
import model.dao.UserRoleDao;
import model.dao.implementation.TransactionalDaoFactory;
import model.dto.ResponseDto;
import model.dto.SignUpDto;
import model.entity.Client;
import model.entity.Role;
import model.entity.User;
import model.entity.UserRole;

import java.sql.SQLException;
import java.util.Set;

public class ClientService {
    private TransactionalDaoFactory daoFactory;

    public ClientService() {
        daoFactory = new TransactionalDaoFactory();
    }

    public String saveNewClient(SignUpDto dto) throws Exception {
        Gson gson = new Gson();
        UserDao userDao = daoFactory.createUserDao();
        UserRoleDao userRoleDao = daoFactory.createUserRoleDao();

        try (ClientDao clientDao = daoFactory.createClientDao()){
            daoFactory.beginTransaction();
            User user = formUser(dto);
            userDao.create(user);

            UserRole userRole = new UserRole();
            Set<Role> roles = user.getAuthorities();
            for (Role role : roles) {
                userRole.setUser(user);
                userRole.setRole(role);
                userRoleDao.create(userRole);
            }

            Client client = formClient(dto);
            client.setUser(user);
            clientDao.create(client);

            daoFactory.commitTransaction();
            return gson.toJson(new ResponseDto(true, "Client is successfully saved"));
        } catch (Exception e){
            e.printStackTrace();
            try {
                daoFactory.rollbackTransaction();
                return gson.toJson(new ResponseDto(false, "Client is not successfully saved"));
            } catch (SQLException er){
                return gson.toJson(new ResponseDto(false, "Something went wrong with transaction"));
            }
        } finally {
            daoFactory.close();
        }
    }

    private Client formClient(SignUpDto dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setMiddleName(dto.getMiddleName());
        client.setLastName(dto.getLastName());
        client.setPassport(dto.getPassport());
        client.setBirthday(dto.getBirthday());
        client.setRegularClients(false);
        return client;
    }

    private User formUser(SignUpDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setAuthorities(Set.of(Role.CLIENT));
        return user;
    }
}
