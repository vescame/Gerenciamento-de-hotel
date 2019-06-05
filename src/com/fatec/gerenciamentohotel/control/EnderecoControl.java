package src.com.fatec.gerenciamentohotel.control;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.EnderecoDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Endereco;

public class EnderecoControl {
	public void insert(Endereco e) {
		if (e.getCep().trim().isEmpty()) {
			msgError("Erro", "Cep Vazio", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getRua().trim().isEmpty()) {
			msgError("Erro", "Rua Vazia", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getBairro().trim().isEmpty()) {
			msgError("Erro", "Bairro Vazio", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getCidade().trim().isEmpty()) {
			msgError("Erro", "Cidade Vazia", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getUf().trim().isEmpty()) {
			msgError("Erro", "UF Vazio", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			EnderecoDAO edao = new EnderecoDAO();
			edao.insert(e);
		} catch (DAOException ex) {
			msgError("Endereco", ex.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public Endereco selectCep(String cep) {
		try {
			EnderecoDAO edao = new EnderecoDAO();
			return edao.select(cep);
		} catch (DAOException e) {
			msgError("Endereco", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void msgError(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
}
