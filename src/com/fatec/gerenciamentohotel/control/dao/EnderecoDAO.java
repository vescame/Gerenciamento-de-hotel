package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class EnderecoDAO implements IObjectDAO<Endereco, String> {

	@Override
	public void insert(Endereco e) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"insert into endereco values (?, ?, ?, ?, ?)");
			pstmt.setString(1, e.getCep());
			pstmt.setString(2, e.getRua());
			pstmt.setString(3, e.getBairro());
			pstmt.setString(4, e.getCidade());
			pstmt.setString(5, e.getUf());
			pstmt.execute();
		} catch (SQLException ex) {
			if (ex.getMessage().contains("Duplicate entry")) {
				throw new DAOException("Endereco do cep " + e.getCep() + " ja existe...");
			} else {
				throw new DAOException("Erro ao inserir Endereco");
			}
		}

	}

	@Override
	public Endereco select(String cep) throws DAOException {
		Endereco e;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select * from endereco where cep = ?");
			pstmt.setInt(1, Integer.parseInt(cep));
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				e = new Endereco();
				do {
					e.setCep(rs.getString("cep"));
					e.setRua(rs.getString("rua"));
					e.setBairro(rs.getString("bairro"));
					e.setCidade(rs.getString("cidade"));
					e.setUf(rs.getString("uf"));
				} while (rs.next());
				return e;
			} else {
				throw new DAOException("Endereco nao existe");
			}
		} catch (SQLException | ParseException except) {
			throw new DAOException("Erro ao buscar Endereco");
		}
	}

	@Override
	public List<Endereco> selectAll(String cep) throws DAOException {
		Endereco end;
		List<Endereco> l = new ArrayList<>();
		Connection con;
		try {
			con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from endereco");
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				do {
					end = new Endereco();
					end.setCep(rs.getString("cep"));
					end.setBairro(rs.getString("bairro"));
					end.setCidade(rs.getString("cidade"));
					end.setRua(rs.getString("rua"));
					end.setUf(rs.getString("uf"));
					l.add(end);
				} while (rs.next());
				return l;
			} else {
				throw new DAOException("Nao ha enderecos");
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao buscar endereco");
		}
	}

	@Override
	public void update(Endereco e) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("update endereco set rua = ?, bairro = ?,"
					+ " cidade = ?, uf = ? where cep = ?");
			pstmt.setString(1, e.getRua());
			pstmt.setString(2, e.getBairro());
			pstmt.setString(3, e.getCidade());
			pstmt.setString(3, e.getUf());
			pstmt.setString(4, e.getCep());
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if(res == resInesperado) {
				throw new SQLException();			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Impossivel alterar endereco");
		}
	}

	@Override
	public void delete(String cep) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("delete from endereco"
					+ " where cep = ?");
			pstmt.setString(1, cep);
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if(res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Impossivel excluir endereco");
		}
	}

}
