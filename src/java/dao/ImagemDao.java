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
import model.Imagem;

/**
 *
 * @author campo
 */
public class ImagemDao {
    
    
    
    public ArrayList<Imagem> readImagesFromUser(int codigo) {
        
        String sql = "SELECT * FROM imagem WHERE usuario_codigo = ?";
        
        try (Connection con = new ConnectionFactory().getConexao()) {
            
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, codigo);
            ResultSet rs = pre.executeQuery();
            
            ArrayList<Imagem> imagens = new ArrayList();
            while (rs.next()) {
                Imagem image = new Imagem();
                image.setUrl(rs.getString("url"));
                image.setCodigo(rs.getInt("codigo"));
                image.setDescricao(rs.getString("descricao"));
                image.setAssociacoes(new AssociaDao().readAssociationsFromImage(image.getCodigo()));
                image.getAssociacoes().forEach(marcador -> marcador.setImagem(image));
                imagens.add(image);
            }
            return imagens;
            
        } catch (SQLException e) {
            
        }
        
        return null;
    }
    
    
    
}
