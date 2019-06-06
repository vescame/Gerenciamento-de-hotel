package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class ConsultarTipoQuarto extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable tblTipoQuartos;
	private List<TipoDeQuarto> tipo_quartos;

	public ConsultarTipoQuarto() {
		final int height = 300, width = 500;
		setTitle("Consulta Tipo de Quarto");
		setClosable(true);
		setBounds(100, 100, width, height);
		getContentPane().setLayout(null);
		setLayout(new BorderLayout(15, 15));

		tblTipoQuartos = new JTable(dataModelTipoDeQuarto());
		JScrollPane scrollPane = new JScrollPane(tblTipoQuartos);
		scrollPane.setVisible(true);
		scrollPane.setBounds(this.getX(), this.getY(), this.getWidth(),
				this.getHeight());
		tblTipoQuartos.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);
	}

	private DefaultTableModel dataModelTipoDeQuarto() {
		DefaultTableModel dataModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "codigo", "descricao", "preco",
					"qtd_adultos", "qtd_criancas" };

			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public String getColumnName(int index) {
				return columnNames[index];
			}

		};
		atualizarDadosTabela(dataModel);
		return dataModel;
	}

	private void atualizarDadosTabela(DefaultTableModel m) {
		this.tipo_quartos = new TipoDeQuartoControl().selectDisponiveis();
		for (TipoDeQuarto t : this.tipo_quartos) {
			m.addRow(new Object[] { t.getId(), t.getTipo(), t.getValorDiaria(),
					t.getQuantidadeAdultos(), t.getQuantidadeCriancas() });
		}
	}
}
