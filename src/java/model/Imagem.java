package model;

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
 * @author Enrico
 */
public final class Imagem {

    private int codigo;
    private String titulo;
    private String url;
    private String descricao;
    private int numeroAcessos;

    /**
     *
     * @param codigo
     * @param titulo
     * @param descricao
     * @param numeroAcessos
     */
    public Imagem(int codigo, String titulo, String descricao, int numeroAcessos, String url) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.numeroAcessos = numeroAcessos;
        this.url = url;
    }

    public Imagem() {

    }

    /**
     *
     * @return
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return
     */
    public int getNumeroAcessos() {
        return numeroAcessos;
    }

    /**
     *
     * @param numeroAcessos
     */
    public void setNumeroAcessos(int numeroAcessos) {
        this.numeroAcessos = numeroAcessos;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
