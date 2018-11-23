<<<<<<< HEAD
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
>>>>>>> c9fd1c3aad4f03433b9766bd570c4aa484c7b220
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Statement;
=======
>>>>>>> c9fd1c3aad4f03433b9766bd570c4aa484c7b220
import java.util.ArrayList;
import model.Associa;
import model.Marcador;

<<<<<<< HEAD
public class AssociaDao {
    
    

    public Associa create(Associa associa) {
        String sql = "INSERT INTO associa (codigo_imagem, codigo_marcador) VALUES (?, ?)";

        try (Connection conn = new ConnectionFactory().getConexao()) {

            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, associa.getImagem().getCodigo());
            pre.setInt(2, associa.getMarcador().getCodigo());

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Associa> readAssociationsFromImage(int codigo) {

        String sql = "SELECT * FROM associa WHERE codigo_imagem = ?";

        try (Connection con = new ConnectionFactory().getConexao()) {

            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, codigo);

            ResultSet rs = pre.executeQuery();

            ArrayList<Associa> associacoes = new ArrayList<>();

            while (rs.next()) {
                Associa associa = new Associa();
                associa.setCodigo(rs.getInt("codigo"));
                associa.setMarcador(new MarcadorDao().readFromAssociation(rs.getInt("codigo_marcador")));
                associacoes.add(associa);
                
            }

            return associacoes;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Associa> readAssociationsFromMarcador(Marcador marcador) {

        String sql = "SELECT * FROM associa WHERE codigo_marcador = ?";

        try (Connection conexao = new ConnectionFactory().getConexao()) {
            PreparedStatement pre = conexao.prepareStatement(sql);

            pre.setInt(1, marcador.getCodigo());
            ResultSet resultado = pre.executeQuery();

            ArrayList<Associa> associacoes = new ArrayList();
            while (resultado.next()) {
                Associa associacao = new Associa(
                        resultado.getInt("codigo"),
                        marcador,
                        new ImagemDao().readImageFromAssociation(resultado.getInt("codigo_imagem")));
                associacoes.add(associacao);
            }

            return associacoes;

        } catch (SQLException e) {

        }

        return null;
    }

    public boolean delete(Associa associa) {
        String sql = "DELETE FROM associa WHERE codigo = ?";

        try (Connection conn = new ConnectionFactory().getConexao()) {
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

=======
/**
 *
 * @author campo
 */
public class AssociaDao {
    
    
    public ArrayList<Associa> readAssociationsFromImage (int codigo) {
        
        String sql = "SELECT * FROM associa WHERE codigo_imagem = ?";
        
        try (Connection con = new ConnectionFactory().getConexao()) {
            
            
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, codigo);
            
            ResultSet rs = pre.executeQuery();
            
            ArrayList<Associa> associacoes = new ArrayList<>();
            
            while (rs.next()) {
                Associa associa = new Associa();
                associa.setMarcador(new MarcadorDao().read(rs.getInt("codigo_marcador")));
                associacoes.add(associa);
            }
            
            return associacoes;
        } catch (SQLException e) {
            
        }
        
        
        return null;
        
        
        
    }
    
}
>>>>>>> c9fd1c3aad4f03433b9766bd570c4aa484c7b220
