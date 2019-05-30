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
	private JLabel lblNQuarto;
	private JLabel lblDescricao;
	private JLabel lblValDiaria;
	private JButton btnCancelar;
	private JButton btnAlterar;
	private JButton btCancelar;

	public ConsultarTipoQuarto() {
		setTitle("Consulta Tipo de Quarto");
		setClosable(true);
		setBounds(100, 100, 450, 338);
		getContentPane().setLayout(null);

		lblNQuarto = new JLabel("Nº do Quarto:");
		lblNQuarto.setBounds(10, 20, 65, 13);
		getContentPane().add(lblNQuarto);

		textNumQuarto = new JTextField();
		textNumQuarto.setBounds(93, 17, 96, 19);
		getContentPane().add(textNumQuarto);
		textNumQuarto.setColumns(10);

		lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(210, 20, 49, 13);
		getContentPane().add(lblDescricao);

		textDescricao = new JTextField();
		textDescricao.setBounds(269, 17, 159, 19);
		getContentPane().add(textDescricao);
		textDescricao.setColumns(10);

		lblValDiaria = new JLabel("Valor da diária:");
		lblValDiaria.setBounds(10, 50, 71, 13);
		getContentPane().add(lblValDiaria);

		textValDiaria = new JTextField();
		textValDiaria.setBounds(93, 46, 96, 19);
		getContentPane().add(textValDiaria);
		textValDiaria.setColumns(10);

		table = new JTable();
		table.setBounds(10, 100, 418, 148);
		getContentPane().add(table);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(62, 269, 114, 25);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(188, 269, 114, 25);
		getContentPane().add(btnAlterar);

		btCancelar = new JButton("Inativar");
		btCancelar.setBounds(314, 269, 114, 25);
		getContentPane().add(btCancelar);

	}

}
