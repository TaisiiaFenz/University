package ua.taisiia.controller.command.travelAgent;

import ua.taisiia.controller.command.MultipleMethodCommand;
import ua.taisiia.model.dto.ClientDto;
import ua.taisiia.model.entity.Client;
import ua.taisiia.model.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class AddClientCommand extends MultipleMethodCommand {
    private final ClientService clientService;

    public AddClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected String executePost(HttpServletRequest request) throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(request.getParameter("firstName"));
        clientDto.setMiddleName(request.getParameter("middleName"));
        clientDto.setLastName(request.getParameter("lastName"));
        clientDto.setBirthday(LocalDate.parse(request.getParameter("birthday")));
        clientDto.setPassport(request.getParameter("passport"));
        return clientService.saveNewClient(clientDto);
    }
}
