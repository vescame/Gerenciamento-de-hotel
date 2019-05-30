package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class ConsultarHospedes extends JInternalFrame {
	
	private static final long serialVersionUID = 2733433101749296723L;
	private JTextField textCPF;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textCelular;
	private JTextField textEmail;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;

	
	public ConsultarHospedes() {
		setClosable(true);
		setTitle("Consulta Hospedes");
		setBounds(100, 100, 468, 491);
		getContentPane().setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 20, 31, 13);
		getContentPane().add(lblCpf);
		
		textCPF = new JTextField();
		textCPF.setBounds(61, 17, 130, 19);
		getContentPane().add(textCPF);
		textCPF.setColumns(10);
		
		JButton btnBusca = new JButton("Busca");
		btnBusca.setBounds(206, 16, 85, 21);
		getContentPane().add(btnBusca);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 50, 31, 13);
		getContentPane().add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(61, 46, 365, 19);
		getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 80, 46, 13);
		getContentPane().add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(61, 75, 148, 19);
		getContentPane().add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(232, 80, 36, 13);
		getContentPane().add(lblCelular);
		
		textCelular = new JTextField();
		textCelular.setBounds(278, 75, 148, 19);
		getContentPane().add(textCelular);
		textCelular.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 110, 36, 13);
		getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(61, 107, 365, 19);
		getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("");
		panel.setBounds(10, 145, 435, 140);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("CEP:");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBounds(10, 19, 46, 13);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(50, 16, 130, 19);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Rua:");
		label_1.setBounds(10, 45, 30, 13);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(50, 42, 215, 19);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("N\u00B0:");
		label_2.setBounds(289, 42, 30, 13);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(329, 39, 96, 19);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("Bairro:");
		label_3.setBounds(10, 74, 40, 13);
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(50, 71, 215, 19);
		panel.add(textField_3);
		
		JLabel label_4 = new JLabel("Cidade:");
		label_4.setBounds(10, 104, 46, 13);
		panel.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(50, 104, 215, 19);
		panel.add(textField_4);
		
		JLabel label_5 = new JLabel("UF:");
		label_5.setBounds(289, 104, 30, 13);
		panel.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(329, 104, 96, 19);
		panel.add(textField_5);
		
		table = new JTable();
		table.setBounds(10, 295, 436, 146);
		getContentPane().add(table);

	}

}
