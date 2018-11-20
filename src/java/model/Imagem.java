package model;

import java.util.ArrayList;

/*
 CREATE TABLE Imagem
    (
        codigo SERIAL PRIMARY KEY,
        titulo VARCHAR,
        descricao VARCHAR,
        numeroAcessos INTEGER,
        usuario_codigo INTEGER,
        url VARCHAR,
        FOREIGN KEY (usuario_codigo) REFERENCES usuario(codigo)
    );

  
 */
/**
 *
 * Classe Imagem representa as cada imagem que o sistema suportará. 
 * Somente um usuário pode fazer download porem, qualquer pessoa, logada ou anonima poderá
 * as ver
 *
 * @author Enrico
 */
public final class Imagem {

    private int codigo;
    private String url;
    private String descricao;
    private ArrayList<Associa> associacoes;

    
    
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
