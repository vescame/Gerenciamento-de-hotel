package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.FuncionarioDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class FuncionarioControl {

	public void novoFuncionario(Funcionario f) {
		if (f.getEndereco() == null) {
			userMessage("Endereco vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getCpf().trim().isEmpty()) {
			userMessage("Cpf vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getNome().trim().isEmpty()) {
			userMessage("Nome vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getTelefone().trim().isEmpty()) {
			userMessage("Telefone vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getDataNascimento() == null) {
			userMessage("Data de Nascimento vazia", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getLogin().trim().isEmpty()) {
			userMessage("Login vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getSenha().trim().isEmpty()) {
			userMessage("Senha vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getStatus() == Character.MIN_VALUE) {
			userMessage("Status vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (f.getStatus() != 'A') {
			if (f.getStatus() != 'I') {
				userMessage("Status Incorreto",
						"Status deve ser A (Ativo) ou I (Inativo)",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if (f.getTipoFuncionario() == null) {
			userMessage("Tipo funcionario vazio", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getNumResidencia() == 0) {
			userMessage("Numero da casa vazio", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			fdao.insert(f);
			userMessage("Funcionario", "Funcionario cadastrado!",
					JOptionPane.NO_OPTION);
		} catch (DAOException e) {
			userMessage("Erro Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public Funcionario selectCPF(String cpf) {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			return fdao.select(cpf);
		} catch (DAOException e) {
			userMessage("Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public Funcionario selectLogin(String login) {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			return fdao.select(login);
		} catch (DAOException e) {
			userMessage("Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public List<Funcionario> selectTodos() {
		try {
			return new FuncionarioDAO().selectAll("");
		} catch (DAOException e) {
			userMessage("Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
}
