import dao.CatalogDaoImpl;
import dao.Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import models.Catalog;

@WebServlet(urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    CatalogDaoImpl countryDao = new CatalogDaoImpl(Connector.getConnection());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("catalog", countryDao.findAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String catalog = req.getParameter("catalog");
        countryDao.save(new Catalog(name, catalog));
    }
}