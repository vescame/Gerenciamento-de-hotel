package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.entity.Quarto;

public class ConsultarQuarto extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblQuartos;

	private List<Quarto> quartos = new ArrayList<>();

	public ConsultarQuarto() {
		setTitle("Consulta Quartos");
		setClosable(true);
		setBounds(100, 100, 600, 350);
		getContentPane().setLayout(null);

		tblQuartos = new JTable(dataModelTipoDeQuarto());
		JScrollPane scrollPane = new JScrollPane(tblQuartos);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 0, this.getWidth(), this.getHeight() - 100);
		tblQuartos.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, 278, 114, 25);
		getContentPane().add(btnCancelar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setBounds(300, 278, 114, 25);
		getContentPane().add(btnAlterar, BorderLayout.EAST);

		JButton btnInativar = new JButton("Inativar");
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		btnInativar.setBounds(450, 278, 114, 25);
		getContentPane().add(btnInativar, BorderLayout.EAST);

	}

	private DefaultTableModel dataModelTipoDeQuarto() {
		DefaultTableModel dataModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "id_quarto", "andar", "cod. tipo_quarto",
					"desc. tipo_quarto", "valor_quarto" };

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
		this.quartos = new QuartoControl().selectTodos();
		for (Quarto t : this.quartos) {
			m.addRow(new Object[] { t.getNumQuarto(), t.getAndar(),
					t.getTipoDeQuarto().getId(), t.getTipoDeQuarto().getTipo(),
					t.getTipoDeQuarto().getValorDiaria() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_alterar")) {

		} else if (nomeEvento.equals("btn_cancelar")) {
			this.dispose();
		} else if (nomeEvento.equals("btn_inativar")) {

		}

	}
}
