package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class ConsultarTipoQuarto extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel lblId;
	private JLabel lblDescricao;
	private JLabel lblValorDiaria;
	private JLabel lblQuantidadeAdultos;
	private JLabel lblQuantidadeCriancas;

	private JTextField txtId;
	private JTextField txtDescricao;
	private JFormattedTextField txtValor;
	private JTextField txtQtdDeAdultos;
	private JTextField txtQtdDeCriancas;

	private JButton btnBuscar;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private JButton btnAlterar;
	private JButton btnDeletar;

	private DefaultTableModel dataModel;
	private JTable tblTipoQuartos;
	private JScrollPane scrollPane;

	private List<TipoDeQuarto> tipoQuartos;

	public ConsultarTipoQuarto() {
		final int x = 100, y = 100, width = 500, height = 450;
		setTitle("Consulta Tipo de Quarto");
		setClosable(true);
		setIconifiable(true);
		setBounds(x, y, width, height);
		setLayout(null);

		lblId = new JLabel("Codigo:");
		lblId.setBounds(10, 10, 60, 15);
		lblId.setSize(150, lblId.getHeight());
		getContentPane().add(lblId);

		lblDescricao = new JLabel("Descricao:");
		lblDescricao.setBounds(10, 45, 65, 15);
		lblDescricao.setSize(150, lblDescricao.getHeight());
		getContentPane().add(lblDescricao);

		lblValorDiaria = new JLabel("Valor Diaria:");
		lblValorDiaria.setBounds(10, 80, 85, 15);
		lblValorDiaria.setSize(150, lblValorDiaria.getHeight());
		getContentPane().add(lblValorDiaria);

		lblQuantidadeAdultos = new JLabel("Quantidade Adultos:");
		lblQuantidadeAdultos.setBounds(10, 115, 165, 15);
		getContentPane().add(lblQuantidadeAdultos);

		lblQuantidadeCriancas = new JLabel("Quantidade Criancas:");
		lblQuantidadeCriancas.setBounds(10, 150, 165, 15);
		getContentPane().add(lblQuantidadeCriancas);

		txtId = new JTextField();
		txtId.setBounds(150, 7, 50, 25);
		txtId.setDocument(new JTextFieldLimit(4));
		getContentPane().add(txtId);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds((txtId.getX() + txtId.getWidth()) + 25,
				txtId.getY(), 114, 25);
		getContentPane().add(btnBuscar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setActionCommand("btn_limpar");
		btnLimpar.addActionListener(this);
		btnLimpar.setBounds((btnBuscar.getX() + btnBuscar.getWidth()) + 25,
				txtId.getY(), 114, 25);
		getContentPane().add(btnLimpar);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(150, 42, 200, 25);
		txtDescricao.setDocument(new JTextFieldLimit(25));
		getContentPane().add(txtDescricao);

		txtValor = new JFormattedTextField();
		txtValor.setDocument(new JTextFieldLimit(6));
		txtValor.setBounds(150, 77, 70, 25);
		getContentPane().add(txtValor);

		txtQtdDeAdultos = new JTextField();
		txtQtdDeAdultos.setBounds(150, 112, 70, 25);
		txtQtdDeAdultos.setDocument(new JTextFieldLimit(2));
		getContentPane().add(txtQtdDeAdultos);

		txtQtdDeCriancas = new JTextField();
		txtQtdDeCriancas.setBounds(150, 147, 70, 25);
		txtQtdDeCriancas.setDocument(new JTextFieldLimit(2));
		getContentPane().add(txtQtdDeCriancas);

		configurarDataModel();
		tblTipoQuartos = new JTable(dataModel);
		tblTipoQuartos.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				/* vazio */
			}

			@Override
			public void focusGained(FocusEvent e) {
				atualizarModel();
			}
		});

		scrollPane = new JScrollPane(tblTipoQuartos);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 200, this.getWidth(), 150);
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

		btnDeletar = new JButton("Deletar");
		btnDeletar.setActionCommand("btn_deletar");
		btnDeletar.addActionListener(this);
		btnDeletar.setBounds(370, buttonsY, 114, 25);
		getContentPane().add(btnDeletar);

		if (MainWindow.getPessoaLogada().getClass().equals(Hospede.class)) {
			new FiltroDeComponentes(btnAlterar, btnDeletar);
		}
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

	private void resetarTela() {
		btnAlterar.setEnabled(false);
		btnDeletar.setEnabled(false);
		txtId.setEnabled(true);
		txtId.setText("");
		txtDescricao.setText("");
		txtValor.setText("");
		txtQtdDeAdultos.setText("");
		txtQtdDeCriancas.setText("");
	}

	private void inserirLinhasModel() {
		this.tipoQuartos = new TipoDeQuartoControl().selectDisponiveis();
		if (this.tipoQuartos != null) {
			for (TipoDeQuarto t : this.tipoQuartos) {
				dataModel.addRow(new Object[] { t.getId(), t.getDescricao(),
						t.getValorDiaria(), t.getQuantidadeAdultos(),
						t.getQuantidadeCriancas() });
			}
		}
	}

	private TipoDeQuarto construirObjTipoQuarto() {
		TipoDeQuarto t = new TipoDeQuarto();
		t.setId(Long.valueOf(txtId.getText()));
		t.setDescricao(txtDescricao.getText());
		t.setQuantidadeAdultos(Short.parseShort(txtQtdDeAdultos.getText()));
		t.setQuantidadeCriancas(Short.parseShort(txtQtdDeCriancas.getText()));
		try {
			t.setValorDiaria(
					Float.valueOf(txtValor.getText().replaceAll(",", ".")));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Valor invalido", "Erro Valor",
					JOptionPane.ERROR_MESSAGE);
		}
		return t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar")) {
			TipoDeQuarto q = buscarEmLista(txtId.getText());
			if (q != null) {
				disposTipoQuartoEmTela(q);
			}
		} else if (nomeEvento.equals("btn_limpar")) {
			resetarTela();
		} else if (nomeEvento.equals("btn_alterar")) {
			new TipoDeQuartoControl()
					.alterarTipoQuarto(construirObjTipoQuarto());
			resetarTela();
		} else if (nomeEvento.equals("btn_deletar")) {
			new TipoDeQuartoControl().excluirTipoQuarto(txtId.getText());
			resetarTela();
			tblTipoQuartos.grabFocus();
		} else if (nomeEvento.equals("btn_cancelar")) {
			hide();
		}
	}

	private void disposTipoQuartoEmTela(TipoDeQuarto q) {
		txtId.setEnabled(false);
		txtDescricao.setText(q.getDescricao());
		txtValor.setText(String.valueOf(q.getValorDiaria()));
		txtQtdDeAdultos.setText(String.valueOf(q.getQuantidadeAdultos()));
		txtQtdDeCriancas.setText(String.valueOf(q.getQuantidadeCriancas()));
	}

	private TipoDeQuarto buscarEmLista(String id) {
		try {
			final long tq = Long.valueOf(id);
			for (TipoDeQuarto q : tipoQuartos) {
				if (q.getId() == tq) {
					return q;
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Valor invalido", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}
