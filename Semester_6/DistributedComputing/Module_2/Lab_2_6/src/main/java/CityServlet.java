import dao.CityDaoImpl;
import dao.Connector;
import models.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/city")
public class CityServlet extends HttpServlet {
    CityDaoImpl cityDao = new CityDaoImpl(Connector.getConnection());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cities", cityDao.findAll());
        req.setAttribute("cityTitle", "Cities:");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityName = req.getParameter("city_name");
        int populationInThousands = Integer.parseInt(req.getParameter("population_in_thousands"));
        int countryId = Integer.parseInt(req.getParameter("country_id"));
        cityDao.save(new City(cityName, populationInThousands, countryId));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}