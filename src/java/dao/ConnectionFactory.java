package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsavel por conectar no banco de dados postgresql.
 * Disponibiliza uma fabrica de conexão
 *
 * @author Enrico
 */
public class ConnectionFactory {

    private final String url = "jdbc:postgresql://localhost:5432/siteImg";
    private final String driver = "org.postgresql.Driver";
    private final String usuario = "postgres";
    private final String senha = "1234";

    /**
     * Retorna uma conexão com o banco de dados
     *
     * 
     * @return Connection
     */
    public Connection getConnection() {
        Connection conexao = null;

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            return conexao;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

}
