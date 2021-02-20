package controller.command.travelAgent;

import com.google.gson.*;
import controller.command.MultipleMethodCommand;
import controller.command.utility.CommandBCryptUtility;
import model.dto.SignUpDto;
import model.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class AddClientCommand extends MultipleMethodCommand {
    private final ClientService clientService;

    public AddClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return "index.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request) throws Exception {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//                Instant instant = Instant.parse(json.toString());
                return LocalDate.parse(json.getAsString());
            }
        }).create();
        SignUpDto signUpDto = gson.fromJson(jb.toString(), SignUpDto.class);
        signUpDto.setPassword(CommandBCryptUtility.encodePassword(signUpDto.getPassword()));
        return clientService.saveNewClient(signUpDto);
    }
}