package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
	private JButton btnCancelar;
	private JButton btnAlterar;
	private JButton btnInativar;

	private JScrollPane scrollPane;

	private DefaultTableModel dataModel;
	private JTable tblQuartos;

	private List<Quarto> quartos = new ArrayList<>();

	public ConsultarQuarto() {
		final int x = 100, y = 100, width = 600, height = 350;
		setTitle("Consulta Quartos");
		setClosable(true);
		setIconifiable(true);
		setBounds(x, y, width, height);
		setLayout(null);

		configurarDataModel();
		tblQuartos = new JTable(dataModel);
		tblQuartos.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				atualizarModel();
			}

			@Override
			public void focusGained(FocusEvent e) {
				atualizarModel();
			}
		});

		scrollPane = new JScrollPane(tblQuartos);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 0, this.getWidth(), (height - 100));
		tblQuartos.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, 278, 114, 25);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setBounds(300, 278, 114, 25);
		getContentPane().add(btnAlterar, BorderLayout.EAST);

		btnInativar = new JButton("Inativar");
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		btnInativar.setBounds(450, 278, 114, 25);
		getContentPane().add(btnInativar, BorderLayout.EAST);

	}

	private void configurarDataModel() {
		dataModel = new DefaultTableModel() {
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
		this.quartos = new QuartoControl().selectTodos();
		if (this.quartos != null) {
			for (Quarto t : this.quartos) {
				dataModel.addRow(new Object[] { t.getNumQuarto(), t.getAndar(),
						t.getTipoDeQuarto().getId(),
						t.getTipoDeQuarto().getDescricao(),
						t.getTipoDeQuarto().getValorDiaria() });
			}
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
