package com.fatec.gerenciamentohotel.entity;

public class Hospede {
    private Endereco endereco;
    private long id;
    private String cpf;
    private String nome;
    private boolean status;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStaus(boolean status) {
        this.status = status;
    }

}
