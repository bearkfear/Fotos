/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author campo
 */
public class Marcador {

    private int codigo;
    private String titulo;
    private ArrayList<Associa> associacoes = new ArrayList<>();

    public Marcador(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public Marcador() {
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Associa> getAssociacoes() {
        return associacoes;
    }

    public void setAssociacoes(ArrayList<Associa> associacoes) {
        this.associacoes = associacoes;
    }

}
