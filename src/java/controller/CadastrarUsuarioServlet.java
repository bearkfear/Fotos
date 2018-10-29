/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(urlPatterns = "/cadastrar")
public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //INFORMAÇÕES DO USUARIO: codigo, nome, email, senha e uma descrição sobre ele;

        // Questiona se o botão de cadastrar tinha definido como value cadastrar
        
         RequestDispatcher disp;
        
        if ("cadastrar".equals(req.getParameter("opcao"))) {

            System.out.println("\n\n\n\n\n\n\n\nENTROU NO IF");

            // nome, email, pass, sobre
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String senha = req.getParameter("pass");
            String sobre = req.getParameter("sobre");

            Usuario usuario = new Usuario(nome, email, senha, sobre);

            usuario = new UsuarioDao().create(usuario);

           
            if (usuario != null) {
                // redireciona para a dashboard
                System.out.println("\n\n\n\n\n\nn\nCADASTROU SÓ FALTA REDIRECT");
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", usuario);
                resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");
                
                // disp = req.getRequestDispatcher("/pages/dashboard.jsp");
                // disp.forward(req, resp);

            } else {
                // redireciona para a cadastrar com informações sobre
                req.setAttribute("resposta", "Não foi possível salvar no sistema, tente mais tarde!");
                resp.sendRedirect(req.getContextPath() + "/pages/cadastro.jsp");
            }

        } else {
            // redireciona para a pagina cadastrar com informações sobre
            req.setAttribute("resposta", "Não foi possível cadastrar, requisição invalida!");
            resp.sendRedirect(req.getContextPath() + "/pages/cadastro.jsp");
            //disp = req.getRequestDispatcher("/pages/cadastro.html");
            //disp.forward(req, resp);
        }

    }

}
