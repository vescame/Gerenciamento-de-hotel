package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroFuncionario extends JInternalFrame {

	private static final long serialVersionUID = 8511310886582278246L;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldCPF;
	private JTextField textFieldCelular;
	private JTextField textFieldCEP;
	private JTextField textFieldRua;
	private JTextField textFieldBairro;
	private JTextField textFieldCidade;
	private JTextField textFieldNumero;
	private JTextField textFieldUF;
	private JTextField textFieldData;
	private JTextField textFieldEMail;
	private JTextField textFieldLogin;
	private JPasswordField textFieldSenha;
	private JPasswordField textFieldConfirmarSenha;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JLabel lblCelular;
	private JPanel panelEndereco;
	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblUf;
	private JLabel lblCidade;
	private JLabel lblNumero;
	private JButton btnBuscar;
	private JLabel lblDataDeNascimento;
	private JLabel lblEmail;
	private JLabel lblPermissoes;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JLabel lblConfirmarSenha;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JComboBox<EFuncionario> comboBoxPermissoes;
	private JButton buttonNovo;
	private CadastroEndereco dialog;

	public CadastroFuncionario() {
		setTitle("Cadastro de Funcionários");
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
		panelEndereco.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Endereco",
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

		textFieldCEP = new JTextField();
		textFieldCEP.setBounds(47, 17, 154, 25);
		panelEndereco.add(textFieldCEP);
		textFieldCEP.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(213, 17, 93, 25);
		panelEndereco.add(btnBuscar);

		textFieldRua = new JTextField();
		textFieldRua.setBounds(47, 49, 221, 25);
		panelEndereco.add(textFieldRua);
		textFieldRua.setColumns(10);

		textFieldBairro = new JTextField();
		textFieldBairro.setBounds(67, 81, 337, 25);
		panelEndereco.add(textFieldBairro);
		textFieldBairro.setColumns(10);

		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(67, 115, 201, 25);
		panelEndereco.add(textFieldCidade);
		textFieldCidade.setColumns(10);

		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(338, 49, 66, 25);
		panelEndereco.add(textFieldNumero);
		textFieldNumero.setColumns(10);

		textFieldUF = new JTextField();
		textFieldUF.setBounds(338, 115, 66, 25);
		panelEndereco.add(textFieldUF);
		textFieldUF.setColumns(10);

		buttonNovo = new JButton("Novo");
		buttonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dialog == null) {
					dialog = new CadastroEndereco();
				}
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		buttonNovo.setBounds(311, 17, 93, 25);
		panelEndereco.add(buttonNovo);

		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(12, 244, 147, 15);
		getContentPane().add(lblDataDeNascimento);

		lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(12, 276, 66, 15);
		getContentPane().add(lblEmail);

		lblPermissoes = new JLabel("Permissões:");
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
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Endereco end = new Endereco();
				
				end.setCep(textFieldCEP.getText());
				end.setBairro(textFieldBairro.getText());
				end.setCidade(textFieldCidade.getText());
				end.setRua(textFieldRua.getText());
				
				Funcionario f = new Funcionario();
				
				f.setCpf(textFieldCPF.getText());
			}
		});
		getContentPane().add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(188, 431, 114, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancelar);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(61, 10, 157, 25);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(81, 47, 137, 25);
		getContentPane().add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		textFieldCPF = new JTextField();
		textFieldCPF.setBounds(281, 10, 147, 25);
		getContentPane().add(textFieldCPF);
		textFieldCPF.setColumns(10);

		textFieldCelular = new JTextField();
		textFieldCelular.setBounds(304, 47, 124, 25);
		getContentPane().add(textFieldCelular);
		textFieldCelular.setColumns(10);

		textFieldData = new JTextField();
		textFieldData.setBounds(178, 239, 124, 25);
		getContentPane().add(textFieldData);
		textFieldData.setColumns(10);

		textFieldEMail = new JTextField();
		textFieldEMail.setBounds(81, 271, 221, 25);
		getContentPane().add(textFieldEMail);
		textFieldEMail.setColumns(10);

		comboBoxPermissoes = new JComboBox<>();
		comboBoxPermissoes.setModel(new DefaultComboBoxModel<>(EFuncionario.values()));
		comboBoxPermissoes.setBounds(155, 308, 147, 25);
		getContentPane().add(comboBoxPermissoes);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(81, 340, 221, 25);
		getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);

		textFieldSenha = new JPasswordField();
		textFieldSenha.setBounds(81, 377, 98, 25);
		getContentPane().add(textFieldSenha);

		textFieldConfirmarSenha = new JPasswordField();
		textFieldConfirmarSenha.setBounds(314, 377, 98, 25);
		getContentPane().add(textFieldConfirmarSenha);

	}
}
