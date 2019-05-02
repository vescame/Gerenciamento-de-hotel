package entity;

import java.util.Date;

public class Quarto extends IQuarto{
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public float getPreco() {
		return preco;
	}
	@Override
	public void setPreco(float preco) {
		this.preco = preco;
	}
	@Override
	public String getTamanho() {
		return tamanho;
	}
	@Override
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	@Override
	public Date getDataCheckIn() {
		return dataCheckIn;
	}
	@Override
	public void setDataCheckIn(Date fabricacao) {
		this.dataCheckIn = fabricacao;
	}
	@Override
	public Cliente getCliente() {
		return cliente;
	}
	@Override
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Tamanho: " + this.tamanho + " preco: " + this.preco 
				+ " Cliente: " + this.cliente.getNome() + " " + this.cliente.getSobrenome();
	}
}