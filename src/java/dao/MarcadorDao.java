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
import model.Marcador;

/**
 *
 * @author campo
 */
public class MarcadorDao {

    MarcadorDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Marcador read (int codigo) {
        String sql = "SELECT * FROM marcador WHERE codigo = ?";
        
        try (Connection conn = new ConnectionFactory().getConexao()) {
         
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, codigo);
            ResultSet rs = pre.executeQuery();
            
            Marcador marcador = null;
            
            while (rs.next()) {
                marcador = new Marcador ();
                marcador.setCodigo(rs.getInt("codig"));
                marcador.setTitulo("titulo");        
            }
            
            return marcador;
            
            
        } catch (SQLException e) {
            
        }
        return null;
    }
    
    
}
