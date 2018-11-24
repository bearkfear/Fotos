package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(urlPatterns = {"/LoginAndRegister"})
public class LoginAndRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = null;

        try {
            option = (String) req.getAttribute("option");
        } catch (Exception e) {

        }

        switch (option) {
            case "login": {

                class Autentication {

                    private final String email;
                    private final String password;

                    public Autentication(String email, String password) {
                        this.email = email;
                        this.password = password;
                    }

                    public boolean autenticate() {
                        Usuario u = new UsuarioDao().readWithEmailAndPassword(this.email, this.password);
                        if (u != null) {
                            req.getSession().setAttribute("Fotos=user", u);
                            return true;
                        }
                        return false;
                    }
                }
                try {

                    if (new Autentication(req.getParameter("email"), req.getParameter("password")).autenticate()) {
                        req.getRequestDispatcher("/user?action=dashboard").forward(req, resp);
                    } else {
                        // redireciona pagina de login informando problema na autenticação;

                        req.setAttribute("infoAutenticate", true);
                        req.getRequestDispatcher("./pages/login.jsp").forward(req, resp);
                    }

                } catch (Exception e) {
                    req.getRequestDispatcher("/").forward(req, resp);
                }

                
                break;
            }
            case "register": {

            }
            default: {
                req.getRequestDispatcher("/").forward(req, resp);
            }
        }

    }

}
