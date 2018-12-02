package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Imagem;
import model.Marcador;

/**
 *
 * CLASSE RESPONSÁVEL PELA MANIPULAÇÃO DE TODOS OS MARCADORES NO SISTEMA
 * @author campo
 */
public class MarcadorDao {

 /**
  * MÉTODO RESPONSÁVEL PELA CRIAÇÃO DE UM MARCADOR NO SISTEMA.
  * Ao ser feito o upload de uma imagem é possível criar marcadores de forma dinãmica para o projeto
  * Então é feito um array de marcadores e enviado junto a solicitação do servidor
  * @param marcador
  * @return 
  */

    public Marcador create(Marcador marcador) {

        String sql = "INSERT INTO marcador (titulo) VALUES (?)";

        try (Connection conexao = new ConnectionFactory().getConnection()){

            PreparedStatement pre = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, marcador.getTitulo());
            pre.executeUpdate();
            ResultSet rs = pre.getGeneratedKeys();

            marcador.setTitulo(null);
            while (rs.next()) {
                marcador.setCodigo(rs.getInt("codigo"));
                marcador.setTitulo(rs.getString("titulo"));

            }

            return marcador;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Marcador readFromAssociation(int codigo, Connection conexao) {
        return readWithOutAssociation(codigo, conexao);
    }

    private Marcador readWithOutAssociation(int codigo, Connection conexao) {
        String sql = "SELECT * FROM marcador WHERE codigo = ?";

        try {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);
            ResultSet rs = pre.executeQuery();

            Marcador marcador = null;

            while (rs.next()) {
                marcador = new Marcador();
                marcador.setCodigo(rs.getInt("codigo"));
                marcador.setTitulo(rs.getString("titulo"));
            }

            return marcador;

        } catch (SQLException e) {

        }

        return null;
    }

    /**
     * Leia imagens de um marcador do banco de dados.
     *
     * Metodo que retorna todas as imagens associadas a um marcador.
     *
     * @param codigoMarcador
     * @return ArrayList<Imagens>
     */
    public ArrayList<Imagem> readImagesFromMarcador(int codigoMarcador) {

        try (Connection conexao = new ConnectionFactory().getConnection()) {

            String sql = "select imagem.codigo, imagem.descricao, imagem.url from imagem, associa where codigo_imagem = imagem.codigo and codigo_marcador = ?;";

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigoMarcador);

            ResultSet resultado = pre.executeQuery();

            ArrayList<Imagem> imagens = new ArrayList();

            while (resultado.next()) {
                Imagem image;
                image = new Imagem(resultado.getInt("codigo"), resultado.getString("url"), resultado.getString("descricao"));
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
     * Busca todos os marcadores no banco de dados.
     *
     * Retorna todos os marcadores porem sem suas associações com imagem e
     * associa
     *
     * @return  marcadores;
     */
    public ArrayList<Marcador> readAll() {
        String sql = "SELECT * FROM marcador";

        try (Connection conexao = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conexao.prepareStatement(sql);
            ResultSet resultado = pre.executeQuery();

            ArrayList<Marcador> marcadores = new ArrayList();
            while (resultado.next()) {
                marcadores.add(new Marcador(
                        resultado.getInt("codigo"),
                        resultado.getString("titulo")
                ));
            }

            return marcadores;
        } catch (SQLException e) {

        }
        return null;
    }

}
