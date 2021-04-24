import dao.Connector;
import dao.CountryDaoImpl;
import models.Country;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {

    CountryDaoImpl countryDao = new CountryDaoImpl(Connector.getConnection());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int countryId = Integer.parseInt(req.getParameter("country_id"));
        String countryName = req.getParameter("country_name");
        String capital = req.getParameter("capital");
        countryDao.update(new Country(countryId, countryName, capital));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}