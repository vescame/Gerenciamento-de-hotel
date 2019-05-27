package src.com.fatec.gerenciamentohotel.entity;

import src.com.fatec.gerenciamentohotel.entity.enums.*;

public class Funcionario extends Pessoa {
	/* layout em banco
	 * id
	 * cep
	 * cpf
	 * nome
	 * telefone
	 * celular
	 * email
	 * dat_nascimento
	 * status
	 * login
	 * senha
	 * tipo_funcionario
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

}
