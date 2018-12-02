package controller;

import dao.MarcadorDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SERVLET RESPONSÁVEL PELO PRIMEIRO ACESSO E PELA PAGINA INICIAL DO SERVIDOR.
 * NÃO É NECESSÁRIO PASSAR UM GET PARA A CLASSE, POREM O MÉTODO GET É CHAMADO
 * POR UM METODO QUE PROCESSA A REQUISIÇÃO SEM NENHUMA INFORMAÇÃO RETORNANDO
 * MARCADORES E A PAGINA INICIAL
 *
 * @author campo
 */
@WebServlet(name = "index", urlPatterns = {"/index.jsp"})
public class BootServlet extends HttpServlet {

    /**
     * MÉTODO QUE PROCESSA A REQUISIÇÃO SEM NENHUMA INFORMAÇÃO. QUANDO UM
     * USUÁRIO ACESSAR A PAGINA DO PROJETO, ESSE SERVLET SERÁ CHAMADO E ESSE
     * METODO SERÁ ENCARREGADO DE RESPONDER A REQUISIÇÃO E, A PARTIR DELE OUTRO
     * MÉTODO REESCRITO )GET) É INVOCADO, EFETUANDO AÇÕES
     *
     * @param req 
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Fotos_Marcadores", new MarcadorDao().readAll());
        req.getRequestDispatcher("pesquisar.jsp").forward(req, resp);
    }

}
