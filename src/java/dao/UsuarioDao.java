/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Usuario;

/**
 *
 * @author Enrico
 */
public class UsuarioDao {

    /**
     * Cria um usuario no banco de dados retornando o mesmo usuario com o codigo
     * dele caso não consiga criar o usuario no banco, retorna null
     *
     *
     * @param usuario
     * @return usuario
     */
    public Usuario create(Usuario usuario) {

        try (Connection conexao = new ConectaBancoDados().getConexao()) {
            String sql = "INSERT INTO usuario (nome, email, senha, sobre, img_url) VALUES (?,?,?,?,?);";
            PreparedStatement pre = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, usuario.getNome());
            pre.setString(2, usuario.getEmail());
            pre.setString(3, usuario.getSenha());
            pre.setString(4, usuario.getSobre());
            pre.setString(5, usuario.getUrlImg());

            if (pre.executeUpdate() != 0) {

                ResultSet rs = pre.getGeneratedKeys();

                int codigo = 0;

                while (rs.next()) {
                    codigo = rs.getInt("codigo");
                }

                if (codigo != 0) {
                    usuario.setCodigo(codigo);
                    return usuario;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     *
     * @param codigo
     * @return
     */
    public Usuario read(int codigo) {
        try (Connection conexao = new ConectaBancoDados().getConexao()) {
            String sql = "SELECT * sobre FROM usuario WHERE usuario.codigo = ?";
            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);
            ResultSet rs = pre.executeQuery();
            rs.next();

            Usuario usuario = new Usuario(
                    rs.getInt("codigo"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("sobre"),
                    rs.getString("img_url"));

            usuario.setImagens(new ImagemDao().readImagens(codigo));

            return usuario;

        } catch (SQLException ex) {
        }

        return null;
    }

    /**
     * Retorna a url da imagem de um usuario;
     *
     * @param email
     * @return url
     */
    public Usuario read(String email) {

        String sql = "SELECT img_url FROM usuario WHERE email = ?";

        try (Connection con = new ConectaBancoDados().getConexao()) {
            PreparedStatement pre = con.prepareStatement(sql);

            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setUrlImg(rs.getString("img_url"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * Lê um usuario do banco de dados a partir do seu email e codigo;
     *
     * @param email
     * @param senha
     * @return
     */
    
    
    public Usuario read(String email, String senha) {

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection con = new ConectaBancoDados().getConexao()) {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, senha);
            ResultSet rs = pre.executeQuery();

            Usuario usuario;

            while (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("sobre"),
                        rs.getString("img_url"));
                usuario.setImagens(new ImagemDao().readImagens(usuario));
                return usuario;
            }

        } catch (SQLException ex) {

        }

        return null;
    }

    /**
     * Atualiza as informações de um usuario de acordo com o seu codigo
     *
     * possivel atualizar: nome, senha, sobre e a url da imagem de perfil;
     *
     * @param usuario
     * @return
     */
    public boolean update(Usuario usuario) {

        try (Connection conexao = new ConectaBancoDados().getConexao()) {
            String sql = "UPDATE usuario SET nome = ?, senha = ?, sobre = ?, img_url = ? WHERE usuario.codigo = ?";
            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setString(1, usuario.getNome());
            pre.setString(2, usuario.getSenha());
            pre.setString(3, usuario.getSobre());
            pre.setString(4, usuario.getUrlImg());

            if (pre.executeUpdate() != 0) {
                return false;
            }

        } catch (SQLException e) {

        }

        return false;
    }

    /**
     * Metodo que remove um usuário do sistema incluindo suas imagens
     *
     * @param usuario
     * @return true or false
     */
    public boolean delete(Usuario usuario) {

        String sql = "DELETE FROM usuario WHERE usuario.codigo = ?";
        try (Connection conexao = new ConectaBancoDados().getConexao()) {

            if (new ImagemDao().delete(usuario)) {

                PreparedStatement pre = conexao.prepareStatement(sql);
                pre.setInt(1, usuario.getCodigo());
                if (pre.executeUpdate() != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException ex) {

        }
        return false;
    }

}
