<<<<<<< HEAD
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

        String sql = "SELECT codigo, url, descricao FROM imagem WHERE usuario_codigo = ?";

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
                image.getAssociacoes().forEach(associacao -> associacao.setImagem(image));
                imagens.add(image);
            }
            return imagens;

        } catch (SQLException e) {

        }

        return null;
    }

    public Imagem read(int codigo) {
        Imagem imagem = readWithOutAssociation(codigo);
        imagem.setAssociacoes(new AssociaDao().readAssociationsFromImage(codigo));
        imagem.getAssociacoes().forEach(i -> i.setImagem(imagem));
        
        return null;
    }

    private Imagem readWithOutAssociation(int codigo) {
        String sql = "SELECT descricao, url FROM imagem WHERE codigo = ?";

        try (Connection conexao = new ConnectionFactory().getConexao()) {

            PreparedStatement pre = conexao.prepareStatement(sql);
            pre.setInt(1, codigo);

            ResultSet resultado = pre.executeQuery();

            Imagem imagem = null;

            while (resultado.next()) {
                imagem = new Imagem();
                imagem.setUrl(resultado.getString("url"));
                imagem.setCodigo(codigo);
                imagem.setDescricao(resultado.getString("descricao"));
            }

            return imagem;

        } catch (SQLException e) {

        }

        return null;
    }

    public Imagem readImageFromAssociation(int codigo) {
        Imagem imagem = readWithOutAssociation(codigo);

        if (imagem != null) {
            return imagem;
        }

        return null;
    }

}
=======
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
>>>>>>> c9fd1c3aad4f03433b9766bd570c4aa484c7b220
