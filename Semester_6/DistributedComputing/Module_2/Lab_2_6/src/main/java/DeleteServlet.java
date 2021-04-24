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
    //BookDaoImpl bookDao = new BookDaoImpl(Connector.getConnection());
    CountryDaoImpl countryDao = new CountryDaoImpl(Connector.getConnection());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //String book = Optional.ofNullable(req.getParameter("id")).orElse("empty");
        String country =Optional.ofNullable(req.getParameter("countryId")).orElse("empty");
//        if (!book.equals("empty")){
//            bookDao.deleteById(Integer.parseInt(book));
//        }
//        else{
            countryDao.deleteById(Integer.parseInt(country));
        //}
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}