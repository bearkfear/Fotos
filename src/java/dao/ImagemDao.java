package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Imagem;

/**
 *CLASSE RESPONÁVEL PELA MANIPULAÇÃO DAS IMAGENS NO BANCO DE DADOS, TODAS INFORMAÇÕES SOBRE AS IMAGENS SÃO SALVAR NO BANCO DE DADOS
 * @author campo
 */
public class ImagemDao {

    
    /**
     * CRIA UMA IMAGEM NO BANCO DE DADOS
     * .
     * A criação de uma imagem no sistema depende de um usuário, então é passado junto o código do usuário por parâmetro
     * @param imagem
     * @param codigo
     * @return imagem
     */
    
    public Imagem create(Imagem imagem, int codigo) {

        String sql = "INSERT INTO imagem (descricao, url, usuario_codigo) VALUES (?, ?, ?)";

        try (Connection conexao = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, imagem.getDescricao());
            pre.setString(2, imagem.getUrl());
            pre.setInt(3, codigo);

            pre.executeUpdate();
            ResultSet rs = pre.getGeneratedKeys();

            while (rs.next()) {
                imagem.setCodigo(rs.getInt("codigo"));
            }

            if (imagem.getCodigo() != 0) {
                return imagem;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * ATUALIZA AS INFORMAÇÕES DE UMA IMAGEM ESPECÍFICA NO BANCO DE DADOs.
     * Ao ser feito o upload de uma imagem no servidor, o banco de dados é encarregador de atualizar o nome da mesma
     * @param codigo
     * @param nome
     * @return true or false
     */
    
    public boolean update(int codigo, String nome) {

        String sql = "Update imagem SET descricao = '" + nome + "' WHERE codigo = ?";

        try (Connection conexao = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);
            
            if (pre.executeUpdate() != 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    
    /**
     * ESSE MÉTODO BUSCA NO BANCO DE DADOS DE FORMA GLOBAL AS IMAGENS QUE ESTÃO SALVAS NELE.
     * Para que a busca possa ser possível é necessário que as imagens sejam públicas
     * @param descricao
     * @return todasImagensBuscadas
     */
    public ArrayList<Imagem> searchImagensDataBase(String descricao) {

        String sql = "SELECT codigo, descricao, url FROM imagem WHERE descricao LIKE '%" + descricao + "%'";
        try (Connection conexao = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conexao.prepareStatement(sql);
            //pre.setString(1, descricao);
            ResultSet resultado = pre.executeQuery();

            ArrayList<Imagem> imagens = new ArrayList<>();

            while (resultado.next()) {
                Imagem image = new Imagem();

                image.setCodigo(resultado.getInt("codigo"));
                image.setDescricao(resultado.getString("descricao"));
                image.setUrl(resultado.getString("url"));

                image.setAssociacoes(new AssociaDao().readAssociationsFromImage(image.getCodigo(), conexao));
                image.getAssociacoes().forEach(associacao -> associacao.setImagem(image));
                imagens.add(image);

            }

            return imagens;

        } catch (SQLException e) {

        }

        return null;

    }

    
    /**
     * MÉTODO QUE FAZ A LEITURA DAS IMAGENS DE UM USUÁRIO.
     * esse método sempre é invocado por outro método na classe usuário
     * @param codigo
     * @param conexao
     * @return imagens
     */
    public ArrayList<Imagem> readImagesFromUser(int codigo, Connection conexao) {

        String sql = "SELECT codigo, url, descricao FROM imagem WHERE usuario_codigo = ?";

        try {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);
            ResultSet rs = pre.executeQuery();

            ArrayList<Imagem> imagens = new ArrayList();
            while (rs.next()) {
                Imagem image = new Imagem();
                image.setUrl(rs.getString("url"));
                image.setCodigo(rs.getInt("codigo"));
                image.setDescricao(rs.getString("descricao"));
                image.setAssociacoes(new AssociaDao().readAssociationsFromImage(image.getCodigo(), conexao));
                image.getAssociacoes().forEach(associacao -> associacao.setImagem(image));
                imagens.add(image);
            }
            return imagens;

        } catch (SQLException e) {

        }

        return null;
    }

    /**
     * Le uma imagem sem sua associação.
     * Esse método sempre é chamado pro outro método, que compartilha sua conexao.
     * @param codigo
     * @param conexao
     * @return imagem
     */
    
    private Imagem readWithOutAssociation(int codigo, Connection conexao) {
        String sql = "SELECT descricao, url FROM imagem WHERE codigo = ?";

        try {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);

            ResultSet resultado = pre.executeQuery();

            Imagem imagem = null;

            while (resultado.next()) {
                imagem = new Imagem();
                imagem.setUrl(resultado.getString("url"));
                imagem.setCodigo(codigo);
                imagem.setDescricao(resultado.getString("descricao"));
            }

            return imagem;

        } catch (SQLException e) {

        }

        return null;
    }

    /**
     * Método que le as imagens sem suas associações.
     * Esse método sempre é invocado por outro metodo que compartilha sua conexao
     * @param codigo
     * @param conexao
     * @return 
     */
    
    public Imagem readImageFromAssociation(int codigo, Connection conexao) {
        Imagem imagem = readWithOutAssociation(codigo, conexao);

        if (imagem != null) {
            return imagem;
        }

        return null;
    }

}
