package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jdk.nashorn.internal.runtime.ParserException;
import src.com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class CadastroTipoQuarto extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField txtDescricao;
	private JFormattedTextField txtValor;
	private JTextField txtQtdDeAdultos;
	private JTextField txtQtdDeCriancas;

	private JLabel lblDescricao;
	private JLabel lblValorDiaria;
	private JLabel lblQuantidadeAdultos;
	private JLabel lblQuantidadeCriancas;

	private JButton btnCadastrar;
	private JButton btnCancelar;

	public CadastroTipoQuarto() {
		final int x = 100, y = 100, width = 450, height = 300;
		setTitle("Cadastrar Tipo de Quartos");
		setClosable(true);
		setIconifiable(true);
		setBounds(x, y, width, height);
		setLayout(null);

		lblDescricao = new JLabel("Descricao");
		lblDescricao.setBounds(10, 12, 66, 15);
		lblDescricao.setSize(150, lblDescricao.getHeight());
		getContentPane().add(lblDescricao);

		lblValorDiaria = new JLabel("Valor Diaria:");
		lblValorDiaria.setBounds(10, 49, 86, 15);
		lblValorDiaria.setSize(150, lblValorDiaria.getHeight());
		getContentPane().add(lblValorDiaria);

		lblQuantidadeAdultos = new JLabel("Quantidade Adultos:");
		lblQuantidadeAdultos.setBounds(10, 86, 165, 15);
		getContentPane().add(lblQuantidadeAdultos);

		lblQuantidadeCriancas = new JLabel("Quantidade Criancas:");
		lblQuantidadeCriancas.setBounds(10, 123, 165, 15);
		getContentPane().add(lblQuantidadeCriancas);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(150, 7, 211, 25);
		txtDescricao.setDocument(new JTextFieldLimit(25));
		getContentPane().add(txtDescricao);

		txtValor = new JFormattedTextField();
		txtValor.setDocument(new JTextFieldLimit(6));
		txtValor.setBounds(150, 44, 78, 25);
		getContentPane().add(txtValor);

		txtQtdDeAdultos = new JTextField();
		txtQtdDeAdultos.setBounds(150, 81, 78, 25);
		txtQtdDeAdultos.setDocument(new JTextFieldLimit(2));
		getContentPane().add(txtQtdDeAdultos);

		txtQtdDeCriancas = new JTextField();
		txtQtdDeCriancas.setBounds(150, 118, 78, 25);
		txtQtdDeCriancas.setDocument(new JTextFieldLimit(2));
		getContentPane().add(txtQtdDeCriancas);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(this);
		btnCadastrar.setActionCommand("btn_cadastrar");
		btnCadastrar.setBounds(314, 231, 114, 25);
		getContentPane().add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(181, 231, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_cadastrar")) {
			TipoDeQuarto t = new TipoDeQuarto();
			t.setQuantidadeAdultos(Short.parseShort(txtQtdDeAdultos.getText()));
			t.setQuantidadeCriancas(
					Short.parseShort(txtQtdDeCriancas.getText()));
			t.setDescricao(txtDescricao.getText());
			try {
				float val = Float
						.parseFloat(txtValor.getText().replace(",", "."));
				t.setValorDiaria(val);
			} catch (ParserException ex) {
				t.setValorDiaria(0F);
			}
			new TipoDeQuartoControl().insert(t);
		} else if (nomeEvento.equals("btn_buscar")) {

		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		}

	}

}
