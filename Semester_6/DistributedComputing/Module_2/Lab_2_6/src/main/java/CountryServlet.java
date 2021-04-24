import dao.Connector;
import dao.CountryDaoImpl;
import models.Country;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/country")
public class CountryServlet extends HttpServlet {

    CountryDaoImpl countryDao = new CountryDaoImpl(Connector.getConnection());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("Part 2");
        req.setAttribute("countries", countryDao.findAll());
        System.out.println(req);
        req.setAttribute("countryTitle", "Countries: ");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello");
        String countryName = req.getParameter("country_name");
        String capital = req.getParameter("capital");
        countryDao.save(new Country(countryName, capital));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}