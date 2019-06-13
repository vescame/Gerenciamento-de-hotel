package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.TipoDeQuartoDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class TipoDeQuartoControl {

	public void insert(TipoDeQuarto t) {
		validarTipoDeQuarto(t);
		try {
			TipoDeQuartoDAO tqdao = new TipoDeQuartoDAO();
			tqdao.insert(t);
			userMessage("Tipo De Quarto", "Tipo De quarto cadastrado!", JOptionPane.NO_OPTION);
		} catch (DAOException e) {
			userMessage("Tipo De Quarto", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}


	public TipoDeQuarto selectTipoQuarto(long id) {
		try {
			TipoDeQuartoDAO tqdao = new TipoDeQuartoDAO();
			return tqdao.select(String.valueOf(id));
		} catch (DAOException e) {
			userMessage("Tipo De Quarto", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public List<TipoDeQuarto> selectDisponiveis() {
		try {
			return new TipoDeQuartoDAO().selectAll("");
		} catch (DAOException e) {
			userMessage("Tipo de Quarto", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
	
	public void alterarTipoQuarto(TipoDeQuarto t) {
		validarTipoDeQuarto(t);
		try {
			new TipoDeQuartoDAO().update(t);
			userMessage("Tipo de Quarto", "Tipo de Quarto alterado!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Tipo de Quarto", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void excluirTipoQuarto(String id) {
		try {
			new TipoDeQuartoDAO().delete(id);
			userMessage("Tipo de Quarto", "Tipo de Quarto excluido!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Tipo de Quarto", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
	
	private void validarTipoDeQuarto(TipoDeQuarto t) {
		if (t.getDescricao().trim().isEmpty()) {
			userMessage("Erro", "Escolha um tipo de Quarto valido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (t.getValorDiaria() == 0) {
			userMessage("Erro", "Diaria Vazia", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (t.getQuantidadeAdultos() == 0) {
			userMessage("Erro", "Quantidade Adultos nao pode ser \"0\"",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
