package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Quarto;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class QuartoDAO implements IObjectDAO<Quarto, String> {

	@Override
	public void insert(Quarto q) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// ID não é AUTO_INCREMENT, representa o numero da porta dos quartos
			pstmt = con
					.prepareStatement(" insert into quarto values (?, ?, ?) ");
			pstmt.setInt(1, q.getNumQuarto());
			pstmt.setLong(2, q.getTipoDeQuarto().getId());
			pstmt.setShort(3, q.getAndar());
			pstmt.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				throw new DAOException(
						"Quarto " + q.getNumQuarto() + " ja existe...");
			} else if (e.getMessage()
					.contains("foreign key constraint fails")) {
				throw new DAOException("Tipo de Quarto nao cadastrado");
			} else {
				e.printStackTrace();
				throw new DAOException("Erro ao inserir Quarto");
			}
		}
	}

	@Override
	public Quarto select(String numQuarto) throws DAOException {
		Quarto quar;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select * from quarto where num_quarto = ?");
			pstmt.setLong(1, Long.parseLong(numQuarto));
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				quar = new Quarto();
				do {
					TipoDeQuartoControl tqc = new TipoDeQuartoControl();
					quar.setNumQuarto(rs.getInt("num_quarto"));
					quar.setTipoDeQuarto(
							tqc.selectTipoQuarto(rs.getInt("id_tipo_quarto")));
					quar.setAndar(rs.getShort("andar"));
					quar.setDisponivel(
							this.quartoDisponível(Long.parseLong(numQuarto)));
				} while (rs.next());
				return quar;
			} else {
				throw new DAOException(
						"Quarto " + numQuarto + " não cadastrado.");
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao buscar Quarto");
		}
	}

	@Override
	public List<Quarto> selectAll(String numQuarto) throws DAOException {
		Quarto quar;
		List<Quarto> l = new ArrayList<>();
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select * from quarto");
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				do {
					quar = new Quarto();
					TipoDeQuartoControl tqc = new TipoDeQuartoControl();
					quar.setNumQuarto(rs.getInt("num_quarto"));
					quar.setTipoDeQuarto(
							tqc.selectTipoQuarto(rs.getInt("id_tipo_quarto")));
					quar.setAndar(rs.getShort("andar"));
					if (quartoDisponível(quar.getNumQuarto())) {
						quar.setDisponivel(true);
						l.add(quar);
					}
				} while (rs.next());
				return l;
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao buscar Quarto");
		}
		return null;
	}

	private boolean quartoDisponível(long numQuarto) {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select num_quarto from reserva where dat_checkout is null and num_quarto = ?");
			pstmt.setLong(1, numQuarto);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
