/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ImagemDao;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Imagem;
import model.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Enrico
 */
@WebServlet(urlPatterns = "/img")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");

        try {

            // Verifique se temos uma solicitação de upload de arquivo
            boolean isMultipart = ServletFileUpload.isMultipartContent(req);

            // Criar uma fábrica para itens de arquivo baseados em disco
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //Configurar um repositório (para garantir que um local temporário seguro seja usado)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            //Criar um novo manipulador de upload de arquivos
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(Long.MAX_VALUE);
            upload.setFileSizeMax(Long.MAX_VALUE);
            // Analise o pedido
            List<FileItem> multifiles = upload.parseRequest(req);

            Iterator<FileItem> iter = multifiles.iterator();

            ImagemDao imagemDao = new ImagemDao();
            String caminho = "D:/Documentos/NetBeansProjects/Fotos/web/img/";
            while (iter.hasNext()) {
                FileItem item = iter.next();

                // pega o tempo do sistema em miliseconds e a extensao da imagem
                String url = String.valueOf(System.currentTimeMillis()) + item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());

                // salvar informações no banco
                Imagem imagem = new Imagem();
                imagem.setDescricao(item.getName());
                imagem.setUrl(url);
                //imagemDao.create(imagem, u.getCodigo());

                // cria um arquivo com local, nome e a extensao do arquivo
                File f = new File(caminho + url);
                // grava o arquivo na pasta;
                item.write(f);
            }
            
            // redireciona 
            resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");

        } catch (Exception e) {
            System.out.println("Deu nul pointer exception");
            resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");

            //redireciona o usuario
        }

    }
}
