package controller;

import dao.ImagemDao;
import dao.MarcadorDao;
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
 * Classe responsável por receber as imagens enviadas pelo cliente ao servidor.
 * A classe tem dependências: biblioteca commonsFileUpload e io.
 *
 * @author campo
 */
@WebServlet(urlPatterns = "/img")
public class UploadServlet extends HttpServlet {

    private ServletFileUpload inicializaConfiguracoes(HttpServletRequest req) {

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);

        /*
        É possível definir o tamanho dos arquivos e da requisição pelos métodos da classe ServletFileUpload.
        Defini-os em que, qualquer valor será aceito, porém, é altamente recomendado que defina um valor dependendo das configurações do servidor.
        Utilizar tipo long  
         */
        upload.setSizeMax(Long.MAX_VALUE);
        upload.setFileSizeMax(Long.MAX_VALUE);

        return upload;
    }

    private void redireciona(HttpServletRequest req, HttpServletResponse resp, Imagem imagem) throws ServletException, IOException {

        if (imagem != null) {
            req.setAttribute("marcadores", new MarcadorDao().readAll());
            req.setAttribute("imagem", imagem);
            req.getRequestDispatcher("/WEB-INF/view/editImage.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");
        }
    }

    private Imagem manipulaImagem(Imagem imagem, String caminho, FileItem item, Usuario usuario) throws Exception {

        /**
         * Url da imagem. Busca o tempo do sistema em MiliSegundos e salva como
         * nome da imagem, assim nunca haverá nomes repetidos, também é feita a
         * busca da extensão da imagem para salvar após o nome definido
         */
        String url;
        url = String.valueOf(
                System.currentTimeMillis())
                + item.getName().substring(
                        item.getName().lastIndexOf("."),
                        item.getName().length()
                );

        /*As informações originais da imagem carregada são salvas no banco de dados, juntamente com o código do dono da imagem.*/
        imagem.setDescricao(item.getName());
        imagem.setUrl(url);
        imagem = new ImagemDao().create(imagem, usuario.getCodigo());

        /*Cria e salva um arquivo na pasta do servidor definida pela string 'caminho'*/
        File f = new File(caminho + url);
        item.write(f);

        return imagem;
    }

    private Imagem manipulaImagens(ServletFileUpload upload, HttpServletRequest req, HttpServletResponse resp, Usuario usuario) {
        /*
        
        Caminho a ser utilizado para salvar as imagens enviadas ao servidor.
        O caminho a ser utilizado pode ser abstrato ou absoluto. Mas é necessário utilizar o caminho do serividir (wildfly/standalone/nomeDoProjeto/assets/files/);
         */
        String urlAbsoluta = "C:\\wildfly-14.0.1.Final\\wildfly-14.0.1.Final\\standalone\\deployments\\Fotos.war\\assets\\img\\";
        /*
        O processamento está sendo definido para o processamento de apenas uma imagem
        Caso seja necessidade de salvar multiplas imagens ou arquivos. Utilize um ArrayList    
         */
        Imagem imagem = new Imagem();
        try {
            List<FileItem> arquivos = upload.parseRequest(req);
            Iterator<FileItem> iterador = arquivos.iterator();
            FileItem item;
            while (iterador.hasNext()) {
                item = iterador.next();
                imagem = manipulaImagem(imagem, urlAbsoluta, item, usuario);
            }
            return imagem;
        } catch (Exception e) {
            // todas as exceções levantadas serão tratadas aqui!
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("Fotos_User");
        redireciona(req, resp, manipulaImagens(inicializaConfiguracoes(req), req, resp, usuario));
    }
}
