package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarReservas extends JInternalFrame {

	
	private static final long serialVersionUID = 8968428703173820614L;
	private JTextField textHospede;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;

	public ConsultarReservas() {
		setTitle("Consulta Reservas");
		setClosable(true);
		setBounds(100, 100, 460, 363);
		getContentPane().setLayout(null);
		
		JLabel lblHospede = new JLabel("H\u00F3spede:");
		lblHospede.setBounds(10, 20, 46, 13);
		getContentPane().add(lblHospede);
		
		textHospede = new JTextField();
		textHospede.setBounds(66, 17, 219, 19);
		getContentPane().add(textHospede);
		textHospede.setColumns(10);
		
		JLabel lblQuarto = new JLabel("Quarto:");
		lblQuarto.setBounds(10, 50, 46, 13);
		getContentPane().add(lblQuarto);
		
		textField = new JTextField();
		textField.setBounds(66, 46, 219, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCheckOut = new JButton("CheckOut");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCheckOut.setBounds(358, 74, 85, 21);
		getContentPane().add(btnCheckOut);
		
		table = new JTable();
		table.setBounds(10, 123, 433, 138);
		getContentPane().add(table);
		
		JLabel lblCheckin = new JLabel("Check-in:");
		lblCheckin.setBounds(10, 80, 46, 13);
		getContentPane().add(lblCheckin);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(66, 75, 96, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCheckout = new JLabel("Check-out:");
		lblCheckout.setBounds(190, 80, 50, 13);
		getContentPane().add(lblCheckout);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(250, 75, 96, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton button = new JButton("Cancelar");
		button.setBounds(62, 294, 114, 25);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Alterar");
		button_1.setBounds(188, 294, 114, 25);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Inativar");
		button_2.setBounds(314, 294, 114, 25);
		getContentPane().add(button_2);

	}

}
