package com.fatec.gerenciamentohotel.entity;

import com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class Funcionario extends Pessoa {
	/*
	 * id cep cpf nome telefone celular email dat_nascimento status login senha
	 * tipo_funcionario num_residencia
	 */
	private EFuncionario tipoFuncionario;
	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(EFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	@Override
	public String toString() {
		return "Funcionario [tipoFuncionario=" + tipoFuncionario + "\nlogin="
				+ login + "\nsenha=" + senha + "\ngetId()=" + getId()
				+ "\ngetEndereco()=" + getEndereco() + "\ngetCpf()=" + getCpf()
				+ "\ngetNome()=" + getNome() + "\ngetTelefone()="
				+ getTelefone() + "\ngetCelular()=" + getCelular()
				+ "\ngetEmail()=" + getEmail() + "\ngetDataNascimento()="
				+ getDataNascimento() + "\ngetStatus()=" + getStatus() + "]";
	}

}
