/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ImagemDao;
import dao.MarcadorDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Imagem;

/**
 * ESSA CLASSE É RESPONSAVEL PELO RETORNO DE UMA SOLICITAÇÃO DE UMA BUSCA OU DE
 * UM MARCADOR EM ESPECÍFICO.
 *
 * @author campo
 */
@WebServlet(name = "BuscaServlet", urlPatterns = {"/find"})
public class BuscaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Mostrar imagens associadas ao marcador; 
        switch (req.getParameter("action")) {

            case "marcador": {
                try {

                    ArrayList<Imagem> imagens = new MarcadorDao().readImagesFromMarcador(Integer.parseInt(req.getParameter("value")));

                    req.setAttribute("correspondence", null);
                    req.setAttribute("Fotos_Images", imagens.isEmpty() == true ? null : imagens);
                    req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("./pages/resultado.jsp").forward(req, resp);
                } catch (IOException | NumberFormatException | ServletException e) {

                }
                break;

            }
            case "buscar": {

                try {
                    ArrayList<Imagem> imagens = new ImagemDao().searchImagensDataBase(req.getParameter("value"));

                    req.setAttribute("correspondence", req.getParameter("value"));
                    req.setAttribute("Fotos_Images", imagens.isEmpty() == true ? null : imagens);
                    req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("/pages/resultado.jsp").forward(req, resp);
                } catch (IOException e) {
                }
                break;

            }
        }
    }
}
