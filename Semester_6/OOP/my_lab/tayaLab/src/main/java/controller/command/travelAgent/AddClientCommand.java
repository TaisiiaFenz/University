package controller.command.travelAgent;

import com.google.gson.*;
import controller.ReadData;
import controller.command.Command;
import controller.command.utility.CommandBCryptUtility;
import model.dto.SignUpDto;
import model.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class AddClientCommand implements Command {
    private final ClientService clientService;

    public AddClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        StringBuilder jb = ReadData.readData(request);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }
        }).create();

        SignUpDto signUpDto = gson.fromJson(jb.toString(), SignUpDto.class);
        signUpDto.setPassword(CommandBCryptUtility.encodePassword(signUpDto.getPassword()));
        return clientService.saveNewClient(signUpDto);
    }
}