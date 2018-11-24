
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Imagem;
import model.Usuario;

public class UsuarioDao {

    // create Read Update Delete
    public Usuario create(Usuario usuario) {

        String SQL = "INSERT INTO usuario(nome, email, senha, sobre, img_url)";

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
            
            ArrayList<Imagem> imagens;
            imagens = new ImagemDao().readImagesFromUser(usuario.getCodigo(), conexao);
            usuario.setImagens(imagens);
            return usuario;
        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * Atualiza as informações de um usuario de acordo com o seu codigo. O
     * método é capaz de atualizar informações que estão no banco de dados, não
     * lida com as imagens do mesmo.
     *
     * @param nome
     * @param sobre
     * @return true ou false
     */
    public boolean update(String nome, String sobre) {

        try (Connection conexao = new ConnectionFactory().getConnection()) {
            String sql = "UPDATE usuario SET nome = ?, sobre = ? WHERE usuario.codigo = ?";
            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setString(1, nome);
            pre.setString(2, sobre);
            if (pre.executeUpdate() != 0) {
                return true;
            }

        } catch (SQLException e) {

        }

        return false;
    }

}
