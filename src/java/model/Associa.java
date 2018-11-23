/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author campo
 */
public class Associa {
    
    private int codigo;
    private Marcador marcador;
    private Imagem imagem;

    public Associa (int codigo, Marcador marcador, Imagem imagem) {
        this.codigo = codigo;
        this.marcador = marcador;
        this.imagem = imagem;
    }
    
    
    public Associa () {
        
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

    public int getCodigo () {
        return codigo;
    }
    
    public void setCodigo (int codigo) {
        this.codigo = codigo;
    }
}
