/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Imagem;
import model.Marcador;
import model.Usuario;

/**
 *
 * @author campo
 */
@WebServlet(name = "UserActionsServlet", urlPatterns = {"/user"})
public class UserActionsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = null;
        usuario = (Usuario) request.getSession().getAttribute("Fotos=user");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/");
        }

        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String option = null;

        try {
            option = req.getParameter("action");

        } catch (Exception e) {
            req.getRequestDispatcher("/user?action=dashboard").forward(req, resp);
        }

        switch (option) {
            case "dashboard": {
                req.setAttribute("Fotos=Marcadores", new MarcadorDao().readAll());
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
                    req.setAttribute("Fotos=images", new ImagemDao().searchImagensDataBase(req.getParameter("value")));
                    req.setAttribute("Fotos=Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("/WEB-INF/view/resultado.jsp").forward(req, resp);
                } catch (Exception e) {
                }
                break;
            }

            case "marcador": {
                try {

                    req.setAttribute("Fotos=images", new MarcadorDao().readImagesFromMarcador(Integer.parseInt(req.getParameter("value"))));
                    req.setAttribute("Fotos=Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("/WEB-INF/view/resultado.jsp").forward(req, resp);
                } catch (Exception e) {

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
                    
                    req.getSession().setAttribute("Fotos=user", usuario);
                    req.getRequestDispatcher("/WEB-INF/view/perfil.jsp").forward(req, resp);
                    
                } catch (Exception e) {

                }

                break;
            }

            default: {
                req.getRequestDispatcher("/user?action=dashboard").forward(req, resp);
            }
        }

    }

}
