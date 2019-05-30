package src.com.fatec.gerenciamentohotel.entity.dao;

import java.util.List;

import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;

public interface IObjectDAO<T, S> {
	public void insert(T obj) throws DAOException;

	public T select(S obj) throws DAOException;

	public List<T> selectAll(S obj) throws DAOException;
}
