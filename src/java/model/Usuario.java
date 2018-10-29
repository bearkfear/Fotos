package model;

import java.util.ArrayList;

/*


  CREATE TABLE Usuario
    (
        codigo SERIAL PRIMARY KEY,
        nome VARCHAR,
        email VARCHAR UNIQUE,
        senha VARCHAR,
        sobre VARCHAR
    );

 */
 
/**
 * 
 * Classe Usuario, contem os metodos e atributos aplicaveis a um usuario
 *
 * @author Enrico
 */
public final class Usuario {

    private int codigo;
    private String nome;
    
    private String email;
    private String senha;
    private String sobre;
    private String urlImg;
    private ArrayList<Imagem> imagens;

    /**
     * Define um usuario com as suas informaçóes carregadas do banco de dados;
     *
     * @param codigo
     * @param nome
     * @param email
     * @param senha
     * @param sobre
     * @param urlImg
     */
    public Usuario(int codigo, String nome, String email, String senha, String sobre, String urlImg) {
	this.setCodigo(codigo);
	this.setEmail(email);
	this.setNome(nome);
	this.setSenha(senha);
	this.setSobre(sobre);
	this.setUrlImg(urlImg);
    }

    /**
     * Cria um usuario com as informações basicas definindo a imagem dele como
     * default.png
     *
     * @param nome
     * @param email
     * @param senha
     * @param sobre
     */
    public Usuario(String nome, String email, String senha, String sobre) {
	this.nome = nome;
	this.email = email;
	this.senha = senha;
	this.sobre = sobre;
	this.urlImg = "default.png";
    }

    /**
     * construtor vazio de um usuario
     */
    public Usuario() {

    }

    /**
     * Seta uma url para a imagem do usuario
     *
     * @param url
     */
    public void setUrlImg(String url) {
	this.urlImg = url;
    }

    /**
     * Retorna a url da imagem do usuario
     *
     * @return
     */
    public String getUrlImg() {
	return this.urlImg;
    }

    /**
     * Adiciona uma imagem a um usuario específico
     *
     * @param imagem
     */
    public void adicionarImagem(Imagem imagem) {
	this.imagens.add(imagem);
    }

    /**
     *
     * @param imagem
     */
    public void removerImagem(Imagem imagem) {
	this.imagens.remove(imagem);

    }

    /**
     * Retorna a quantidade de imagens de um usuario
     *
     * @return
     */
    public int quantidadeImagens() {
	return this.imagens.size();
    }

    /**
     * Retorna uma imagem pela sua posição
     *
     * @param posicao
     * @return
     */
    public Imagem getImagem(int posicao) {
	return this.imagens.get(posicao);
    }

    /**
     * Retorna o codigo do usuario
     *
     * @return
     */
    public int getCodigo() {
	return codigo;
    }

    /**
     * Seta um codigo ao usuario
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    /**
     * Retorna o nome do usuario
     *
     * @return
     */
    public String getNome() {
	return nome;
    }

    /**
     * Seta o nome do usuario
     *
     * @param nome
     */
    public void setNome(String nome) {
	this.nome = nome;
    }

    /**
     * retorna o email do usuario
     *
     * @return email
     */
    public String getEmail() {
	return email;
    }

    /**
     * seta o email do usuario
     *
     * @param email
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * Seta uma senha para o usuario
     *
     * @return
     */
    public String getSenha() {
	return senha;
    }

    /**
     * retorna a senha do usuario
     *
     * @param senha
     */
    public void setSenha(String senha) {
	this.senha = senha;
    }

    /**
     * retorna uma breve descrição do usuario
     *
     * @return
     */
    public String getSobre() {
	return sobre;
    }

    /**
     * Seta uma descrição do usuario
     *
     * @param sobre
     */
    public void setSobre(String sobre) {
	this.sobre = sobre;
    }

    /**
     * Retorna um arrayList de imagens do usuario
     *
     * @return
     */
    public ArrayList<Imagem> getImagens() {
	return imagens;
    }

    /**
     * Seta um arraylist de imagens do usuario
     *
     * @param imagens
     */
    public void setImagens(ArrayList<Imagem> imagens) {
	this.imagens = imagens;
    }
}
