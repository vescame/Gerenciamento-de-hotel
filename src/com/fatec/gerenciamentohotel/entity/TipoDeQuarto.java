package src.com.fatec.gerenciamentohotel.entity;

public class TipoDeQuarto {
	private long id;
	private String tipo;
	private float valorDiaria;
	private short quantidadeAdultos;
	private short quantidateCriancas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(float valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public short getQuantidadeAdultos() {
		return quantidadeAdultos;
	}

	public void setQuantidadeAdultos(short quantidadeAdultos) {
		this.quantidadeAdultos = quantidadeAdultos;
	}

	public short getQuantidadeCriancas() {
		return quantidateCriancas;
	}

	public void setQuantidadeCriancas(short quantidateCriancas) {
		this.quantidateCriancas = quantidateCriancas;
	}

	@Override
	public String toString() {
		return "TipoDeQuarto [id=" + id + "\ntipo=" + tipo + "\nvalorDiaria="
				+ valorDiaria + "\nquantidadeAdultos=" + quantidadeAdultos
				+ "\nquantidateCriancas=" + quantidateCriancas + "]";
	}

}
