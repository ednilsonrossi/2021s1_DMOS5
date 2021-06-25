package br.edu.ifsp.arq.domos5_2021.meuslivros.model;

public class Livro {

    private String titulo;
    private String autor;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return getTitulo();
    }

    public String getTitulo() {
        return titulo.toUpperCase();
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor.toUpperCase();
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
