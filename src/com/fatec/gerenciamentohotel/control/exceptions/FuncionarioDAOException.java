package src.com.fatec.gerenciamentohotel.control.exceptions;

public class FuncionarioDAOException extends Exception {
	private static final long serialVersionUID = 1L;
	private final String description;
	private final String title;
	
	public FuncionarioDAOException(String title, String description) {
		this.description = description;
		this.title = title;
	}
	
	@Override
	public String getMessage() {
		// return super.getMessage();
		return String.format("Erro FuncionarioDAO:\n"
				+ "title:[%s]\n"
				+ "description:[%s]",
				this.title,
				this.description);
	}
	
}
