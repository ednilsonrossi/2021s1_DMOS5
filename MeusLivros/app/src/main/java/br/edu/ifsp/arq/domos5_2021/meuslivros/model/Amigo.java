package br.edu.ifsp.arq.domos5_2021.meuslivros.model;

import androidx.annotation.NonNull;

public class Amigo {

    private int id;
    private String nome;

    public Amigo(String nome) {
        this.nome = nome;
    }

    public Amigo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome().toUpperCase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
