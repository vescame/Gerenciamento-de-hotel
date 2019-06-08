package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import src.com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class ConsultaFuncionarios extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtDataNasc;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;

	private JLabel lblCpf;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblCelular;
	private JLabel lblEmail;
	private JLabel lblDataNasc;
	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblNumero;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblUf;

	private JButton btnBuscar;
	private JButton btnAlterar;
	private JButton btnInativar;
	private JButton btnCancelar;

	private DefaultTableModel dataModel;
	private JTable tblFuncionarios;
	private List<Funcionario> funcionarios;

	public ConsultaFuncionarios() {
		setTitle("Consulta Funcionarios");
		setClosable(true);
		setIconifiable(true);
		setLayout(null);
		setBounds(100, 100, 470, 540);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 20, 31, 13);
		getContentPane().add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(70, 20, 130, 19);
		txtCpf.setDocument(new JTextFieldLimit(11));
		getContentPane().add(txtCpf);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(225, 15, 114, 25);
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 50, 50, 13);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(70, 46, 365, 19);
		txtNome.setDocument(new JTextFieldLimit(35));
		getContentPane().add(txtNome);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(8, 80, 60, 13);
		getContentPane().add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(70, 75, 148, 19);
		txtTelefone.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtTelefone);

		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(232, 80, 50, 13);
		getContentPane().add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setBounds(285, 75, 148, 19);
		txtCelular.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtCelular);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 110, 50, 13);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(70, 107, 150, 19);
		txtEmail.setDocument(new JTextFieldLimit(35));
		getContentPane().add(txtEmail);

		lblDataNasc = new JLabel("Dat. Nasc.:");
		lblDataNasc.setBounds(232, 107, 80, 19);
		getContentPane().add(lblDataNasc);

		try {
			txtDataNasc = new JFormattedTextField(
					new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtDataNasc.setBounds(300, 107, 80, 19);
		getContentPane().add(txtDataNasc);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("");
		panel.setBounds(10, 145, 435, 140);
		getContentPane().add(panel);

		lblCep = new JLabel("CEP:");
		lblCep.setBounds(5, 19, 46, 13);
		panel.add(lblCep);

		txtCep = new JTextField();
		txtCep.setBounds(60, 16, 130, 19);
		txtCep.setDocument(new JTextFieldLimit(8));
		panel.add(txtCep);

		lblRua = new JLabel("Rua:");
		lblRua.setBounds(5, 45, 30, 13);
		panel.add(lblRua);

		txtRua = new JTextField();
		txtRua.setBounds(60, 42, 215, 19);
		txtRua.setDocument(new JTextFieldLimit(50));
		panel.add(txtRua);

		lblNumero = new JLabel("N:");
		lblNumero.setBounds(310, 45, 30, 13);
		panel.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setBounds(329, 42, 96, 19);
		txtNumero.setDocument(new JTextFieldLimit(5));
		panel.add(txtNumero);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(5, 74, 40, 13);
		panel.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(60, 71, 215, 19);
		txtBairro.setDocument(new JTextFieldLimit(50));
		panel.add(txtBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(5, 107, 46, 13);
		panel.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(60, 104, 215, 19);
		txtCidade.setDocument(new JTextFieldLimit(50));
		panel.add(txtCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(303, 107, 30, 13);
		panel.add(lblUf);

		txtUf = new JTextField();
		txtUf.setBounds(329, 104, 96, 19);
		txtUf.setDocument(new JTextFieldLimit(2));
		panel.add(txtUf);

		configurarDataModel();
		tblFuncionarios = new JTable(dataModel);
		tblFuncionarios.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				atualizarModel();
			}

			@Override
			public void focusGained(FocusEvent e) {
				atualizarModel();
			}
		});
		JScrollPane scrollPane = new JScrollPane(tblFuncionarios);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 300, getWidth(), 150);
		tblFuncionarios.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 472, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(206, 472, 114, 25);
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		getContentPane().add(btnAlterar);

		btnInativar = new JButton("Inativar");
		btnInativar.setBounds(332, 472, 114, 25);
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		getContentPane().add(btnInativar);

	}

	private void configurarDataModel() {
		dataModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "cpf", "nome", "email", "tipo_funcionario",
					"status" };

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
		this.funcionarios = new FuncionarioControl().selectTodos();
		if (this.funcionarios != null) {
			for (Funcionario t : this.funcionarios) {
				dataModel.addRow(new Object[] { t.getCpf(), t.getNome(),
						t.getEmail(), t.getTipoFuncionario().role,
						t.getStatus() == 'I' ? "Inativo" : "Ativo" });
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar")) {
			if (!txtCpf.getText().trim().isEmpty()) {
				Funcionario f = new FuncionarioControl()
						.selectCPF(txtCpf.getText());
				if (f != null) {
					txtNome.setText(f.getNome());
					txtDataNasc.setText(new SimpleDateFormat("dd/MM/yyyy")
							.format(f.getDataNascimento()));
					txtCelular.setText(f.getCelular());
					txtTelefone.setText(f.getTelefone());
					txtEmail.setText(f.getEmail());
					txtNumero.setText(String.valueOf(f.getNumResidencia()));
					txtCep.setText(f.getEndereco().getCep());
					txtRua.setText(f.getEndereco().getRua());
					txtCidade.setText(f.getEndereco().getCidade());
					txtBairro.setText(f.getEndereco().getBairro());
					txtUf.setText(f.getEndereco().getUf());
				}
			}
		} else if (nomeEvento.equals("btn_inativar")) {

		} else if (nomeEvento.equals("btn_alterar")) {
			atualizarModel();
		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		}

	}
}
