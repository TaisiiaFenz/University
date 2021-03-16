package controller.command.config;

import model.entity.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
    private static final Map<Role, Set<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        Set<String> agentUrlPatterns = new HashSet<>();
        agentUrlPatterns.add("add-client");
        agentUrlPatterns.add("update-hot-tour");
        agentUrlPatterns.add("get-all-reservations");
        agentUrlPatterns.add("accept-reservation");
        agentUrlPatterns.add("cancel-reservation");

        Set<String> clientUrlPatterns = new HashSet<>();
        //clientUrlPatterns.add("get-tours");
        clientUrlPatterns.add("reserve-tour");
        clientUrlPatterns.add("get-my-reservations");

        mapConfig.put(Role.AGENT, agentUrlPatterns);
        mapConfig.put(Role.CLIENT, clientUrlPatterns);
    }

    public static boolean isSecured(String url) {
        return mapConfig.values()
                .stream()
                .flatMap(Set::stream)
                .anyMatch(pattern -> pattern.equals(url));
    }

    public static boolean isAccessAllowed(String url, Set<Role> roles) {
        return mapConfig.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(url))
                .map(Map.Entry::getKey)
                .anyMatch(roles::contains);
    }

}
