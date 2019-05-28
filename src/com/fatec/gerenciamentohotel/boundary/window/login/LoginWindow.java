package src.com.fatec.gerenciamentohotel.boundary.window.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

public class LoginWindow extends JFrame {
	private static final long serialVersionUID = -6285352374565410479L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private JPanel panelClientes, panelFuncionarios;
	private JTextField txtLogin;
	private JFormattedTextField txtCPF;
	private JPasswordField txtSenha;
	private JLabel lblCpf, lblLogin, lblSenha;
	private JButton btnAcessarCliente, btnAcessarFuncionario;

	public LoginWindow() {
		setResizable(false);
		setTitle("Gerenciamento de Hotel - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenSize.width / 2 - 250, screenSize.height / 2 - 125, 500, 250);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		panelFuncionarios = new JPanel();
		panelFuncionarios.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Funcionários",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelFuncionarios);
		panelFuncionarios.setLayout(null);

		lblLogin = new JLabel("Login");
		lblLogin.setBounds(12, 28, 66, 15);
		panelFuncionarios.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setBounds(12, 51, 225, 25);
		panelFuncionarios.add(txtLogin);
		txtLogin.setColumns(10);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(12, 82, 66, 15);
		panelFuncionarios.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(12, 109, 225, 25);
		panelFuncionarios.add(txtSenha);

		btnAcessarFuncionario = new JButton("Acessar");
		btnAcessarFuncionario.setBounds(123, 146, 114, 25);
		panelFuncionarios.add(btnAcessarFuncionario);

		panelClientes = new JPanel();
		panelClientes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Hospédes",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelClientes);
		panelClientes.setLayout(null);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(12, 28, 66, 15);
		panelClientes.add(lblCpf);

		btnAcessarCliente = new JButton("Acessar");
		btnAcessarCliente.setBounds(123, 89, 114, 25);
		panelClientes.add(btnAcessarCliente);

		try {
			txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCPF.setBounds(12, 51, 225, 25);
		panelClientes.add(txtCPF);
	}
}
