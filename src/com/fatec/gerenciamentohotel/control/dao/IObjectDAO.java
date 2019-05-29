package src.com.fatec.gerenciamentohotel.control.dao;

import java.util.List;

public interface IObjectDAO<T> {
	public void insert(T obj) throws DAOException;
	public T select(T obj) throws DAOException;
	public List<T> selectAll(T obj) throws DAOException;
}
