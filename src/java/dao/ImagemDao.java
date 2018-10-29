/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import model.Imagem;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

            //insert into imagem (titulo, descricao, data, numeroAcessos, usuario_codigo, url);
            String sql = "INSERT INTO imagem (titulo, descricao, numeroacessos, usuario_codigo, url) VALUES (?,?,?,?,?)";
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

            pre.executeQuery();

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
     * @param codigoUsuario
     * @return
     */
    public ArrayList<Imagem> readImagens(int codigoUsuario) {

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

            /*
            Atualiza a entidade Imagem no banco de dados;
             */
            String sql = "UPDATE imagem SET titulo = ?, descricao = ?, numeroAcessos = ?";
            PreparedStatement pre = conexao.prepareStatement(sql);
            // titulo
            pre.setString(1, imagem.getTitulo());
            // descricao 
            pre.setString(2, imagem.getDescricao());
            // numeroAcessos 
            pre.setInt(3, imagem.getNumeroAcessos());

            return pre.executeUpdate() != 0;

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
            return pre.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    /**
     * Metodo que consulta todas as imagens indepentende de usuário, e traz as
     * mais recentes adicionadas Também orderna por codigo e limita a 10 imagens
     * por consulta
     *
     * @return ArrayList
     */
    public ArrayList<Imagem> readImagens() {

        // ORDENADO PELO CODIGO E COM LIMITE DE 10
        String sql = "SELECT * FROM imagem ORDER BY CODIGO DESC LIMIT 10";

        try (Connection con = new ConectaBancoDados().getConexao()) {

            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            ArrayList<Imagem> imagens = new ArrayList<>();

            // imagem: titulo, descricao, **data, numeroAcessos, usuario_codigo, url
            while (rs.next()) {
                imagens.add(new Imagem(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("descricao"), rs.getInt("numeroacessos"), rs.getString("url")));
            }

            return imagens;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

}
