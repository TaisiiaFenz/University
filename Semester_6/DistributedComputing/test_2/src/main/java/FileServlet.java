import dao.Connector;
import dao.FileDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import models.File;

@WebServlet(urlPatterns = "/file")
public class FileServlet extends HttpServlet {
    FileDaoImpl fileDao = new FileDaoImpl(Connector.getConnection());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cities", fileDao.findAll());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String catalog = req.getParameter("catalog");
        int size = Integer.parseInt(req.getParameter("id"));
        fileDao.save(new File(name, catalog, size));

    }
}