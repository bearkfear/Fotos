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

        // pega o value do submit com name opcao
        String opcao = "";

        try {
            /**
             * Sobre os parâmetros.
             * 
             * Só podem existir dois tipos de parâmetros:
             * 1. Uma String com "entrar", que significa que foi feito a requisição de um email;
             * 2. Uma String com "eandp", que signifíca que foi feita a requisição de um login completo;
             */
            opcao = req.getParameter("opcao");
            
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        switch (opcao) {
            
            
            case "entrar": {
                // caso ele peça apenas o email retorna-se para a página de login com email e senha, fixando o email e a senha do usuario;
                String email = req.getParameter("email");
                Usuario usuario = new UsuarioDao().read(email);
                if (usuario.equals(null)) {
                    // Retorna erro
                } else {
                    System.out.println("Email usuarii" + email);
                    System.out.println("imagem usuario" + usuario.getUrlImg());
                    req.setAttribute("email", email);
                    req.setAttribute("imagem", usuario.getUrlImg());
                    req.setAttribute("contexto", req.getContextPath());

                    req.getRequestDispatcher("/pages/signin.jsp").forward(req, resp);
                }
                break;
            }
            
            
            
            
            
            
            
            
            case "eandp": {
                System.out.println("Entrou no e and p");
                // pede para logar com email e senha;
                String email = req.getParameter("email");
                String senha = req.getParameter("pass");
                Usuario u = new UsuarioDao().read(email, senha);
                if (u.equals(null)) {
                    /* Falha, redirecionar usuarios para pagina de erro  */
                    System.out.println("Erro!");
                    req.setAttribute("nota", "falha");
                } else {
                    /* sucesso, redirecionar usuario para dashboard */
                    HttpSession session = req.getSession();
                    session.setAttribute("usuarioLogado", u);
                    resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");

                }
                break;
            }
            default:
                resp.sendRedirect("index.jsp");
                break;
        }

    }

}
