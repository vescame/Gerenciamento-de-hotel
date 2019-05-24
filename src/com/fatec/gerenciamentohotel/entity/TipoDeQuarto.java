package com.fatec.gerenciamentohotel.entity;

public class TipoDeQuarto {
    private long id;
    private String tipo;
    private float valorDiaria;
    private short quantidadeAdultos;
    private short quantidateCriacas;
    private boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public short getQuantidadeAdultos() {
        return quantidadeAdultos;
    }

    public void setQuantidadeAdultos(short quantidadeAdultos) {
        this.quantidadeAdultos = quantidadeAdultos;
    }

    public short getQuantidateCriacas() {
        return quantidateCriacas;
    }

    public void setQuantidateCriacas(short quantidateCriacas) {
        this.quantidateCriacas = quantidateCriacas;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStaus(boolean status) {
        this.status = status;
    }
}
