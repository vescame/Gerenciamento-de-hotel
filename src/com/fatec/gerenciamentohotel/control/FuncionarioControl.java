package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.FuncionarioDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class FuncionarioControl {

	public void novoFuncionario(Funcionario f) {
		if (!validarFuncionario(f)) {
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

	public void alterarFuncionario(Funcionario f) {
		if (!validarFuncionario(f)) {
			return;
		}
		try {
			new FuncionarioDAO().update(f);
			userMessage("Funcionario", "Funcionario alterado!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void inativarFuncionario(String cpf) {
		try {
			new FuncionarioDAO().delete(cpf);
			userMessage("Funcionario", "Funcionario In / Ativado!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Funcionario", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
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
	
	private boolean validarFuncionario(Funcionario f) {
		if (f.getEndereco() == null) {
			userMessage("Erro", "Endereco vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getCpf().trim().isEmpty()) {
			userMessage("Erro", "Cpf vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getNome().trim().isEmpty()) {
			userMessage("Erro", "Nome vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getTelefone().trim().isEmpty()) {
			userMessage("Erro", "Telefone vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getDataNascimento() == null) {
			userMessage("Erro", "Data de Nascimento vazia",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getLogin().trim().isEmpty()) {
			userMessage("Erro", "Login vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getSenha().trim().isEmpty()) {
			userMessage("Erro", "Senha vazia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getStatus() == Character.MIN_VALUE) {
			userMessage("Erro", "Status vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (f.getStatus() != 'A') {
			if (f.getStatus() != 'I') {
				userMessage("Status Incorreto",
						"Status deve ser A (Ativo) ou I (Inativo)",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (f.getTipoFuncionario() == null) {
			userMessage("Erro", "Tipo funcionario vazio",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (f.getNumResidencia() == 0) {
			userMessage("Erro", "Numero da casa vazio",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
