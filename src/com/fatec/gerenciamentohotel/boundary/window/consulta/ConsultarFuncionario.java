package com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import com.fatec.gerenciamentohotel.control.EnderecoControl;
import com.fatec.gerenciamentohotel.control.FuncionarioControl;
import com.fatec.gerenciamentohotel.entity.Endereco;
import com.fatec.gerenciamentohotel.entity.Funcionario;
import com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class ConsultarFuncionario extends JInternalFrame
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
	private JTextField txtLogin;
	private JPasswordField txtPsswdSenha;
	private JPasswordField txtPsswdConfirmarSenha;

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
	private JLabel lblPermissoes;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JLabel lblConfirmarSenha;

	private JButton btnBuscar;
	private JButton btnLimpar;
	private JButton btnBuscarCep;
	private JButton btnAlterar;
	private JButton btnInativar;
	private JButton btnCancelar;
	
	private JComboBox<EFuncionario> cmbPermissoes;

	private DefaultTableModel dataModel;
	private JTable tblFuncionarios;
	private List<Funcionario> funcionarios;

	public ConsultarFuncionario() {
		setTitle("Consulta Funcionarios");
		setClosable(true);
		setIconifiable(true);
		setLayout(null);
		setBounds(30, 30, 480, 600);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 20, 31, 13);
		getContentPane().add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(70, 20, 130, 19);
		txtCpf.setDocument(new JTextFieldLimit(11));
		getContentPane().add(txtCpf);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(220, 15, 100, 25);
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(340, 15, 100, 25);
		btnLimpar.setActionCommand("btn_limpar");
		btnLimpar.addActionListener(this);
		getContentPane().add(btnLimpar);

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
		lblDataNasc.setBounds(232, 110, 80, 19);
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
		panel.setBounds(10, 140, 435, 140);
		getContentPane().add(panel);

		lblCep = new JLabel("CEP:");
		lblCep.setBounds(5, 19, 46, 13);
		panel.add(lblCep);

		txtCep = new JTextField();
		txtCep.setBounds(60, 16, 130, 19);
		txtCep.setDocument(new JTextFieldLimit(8));
		panel.add(txtCep);

		btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.setBounds(((txtCep.getX() + txtCep.getWidth()) + 50), 14, 115, 20);
		btnBuscarCep.setActionCommand("btn_buscar_cep");
		btnBuscarCep.addActionListener(this);
		panel.add(btnBuscarCep);

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
		
		lblPermissoes = new JLabel("Permissoes:");
		lblPermissoes.setBounds(10, 295, 98, 15);
		getContentPane().add(lblPermissoes);
		
		cmbPermissoes = new JComboBox<>();
		cmbPermissoes
				.setModel(new DefaultComboBoxModel<>(EFuncionario.values()));
		cmbPermissoes.setBounds(155, 292, 147, 25);
		getContentPane().add(cmbPermissoes);

		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 325, 66, 15);
		getContentPane().add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(81, 322, 221, 25);
		getContentPane().add(txtLogin);
		txtLogin.setDocument(new JTextFieldLimit(15));

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 355, 66, 15);
		getContentPane().add(lblSenha);
		
		txtPsswdSenha = new JPasswordField();
		txtPsswdSenha.setBounds(81, 352, 98, 25);
		txtPsswdSenha.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtPsswdSenha);

		lblConfirmarSenha = new JLabel("Confirmar Senha:");
		lblConfirmarSenha.setBounds(188, 355, 147, 15);
		getContentPane().add(lblConfirmarSenha);
		
		txtPsswdConfirmarSenha = new JPasswordField();
		txtPsswdConfirmarSenha.setBounds(314, 352, 98, 25);
		txtPsswdConfirmarSenha.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtPsswdConfirmarSenha);

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
		scrollPane.setBounds(0, 390, getWidth(), 125);
		tblFuncionarios.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		final int btnsYPos = getHeight() - 75;
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, btnsYPos, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(215, btnsYPos, 114, 25);
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setEnabled(false);
		getContentPane().add(btnAlterar);

		btnInativar = new JButton("In/Ativar");
		btnInativar.setBounds(350, btnsYPos, 114, 25);
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		btnInativar.setEnabled(false);
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
			Funcionario f = consultarLista(txtCpf.getText());
			if (f != null) {
				disporFuncionarioEmTela(f);
				txtCpf.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnInativar.setEnabled(true);
			}
		} else if (nomeEvento.equals("btn_limpar")) {
			resetarTela();
		} else if (nomeEvento.equals("btn_inativar")) {
			new FuncionarioControl().inativarFuncionario(txtCpf.getText());
			atualizarModel();
		} else if (nomeEvento.equals("btn_alterar")) {
			new FuncionarioControl().alterarFuncionario(construirObjFuncionario());
			atualizarModel();
		} else if (nomeEvento.equals("btn_cancelar")) {
			hide();
		} else if (nomeEvento.equals("btn_buscar_cep")) {
			Endereco end = new EnderecoControl().selectCep(txtCep.getText());
			if (end != null) {
				disporEnderecoEmTela(end);
			}
		}
	}

	private void disporFuncionarioEmTela(Funcionario f) {
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
		txtLogin.setText(f.getLogin());
		cmbPermissoes.setSelectedIndex(ajustarComboBox(f.getTipoFuncionario()));
		txtPsswdSenha.setText(f.getSenha());
		txtPsswdConfirmarSenha.setText(f.getSenha());
	}
	
	private int ajustarComboBox(EFuncionario ef) {
		int cmbSize = cmbPermissoes.getItemCount();
		for (int i = 0; i < cmbSize; ++i) {
			if (cmbPermissoes.getItemAt(i).equals(ef)) {
				return i;
			}
		}
		return 0;
	}
	
	private void disporEnderecoEmTela(Endereco end) {
		txtRua.setText(end.getRua());
		txtBairro.setText(end.getBairro());
		txtCidade.setText(end.getCidade());
		txtUf.setText(end.getUf());
	}

	private void resetarTela() {
		txtCpf.setText("");
		txtNome.setText("");
		txtDataNasc.setText("");
		txtCelular.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtNumero.setText("");
		txtCep.setText("");
		txtRua.setText("");
		txtCidade.setText("");
		txtBairro.setText("");
		txtUf.setText("");
		txtLogin.setText("");
		txtPsswdSenha.setText("");
		txtPsswdConfirmarSenha.setText("");
		txtCpf.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnInativar.setEnabled(false);
	}
	
	private Funcionario construirObjFuncionario() {
		Funcionario f = new Funcionario();

		f.setCpf(txtCpf.getText());
		f.setEndereco(new EnderecoControl().selectCep(txtCep.getText()));
		f.setNome(txtNome.getText());
		f.setTelefone(txtTelefone.getText());
		f.setCelular(txtCelular.getText());
		String strDate = txtDataNasc.getText();
		Date nasc = null;
		try {
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			nasc = sdf.parse(strDate);
		} catch (ParseException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"Formate a data, por favor.", "Data inadequada",
					JOptionPane.ERROR_MESSAGE);
		}
		f.setEmail(txtEmail.getText());
		f.setDataNascimento(nasc);
		f.setStatus('A');
		try {
			f.setNumResidencia(Integer.parseInt(txtNumero.getText()));
		} catch (NumberFormatException ex) {
			f.setNumResidencia(0);
		}
		f.setTipoFuncionario(EFuncionario.valueOf(cmbPermissoes.getSelectedItem().toString()));
		f.setLogin(txtLogin.getText());
		final String pswd = String.valueOf(txtPsswdSenha.getPassword());
		final String confPswd = String.valueOf(txtPsswdConfirmarSenha.getPassword());
		if (pswd.equals(confPswd)) {
			f.setSenha(String.valueOf(txtPsswdSenha.getPassword()));
		} else {
			JOptionPane.showMessageDialog(this, "Senhas nao conferem", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return f;
	}

	private Funcionario consultarLista(String cpf) {
		for (Funcionario f : funcionarios) {
			if (f.getCpf().equals(cpf)) {
				return f;
			}
		}
		return null;
	}
}
