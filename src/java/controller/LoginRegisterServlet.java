/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
@WebServlet(urlPatterns = "/RegisterAndLogin")
public class LoginRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Entrou no doPOST");

        class Autentication {

            private String email;
            private String password;

            public Autentication(String email, String password) {
                this.email = email;
                this.password = password;
            }

            public boolean autenticate() {
                Usuario u = new UsuarioDao().readWithEmailAndPassword(this.email, this.password);
                if (u != null) {
                    req.getSession().setAttribute("Fotos_User", u);
                    return true;
                }
                return false;
            }
        }

        switch (req.getParameter("option")) {
            case "login": {

                try {

                    if (new Autentication(req.getParameter("email"), req.getParameter("password")).autenticate()) {
                        resp.sendRedirect(req.getContextPath() + "/user?action=dashboard");

                    } else {
                        // redireciona pagina de login informando problema na autenticação
                        req.setAttribute("path", "true");
                        req.setAttribute("infoAutenticate", true);
                        req.getRequestDispatcher("./pages/login.jsp").forward(req, resp);
                    }

                } catch (IOException | ServletException e) {
                    req.setAttribute("path", "true");
                    req.setAttribute("infoAutenticate", true);
                    req.getRequestDispatcher("./pages/login.jsp").forward(req, resp);
                }
                req.setAttribute("path", "true");
                req.setAttribute("infoAutenticate", true);
                req.getRequestDispatcher("./pages/login.jsp").forward(req, resp);

                break;
            }
            case "register": {

                try {

                    Usuario usuario = new Usuario(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"), req.getParameter("about"));
                    usuario = new UsuarioDao().create(usuario);

                    if (usuario != null) {
                        if (new Autentication(usuario.getEmail(), usuario.getSenha()).autenticate()) {
                            resp.sendRedirect(req.getContextPath() + "/user?action=dashboard");

                        } else {
                            req.setAttribute("path", "true");
                            req.setAttribute("infoAutenticate", true);
                            req.getRequestDispatcher("./pages/login.jsp").forward(req, resp);
                        }

                    } else {
                        req.setAttribute("path", "true");
                        req.setAttribute("infoRegister", true);
                        req.getRequestDispatcher("./pages/register.jsp").forward(req, resp);
                    }

                } catch (IOException | ServletException e) {

                }
                req.setAttribute("path", "true");
                req.setAttribute("infoRegister", true);
                req.getRequestDispatcher("./pages/register.jsp").forward(req, resp);
            }
            default: {
                resp.sendRedirect(req.getContextPath() + "/");

            }
        }

    }

}
