package controller;

import dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author Enrico
 */
// url do servlet

/**
 * Servlet para logar o usuario, recebe requisições de doPost e retorna a imagem
 * valida do usuario, ou loga o mesmo na conta
 */
@WebServlet (urlPatterns = "/logar")
public class LogarUsuarioServlet extends HttpServlet {
    
    
   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       
        
        // pega o value do submit com name opcao
        String opcao = req.getParameter("opcao");
       
        
        /*
            Opcao pode ter dois valores: 1. entrar; 2.eandp (significa email e senha);
        */
        System.out.println("OPCAO: " + opcao);
        System.out.println("EMAIL: " + req.getParameter("email"));
        
        
        
        
        
        if (opcao.equals("entrar")) {
            System.out.println("ENTROU NO LOGAR COM O EMAIL, SO FALTA IR PARA SIGNIN.JSP");
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

        } else if (opcao.equals("eandp")) {
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
        } else {
            resp.sendRedirect("index.jsp");
        }

    }

}
