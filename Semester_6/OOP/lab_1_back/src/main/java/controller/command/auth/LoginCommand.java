package controller.command.auth;

import com.google.gson.*;
import controller.command.MultipleMethodCommand;
import controller.command.utility.CommandBCryptUtility;
import model.dto.LoginDto;
import model.entity.Role;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Optional;

public class LoginCommand extends MultipleMethodCommand {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return null;
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

        Gson gson = new Gson();
        LoginDto loginDto = gson.fromJson(jb.toString(), LoginDto.class);
        Optional<User> user = userService.getUserByUsername(loginDto.getLogin());
        if (user.isEmpty() || !CommandBCryptUtility.isPasswordMatches(loginDto.getPassword(), user.get().getPassword())) {
            return "";
        } else {
            JwtManager jwtManager = new JwtManager();
            if (user.get().getAuthorities().contains(Role.CLIENT)) {
                return jwtManager.createToken("Bearer", "CLIENT", user.get().getId());
            } else return jwtManager.createToken("Bearer", "AGENT", user.get().getId()  );
        }

    }
}
