package src.com.fatec.gerenciamentohotel.control;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.TipoDeQuartoDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class TipoDeQuartoControl {

	public void insert(TipoDeQuarto t) {
		if (t.getTipo().trim().isEmpty()) {
			userMessage("Erro", "Escolha um tipo de Quarto válido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (t.getValorDiaria() == 0) {
			userMessage("Erro", "Diaria Vazia", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (t.getQuantidadeAdultos() == 0) {
			userMessage("Erro", "Quantidade Adultos não pode ser \"0\"",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
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

	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
}
