package br.edu.ednilsonrossi.meualunofirebase.model;

public class Aluno {

    private String prontuario;
    private String nome;
    private String email;

    public Aluno() {
    }

    public Aluno(String prontuario, String nome, String email) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
