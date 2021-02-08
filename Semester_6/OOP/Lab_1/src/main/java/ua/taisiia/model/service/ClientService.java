package ua.taisiia.model.service;

import com.google.gson.Gson;
import ua.taisiia.model.dao.ClientDao;
import ua.taisiia.model.dao.DaoFactory;
import ua.taisiia.model.dao.implementation.TransactionalDaoFactory;
import ua.taisiia.model.dto.ClientDto;
import ua.taisiia.model.dto.ResponseDto;
import ua.taisiia.model.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public ClientService() {
        daoFactory = new TransactionalDaoFactory();
    }

    public String saveNewClient(ClientDto dto) throws Exception {
        Gson gson = new Gson();
        try (ClientDao clientDao = daoFactory.createClientDao()){
            Client client = new Client();
            client.setFirstName(dto.getFirstName());
            client.setMiddleName(dto.getMiddleName());
            client.setLastName(dto.getLastName());
            client.setPassport(dto.getPassport());
            client.setBirthday(dto.getBirthday());
            client.setRegularClients(false);
            clientDao.create(client);
            return gson.toJson(new ResponseDto(true, "Client is successfully saved"));
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new ResponseDto(false, "Client is not successfully saved"));
        }
    }

    public Optional<Client> getClientByName(String lastName, String firstName) {
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            return clientDao.findByClientName(lastName, firstName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Client> getAllReservations(Boolean regularClients) {
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            return clientDao.findAllRegularClients(regularClients);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
