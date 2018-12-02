package model;

/**
 * CLASSE DE MODELAGEM RESPONSÁVEL PELAS ASSOCIAÇÕES ENTRE MARCADORES E IMAGENS.
 * SENDO QUE UM MARCADOR PODE ESTAR ASSOCIADO A VÁRIAS IMAGENS, E UMA IMAGEM
 * PODE ESTAR ASSOCIADA A VÁRIOS MARCADORES. PORÉM UM MARCADOR NUNCA ESTARÁ DUAS
 * OU MAIS VEZES ASSOCIADO A UMA MESMA IMAGEM E, UMA IMAGEM NUNCA TERÁ DUAS
 * VEZES OU MAIS O MESMO MARCADOR.
 *
 * @author campo
 */
public class Associa {

    private int codigo;
    private Marcador marcador;
    private Imagem imagem;

    public Associa(int codigo, Marcador marcador, Imagem imagem) {
        this.codigo = codigo;
        this.marcador = marcador;
        this.imagem = imagem;
    }

    public Associa() {

    }

    public Marcador getMarcador() {
        return marcador;
    }

    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
