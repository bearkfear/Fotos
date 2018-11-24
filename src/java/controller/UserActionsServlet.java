package controller;

import dao.ImagemDao;
import dao.MarcadorDao;
import dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

/**
 *
 * @author campo
 */
@WebServlet(name = "UserActionsServlet", urlPatterns = {"/user"})
public class UserActionsServlet extends HttpServlet {

    private void verifySession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("Fotos_User") == null) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.verifySession(req, resp);

        String option = null;

        try {
            option = req.getParameter("action");

        } catch (Exception e) {
            req.getRequestDispatcher("/user?action=dashboard").forward(req, resp);
        }

        switch (option) {
            case "dashboard": {
                
                System.out.println("Entrou dashboard");
                req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
                req.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(req, resp);
                break;
            }
            case "logoff": {
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + "/");
                break;
            }

            case "find": {

                try {
                    req.setAttribute("Fotos_Images", new ImagemDao().searchImagensDataBase(req.getParameter("value")));
                    req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("/WEB-INF/view/resultado.jsp").forward(req, resp);
                } catch (IOException | ServletException e) {
                }
                break;
            }

            case "marcador": {
                try {

                    req.setAttribute("Fotos_Images", new MarcadorDao().readImagesFromMarcador(Integer.parseInt(req.getParameter("value"))));
                    req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("/WEB-INF/view/resultado.jsp").forward(req, resp);
                } catch (IOException | NumberFormatException | ServletException e) {

                }
                break;
            }

            case "perfil": {
                req.getRequestDispatcher("/WEB-INF/view/perfil.jsp").forward(req, resp);
                break;
            }

            case "removeImage": {
                // remover imagem do banco de dados;

                break;
            }

            case "updateInformations": {

                try {
                    String nome = req.getParameter("name");
                    String sobre = req.getParameter("about");
                    new UsuarioDao().update(nome, sobre);
                    Usuario usuario = (Usuario) req.getSession().getAttribute("Fotos=user");
                    usuario.setNome(nome);
                    usuario.setSobre(sobre);

                    req.getSession().setAttribute("Fotos_User", usuario);
                    req.getRequestDispatcher("/WEB-INF/view/perfil.jsp").forward(req, resp);

                } catch (Exception e) {

                }

                break;
            }

            default: {
                resp.sendRedirect(req.getContextPath() + "/user?action=dashboard");
            }
        }

    }

}