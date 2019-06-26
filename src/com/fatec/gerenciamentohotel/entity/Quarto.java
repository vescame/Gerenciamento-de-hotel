package com.fatec.gerenciamentohotel.entity;

public class Quarto {
	private int numQuarto; // ID PRIMARY KEY
	private TipoDeQuarto tipoDeQuarto;
	private short andar;
	private boolean disponivel;

	public int getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}

	public TipoDeQuarto getTipoDeQuarto() {
		return tipoDeQuarto;
	}

	public void setTipoDeQuarto(TipoDeQuarto tipoDeQuarto) {
		this.tipoDeQuarto = tipoDeQuarto;
	}

	public short getAndar() {
		return andar;
	}

	public void setAndar(short andar) {
		this.andar = andar;
	}
	
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Quarto [id=" + numQuarto + "\ntipoDeQuarto=" + tipoDeQuarto
				+ "\nandar=" + andar + "]";
	}
}
