package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsavel por conectar no banco de dados postgresql
 *
 * @author Enrico
 */
public class ConectaBancoDados {

    private final String url = "jdbc:postgresql://localhost:5432/siteImg";
    private final String driver = "org.postgresql.Driver";
    private final String usuario = "postgres";
    private final String senha = "1234";

    /**
     * retorna uma conexão com o banco de dados
     *
     * @return
     */
    public Connection getConexao() {
        Connection conexao = null;

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {
        }

        return conexao;
    }

}
