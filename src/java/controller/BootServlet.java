package controller;

import dao.MarcadorDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author campo
 */
@WebServlet(name = "index", urlPatterns = {"/"})
public class BootServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Fotos=Marcadores", new MarcadorDao().readAll());
        req.getRequestDispatcher("pesquisar.jsp").forward(req, resp);  
    }

}
