package src.com.fatec.gerenciamentohotel.control;

import java.util.Map;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.FuncionarioDAO;
import src.com.fatec.gerenciamentohotel.control.exceptions.FuncionarioDAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class FuncionarioControl {
	FuncionarioDAO fdao;
	
	public FuncionarioControl() {
		 this.fdao = new FuncionarioDAO();
	}
	
	public void insert(Funcionario f) {
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
			msgError("Data de Nascimento vazia", "Erro", JOptionPane.ERROR_MESSAGE);
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
			if (f.getStatus() != 'I' ) {
				msgError("Status deve ser A (Ativo) ou I (Inativo)", "Status Incorreto", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}		
		if (f.getTipoFuncionario() == null) {
			msgError("Tipo funcionario vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			this.fdao.insert(f);
		} catch (FuncionarioDAOException e) {
			Map<String, String> h = ExceptionFormat.formatarTituloECorpo(e.getMessage());
			msgError(h.get("title"), h.get("description"), JOptionPane.WARNING_MESSAGE);
		}
	}

	private void msgError(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public Funcionario selectDocFuncionario(String docFuncionario) {
		return fdao.selectDocFuncionario(docFuncionario);
	}
}
