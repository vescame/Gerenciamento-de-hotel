package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;

public class CadastroHospede extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldCPF;
	private JTextField textFieldCelular;
	private JTextField textFieldTelefone;
	private JTextField textFieldCep;
	private JTextField textFieldRua;
	private JTextField textFieldBairro;
	private JTextField textFieldCidade;
	private JTextField textFieldNumero;
	private JTextField textFieldUf;
	private JTextField textFieldDataNascimento;
	private JTextField textFieldEmail;
	private JLabel labelNome;
	private JLabel labelCPF;
	private JLabel labelCelular;
	private JPanel panelEndereco;
	private JLabel labelCep;
	private JLabel labelRua;
	private JLabel labelBairro;
	private JLabel labelCidade;
	private JLabel labelUf;
	private JLabel labelNumero;
	private JLabel labelTelefone;
	private JButton buttonBuscar;
	private JLabel labelDataNascimento;
	private JLabel labelEmail;
	private JButton buttonCancelar;
	private JButton buttonCadastrar;

	public CadastroHospede() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Hóspede");
		setBounds(100, 100, 450, 487);
		getContentPane().setLayout(null);

		labelNome = new JLabel("Nome:");
		labelNome.setBounds(12, 14, 66, 15);
		getContentPane().add(labelNome);

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(61, 12, 157, 25);
		getContentPane().add(textFieldNome);

		textFieldCPF = new JTextField();
		textFieldCPF.setColumns(10);
		textFieldCPF.setBounds(281, 12, 147, 25);
		getContentPane().add(textFieldCPF);

		labelCPF = new JLabel("CPF:");
		labelCPF.setBounds(236, 14, 66, 15);
		getContentPane().add(labelCPF);

		labelCelular = new JLabel("Celular:");
		labelCelular.setBounds(236, 54, 66, 15);
		getContentPane().add(labelCelular);

		textFieldCelular = new JTextField();
		textFieldCelular.setColumns(10);
		textFieldCelular.setBounds(304, 49, 124, 25);
		getContentPane().add(textFieldCelular);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(81, 49, 137, 25);
		getContentPane().add(textFieldTelefone);

		labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(12, 54, 66, 15);
		getContentPane().add(labelTelefone);

		panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Endereco",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEndereco.setBounds(12, 86, 416, 153);
		getContentPane().add(panelEndereco);

		labelCep = new JLabel("CEP:");
		labelCep.setBounds(12, 22, 66, 15);
		panelEndereco.add(labelCep);

		labelRua = new JLabel("Rua:");
		labelRua.setBounds(12, 54, 66, 15);
		panelEndereco.add(labelRua);

		labelBairro = new JLabel("Bairro:");
		labelBairro.setBounds(12, 86, 66, 15);
		panelEndereco.add(labelBairro);

		labelCidade = new JLabel("Cidade:");
		labelCidade.setBounds(12, 120, 66, 15);
		panelEndereco.add(labelCidade);

		labelUf = new JLabel("UF:");
		labelUf.setBounds(290, 120, 66, 15);
		panelEndereco.add(labelUf);

		labelNumero = new JLabel("Nº:");
		labelNumero.setBounds(290, 54, 30, 15);
		panelEndereco.add(labelNumero);

		textFieldCep = new JTextField();
		textFieldCep.setColumns(10);
		textFieldCep.setBounds(47, 17, 154, 25);
		panelEndereco.add(textFieldCep);

		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setBounds(213, 17, 93, 25);
		panelEndereco.add(buttonBuscar);

		textFieldRua = new JTextField();
		textFieldRua.setColumns(10);
		textFieldRua.setBounds(47, 49, 221, 25);
		panelEndereco.add(textFieldRua);

		textFieldBairro = new JTextField();
		textFieldBairro.setColumns(10);
		textFieldBairro.setBounds(67, 81, 337, 25);
		panelEndereco.add(textFieldBairro);

		textFieldCidade = new JTextField();
		textFieldCidade.setColumns(10);
		textFieldCidade.setBounds(67, 115, 201, 25);
		panelEndereco.add(textFieldCidade);

		textFieldNumero = new JTextField();
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(338, 49, 66, 25);
		panelEndereco.add(textFieldNumero);

		textFieldUf = new JTextField();
		textFieldUf.setColumns(10);
		textFieldUf.setBounds(338, 115, 66, 25);
		panelEndereco.add(textFieldUf);

		labelDataNascimento = new JLabel("Data de Nascimento:");
		labelDataNascimento.setBounds(12, 256, 147, 15);
		getContentPane().add(labelDataNascimento);

		textFieldDataNascimento = new JTextField();
		textFieldDataNascimento.setColumns(10);
		textFieldDataNascimento.setBounds(178, 251, 124, 25);
		getContentPane().add(textFieldDataNascimento);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(81, 283, 221, 25);
		getContentPane().add(textFieldEmail);

		labelEmail = new JLabel("E-Mail:");
		labelEmail.setBounds(12, 288, 66, 15);
		getContentPane().add(labelEmail);

		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(188, 418, 114, 25);
		getContentPane().add(buttonCancelar);

		buttonCadastrar = new JButton("Cadastrar");
		buttonCadastrar.setBounds(314, 418, 114, 25);
		getContentPane().add(buttonCadastrar);
	}

}
