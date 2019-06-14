package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import src.com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import src.com.fatec.gerenciamentohotel.control.EnderecoControl;
import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class CadastroFuncionario extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 8511310886582278246L;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtCPF;
	private JTextField txtCelular;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtUf;
	private JTextField txtData;
	private JTextField txtEMail;
	private JTextField txtLogin;
	private JPasswordField txtPsswdSenha;
	private JPasswordField txtPsswdConfirmarSenha;

	private JPanel panelEndereco;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JLabel lblCelular;
	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblUf;
	private JLabel lblCidade;
	private JLabel lblNumero;
	private JLabel lblDataDeNascimento;
	private JLabel lblEmail;
	private JLabel lblPermissoes;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JLabel lblConfirmarSenha;

	private JButton btnBuscar;
	private JButton btnNovoEndereco;
	private JButton btnCadastrar;
	private JButton btnCancelar;

	private JComboBox<EFuncionario> cmbPermissoes;

	private Endereco end = null;
	private JDialog enderecoDialog = null;

	public CadastroFuncionario() {
		setTitle("Cadastro de Funcionarios");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 66, 15);
		getContentPane().add(lblNome);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(236, 12, 66, 15);
		getContentPane().add(lblCpf);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 52, 66, 15);
		getContentPane().add(lblTelefone);

		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(236, 52, 66, 15);
		getContentPane().add(lblCelular);

		panelEndereco = new JPanel();
		panelEndereco.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0), 1, true), "Endereco",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEndereco.setBounds(12, 79, 416, 153);
		getContentPane().add(panelEndereco);
		panelEndereco.setLayout(null);

		lblCep = new JLabel("CEP:");
		lblCep.setBounds(12, 22, 66, 15);
		panelEndereco.add(lblCep);

		lblRua = new JLabel("Rua:");
		lblRua.setBounds(12, 54, 66, 15);
		panelEndereco.add(lblRua);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 86, 66, 15);
		panelEndereco.add(lblBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 120, 66, 15);
		panelEndereco.add(lblCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(290, 120, 66, 15);
		panelEndereco.add(lblUf);

		lblNumero = new JLabel("Nº:");
		lblNumero.setBounds(290, 54, 30, 15);
		panelEndereco.add(lblNumero);

		txtCep = new JTextField();
		txtCep.setBounds(47, 17, 154, 25);
		panelEndereco.add(txtCep);
		txtCep.setDocument(new JTextFieldLimit(8));

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(213, 17, 93, 25);
		btnBuscar.setActionCommand("btn_buscar_endereco");
		btnBuscar.addActionListener(this);
		panelEndereco.add(btnBuscar);

		txtRua = new JTextField();
		txtRua.setBounds(47, 49, 221, 25);
		panelEndereco.add(txtRua);
		txtRua.setDocument(new JTextFieldLimit(50));

		txtBairro = new JTextField();
		txtBairro.setBounds(67, 81, 337, 25);
		panelEndereco.add(txtBairro);
		txtBairro.setDocument(new JTextFieldLimit(50));

		txtCidade = new JTextField();
		txtCidade.setBounds(67, 115, 201, 25);
		panelEndereco.add(txtCidade);
		txtCidade.setDocument(new JTextFieldLimit(50));

		txtNumero = new JTextField();
		txtNumero.setBounds(338, 49, 66, 25);
		panelEndereco.add(txtNumero);
		txtNumero.setDocument(new JTextFieldLimit(5));

		txtUf = new JTextField();
		txtUf.setBounds(338, 115, 66, 25);
		panelEndereco.add(txtUf);
		txtUf.setDocument(new JTextFieldLimit(2));

		btnNovoEndereco = new JButton("Novo");
		btnNovoEndereco.setActionCommand("btn_novo_endereco");
		btnNovoEndereco.addActionListener(this);
		btnNovoEndereco.setBounds(311, 17, 93, 25);
		panelEndereco.add(btnNovoEndereco);

		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(12, 244, 147, 15);
		getContentPane().add(lblDataDeNascimento);

		lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(12, 276, 66, 15);
		getContentPane().add(lblEmail);

		lblPermissoes = new JLabel("Permissoes:");
		lblPermissoes.setBounds(12, 313, 98, 15);
		getContentPane().add(lblPermissoes);

		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(12, 345, 66, 15);
		getContentPane().add(lblLogin);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(12, 382, 66, 15);
		getContentPane().add(lblSenha);

		lblConfirmarSenha = new JLabel("Confirmar Senha:");
		lblConfirmarSenha.setBounds(188, 382, 147, 15);
		getContentPane().add(lblConfirmarSenha);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(314, 431, 114, 25);
		btnCadastrar.setActionCommand("btn_cadastrar");
		btnCadastrar.addActionListener(this);
		getContentPane().add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(188, 431, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		txtNome = new JTextField();
		txtNome.setBounds(61, 10, 157, 25);
		getContentPane().add(txtNome);
		txtNome.setDocument(new JTextFieldLimit(35));

		txtTelefone = new JTextField();
		txtTelefone.setBounds(81, 47, 137, 25);
		getContentPane().add(txtTelefone);
		txtTelefone.setDocument(new JTextFieldLimit(15));

		txtCPF = new JTextField();
		txtCPF.setBounds(281, 10, 147, 25);
		getContentPane().add(txtCPF);
		txtCPF.setDocument(new JTextFieldLimit(11));

		txtCelular = new JTextField();
		txtCelular.setBounds(304, 47, 124, 25);
		getContentPane().add(txtCelular);
		txtCelular.setDocument(new JTextFieldLimit(15));

		// txtData = new JTextField();
		try {
			txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtData.setBounds(178, 239, 124, 25);
		getContentPane().add(txtData);

		txtEMail = new JTextField();
		txtEMail.setBounds(81, 271, 221, 25);
		getContentPane().add(txtEMail);
		txtEMail.setDocument(new JTextFieldLimit(35));

		cmbPermissoes = new JComboBox<>();
		cmbPermissoes
				.setModel(new DefaultComboBoxModel<>(EFuncionario.values()));
		cmbPermissoes.setBounds(155, 308, 147, 25);
		getContentPane().add(cmbPermissoes);

		txtLogin = new JTextField();
		txtLogin.setBounds(81, 340, 221, 25);
		getContentPane().add(txtLogin);
		txtLogin.setDocument(new JTextFieldLimit(15));

		txtPsswdSenha = new JPasswordField();
		txtPsswdSenha.setBounds(81, 377, 98, 25);
		txtPsswdSenha.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtPsswdSenha);

		txtPsswdConfirmarSenha = new JPasswordField();
		txtPsswdConfirmarSenha.setBounds(314, 377, 98, 25);
		txtPsswdConfirmarSenha.setDocument(new JTextFieldLimit(15));
		getContentPane().add(txtPsswdConfirmarSenha);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar_endereco")) {
			final String cep = txtCep.getText().trim();
			if (!cep.equals("")) {
				this.end = new EnderecoControl().selectCep(cep);
				if (end != null) {
					txtRua.setText(end.getRua());
					txtBairro.setText(end.getBairro());
					txtCidade.setText(end.getCidade());
					txtUf.setText(end.getUf());
				}
			}
		} else if (nomeEvento.equals("btn_novo_endereco")) {
			if (enderecoDialog == null) {
				enderecoDialog = new CadastroEndereco();
			}
			enderecoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			enderecoDialog.setVisible(true);
			enderecoDialog.requestFocus();
		} else if (nomeEvento.equals("btn_cadastrar")) {
			Funcionario f = new Funcionario();

			f.setCpf(txtCPF.getText());
			f.setEndereco(end);
			f.setNome(txtNome.getText());
			f.setTelefone(txtTelefone.getText());
			f.setCelular(txtCelular.getText());
			f.setEmail(txtEMail.getText());
			String strDate = txtData.getText();
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
			f.setDataNascimento(nasc);
			f.setStatus('A');
			f.setLogin(txtLogin.getText());
			String senha = String.valueOf(txtPsswdSenha.getPassword());
			String confSenha = String
					.valueOf(txtPsswdConfirmarSenha.getPassword());
			if (confSenha.equals(senha)) {
				f.setSenha(confSenha);
			} else {
				JOptionPane.showMessageDialog(null, "Senhas não conferem",
						"Senha", JOptionPane.ERROR_MESSAGE);
				return;
			}

			f.setTipoFuncionario(EFuncionario
					.valueOf(cmbPermissoes.getSelectedItem().toString()));
			try {
				f.setNumResidencia(Integer.parseInt(txtNumero.getText()));
			} catch (NumberFormatException ex) {
				f.setNumResidencia(0);
			}

			new FuncionarioControl().novoFuncionario(f);
		} else if (nomeEvento.equals("btn_cancelar")) {
			hide();
		}
	}
}
