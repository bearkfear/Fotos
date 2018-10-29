package controller;

import dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 * Classe para logar um usuário no sistema.
 *
 * @author Enrico
 */
@WebServlet(urlPatterns = "/logar")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Variável que irá receber o valor do parâmetro do form, que tem atributo name[opcao].
        String opcao = null;

        try {
            /**
             * Sobre os parâmetros.
             *
             * Só podem existir dois tipos de parâmetros: 1. Uma String com
             * "entrar", que significa que foi feito a requisição de um email;
             * 2. Uma String com "eandp", que signifíca que foi feita a
             * requisição de um login completo;
             */
            opcao = req.getParameter("opcao");

        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        switch (opcao) {

            case "entrar": {

                String email = null;

                // tenta pegar o parametro email
                try {
                    email = req.getParameter("email");
                } catch (NullPointerException e) {
                    System.out.println(e);
                } catch (Exception e) {
                    System.out.println(e);
                }

                Usuario usuario = new UsuarioDao().read(email);

                if (usuario != null) {

                    req.setAttribute("email", email);
                    req.setAttribute("imagem", usuario.getUrlImg());
                    req.setAttribute("contexto", req.getContextPath());

                    req.getRequestDispatcher("/pages/signin.jsp").forward(req, resp);

                } else {

                    req.setAttribute("nota", "Email não existe!");
                    req.setAttribute("imagem", "default.png");
                    req.setAttribute("contexto", req.getContextPath());
                    req.getRequestDispatcher("/pages/signin.jsp").forward(req, resp);

                    // FALHA, EMAIL NÃO ENCONTRADO OU NÃO FOI POSSÍVEL CONECTAR
                }
                break;
            }

            case "eandp": {

                String email = null;
                String senha = null;

                try {
                    email = req.getParameter("email");
                    senha = req.getParameter("pass");
                } catch (NullPointerException e) {
                    System.out.println(e);
                }

                Usuario usuario = new UsuarioDao().read(email, senha);

                if (usuario != null) {

                    // SUCESSO, REDIRECIONAR PARA DASHBOARD;
                    HttpSession session = req.getSession();
                    session.setAttribute("usuarioLogado", usuario);
                    resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");

                } else {
                    /**
                     * FALHA AO LOGAR USUÁRIO.
                     *
                     * REDIRECIONAR PARA SIGNIN INFORMANDO FALHA, POSSÍVEL DE
                     * SER EMAIL OU SENHA INCORRETOS.
                     */
                    
                    req.setAttribute("imagem", "default.png");
                    req.setAttribute("nota", "Email ou senha incorretos, tente novamente!");
                    req.setAttribute("contexto", req.getContextPath());
                    req.getRequestDispatcher("/pages/signin.jsp").forward(req, resp);
                }
                break;
            }

            default: {
 
                req.setAttribute("imagem", "default.png");
                req.setAttribute("nota", "Alguma coisa não está certa, por favor tente mais tarde!");
                req.setAttribute("contexto", req.getContextPath());
                req.getRequestDispatcher("/pages/signin.jsp").forward(req, resp);
            }
        }

    }

}
