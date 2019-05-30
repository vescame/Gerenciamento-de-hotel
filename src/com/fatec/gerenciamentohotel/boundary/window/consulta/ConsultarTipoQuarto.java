package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class ConsultarTipoQuarto extends JInternalFrame {

	private static final long serialVersionUID = 7896059643589466582L;
	private JTextField textNumQuarto;
	private JTextField textDescricao;
	private JTextField textValDiaria;
	private JTable table;

	public ConsultarTipoQuarto() {
		setTitle("Consulta Tipo de Quarto");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNQuarto = new JLabel("N\u00B0 do Quarto:");
		lblNQuarto.setBounds(10, 20, 65, 13);
		getContentPane().add(lblNQuarto);
		
		textNumQuarto = new JTextField();
		textNumQuarto.setBounds(93, 17, 96, 19);
		getContentPane().add(textNumQuarto);
		textNumQuarto.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(210, 20, 49, 13);
		getContentPane().add(lblDescricao);
		
		textDescricao = new JTextField();
		textDescricao.setBounds(269, 17, 159, 19);
		getContentPane().add(textDescricao);
		textDescricao.setColumns(10);
		
		JLabel lblValDiaria = new JLabel("Valor da di\u00E1ria:");
		lblValDiaria.setBounds(10, 50, 71, 13);
		getContentPane().add(lblValDiaria);
		
		textValDiaria = new JTextField();
		textValDiaria.setBounds(93, 46, 96, 19);
		getContentPane().add(textValDiaria);
		textValDiaria.setColumns(10);
		
		JButton btnBusca = new JButton("Busca");
		btnBusca.setBounds(210, 46, 85, 21);
		getContentPane().add(btnBusca);
		
		table = new JTable();
		table.setBounds(10, 100, 418, 148);
		getContentPane().add(table);

	}

}
