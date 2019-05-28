package src.com.fatec.gerenciamentohotel.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.entity.ItemServico;

public class ItemServicoControl {
	List<ItemServico> itens = new ArrayList<>();

	public void insert(ItemServico i) {
		if (i.getId() == 0) {
			msgError("Id Vazio");
			return;
		}
		if (i.getValor() == 0) {
			msgError("Valor Vazio");
			return;
		}
		if (i.getDescricao().trim().isEmpty()) {
			msgError("Descricao Vazio");
			return;
		}
		this.itens.add(i);
	}

	private void msgError(String corpo) {
		JOptionPane.showMessageDialog(null, corpo, "ERRO", JOptionPane.ERROR_MESSAGE);
	}

	public ItemServico selectItemServico(long id) {
		for (ItemServico i : this.itens) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}
}
