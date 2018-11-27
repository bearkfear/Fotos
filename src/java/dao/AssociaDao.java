package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;
import model.Associa;
import model.Marcador;

public class AssociaDao {

    public int create(int codigoImagem, int codigoMarcador) {
        String sql = "INSERT INTO associa (codigo_imagem, codigo_marcador) VALUES (?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection()) {

            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, codigoImagem);
            pre.setInt(2, codigoMarcador);
            pre.executeUpdate();
            
            ResultSet rs = pre.getGeneratedKeys();
            rs.next();
            return rs.getInt("codigo");
            

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public ArrayList<Associa> readAssociationsFromImage(int codigo, Connection conexao) {

        String sql = "SELECT * FROM associa WHERE codigo_imagem = ?";

        try {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);

            ResultSet rs = pre.executeQuery();

            ArrayList<Associa> associacoes = new ArrayList<>();

            while (rs.next()) {
                Associa associa = new Associa();
                associa.setCodigo(rs.getInt("codigo"));
                associa.setMarcador(new MarcadorDao().readFromAssociation(rs.getInt("codigo_marcador"), conexao));
                associacoes.add(associa);

            }

            return associacoes;
            
            
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Associa> readAssociationsFromMarcador(Marcador marcador, Connection conexao) {

        String sql = "SELECT * FROM associa WHERE codigo_marcador = ?";

        try {
            PreparedStatement pre = conexao.prepareStatement(sql);

            pre.setInt(1, marcador.getCodigo());
            ResultSet resultado = pre.executeQuery();

            ArrayList<Associa> associacoes = new ArrayList();
            while (resultado.next()) {
                Associa associacao = new Associa(
                        resultado.getInt("codigo"),
                        marcador,
                        new ImagemDao().readImageFromAssociation(resultado.getInt("codigo_imagem"), conexao));
                associacoes.add(associacao);
            }

            return associacoes;

        } catch (SQLException e) {

        }

        return null;
    }

    public boolean delete(Associa associa) {
        String sql = "DELETE FROM associa WHERE codigo = ?";

        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, associa.getCodigo());
            if (pre.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

}
