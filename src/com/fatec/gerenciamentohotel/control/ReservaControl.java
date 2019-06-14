package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.ReservaDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Reserva;

public class ReservaControl {
	public void insert(Reserva r) {
		if (!validarCampos(r)) {
			return;
		}
		try {
			ReservaDAO rdao = new ReservaDAO();
			rdao.insert(r);
			userMessage("Reserva", "Reserva cadastrada com sucesso!", JOptionPane.WARNING_MESSAGE);
		} catch (DAOException e) {
			userMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void update(Reserva r) {
		if (!validarCampos(r)) {
			return;
		}
		try {
			ReservaDAO rdao = new ReservaDAO();
			rdao.checkOutReserva(r);
			userMessage("Reserva", "Reserva atualizada", JOptionPane.WARNING_MESSAGE);
		} catch (DAOException e) {
			userMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public Reserva selectReserva(String cpfHospede) {
		try {
			ReservaDAO rdao = new ReservaDAO();
			return rdao.select(cpfHospede);
		} catch (DAOException e) {
			userMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
	
	public void deletarReserva(long cpfHospede) {
		try {
			new ReservaDAO().delete(String.valueOf(cpfHospede));
			userMessage("Reserva", "Reserva deletada!", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public List<Reserva> selectHistoricoReservas(String cpfHospede) {
		try {
			ReservaDAO rdao = new ReservaDAO();
			return rdao.selectAll(cpfHospede);
		} catch (DAOException e) {
			userMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
	
	private boolean validarCampos(Reserva r) {
		if (r.getCheckIn() == null) {
			userMessage("Erro", "CheckIn Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// checkout pode ser null, assim validamos se esta ou não ativo o
		if (r.getFuncionario() == null) {
			// funcionario nao pode ser vazio, vai ser atribuido por propriedade
			// estatica do sistema, validando o login e procurando suas
			// informacoes no banco
			userMessage("Erro", "Funcionario Vazio, contate um administrador",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (r.getHospede() == null) {
			userMessage("Erro", "A reserva deve conter um hospede valido",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (r.getQuarto() == null) {
			userMessage("Erro", "A reserva deve conter um quarto valido",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (r.getStatus() == Character.MIN_VALUE) {
			userMessage("Erro", "Status Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (r.getStatus() != 'A') {
			if (r.getStatus() != 'I') {
				userMessage("Status Incorreto",
						"Status deve ser A (Ativo) ou I (Inativo)",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		return true;
	}
}
