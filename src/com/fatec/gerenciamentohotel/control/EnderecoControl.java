package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.EnderecoDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Endereco;

public class EnderecoControl {
	public void insert(Endereco e) {
		if (!validarEndereco(e)) {
			return;
		}
		try {
			EnderecoDAO edao = new EnderecoDAO();
			edao.insert(e);
			userMessage("Endereco", "Endereco cadastrado!", JOptionPane.NO_OPTION);
		} catch (DAOException ex) {
			userMessage("Endereco", ex.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public Endereco selectCep(String cep) {
		try {
			EnderecoDAO edao = new EnderecoDAO();
			return edao.select(cep);
		} catch (DAOException e) {
			userMessage("Endereco", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
	
	public List<Endereco> selectTodos(){
		try {
			return new EnderecoDAO().selectAll("");
		} catch (DAOException e) {
			userMessage("Endereco", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
	
	public void alterarEndereco(Endereco e) {
		if (!validarEndereco(e)) {
			return;
		}
		try {
			new EnderecoDAO().update(e);
			userMessage("Endereco", "Endereco alterado!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException ex) {
			userMessage("Hospede", ex.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void excluirEndereco(String cep) {
		try {
			new EnderecoDAO().delete(cep);
			userMessage("Endereco", "Endereco Excluido!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Endereco", e.getMessage(),
					 JOptionPane.WARNING_MESSAGE);
		}
	}	

	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
	
	private boolean validarEndereco(Endereco e) {
		if (e.getCep().trim().isEmpty()) {
			userMessage("Erro", "Cep Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (e.getRua().trim().isEmpty()) {
			userMessage("Erro", "Rua Vazia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (e.getBairro().trim().isEmpty()) {
			userMessage("Erro", "Bairro Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (e.getCidade().trim().isEmpty()) {
			userMessage("Erro", "Cidade Vazia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (e.getUf().trim().isEmpty()) {
			userMessage("Erro", "UF Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
