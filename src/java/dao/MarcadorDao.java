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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Consumer;
import model.Associa;
import model.Marcador;

/**
 *
 * @author campo
 */
public class MarcadorDao {

    public Marcador create(Marcador marcador) {

        String sql = "INSERT INTO marcador (titulo) VALUES (?)";

        try (Connection conn = new ConnectionFactory().getConexao()) {

            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, marcador.getTitulo());
            pre.executeUpdate();
            ResultSet rs = pre.getGeneratedKeys();

            marcador.setTitulo(null);
            while (rs.next()) {
                marcador.setCodigo(rs.getInt("codigo"));
                marcador.setTitulo(rs.getString("titulo"));

            }

            return marcador;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Marcador readFromAssociation(int codigo) {
        return readWithOutAssociation(codigo);
    }

    private Marcador readWithOutAssociation(int codigo) {
        String sql = "SELECT * FROM marcador WHERE codigo = ?";

        try (Connection conn = new ConnectionFactory().getConexao()) {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, codigo);
            ResultSet rs = pre.executeQuery();

            Marcador marcador = null;

            while (rs.next()) {
                marcador = new Marcador();
                marcador.setCodigo(rs.getInt("codigo"));
                marcador.setTitulo(rs.getString("titulo"));
            }

            return marcador;

        } catch (SQLException e) {

        }

        return null;
    }

    public Marcador read(int codigo) {
        Marcador marcador = readWithOutAssociation(codigo);
        marcador.setAssociacoes(new AssociaDao().readAssociationsFromMarcador(marcador));
        return marcador;
    }

    public static void main(String[] args) {
        ArrayList<Marcador> marcadores = new MarcadorDao().readAll();

        marcadores.forEach(new Consumer<Marcador>() {
            @Override
            public void accept(Marcador marcador) {
                System.out.println("Marcaodor: " + marcador.getTitulo());
                Marcador m = new MarcadorDao().read(marcador.getCodigo());

                ArrayList<Associa> a = m.getAssociacoes();
                System.out.println("Sem associações?" + a.isEmpty());

                a.forEach(i -> {
                    System.out.println("Codigo associação: " + i.getCodigo());
                    System.out.println("Descricao da imagem: " + i.getImagem().getDescricao());
                    System.out.println("Titulo marcador: " + i.getMarcador().getTitulo());
                });
            }
        });
    }

    public ArrayList<Marcador> readAll() {
        String sql = "SELECT * FROM marcador";

        try (Connection conexao = new ConnectionFactory().getConexao()) {

            PreparedStatement pre = conexao.prepareStatement(sql);
            ResultSet resultado = pre.executeQuery();

            ArrayList<Marcador> marcadores = new ArrayList();
            while (resultado.next()) {
                marcadores.add(new Marcador(
                        resultado.getInt("codigo"),
                        resultado.getString("titulo")
                ));
            }

            return marcadores;
        } catch (SQLException e) {

        }
        return null;
    }

    /*Revisar metodo */
    public boolean delete(Marcador marcador) {

        String sql = "delete from marcador WHERE codigo = ?";

        try (Connection conn = new ConnectionFactory().getConexao()) {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, marcador.getCodigo());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
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
>>>>>>> c9fd1c3aad4f03433b9766bd570c4aa484c7b220
