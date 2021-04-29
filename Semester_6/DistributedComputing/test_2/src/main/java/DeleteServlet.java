import dao.CatalogDaoImpl;
import dao.Connector;
import dao.FileDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    CatalogDaoImpl catalogDao = new CatalogDaoImpl(Connector.getConnection());
    FileDaoImpl fileDao = new FileDaoImpl(Connector.getConnection());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String catalog = Optional.ofNullable(req.getParameter("id")).orElse("empty");
        String file = Optional.ofNullable(req.getParameter("id")).orElse("empty");
        if (!catalog.equals("empty")){
            catalogDao.deleteById(Integer.parseInt(catalog));
        } else {
            fileDao.deleteById(Integer.parseInt(file));
        }
    }
}