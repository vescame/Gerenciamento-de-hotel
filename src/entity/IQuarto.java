package entity;

import java.util.Date;

public abstract class IQuarto {
	protected long id;
	protected float preco;
	protected String tamanho;
	protected Date dataCheckIn = new Date();
	protected Cliente cliente = new Cliente();
	public abstract Cliente getCliente();
	public abstract void setCliente(Cliente cliente);
	protected abstract long getId();
	protected abstract void setId(long id);
	protected abstract float getPreco();
	protected abstract void setPreco(float preco);
	protected abstract String getTamanho();
	protected abstract void setTamanho(String tamanho);
	protected abstract Date getDataCheckIn();
	protected abstract void setDataCheckIn(Date dataCheckIn);
}
