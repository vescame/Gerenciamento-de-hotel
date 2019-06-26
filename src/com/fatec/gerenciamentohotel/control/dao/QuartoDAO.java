package com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import com.fatec.gerenciamentohotel.entity.Quarto;
import com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class QuartoDAO implements IObjectDAO<Quarto, String> {

	@Override
	public void insert(Quarto q) throws DAOException {
		try {
			Connection conn = ConnectionDB.getInstance().getConnection();
			final String sql = " insert into quarto values (?, ?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
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
				throw new DAOException("Quarto nao encontrado.");
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
					
					quar.setNumQuarto(rs.getInt("num_quarto"));
					quar.setTipoDeQuarto(new TipoDeQuartoDAO().select(rs.getString("id_tipo_quarto")));
					quar.setAndar(rs.getShort("andar"));
					if (quartoDisponível(quar.getNumQuarto())) {
						quar.setDisponivel(true);
						l.add(quar);
					}
				} while (rs.next());
				return l;
			} else {
				throw new DAOException("Nao ha quartos cadastrados.");
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao buscar Quarto");
		}
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

	@Override
	public void update(Quarto q) throws DAOException {
		try {
			Connection conn = ConnectionDB.getInstance().getConnection();
			final String sql = " update quarto set id_tipo_quarto = ?, andar = ? where num_quarto = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, q.getTipoDeQuarto().getId());
			pstmt.setShort(2, q.getAndar());
			pstmt.setInt(3, q.getNumQuarto());
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if (res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Nao foi possivel alterar Quarto");
		}
	}

	@Override
	public void delete(String numQuarto) throws DAOException {
		try {
			Connection conn = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from quarto where num_quarto = ?");
			pstmt.setLong(1, Long.parseLong(numQuarto));
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if (res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Nao foi possivel inativar Quarto");
		}
	}
}
