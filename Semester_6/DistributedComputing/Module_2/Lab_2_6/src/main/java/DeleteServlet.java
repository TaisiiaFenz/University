import dao.CityDaoImpl;
import dao.Connector;
import dao.CountryDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    CityDaoImpl cityDao = new CityDaoImpl(Connector.getConnection());
    CountryDaoImpl countryDao = new CountryDaoImpl(Connector.getConnection());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Try delete");
        String city = Optional.ofNullable(req.getParameter("id")).orElse("empty");
        String country = Optional.ofNullable(req.getParameter("countryId")).orElse("empty");
        System.out.println(city + " " + country);
        if (!city.equals("empty")){
            cityDao.deleteById(Integer.parseInt(city));
        } else {
            countryDao.deleteById(Integer.parseInt(country));
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}