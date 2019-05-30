package src.com.fatec.gerenciamentohotel.control.dao;

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

import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.control.HospedeControl;
import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Reserva;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class ReservaDAO implements IObjectDAO<Reserva, String> {

	@Override
	public void insert(Reserva r) throws DAOException {
		try {
			// se retornar null, nao ha registros de reservas ativas pelo
			// hospede
			boolean podeCadastrar = this.select(r.getHospede().getCpf()) == null
					&& r.getQuarto().isDisponivel();
			if (!podeCadastrar) {
				return;
			}
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(" insert into reserva "
					+ " (cpf_funcionario, cpf_hospede, num_quarto, dat_checkin, "
					+ "dat_checkout, status) values (?, ?, ?, ?, ?, ?) ");
			// ID Funcionario vai ser uma propriedade estatica do sistema pra
			// pegar o funcionario logado
			pstmt.setString(1, r.getFuncionario().getCpf()); // Login.getIDFuncionario();
			pstmt.setString(2, r.getHospede().getCpf());
			pstmt.setInt(3, r.getQuarto().getNumQuarto());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String checkinString = sdf.format(r.getCheckIn());
			Date datCheckin = sdf.parse(checkinString);
			pstmt.setDate(4, new java.sql.Date(datCheckin.getTime()));
			pstmt.setDate(5, r.getCheckOut() == null ? null
					: new java.sql.Date(r.getCheckOut().getTime()));
			pstmt.setString(6, String.valueOf(r.getStatus()));
			pstmt.executeQuery();
		} catch (SQLException | ParseException except) {
			throw new DAOException("Reserva não pode ser inserida...");
		}

	}

	@Override
	public Reserva select(String cpfHospede) throws DAOException {
		Reserva res;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select * from reserva where cpf_hospede = ? and status = 'A'");
			pstmt.setString(1, cpfHospede);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				res = new Reserva();
				do {
					HospedeDAO hdao = new HospedeDAO();
					FuncionarioDAO fdao = new FuncionarioDAO();
					QuartoDAO qdao = new QuartoDAO();
					res.setId(rs.getInt("id"));
					res.setFuncionario(
							fdao.select(rs.getString("cpf_funcionario")));
					res.setHospede(hdao.select(rs.getString("cpf_hospede")));
					res.setQuarto(qdao
							.select(String.valueOf(rs.getLong("num_quarto"))));
					res.setCheckIn(rs.getDate("dat_checkin"));
					res.setCheckOut(rs.getDate("dat_checkout"));
					res.setStatus(rs.getString("status").charAt(0));
				} while (rs.next());
				return res;
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao buscar Reserva.");
		}
		return null;
	}

	@Override
	public List<Reserva> selectAll(String cpfHospede) throws DAOException {
		List<Reserva> listReserva;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select * from reserva where cpf_hospede = ? and status = 'I'");
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
					res.setFuncionario(
							fc.selectCPF(rs.getString("cpf_funcionario")));
					res.setHospede(hc.selectCPF(rs.getString("cpf_hospede")));
					res.setQuarto(qc.selectNumQuarto(rs.getLong("num_quarto")));
					res.setCheckIn(rs.getDate("dat_checkin"));
					res.setCheckOut(rs.getDate("dat_checkout"));
					res.setStatus(rs.getString("status").charAt(0));
					listReserva.add(res);
				} while (rs.next());
				return listReserva;
			} else {
				throw new DAOException(
						"Não há histórico de Reservas para o CPF: " + cpfHospede
								+ ".");
			}
		} catch (SQLException except) {
			throw new DAOException("Erro ao buscar histórico de Reservas");
		}
	}

}
