package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.Quarto;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class CadastroQuarto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2084077809581858219L;
	private JTextField txtNumeroQuarto;
	private JTextField txtTipo;
	private JTextField txtAndar;
	private JLabel lblNmeroDoQuarto;
	private JLabel lblTipoDeQuarto;
	private JLabel lblAndar;
	private JButton btnBuscar;
	private JButton btnCadastrar;
	private JButton btnCancelar;

	public CadastroQuarto() {
		setTitle("Cadastrar Quartos");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 275, 300);
		getContentPane().setLayout(null);

		lblNmeroDoQuarto = new JLabel("Número do Quarto:");
		lblNmeroDoQuarto.setBounds(12, 12, 131, 15);
		getContentPane().add(lblNmeroDoQuarto);

		lblTipoDeQuarto = new JLabel("Tipo de Quarto:");
		lblTipoDeQuarto.setBounds(12, 81, 106, 15);
		getContentPane().add(lblTipoDeQuarto);

		txtNumeroQuarto = new JTextField();
		txtNumeroQuarto.setBounds(197, 7, 63, 25);
		getContentPane().add(txtNumeroQuarto);
		txtNumeroQuarto.setColumns(10);

		lblAndar = new JLabel("Andar:");
		lblAndar.setBounds(12, 49, 51, 15);
		getContentPane().add(lblAndar);

		txtTipo = new JTextField();
		txtTipo.setEnabled(true);
		txtTipo.setBounds(136, 76, 124, 25);
		getContentPane().add(txtTipo);
		txtTipo.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(146, 113, 114, 25);
		getContentPane().add(btnBuscar);

		txtAndar = new JTextField();
		txtAndar.setColumns(10);
		txtAndar.setBounds(197, 44, 63, 25);
		getContentPane().add(txtAndar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoDeQuarto tipo = new TipoDeQuartoControl()
						.selectTipoQuarto(Long.parseLong(txtTipo.getText()));

				Quarto quarto = new Quarto();
				quarto.setNumQuarto(
						Integer.parseInt(txtNumeroQuarto.getText()));
				quarto.setAndar(Short.parseShort(txtAndar.getText()));
				quarto.setTipoDeQuarto(tipo);

				new QuartoControl().insert(quarto);
			}
		});
		btnCadastrar.setBounds(146, 231, 114, 25);
		getContentPane().add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 231, 114, 25);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancelar);
	}

}
