package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.QuartoDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Quarto;

public class QuartoControl {

	public void insert(Quarto q) {
		if (q.getNumQuarto() == 0) {
			userMessage("Erro", "Numero do Quarto nao pode ser \"0\"",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (q.getTipoDeQuarto() == null) {
			userMessage("Erro", "Tipo de Quarto Vazio", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (q.getAndar() == 0) {
			userMessage("Erro", "O número do andar não pode ser \"0\"",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			QuartoDAO qdao = new QuartoDAO();
			qdao.insert(q);
			userMessage("Quarto", "Quarto cadastrado!", JOptionPane.NO_OPTION);
		} catch (DAOException e) {
			userMessage("Quarto", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public Quarto selectNumQuarto(long id) {
		try {
			QuartoDAO qdao = new QuartoDAO();
			return qdao.select(String.valueOf(id));
		} catch (DAOException e) {
			userMessage("Quarto", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public List<Quarto> selectTodos() {
		try {
			return new QuartoDAO().selectAll("");
		} catch (DAOException e) {
			userMessage("Quarto", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
}
