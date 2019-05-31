package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ConsultarQuarto extends JInternalFrame {

	private static final long serialVersionUID = -5279211457994228009L;
	private JTextField textNumQuarto;
	private JTextField textAndar;
	private JTextField textTipoQuarto;
	private JTable table;

	public ConsultarQuarto() {
		setTitle("Consulta Quartos");
		setClosable(true);
		setBounds(100, 100, 401, 347);
		getContentPane().setLayout(null);

		JLabel lblNmeroDoQuarto = new JLabel("N\u00FAmero do Quarto:");
		lblNmeroDoQuarto.setBounds(10, 20, 97, 13);
		getContentPane().add(lblNmeroDoQuarto);

		JLabel lblAndar = new JLabel("Andar:");
		lblAndar.setBounds(10, 51, 46, 13);
		getContentPane().add(lblAndar);

		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto:");
		lblTipoDeQuarto.setBounds(10, 80, 78, 13);
		getContentPane().add(lblTipoDeQuarto);

		textNumQuarto = new JTextField();
		textNumQuarto.setBounds(167, 17, 96, 19);
		getContentPane().add(textNumQuarto);
		textNumQuarto.setColumns(10);

		textAndar = new JTextField();
		textAndar.setBounds(167, 48, 96, 19);
		getContentPane().add(textAndar);
		textAndar.setColumns(10);

		textTipoQuarto = new JTextField();
		textTipoQuarto.setEditable(false);
		textTipoQuarto.setBounds(167, 77, 96, 19);
		getContentPane().add(textTipoQuarto);
		textTipoQuarto.setColumns(10);

		table = new JTable();
		table.setBounds(10, 108, 366, 139);
		getContentPane().add(table);

		JButton button = new JButton("Cancelar");
		button.setBounds(10, 278, 114, 25);
		getContentPane().add(button);

		JButton button_1 = new JButton("Alterar");
		button_1.setBounds(136, 278, 114, 25);
		getContentPane().add(button_1);

		JButton button_2 = new JButton("Inativar");
		button_2.setBounds(262, 278, 114, 25);
		getContentPane().add(button_2);

	}

}
