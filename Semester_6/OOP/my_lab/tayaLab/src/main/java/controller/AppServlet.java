package controller;

import controller.command.Command;
import controller.command.auth.LoginCommand;
import controller.command.client.GetMyReservedToursCommand;
import controller.command.client.ReserveTourCommand;
import controller.command.travelAgent.*;
import model.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet
public class AppServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private ClientService clientService = new ClientService();
    private UserService userService = new UserService();
    private TourService tourService = new TourService();
    private ReservationService reservationService = new ReservationService();
    private DiscountService discountService = new DiscountService();

    public void init(ServletConfig servletConfig) {
        commands.put("add-client", new AddClientCommand(clientService));
        commands.put("login", new LoginCommand(userService));
        commands.put("get-tours", new GetToursCommand(tourService));
        commands.put("update-hot-tour", new UpdateHotTour(tourService));
        commands.put("reserve-tour", new ReserveTourCommand(reservationService));
        commands.put("get-all-reservations", new GetAllReservedTours(reservationService));
        commands.put("cancel-reservation", new NotAcceptReservationCommand(reservationService));
        commands.put("accept-reservation", new AcceptReservationCommand(reservationService));
        commands.put("get-all-discounts", new GetAllDiscounts(discountService));
        commands.put("get-my-reservations", new GetMyReservedToursCommand(reservationService));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI();
        path = path.replace("/", "");
        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");


        String page = command.execute(request);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(page);
        out.flush();
    }
}
