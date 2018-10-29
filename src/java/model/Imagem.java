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
 * Classe Imagem representa as cada imagem que o sistema suportará. 
 * Somente um usuário pode fazer download porem, qualquer pessoa, logada ou anonima poderá
 * as ver
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
     * Contrutor de montagem, recebe "n" informações para criar uma imagem no
     * sistema
     *
     * @param codigo
     * @param titulo
     * @param descricao
     * @param numeroAcessos
     * @param url
     */
    public Imagem(int codigo, String titulo, String descricao, int numeroAcessos, String url) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.numeroAcessos = numeroAcessos;
        this.url = url;
    }
/**
 * Cria uma imagem sem o código;
 * @param titulo
 * @param descricao
 * @param numeroAcessos
 * @param url 
 */
    public Imagem(String titulo, String descricao, int numeroAcessos, String url) {
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setNumeroAcessos(numeroAcessos);
        this.setUrl(url);
    }
    
    /**
     * construtor vazio
     */
    public Imagem() {

    }

    /**
     *  pega o codigo da imagem
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     *  seta um codigo para a imagem
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     *  retorna o titulo da imagem
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *  seta um titulo para a image
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *retorna a descrição da imagem
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *seta a descrição da imagem
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *retorna o numero de acessos
     * @return
     */
    public int getNumeroAcessos() {
        return numeroAcessos;
    }

    /**
     *seta um numero de acessos
     * @param numeroAcessos
     */
    public void setNumeroAcessos(int numeroAcessos) {
        this.numeroAcessos = numeroAcessos;
    }

    
    /**
     * seta a url da imagem no sistema
     * @param url 
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * retorna a url da imagem no sistema
     * @return url
     */

    public String getUrl() {
        return this.url;
    }
}
