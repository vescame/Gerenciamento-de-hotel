package src.com.fatec.gerenciamentohotel.control;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.EnderecoDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.EnderecoDAOException;
import src.com.fatec.gerenciamentohotel.entity.Endereco;

public class EnderecoControl {
    public void insert(Endereco e) {
	if (e.getCep().trim().isEmpty()) {
	    msgError("Cep Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	if (e.getRua().trim().isEmpty()) {
	    msgError("Rua Vazia", "Erro", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	if (e.getBairro().trim().isEmpty()) {
	    msgError("Bairro Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	if (e.getCidade().trim().isEmpty()) {
	    msgError("Cidade Vazia", "Erro", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	if (e.getUf().trim().isEmpty()) {
	    msgError("UF Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	try {
	    EnderecoDAO edao = new EnderecoDAO();
	    edao.insert(e);
	} catch (EnderecoDAOException ex) {
	    msgError("Endereco", ex.getMessage(), JOptionPane.WARNING_MESSAGE);
	}
    }

    public Endereco selectCep(String cep) {
	try {
	    EnderecoDAO edao = new EnderecoDAO();
	    return edao.select(cep);
	} catch (EnderecoDAOException e) {
	    msgError("Endereco", e.getMessage(), JOptionPane.WARNING_MESSAGE);
	}
	return null;
    }
    
    private void msgError(String titulo, String mensagem, int errorType) {
	JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
    }
}
