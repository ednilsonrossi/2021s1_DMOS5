package br.edu.ifsp.arq.domos5_2021.meuslivros.model;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private boolean emprestado;
    private Amigo amigo;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
        id = 0;
        amigo = null;
    }

    public Livro(int id, String titulo, String autor, boolean emprestado, Amigo amigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = emprestado;
        this.amigo = amigo;
        this.id = id;
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

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }
}
