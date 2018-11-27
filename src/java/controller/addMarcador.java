package controller;

import dao.ImagemDao;
import dao.MarcadorDao;
import dao.UsuarioDao;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "addMarcador", urlPatterns = {"/addMarcadores"})
public class addMarcador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Edita nome da imagem no banco de dados;
        int codigo = Integer.parseInt(req.getParameter("codigoImagem"));
        String nome = req.getParameter("nomeImagem");

        new ImagemDao().update(codigo, nome);
        
        String[] marcadores;
        marcadores = req.getParameterValues("marcadores[]");
        System.out.println("Nome imagem:" + nome);
        for (String marcadore : marcadores) {
            System.out.println("Nome marcador: " + marcadore);
            new MarcadorDao().addMarcadorImagem(marcadore, codigo, nome);
        }

        Usuario userAccount = (Usuario) req.getSession().getAttribute("Fotos_User");

        req.getSession().setAttribute("Fotos_User", new UsuarioDao().readWithEmailAndPassword(userAccount.getEmail(), userAccount.getSenha()));
        resp.sendRedirect(req.getContextPath() + "/user?action=dashboard");
    }
}
