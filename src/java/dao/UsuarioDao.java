package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Imagem;
import model.Usuario;

/**
 * CLASSE RESPONSÁVEL PELA MANIPULAÇÃO DAS INFORMAÇÕES DOS USUÁRIOS NO BANCO DE DADOS.
 * @author campo
 */

public class UsuarioDao {

    
    /**
     * MÉTODO RESPONSAVEL PELA INSERÇÃO DE UM USUÁRO NO SISTEMA.
     * Ao usuário efetura seu registro via formulário, um controller o encaminhará para cá, criando o mesmo no sistema
     * @param usuario
     * @return novoUsuario
     */
    public Usuario create(Usuario usuario) {

        String SQL = "INSERT INTO usuario(nome, email, senha, sobre, img_url) VALUES (?, ?, ?, ?, ?);";

        try (Connection con = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, usuario.getNome());
            pre.setString(2, usuario.getEmail());
            pre.setString(3, usuario.getSenha());
            pre.setString(4, usuario.getSobre());
            pre.setString(5, usuario.getUrlImg());

            if (pre.executeUpdate() != 0) {

                ResultSet rs = pre.getGeneratedKeys();

                while (rs.next()) {
                    usuario.setCodigo(rs.getInt("codigo"));
                }

                if (usuario.getCodigo() != 0) {
                    return usuario;
                }
            }

        } catch (SQLException e) {

        }

        return null;
    }

    /**
     * MÉTODO RESPONSÁVEL PELO LOGIN DE UM USUÁRIO NO SISTEMA E ATUALIZAÇÃO DE TODAS AS INFORMAÇÕES DELE.
     * Ao usuário faz login o sistema, esse método será invocado podendo retornar null ou o objeto do usuário junto de todas as suas informações
     * @param email
     * @param senha
     * @return usuarioLogado
     */
    
    public Usuario readWithEmailAndPassword(String email, String senha) {

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (Connection conexao = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, senha);

            ResultSet rs = pre.executeQuery();

            Usuario usuario = null;
            while (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("sobre"),
                        rs.getString("img_url"));
            }

            if (usuario != null) {

                ArrayList<Imagem> imagens;
                imagens = new ImagemDao().readImagesFromUser(usuario.getCodigo(), conexao);
                usuario.setImagens(imagens);
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
