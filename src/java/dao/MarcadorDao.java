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
 * @author campo
 */
public class MarcadorDao {

    public Marcador create(Marcador marcador) {

        String sql = "INSERT INTO marcador (titulo) VALUES (?)";

        try (Connection conn = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
     * @param codigoMarcador
     * @return ArrayList<Imagens>
     */
    
    
    public ArrayList<Imagem> readImagesFromMarcador (int codigoMarcador) {
        
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
     * Le um marcador do banco de dados.
     *
     * Ao ler o marcador do banco de dados retorna também suas associações, e
     * imagens envolvidas.
     *
     * @param codigo
     * @return
     */
    
    
    
    public Marcador read(int codigo) {

        try (Connection conexao = new ConnectionFactory().getConnection()) {
            Marcador marcador = readWithOutAssociation(codigo, conexao);
            marcador.setAssociacoes(new AssociaDao().readAssociationsFromMarcador(marcador, conexao));
            return marcador;

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
     * @return ArrayList<Marcador> marcadores;
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

    /*Revisar metodo */
    public boolean delete(Marcador marcador) {

        String sql = "delete from marcador WHERE codigo = ?";

        try (Connection conn = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, marcador.getCodigo());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

}
