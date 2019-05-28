package src.com.fatec.gerenciamentohotel.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.entity.Reserva;

public class ReservaControl {
	public void insert(Reserva r) {
		if (r.getCheckIn() == null) {
			msgError("CheckIn Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		/*
		 * checkout pode ser null, validamos assim se esta ou não ativo o registro da
		 * reserva if (r.getCheckOut() == null) { msgError("CheckOut Vazio", "Erro",
		 * JOptionPane.ERROR_MESSAGE); return; }
		 */
		if (r.getFuncionario() == null) {
			// se o funcionario estiver vazio tem alguma merda muito grande rolando
			msgError("Funcionario Vazio, contate o administrador", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (r.getHospede() == null) {
			msgError("Hospede Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (r.getQuarto() == null) {
			msgError("Quarto Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (r.getStatus() == Character.MIN_VALUE) {
			msgError("Status Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (r.getStatus() != 'A') {
			if (r.getStatus() != 'I') {
				msgError("Status deve ser A (Ativo) ou I (Inativo)", "Status Incorreto", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		try {
			// se retornar null, nao ha registros de reservas ativas pelo hospede
			boolean podeCadastrar = this.selectReserva(r.getHospede().getCpf()) == null;
			if (!podeCadastrar) {
				return;
			}
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(" insert into reserva "
					+ " (cpf_funcionario, cpf_hospede, num_quarto, dat_checkin, dat_checkout, status) "
					+ " values (?, ?, ?, ?, ?, ?) ");
			// ID Funcionario vai ser uma propriedade estatica do sistema pra pegar o
			// funcionario logado
			pstmt.setString(1, r.getFuncionario().getCpf()); // Login.getIDFuncionario();
			pstmt.setString(2, r.getHospede().getCpf());
			pstmt.setInt(3, r.getQuarto().getNumQuarto());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String checkinString = sdf.format(r.getCheckIn());
			Date datCheckin = sdf.parse(checkinString);
			pstmt.setDate(4, new java.sql.Date(datCheckin.getTime()));
			pstmt.setDate(5, 
					r.getCheckOut() == null ?
							null : new java.sql.Date(r.getCheckOut().getTime())
							);
			pstmt.setString(6, String.valueOf(r.getStatus()));
			pstmt.executeQuery();
		} catch (SQLException | ParseException except) {
			String errParser = except.getMessage();
			except.printStackTrace();
			if (errParser.contains("Unknown column")) {
				msgError("Coluna da tabela esta incorreta!\nContate um administrador.", "Reserva",
						JOptionPane.ERROR_MESSAGE);
			} else {
				msgError("Erro desconhecido...\nContate um administrador", "Reserva", JOptionPane.ERROR_MESSAGE);
				except.printStackTrace();
			}
		}
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public Reserva selectReserva(String cpfHospede) {
		Reserva res;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select * from reserva where cpf_hospede = ? and status = 'A'");
			pstmt.setString(1, cpfHospede);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				res = new Reserva();
				do {
					HospedeControl hc = new HospedeControl();
					FuncionarioControl fc = new FuncionarioControl();
					QuartoControl qc = new QuartoControl();
					res.setId(rs.getInt("id"));
					res.setFuncionario(fc.selectDocFuncionario(rs.getString("cpf_funcionario")));
					res.setHospede(hc.selectDocHospede(rs.getString("cpf_hospede")));
					res.setQuarto(qc.selectQuarto(rs.getLong("num_quarto")));
					res.setCheckIn(rs.getDate("dat_checkin"));
					res.setCheckOut(rs.getDate("dat_checkout"));
					res.setStatus(rs.getString("status").charAt(0));
				} while (rs.next());
				return res;
			}
		} catch (SQLException e) {
			String errParser = e.getMessage();
			if (errParser.contains("not found")) {
				msgError("Não encontrado", "Funcionario", JOptionPane.CLOSED_OPTION);
			}
		}
		return null;
	}
	
	public List<Reserva> selectHistoricoReservas(String cpfHospede) {
		List<Reserva> listReserva;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select * from reserva where cpf_hospede = ? and status = 'I'");
			pstmt.setString(1, cpfHospede);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				listReserva = new ArrayList<>();
				do {
					Reserva res = new Reserva();
					HospedeControl hc = new HospedeControl();
					FuncionarioControl fc = new FuncionarioControl();
					QuartoControl qc = new QuartoControl();
					res.setId(rs.getInt("id"));
					res.setFuncionario(fc.selectDocFuncionario(rs.getString("cpf_funcionario")));
					res.setHospede(hc.selectDocHospede(rs.getString("cpf_hospede")));
					res.setQuarto(qc.selectQuarto(rs.getLong("num_quarto")));
					res.setCheckIn(rs.getDate("dat_checkin"));
					res.setCheckOut(rs.getDate("dat_checkout"));
					res.setStatus(rs.getString("status").charAt(0));
					listReserva.add(res);
				} while (rs.next());
				return listReserva;
			}
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Unknown column")) {
				msgError("Coluna incorreta", "Reserva", JOptionPane.CLOSED_OPTION);
			} else if (errParser.contains("Unknown database")) {
				msgError("Base de dados desconhecida...", "Reserva", JOptionPane.CLOSED_OPTION);
			} else {
				except.printStackTrace();
			}
		}
		return null;
	}
}
