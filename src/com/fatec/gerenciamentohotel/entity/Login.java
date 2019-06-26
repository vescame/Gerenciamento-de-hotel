package com.fatec.gerenciamentohotel.entity;

public class Login {
	private String login;
	private String senha;
	private String cpf;
	
	public Login (String login, String senha) {
		this.login = login;
		this.senha = senha;
		this.cpf = null;
	}
	
	public Login (String cpf) {
		this.login = null;
		this.senha = null;
		this.cpf = cpf;
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
