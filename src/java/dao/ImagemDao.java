package dao;

import java.sql.Connection;
import model.Imagem;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Usuario;

/**
 * Implementa os CRUD's no banco de dados Cria, Le, Atualiza e remove
 *
 * @author Enrico
 */
public class ImagemDao {

    /**
     *
     * Essa função cria uma instancia da Entidade imagem no banco de dados
     *
     * Recebe como argumento uma imagem e retorna a chave da imagem no banco de
     * dados
     *
     * @param imagem
     * @param codigoUsuario
     * @return Imagem
     */
    public Imagem create(Imagem imagem, int codigoUsuario) {
        try (Connection conexao = new ConectaBancoDados().getConexao()) {

            String sql = "INSERT INTO imagem (titulo, descricao, numeroacessos, usuario_codigo, url) VALUES (?,?,?,?,?)";

            // retorna a chave gerada
            PreparedStatement pre = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //titulo
            pre.setString(1, imagem.getTitulo());

            // descricao
            pre.setString(2, imagem.getDescricao());
            // numeroAcessos
            pre.setInt(3, imagem.getNumeroAcessos());
            //usuario_codigo
            pre.setInt(4, codigoUsuario);

            //url
            pre.setString(5, imagem.getUrl());

            /*
            Visto que para utilizar o getGeneratedKeys é necessário fazer uso do executeUpdate() em vez do executeQuery()
             */
            pre.executeUpdate();

            // retorna o codigo gerado pelo banco de dados;
            ResultSet rs = pre.getGeneratedKeys();

            int codigo = 0;
            while (rs.next()) {
                codigo = rs.getInt("codigo");
            }
            if (codigo > 0) {
                imagem.setCodigo(codigo);
                return imagem;
            }

        } catch (SQLException e) {

        }

        // não conseguiu criar a imagem no banco 
        return null;
    }

    /**
     * Lê uma imagem pelo seu codigo
     *
     * @param codigo
     * @return Imagem
     */
    public Imagem read(int codigo) {

        try (Connection conexao = new ConectaBancoDados().getConexao()) {

            String sql = "SELECT * FROM imagem WHERE imagem.codigo = ?";

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);
            ResultSet rs = pre.executeQuery();
            rs.next();
            Imagem imagem = new Imagem(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getInt("numeroacessos"),
                    rs.getString("url")
            );

            return imagem;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

    /**
     * Busca todas as imagens que um usuario tem retornando um ArrayList
     *
     * @param usuario
     * @return
     */
    public ArrayList<Imagem> readImagens(Usuario usuario) {
        int codigoUsuario = usuario.getCodigo();
        try (Connection conexao = new ConectaBancoDados().getConexao()) {

            String sql = "SELECT * FROM imagem WHERE imagem.usuario_codigo = ?";

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigoUsuario);
            ResultSet rs = pre.executeQuery();

            ArrayList<Imagem> imagens = new ArrayList();

            while (rs.next()) {

                imagens.add(new Imagem(
                        rs.getInt("codigo"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("numeroacessos"),
                        rs.getString("url")
                ));
            }
            return imagens;
        } catch (SQLException ex) {

        }

        return null;

    }

    /**
     * Atualiza as informações da imagem no banco de dados
     *
     * @param imagem
     * @return
     */
    public boolean update(Imagem imagem) {
        try (Connection conexao = new ConectaBancoDados().getConexao()) {

            //Atualiza a entidade Imagem no banco de dados;
            String sql = "UPDATE imagem SET titulo = ?, descricao = ?, numeroAcessos = ?";
            PreparedStatement pre = conexao.prepareStatement(sql);
            // titulo
            pre.setString(1, imagem.getTitulo());
            // descricao 
            pre.setString(2, imagem.getDescricao());
            // numeroAcessos 
            pre.setInt(3, imagem.getNumeroAcessos());

            if (pre.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    /**
     * Remove a imagem do banco de dados
     *
     * @param codigo
     * @return
     */
    public boolean delete(int codigo) {
        try (Connection conexao = new ConectaBancoDados().getConexao()) {
            String sql = "DELETE FROM imagem WHERE imagem.codigo = ?";
            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);

            if (pre.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    
    /**
     * Remove todas as imagens de um usúário, Feito para ser utilizado ao remover um usuario do sistema
     * @param usuario
     * @return 
     */
    public boolean delete (Usuario usuario) {
        
        String sql = "DELETE FROM imagem WHERE imagem.usuario_codigo = ?";
        
        try (Connection conexao = new ConectaBancoDados().getConexao()) {
            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, usuario.getCodigo());
            
            if (pre.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    /**
     * Metodo que consulta todas as imagens indepentende de usuário, e traz as
     * mais recentes adicionadas Também orderna por codigo e limita a 10 imagens
     * por consulta
     *
     * @param offset
     * @return ArrayList
     */
    public ArrayList<Imagem> readImagens(int offset) {

        int limite = 10;

        // ORDENADO PELO CODIGO E COM LIMITE DE 10
        String sql = "SELECT * FROM imagem ORDER BY codigo DESC LIMIT ? OFFSET ?"; // implementar offset em futura manutenção

        try (Connection con = new ConectaBancoDados().getConexao()) {

            PreparedStatement pre = con.prepareStatement(sql);

            pre.setInt(1, limite);
            pre.setInt(2, offset);

            ResultSet rs = pre.executeQuery();

            ArrayList<Imagem> imagens = new ArrayList<>();

            // imagem: titulo, descricao, numeroAcessos, usuario_codigo, url
            while (rs.next()) {
                imagens.add(
                        new Imagem(
                                rs.getInt("codigo"),
                                rs.getString("titulo"),
                                rs.getString("descricao"),
                                rs.getInt("numeroacessos"),
                                rs.getString("url")));
            }

            return imagens;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

}
