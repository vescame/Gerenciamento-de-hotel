package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CadastroQuarto extends JInternalFrame {
	private JTextField textFieldNumeroQuarto;
	private JTextField textField;
	private JTextField textField_1;

	public CadastroQuarto() {
		setTitle("Cadastrar Quartos");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 275, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNmeroDoQuarto = new JLabel("Número do Quarto:");
		lblNmeroDoQuarto.setBounds(12, 12, 131, 15);
		getContentPane().add(lblNmeroDoQuarto);
		
		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto:");
		lblTipoDeQuarto.setBounds(12, 81, 106, 15);
		getContentPane().add(lblTipoDeQuarto);
		
		textFieldNumeroQuarto = new JTextField();
		textFieldNumeroQuarto.setBounds(197, 7, 63, 25);
		getContentPane().add(textFieldNumeroQuarto);
		textFieldNumeroQuarto.setColumns(10);
		
		JLabel lblAndar = new JLabel("Andar:");
		lblAndar.setBounds(12, 49, 51, 15);
		getContentPane().add(lblAndar);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(136, 76, 124, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(146, 113, 114, 25);
		getContentPane().add(btnBuscar);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(197, 44, 63, 25);
		getContentPane().add(textField_1);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(146, 231, 114, 25);
		getContentPane().add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 231, 114, 25);
		getContentPane().add(btnCancelar);

	}

}
