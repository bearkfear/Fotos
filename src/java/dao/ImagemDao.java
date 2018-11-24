package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Imagem;

/**
 *
 * @author campo
 */
public class ImagemDao {

    public ArrayList<Imagem> searchImagensDataBase(String descricao) {

        String sql = "SELECT * FROM imagem WHERE imagem.descricao like '%?%';";

        try (Connection conexao = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setString(1, descricao);
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

    public Imagem read(int codigo) {

        try (Connection conexao = new ConnectionFactory().getConnection()) {
            Imagem imagem = readWithOutAssociation(codigo, conexao);
            imagem.setAssociacoes(new AssociaDao().readAssociationsFromImage(codigo, conexao));
            imagem.getAssociacoes().forEach(i -> i.setImagem(imagem));

        } catch (SQLException e) {

        }

        return null;
    }

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

    public Imagem readImageFromAssociation(int codigo, Connection conexao) {
        Imagem imagem = readWithOutAssociation(codigo, conexao);

        if (imagem != null) {
            return imagem;
        }

        return null;
    }

}
