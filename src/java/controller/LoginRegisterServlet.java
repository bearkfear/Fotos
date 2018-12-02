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
 * Classe responsável pelo login e cadastro dos usuários. As requisições de um
 * usuário para login ou para registrar são encaminhadas para essa servlet
 *
 * @author campo
 */
@WebServlet(urlPatterns = "/RegisterAndLogin")
public class LoginRegisterServlet extends HttpServlet {

    /**
     * MÉTODO IRÁ LIDAR COM TODAS REQUISIÇÕES POST. MÉTODO UTILIZA REESCRITA
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * CLASSE INTERNA. FEITA PARA REUSO DE CÓDIGO E FACILITAMENTO DAS AÇÕES
         */
        class Autentication {

            private String email;
            private String password;

            /**
             * CONTRUTOR RESPONSÁVEL POR RECEBER AS INFORMAÇÕES DO USUÁRIO COMO
             * EMAIL E SENHA
             *
             * @param email
             * @param password
             */
            public Autentication(String email, String password) {
                this.email = email;
                this.password = password;
            }

            /**
             * MÉTODO QUE AUTENTICA O USUARIO NO SISTEMA, CASO NÃO CONSIRA
             * EFEUTAR A ATUENTICAÇÃO, ELE RETORNA FALSE
             *
             * @return
             */
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
