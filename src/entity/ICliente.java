package entity;

import java.util.Date;

public abstract class ICliente {
	protected int codigo;
	protected String nome, sobrenome;
	protected char sexo;
	protected Date dataCadastro = new Date();
	public abstract int getCodigo();
	public abstract void setCodigo(int codigo);
	public abstract String getNome();
	public abstract void setNome(String nome);
	public abstract String getSobrenome();
	public abstract void setSobrenome(String sobrenome);
	public abstract char getSexo();
	public abstract void setSexo(char sexo);
	public abstract Date getDataCadastro();
	public abstract void setDataCadastro(Date dataCadastro);
	@Override
	public String toString() {
		return "[codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", sexo=" + sexo
				+ ", dataCadastro=" + dataCadastro + "]";
	}
}
