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

import src.com.fatec.gerenciamentohotel.control.EnderecoControl;
import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class HospedeDAO implements IObjectDAO<Hospede, String> {

	@Override
	public void insert(Hospede h) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(
					"insert into hospede values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, h.getCpf());
			pstmt.setString(2, h.getEndereco().getCep());
			pstmt.setString(3, h.getNome());
			pstmt.setString(4, h.getTelefone());
			pstmt.setString(5, h.getCelular());
			pstmt.setString(6, h.getEmail());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nascString = sdf.format(h.getDataNascimento());
			Date nasc = sdf.parse(nascString);
			h.setDataNascimento(nasc);
			pstmt.setDate(7, new java.sql.Date(nasc.getTime()));
			// se "A" hospede Ativo, caso contrário, "I" para Inativo
			pstmt.setString(8, String.valueOf(h.getStatus()));
			pstmt.setInt(9, h.getNumResidencia());
			pstmt.executeQuery();
		} catch (SQLException | ParseException except) {
			String errParser = except.getMessage();
			if (errParser.contains("foreign key constraint")) {
				throw new DAOException(
						"Endereco " + h.getEndereco().getCep() + " nao existe");
			} else if (errParser.contains("Duplicate entry")) {
				throw new DAOException(
						"Hospede " + h.getCpf() + " já existe...");
			} else {
				throw new DAOException("Erro ao inserir Hospede");
			}
		}
	}

	@Override
	public Hospede select(String cpf) throws DAOException {
		Hospede hosp;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select * from hospede where cpf = ?");
			pstmt.setString(1, cpf);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				hosp = new Hospede();
				do {
					EnderecoControl ec = new EnderecoControl();
					hosp.setCpf(rs.getString("cpf"));
					hosp.setEndereco(ec.selectCep(rs.getString("cep")));
					hosp.setNome(rs.getString("nome"));
					hosp.setTelefone(rs.getString("telefone"));
					hosp.setCelular(rs.getString("celular"));
					hosp.setEmail(rs.getString("email"));
					hosp.setDataNascimento(rs.getDate("dat_nascimento"));
					hosp.setNumResidencia(rs.getInt("num_residencia"));
					hosp.setStatus(rs.getString("status").charAt(0));
				} while (rs.next());
				return hosp;
			} else {
				throw new DAOException(
						"Hospede nao encontrado.");
			}
		} catch (SQLException except) {
			throw new DAOException("Erro ao buscar Hospede");
		}
	}

	@Override
	public List<Hospede> selectAll(String docFuncionario) throws DAOException {
		Hospede hosp;
		List<Hospede> l = new ArrayList<>();
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select * from hospede");
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				do {
					hosp = new Hospede();
					EnderecoControl ec = new EnderecoControl();
					hosp.setCpf(rs.getString("cpf"));
					hosp.setEndereco(ec.selectCep(rs.getString("cep")));
					hosp.setNome(rs.getString("nome"));
					hosp.setTelefone(rs.getString("telefone"));
					hosp.setCelular(rs.getString("celular"));
					hosp.setEmail(rs.getString("email"));
					hosp.setDataNascimento(rs.getDate("dat_nascimento"));
					hosp.setNumResidencia(rs.getInt("num_residencia"));
					hosp.setStatus(rs.getString("status").charAt(0));
					l.add(hosp);
				} while (rs.next());
				return l;
			} else {
				throw new DAOException("Nao ha Hospedes");
			}
		} catch (SQLException except) {
			throw new DAOException("Erro ao buscar Hospede");
		}
	}

	@Override
	public void update(Hospede h) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			pstmt = con.prepareStatement("update hospede set cep = ?, nome = ?, telefone = ?, celular = ?, email = ?,"
					+ "dat_nascimento = ?, num_residencia = ? where cpf = ?");
			pstmt.setString(1, h.getEndereco().getCep());
			pstmt.setString(2, h.getNome());
			pstmt.setString(3, h.getTelefone());
			pstmt.setString(4, h.getCelular());
			pstmt.setString(5, h.getEmail());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nascString = sdf.format(h.getDataNascimento());
			Date nasc = sdf.parse(nascString);
			pstmt.setDate(6, new java.sql.Date(nasc.getTime()));
			pstmt.setInt(7, h.getNumResidencia());
			pstmt.setString(8, h.getCpf());
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
				if (res == resInesperado) {
					throw new SQLException();
				}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
			throw new DAOException("Impossivel alterar Hospede");
		}
	}

	@Override
	public void delete(String cpf) throws DAOException {
		try {
			Hospede f = select(cpf);
			String ativarOuInativar = "I";
			if (f.getStatus() == 'I') {
				ativarOuInativar = "A";
			}
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			pstmt = con.prepareStatement("update hospede set status = ? where cpf = ?");
			pstmt.setString(1, ativarOuInativar);
			pstmt.setString(2, cpf);
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if( res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Impossivel alterar Hospede");
		}
	}
}
