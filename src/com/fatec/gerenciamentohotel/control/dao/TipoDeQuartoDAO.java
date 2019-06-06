package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class TipoDeQuartoDAO implements IObjectDAO<TipoDeQuarto, String> {

	@Override
	public void insert(TipoDeQuarto t) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(" Insert into tipo_quarto "
					+ " (tipo_quarto, preco, qtd_adultos, qtd_criancas) values  (?, ?, ?, ?) ");
			pstmt.setString(1, t.getTipo());
			pstmt.setFloat(2, t.getValorDiaria());
			pstmt.setInt(3, t.getQuantidadeAdultos());
			pstmt.setInt(4, t.getQuantidadeCriancas());
			pstmt.executeQuery();
		} catch (SQLException except) {
			if (except.getMessage().contains("Duplicate entry")) {
				throw new DAOException(
						"Código " + t.getId() + " do Tipo de Quarto \""
								+ t.getTipo() + "\" já existe...");
			} else {
				throw new DAOException("Erro ao inserir Tipo de Quarto");
			}
		}
	}

	@Override
	public TipoDeQuarto select(String numQuarto) throws DAOException {
		TipoDeQuarto tQuarto;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("Select * from tipo_quarto where id = ?");
			pstmt.setLong(1, Long.parseLong(numQuarto));
			ResultSet rs = pstmt.executeQuery();
			if (!rs.wasNull()) {
				tQuarto = new TipoDeQuarto();
				while (rs.next()) {
					tQuarto.setId(rs.getInt("id"));
					tQuarto.setTipo(rs.getString("tipo_quarto"));
					tQuarto.setValorDiaria(rs.getFloat("preco"));
					tQuarto.setQuantidadeAdultos(rs.getShort("qtd_adultos"));
					tQuarto.setQuantidadeCriancas(rs.getShort("qtd_criancas"));
				}
				return tQuarto;
			} else {
				throw new DAOException("Quarto de número: " + numQuarto
						+ " não existe.\nCadastre-o antes.");
			}
		} catch (SQLException except) {
			throw new DAOException("Erro ao buscar Tipo de Quarto");
		}
	}

	@Override
	public List<TipoDeQuarto> selectAll(String numQuarto) throws DAOException {
		TipoDeQuarto tQuarto;
		List<TipoDeQuarto> l = new ArrayList<>();
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("Select * from tipo_quarto");
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				do {
					tQuarto = new TipoDeQuarto();
					tQuarto.setId(rs.getInt("id"));
					tQuarto.setTipo(rs.getString("tipo_quarto"));
					tQuarto.setValorDiaria(rs.getFloat("preco"));
					tQuarto.setQuantidadeAdultos(rs.getShort("qtd_adultos"));
					tQuarto.setQuantidadeCriancas(rs.getShort("qtd_criancas"));
					l.add(tQuarto);
				} while (rs.next());
				return l;
			} else {
				throw new DAOException("Quarto de número: " + numQuarto
						+ " não existe.\nCadastre-o antes.");
			}
		} catch (SQLException except) {
			throw new DAOException("Erro ao buscar Tipo de Quarto");
		}
	}
}
