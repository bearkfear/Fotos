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
import java.util.ArrayList;
import model.Associa;
import model.Marcador;

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
