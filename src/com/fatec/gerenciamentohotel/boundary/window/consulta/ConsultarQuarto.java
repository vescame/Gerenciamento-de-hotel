package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class ConsultarQuarto extends JInternalFrame {

	
	private static final long serialVersionUID = -5279211457994228009L;
	private JTextField textNumQuarto;
	private JTextField textAndar;
	private JTextField textTipoQuarto;
	private JTable table;

	public ConsultarQuarto() {
		setTitle("Consulta Quartos");
		setClosable(true);
		setBounds(100, 100, 285, 300);
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
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(88, 106, 85, 21);
		getContentPane().add(btnBuscar);
		
		table = new JTable();
		table.setBounds(10, 152, 253, 95);
		getContentPane().add(table);

	}

}
