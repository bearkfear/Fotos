package model;

import java.util.ArrayList;

/**
 *
 * Classe Imagem representa as cada imagem que o sistema suportará. Somente um
 * usuário pode fazer download porem, qualquer pessoa, logada ou anonima poderá
 * as ver
 *
 * @author Enrico
 */
public final class Imagem {

    private int codigo;
    private String url;
    private String descricao;
    private ArrayList<Associa> associacoes = new ArrayList<Associa>();

    public Imagem() {

    }

    public Imagem(int codigo, String url, String descricao) {
        this.url = url;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Associa> getAssociacoes() {
        return associacoes;
    }

    public void setAssociacoes(ArrayList<Associa> associacoes) {
        this.associacoes = associacoes;
    }

}
