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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import src.com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import src.com.fatec.gerenciamentohotel.boundary.window.MainWindow;
import src.com.fatec.gerenciamentohotel.boundary.window.filtro_usuario.FiltroDeComponentes;
import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.Quarto;

public class ConsultarQuarto extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNumeroQuarto;
	private JTextField txtTipo;
	private JTextField txtAndar;

	private JLabel lblNmeroDoQuarto;
	private JLabel lblTipoDeQuarto;
	private JLabel lblAndar;

	private JButton btnBuscar;
	private JButton btnLimpar;
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

		lblNmeroDoQuarto = new JLabel("Numero do Quarto:");
		lblNmeroDoQuarto.setBounds(10, 10, 130, 15);
		getContentPane().add(lblNmeroDoQuarto);

		txtNumeroQuarto = new JTextField();
		txtNumeroQuarto.setBounds(150, 7, 50, 25);
		getContentPane().add(txtNumeroQuarto);
		txtNumeroQuarto.setDocument(new JTextFieldLimit(4));

		lblAndar = new JLabel("Andar:");
		lblAndar.setBounds(10, 50, 50, 15);
		getContentPane().add(lblAndar);

		txtAndar = new JTextField();
		txtAndar.setDocument(new JTextFieldLimit(2));
		txtAndar.setBounds(150, 47, 50, 25);
		getContentPane().add(txtAndar);

		lblTipoDeQuarto = new JLabel("Cod. Tipo Quarto:");
		lblTipoDeQuarto.setBounds(10, 90, 110, 15);
		getContentPane().add(lblTipoDeQuarto);

		txtTipo = new JTextField();
		txtTipo.setBounds(150, 87, 50, 25);
		getContentPane().add(txtTipo);
		txtTipo.setDocument(new JTextFieldLimit(2));

		configurarDataModel();
		tblQuartos = new JTable(dataModel);
		tblQuartos.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				/* vazio */
			}

			@Override
			public void focusGained(FocusEvent e) {
				atualizarModel();
			}
		});

		scrollPane = new JScrollPane(tblQuartos);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 140, this.getWidth(), (height - 220));
		tblQuartos.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(220, 7, 100, 25);
		getContentPane().add(btnBuscar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setActionCommand("btn_limpar");
		btnLimpar.addActionListener(this);
		btnLimpar.setBounds((btnBuscar.getX() + btnBuscar.getWidth()) + 30, 7,
				100, 25);
		getContentPane().add(btnLimpar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, 278, 114, 25);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setBounds(300, 278, 114, 25);
		btnAlterar.setEnabled(false);
		getContentPane().add(btnAlterar, BorderLayout.EAST);

		btnInativar = new JButton("Inativar");
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		btnInativar.setBounds(450, 278, 114, 25);
		btnInativar.setEnabled(false);
		getContentPane().add(btnInativar, BorderLayout.EAST);

		if (MainWindow.getPessoaLogada().getClass().equals(Hospede.class)) {
			new FiltroDeComponentes(btnAlterar, btnInativar);
		}

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
		if (nomeEvento.equals("btn_buscar")) {
			Quarto q = consultaLista(txtNumeroQuarto.getText());
			if (q != null) {
				disporDadosNaTela(q);
				txtNumeroQuarto.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnInativar.setEnabled(true);
			}
		} else if (nomeEvento.equals("btn_limpar")) {
			resetarTela();
		} else if (nomeEvento.equals("btn_alterar")) {
			new QuartoControl().alterarQuarto(construirObjetoQuarto());
			resetarTela();
			tblQuartos.grabFocus();
		} else if (nomeEvento.equals("btn_inativar")) {
			new QuartoControl().deletarQuarto(txtNumeroQuarto.getText());
			resetarTela();
			tblQuartos.grabFocus();
		} else if (nomeEvento.equals("btn_cancelar")) {
			hide();
		}
	}

	private void disporDadosNaTela(Quarto q) {
		txtNumeroQuarto.setText(String.valueOf(q.getNumQuarto()));
		txtTipo.setText(String.valueOf(q.getTipoDeQuarto().getId()));
		txtAndar.setText(String.valueOf(q.getAndar()));
	}

	private void resetarTela() {
		txtNumeroQuarto.setText("");
		txtAndar.setText("");
		txtTipo.setText("");
		txtNumeroQuarto.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnInativar.setEnabled(false);
	}

	private Quarto consultaLista(String numQuarto) {
		try {
			final short nQ = Short.parseShort(numQuarto);
			for (Quarto q : quartos) {

				if (q.getNumQuarto() == nQ) {
					return q;
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Valor invalido", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}

		return null;
	}

	private Quarto construirObjetoQuarto() {
		Quarto q = new Quarto();
		try {
			q.setNumQuarto(Short.valueOf(txtNumeroQuarto.getText()));
			q.setAndar(Short.valueOf(txtAndar.getText()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		q.setTipoDeQuarto(new TipoDeQuartoControl()
				.selectTipoQuarto(Long.parseLong(txtTipo.getText())));
		q.setDisponivel(true);
		return q;
	}
}
