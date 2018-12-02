package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Associa;
import model.Marcador;


/**
 * Classe inteiramente designada a leitura e gravação de associações no banco de dados.
 * Todas as requisições ao acesso de uma associação entre um Marcador ou uma Imagem são feitas aqui
 * @author campo
 */

public class AssociaDao {

    
    /**
     * O MÉTODO CREATE DA CLASSE ASSOCIA É RESPONSAVEL POR CRIAR ASSOCIAÇÕES.
     * As associações que são feitas por um usuário ao pubicar uma imagem no seu perfil.
     * designada a esse método, que informa o banco de dados que a imagem e o código do marcador vão ser associados
     * @param codigoImagem
     * @param codigoMarcador
     * @return codigoGerado
     */
    
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

    /**
     * MÉTODO RESPONSÁVEL POR LER ASSOCIAÇÕES DE UMA IMAGEM.
     * Quando uma imagem é carregada, pode ser feita a leitura de todas as associações dela. Devolvendo-as para o método invocador.
     * É feito o compartilhamento da conexão pelo método chamador pois, esse método nunca é chamado sozinho mas, sempre por outro responsável por ele
     * @param codigo
     * @param conexao
     * @return associações
     */
    
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

    
    /**
     * MÉTODO RESPONSÁVEL POR LER AS ASSOCIAÇÕES DE UM MARCADOR.
     * Quando um marcador é lido, ele precisa saber quais imagens estão associadas a ele
     * então é invocado o método responsável por ler as imagens associadas a ele
     * @param marcador
     * @param conexao
     * @return associaçõesDoMarcador
     */
    
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
}
