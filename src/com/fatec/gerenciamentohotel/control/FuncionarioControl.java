package src.com.fatec.gerenciamentohotel.control;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.FuncionarioDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class FuncionarioControl {

	public void novoFuncionario(Funcionario f) {
		if (f.getEndereco() == null) {
			msgError("Endereco vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getCpf().trim().isEmpty()) {
			msgError("Cpf vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getNome().trim().isEmpty()) {
			msgError("Nome vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getTelefone().trim().isEmpty()) {
			msgError("Telefone vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getDataNascimento() == null) {
			msgError("Data de Nascimento vazia", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getLogin().trim().isEmpty()) {
			msgError("Login vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getSenha().trim().isEmpty()) {
			msgError("Senha vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getStatus() == Character.MIN_VALUE) {
			msgError("Status vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (f.getStatus() != 'A') {
			if (f.getStatus() != 'I') {
				msgError("Status Incorreto",
						"Status deve ser A (Ativo) ou I (Inativo)",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if (f.getTipoFuncionario() == null) {
			msgError("Tipo funcionario vazio", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getNumResidencia() == 0) {
			msgError("Numero da casa vazio", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			fdao.insert(f);
		} catch (DAOException e) {
			msgError("Erro Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public Funcionario selectCPF(String cpf) {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			return fdao.select(cpf);
		} catch (DAOException e) {
			msgError("Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
	
	public Funcionario selectLogin(String login) {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			return fdao.select(login);
		} catch (DAOException e) {
			msgError("Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
}
