package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class ConsultarTipoQuarto extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnCancelar;
	private JButton btnAlterar;
	private JButton btnInativar;

	private DefaultTableModel dataModel;
	private JTable tblTipoQuartos;
	private JScrollPane scrollPane;

	private List<TipoDeQuarto> tipo_quartos;

	public ConsultarTipoQuarto() {
		final int x = 100, y = 100, width = 500, height = 350;
		setTitle("Consulta Tipo de Quarto");
		setClosable(true);
		setIconifiable(true);
		setBounds(x, y, width, height);
		setLayout(null);

		configurarDataModel();
		tblTipoQuartos = new JTable(dataModel);
		tblTipoQuartos.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				atualizarModel();
			}

			@Override
			public void focusGained(FocusEvent e) {
				atualizarModel();
			}
		});

		scrollPane = new JScrollPane(tblTipoQuartos);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 0, this.getWidth(), (height - 100));
		tblTipoQuartos.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		final int buttonsY = (height - 75);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, buttonsY, 114, 25);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setBounds(220, buttonsY, 114, 25);
		getContentPane().add(btnAlterar);

		btnInativar = new JButton("Inativar");
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		btnInativar.setBounds(370, buttonsY, 114, 25);
		getContentPane().add(btnInativar);
	}

	private void configurarDataModel() {
		dataModel = new DefaultTableModel() {
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
		atualizarModel();
	}

	private void atualizarModel() {
		limparLinhasModel();
		inserirLinhasModel();
	}

	private void limparLinhasModel() {
		while (dataModel.getRowCount() > 0) {
			dataModel.removeRow(0);
		}
	}

	private void inserirLinhasModel() {
		this.tipo_quartos = new TipoDeQuartoControl().selectDisponiveis();
		for (TipoDeQuarto t : this.tipo_quartos) {
			dataModel.addRow(new Object[] { t.getId(), t.getDescricao(),
					t.getValorDiaria(), t.getQuantidadeAdultos(),
					t.getQuantidadeCriancas() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_alterar")) {

		} else if (nomeEvento.equals("btn_inativar")) {

		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		}
	}
}
