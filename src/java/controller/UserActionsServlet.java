package controller;

import model.Imagem;
import model.Marcador;
import model.Usuario;
import dao.AssociaDao;
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

@WebServlet(name = "UserActionsServlet", urlPatterns = {"/user"})
public class UserActionsServlet extends HttpServlet {

    private void verifySession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("Fotos_User") == null) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.verifySession(req, resp);

        String option = null;

        try {
            option = req.getParameter("action");

        } catch (Exception e) {
            req.getRequestDispatcher("/user?action=dashboard").forward(req, resp);
        }

        switch (option) {
            case "dashboard": {

                req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
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
                    ArrayList<Imagem> imagens = new ImagemDao().searchImagensDataBase(req.getParameter("value"));

                    req.setAttribute("correspondence", req.getParameter("value"));
                    req.setAttribute("Fotos_Images", imagens.isEmpty() == true ? null : imagens);
                    req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("/WEB-INF/view/resultado.jsp").forward(req, resp);
                } catch (IOException e) {
                }
                break;
            }

            case "marcador": {
                try {

                    ArrayList<Imagem> imagens = new MarcadorDao().readImagesFromMarcador(Integer.parseInt(req.getParameter("value")));

                    req.setAttribute("correspondence", null);
                    req.setAttribute("Fotos_Images", imagens.isEmpty() == true ? null : imagens);
                    req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
                    req.getRequestDispatcher("/WEB-INF/view/resultado.jsp").forward(req, resp);
                } catch (IOException | NumberFormatException | ServletException e) {

                }
                break;
            }

            case "perfil": {

                req.getRequestDispatcher("/WEB-INF/view/perfil.jsp").forward(req, resp);
                break;
            }

            case "upload": {
                req.getRequestDispatcher("/WEB-INF/view/uploadImagem.jsp").forward(req, resp);
                break;
            }

            default: {
                resp.sendRedirect(req.getContextPath() + "/user?action=dashboard");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        verifySession(req, resp);
        String option = null;
        try {
            option = req.getParameter("option");
        } catch (Exception e) {
        }
        switch (option) {
            case "processaDetalhesImagem": {

                System.out.println("Entrou no processa detalhes imagem");
                /*
                Processa informações requisitadas pelo cliente a respeito de uma imagem em questão;
                 */
                try {

                    int codigoImagem = Integer.parseInt(req.getParameter("codigoImagem"));
                    String nomeImagem = req.getParameter("nomeImagem");

                    String[] novosMarcadores = null;

                    try {
                        novosMarcadores = req.getParameterValues("newMarcador");
                    } catch (NullPointerException e) {
                        System.out.println("Deu null nos novos marcadores" + e);
                    }

                    ArrayList<Marcador> marcadores = new ArrayList();

                    try {
                        marcadores = converteMarcadores(req.getParameterValues("marcador"));
                    } catch (NullPointerException e) {
                        System.out.println("Deu null nos marcadores já existentes" + e);
                    }

                    System.out.println("Codigo da imagem: " + codigoImagem);
                    System.out.println("Nome da imagem: " + nomeImagem);

                    // atualiza as informações da imagem pelo código;
                    new ImagemDao().update(codigoImagem, nomeImagem);

                    if (novosMarcadores != null) {
                        // Processa os novos marcadores que a imagem tem.
                        for (String marcador : novosMarcadores) {
                            System.out.println("Nome dos novos marcadores: " + marcador);
                            Marcador m = new MarcadorDao().create(new Marcador(marcador));
                            if (m.getCodigo() != 0) {
                                new AssociaDao().create(codigoImagem, m.getCodigo());
                            }
                        }
                    }

                    if (!marcadores.isEmpty()) {

                        // Cria associações entre os marcadores que já existem e a nova imagem
                        marcadores.forEach(m -> {
                            System.out.println("Codigo dos marcadores já existentes: " + m.getCodigo());
                            new AssociaDao().create(codigoImagem, m.getCodigo());
                        });

                    }

                    // Atualiza as informações do usuário
                    Usuario userAccount = (Usuario) req.getSession().getAttribute("Fotos_User");
                    req.getSession().setAttribute("Fotos_User", new UsuarioDao().readWithEmailAndPassword(userAccount.getEmail(), userAccount.getSenha()));
                    resp.sendRedirect(req.getContextPath() + "/user?action=dashboard");

                } catch (IOException | NumberFormatException e) {
                    System.out.println(e);
                }
                break;
            }
            default: {

                resp.sendRedirect(req.getContextPath() + "/user?action=dashboard");
                break;
            }

        }
    }

    /**
     * Converte todos os marcadores enviados por parametro em forma de array,
     * agora em ArrayList de Marcador.
     *
     * @param marcadores
     * @return ArrayList<Marcador> m
     */
    private ArrayList<Marcador> converteMarcadores(String[] marcadores) {

        ArrayList<Marcador> m = new ArrayList();

        for (String marcador : marcadores) {
            Marcador mark = new Marcador();
            mark.setCodigo(Integer.parseInt(marcador));
            m.add(mark);
        }

        return m;
    }

}
