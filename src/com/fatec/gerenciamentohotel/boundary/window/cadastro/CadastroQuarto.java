package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import src.com.fatec.gerenciamentohotel.boundary.window.consulta.ConsultarTipoQuarto;
import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.Quarto;

public class CadastroQuarto extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNumeroQuarto;
	private JTextField txtTipo;
	private JTextField txtAndar;
	
	private JLabel lblNmeroDoQuarto;
	private JLabel lblTipoDeQuarto;
	private JLabel lblAndar;
	
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JButton btnBuscarTipoquarto;

	public CadastroQuarto() {
		setTitle("Cadastrar Quartos");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 275, 300);
		getContentPane().setLayout(null);

		lblNmeroDoQuarto = new JLabel("Número do Quarto:");
		lblNmeroDoQuarto.setBounds(12, 12, 131, 15);
		getContentPane().add(lblNmeroDoQuarto);

		txtNumeroQuarto = new JTextField();
		txtNumeroQuarto.setBounds(197, 7, 63, 25);
		getContentPane().add(txtNumeroQuarto);
		txtNumeroQuarto.setDocument(new JTextFieldLimit(5));

		lblAndar = new JLabel("Andar:");
		lblAndar.setBounds(12, 49, 51, 15);
		getContentPane().add(lblAndar);

		txtAndar = new JTextField();
		txtAndar.setDocument(new JTextFieldLimit(2));
		txtAndar.setBounds(197, 44, 63, 25);
		getContentPane().add(txtAndar);

		lblTipoDeQuarto = new JLabel("Cod. Tipo Quarto:");
		lblTipoDeQuarto.setBounds(12, 81, 106, 15);
		getContentPane().add(lblTipoDeQuarto);

		txtTipo = new JTextField();
		txtTipo.setBounds(130, 75, 130, 25);
		getContentPane().add(txtTipo);
		txtTipo.setDocument(new JTextFieldLimit(2));

		btnBuscarTipoquarto = new JButton("Procurar Tipo");
		btnBuscarTipoquarto.setActionCommand("btn_procurar");
		btnBuscarTipoquarto.addActionListener(this);
		btnBuscarTipoquarto.setBounds(130, 120, 120, 25);
		getContentPane().add(btnBuscarTipoquarto);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(this);
		btnCadastrar.setActionCommand("btn_cadastrar");
		btnCadastrar.setBounds(146, 231, 114, 25);
		getContentPane().add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 231, 114, 25);
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("btn_cancelar");
		getContentPane().add(btnCancelar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_cadastrar")) {
			Quarto quarto = new Quarto();
			quarto.setNumQuarto(Integer.parseInt(txtNumeroQuarto.getText()));
			quarto.setAndar(Short.parseShort(txtAndar.getText()));
			quarto.setTipoDeQuarto(new TipoDeQuartoControl()
					.selectTipoQuarto(Long.parseLong(txtTipo.getText())));

			new QuartoControl().insert(quarto);
		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		} else if (nomeEvento.equals("btn_procurar")) {
			JInternalFrame tq = new ConsultarTipoQuarto();
			getParent().add(tq);
			tq.setVisible(true);
			tq.show();
		}

	}

}
