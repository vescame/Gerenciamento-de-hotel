package entity;

import java.util.Date;

public class Cliente extends ICliente {
	@Override
	public int getCodigo() {
		return codigo;
	}
	@Override
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getSobrenome() {
		return sobrenome;
	}
	@Override
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	@Override
	public char getSexo() {
		return sexo;
	}
	@Override
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	@Override
	public Date getDataCadastro() {
		return dataCadastro;
	}
	@Override
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
