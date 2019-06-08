package src.com.fatec.gerenciamentohotel.boundary.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import src.com.fatec.gerenciamentohotel.boundary.window.cadastro.CadastroFuncionario;
import src.com.fatec.gerenciamentohotel.boundary.window.cadastro.CadastroHospede;
import src.com.fatec.gerenciamentohotel.boundary.window.cadastro.CadastroQuarto;
import src.com.fatec.gerenciamentohotel.boundary.window.cadastro.CadastroTipoQuarto;
import src.com.fatec.gerenciamentohotel.boundary.window.consulta.ConsultaFuncionarios;
import src.com.fatec.gerenciamentohotel.boundary.window.consulta.ConsultarHospedes;
import src.com.fatec.gerenciamentohotel.boundary.window.consulta.ConsultarQuarto;
import src.com.fatec.gerenciamentohotel.boundary.window.reserva.ConsultarReservas;
import src.com.fatec.gerenciamentohotel.boundary.window.consulta.ConsultarTipoQuarto;
import src.com.fatec.gerenciamentohotel.boundary.window.reserva.CadastroReserva;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.Pessoa;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -5038047263946063083L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel contentPane, StatusBar;
	private JMenuBar menuBar;
	private JMenu menuFuncionarios, menuHospedes, menuReservas, menuQuartos,
			menuTipoQuarto;
	private JLabel lblData, lblHora;
	private JDesktopPane desktopPane;
	private JMenuItem mItemCadastroFuncionario, mItemConsultarFuncionario;
	private JMenuItem mItemCadastroHospede, mItemConsultaDeHospede;
	private JMenuItem mItemCadastroReserva, mItemConsultarReserva;
	private JMenuItem mItemCadastrarQuartos, mItemConsultarQuartos,
			mItemCadastrarTipoQuarto, mItemConsultarTipos;
	private JSeparator separatorQuartos;
	private CadastroFuncionario cadastroFuncionario;
	private ConsultaFuncionarios consultaFuncionarios;
	private CadastroHospede cadadastroHospede;
	private ConsultarHospedes consultarHospedes;
	private CadastroQuarto cadastroQuarto;
	private ConsultarQuarto consultarQuarto;
	private CadastroTipoQuarto cadastroTipoQuarto;
	private ConsultarTipoQuarto consultarTipoQuarto;
	private CadastroReserva cadastroReserva;
	private ConsultarReservas consultarReservas;
	private Timer timer;
	private static Hospede hospedeLogado = null;
	private static Funcionario funcionarioLogado = null;

	public MainWindow(Object logado) {
		if (logado.getClass().toString().contains("Funcionario")) {
			MainWindow.funcionarioLogado = (Funcionario) logado;
		} else {
			MainWindow.hospedeLogado = (Hospede) logado;
		}
		this.construirJanelaPrincipal();
		this.esconderPropriedades();
	}

	private void esconderPropriedades() {
		if (MainWindow.hospedeLogado != null) {
			this.menuFuncionarios.setVisible(false);
			this.menuHospedes.setVisible(false);
			this.mItemCadastroReserva.setVisible(false);
			this.mItemCadastrarQuartos.setVisible(false);
			this.mItemCadastrarTipoQuarto.setVisible(false);
		} else if (MainWindow.funcionarioLogado != null) {
			if (funcionarioLogado
					.getTipoFuncionario() == EFuncionario.RECEPCIONISTA) {
				this.menuFuncionarios.setVisible(false);
				this.mItemCadastrarQuartos.setVisible(false);
				this.mItemCadastrarTipoQuarto.setVisible(false);
			}
		}
	}

	private void construirJanelaPrincipal() {
		setTitle("Gerenciamento de Reservas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenSize.width / 2 - 800, screenSize.height / 2 - 450, 1600,
				900);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				timer = new Timer(1000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						SimpleDateFormat timeFormat = new SimpleDateFormat(
								"HH:mm:ss");
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"dd/MM/yyyy");
						lblHora.setText(
								"Hora: " + timeFormat.format(new Date()));
						lblData.setText(
								"Data: " + dateFormat.format(new Date()));
					}
				});
				timer.start();
			}
		});
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// cascade menu Funcionario
		menuFuncionarios = new JMenu("Funcionarios");
		menuBar.add(menuFuncionarios);
		mItemCadastroFuncionario = new JMenuItem("Cadastro de Funcionario");
		mItemCadastroFuncionario.setActionCommand("mitem_cad_funcionario");
		mItemCadastroFuncionario.addActionListener(this);
		menuFuncionarios.add(mItemCadastroFuncionario);
		mItemConsultarFuncionario = new JMenuItem("Consultar Funcionario");
		mItemConsultarFuncionario.setActionCommand("mitem_cons_funcionario");
		mItemConsultarFuncionario.addActionListener(this);
		menuFuncionarios.add(mItemConsultarFuncionario);

		// cascade menu Hospedes
		menuHospedes = new JMenu("Hospedes");
		menuBar.add(menuHospedes);
		mItemCadastroHospede = new JMenuItem("Cadastro de Hospede");
		mItemCadastroHospede.setActionCommand("mitem_cad_hospede");
		mItemCadastroHospede.addActionListener(this);
		menuHospedes.add(mItemCadastroHospede);

		mItemConsultaDeHospede = new JMenuItem("Consultar Hospede");
		mItemConsultaDeHospede.setActionCommand("mitem_cons_hospede");
		mItemConsultaDeHospede.addActionListener(this);
		menuHospedes.add(mItemConsultaDeHospede);

		// cascade menu Reservas
		menuReservas = new JMenu("Reservas");
		menuBar.add(menuReservas);
		mItemCadastroReserva = new JMenuItem("Nova Reserva");
		mItemCadastroReserva.setActionCommand("mitem_cad_reserva");
		mItemCadastroReserva.addActionListener(this);
		menuReservas.add(mItemCadastroReserva);

		mItemConsultarReserva = new JMenuItem("Consultar Reserva");
		mItemConsultarReserva.setActionCommand("mitem_cons_reserva");
		mItemConsultarReserva.addActionListener(this);
		menuReservas.add(mItemConsultarReserva);

		// cascade menu Quartos
		menuQuartos = new JMenu("Quartos");
		menuBar.add(menuQuartos);
		mItemCadastrarQuartos = new JMenuItem("Cadastrar Quartos");
		mItemCadastrarQuartos.setActionCommand("mitem_cad_quarto");
		mItemCadastrarQuartos.addActionListener(this);
		menuQuartos.add(mItemCadastrarQuartos);

		mItemConsultarQuartos = new JMenuItem("Consultar Quartos");
		mItemConsultarQuartos.setActionCommand("mitem_cons_quarto");
		mItemConsultarQuartos.addActionListener(this);
		menuQuartos.add(mItemConsultarQuartos);
		separatorQuartos = new JSeparator();
		menuQuartos.add(separatorQuartos);

		// cascade submenu Tipo de Quarto
		menuTipoQuarto = new JMenu("Tipos de Quarto");
		mItemCadastrarTipoQuarto = new JMenuItem("Cadastrar Tipo");
		mItemCadastrarTipoQuarto.setActionCommand("mitem_cad_tipo_quarto");
		mItemCadastrarTipoQuarto.addActionListener(this);

		menuTipoQuarto.add(mItemCadastrarTipoQuarto);
		mItemConsultarTipos = new JMenuItem("Consultar Tipos");
		mItemConsultarTipos.setActionCommand("mitem_cons_tipo_quarto");
		mItemConsultarTipos.addActionListener(this);
		menuTipoQuarto.add(mItemConsultarTipos);

		// add menu Tipo de Quarto como submenu do menu Quartos
		menuQuartos.add(menuTipoQuarto);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.desktop);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		StatusBar = new JPanel();
		StatusBar.setBackground(SystemColor.windowBorder);
		StatusBar.setPreferredSize(new Dimension(50, 25));
		contentPane.add(StatusBar, BorderLayout.SOUTH);
		StatusBar.setLayout(new BorderLayout());

		lblData = new JLabel("Data");
		StatusBar.add(lblData, BorderLayout.EAST);

		lblHora = new JLabel("Hora");
		StatusBar.add(lblHora, BorderLayout.WEST);

	}

	private void abrirJanelas(JInternalFrame frame) {
		if (frame.isVisible()) {
			frame.requestFocus();
			frame.toFront();
		} else {
			desktopPane.add(frame);
			frame.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		// eventos de cadastros
		if (nomeEvento.equals("mitem_cad_funcionario")) {
			if (cadastroFuncionario == null) {
				cadastroFuncionario = new CadastroFuncionario();
			}
			abrirJanelas(cadastroFuncionario);
		} else if (nomeEvento.equals("mitem_cad_hospede")) {
			if (cadadastroHospede == null) {
				cadadastroHospede = new CadastroHospede();
			}
			abrirJanelas(cadadastroHospede);
		} else if (nomeEvento.equals("mitem_cad_reserva")) {
			if (cadastroReserva == null) {
				cadastroReserva = new CadastroReserva();
			}
			abrirJanelas(cadastroReserva);
		} else if (nomeEvento.equals("mitem_cad_quarto")) {
			if (cadastroQuarto == null) {
				cadastroQuarto = new CadastroQuarto();
			}
			abrirJanelas(cadastroQuarto);
		} else if (nomeEvento.equals("mitem_cad_tipo_quarto")) {
			if (cadastroTipoQuarto == null) {
				cadastroTipoQuarto = new CadastroTipoQuarto();
			}
			abrirJanelas(cadastroTipoQuarto);
		}

		// eventos de consultas
		if (nomeEvento.equals("mitem_cons_funcionario")) {
			if (consultaFuncionarios == null) {
				consultaFuncionarios = new ConsultaFuncionarios();
			}
			abrirJanelas(consultaFuncionarios);
		} else if (nomeEvento.equals("mitem_cons_hospede")) {
			if (consultarHospedes == null) {
				consultarHospedes = new ConsultarHospedes();
			}
			abrirJanelas(consultarHospedes);
		} else if (nomeEvento.equals("mitem_cons_reserva")) {
			if (consultarReservas == null) {
				consultarReservas = new ConsultarReservas();
			}
			abrirJanelas(consultarReservas);
		} else if (nomeEvento.equals("mitem_cons_quarto")) {
			if (consultarQuarto == null) {
				consultarQuarto = new ConsultarQuarto();
			}
			abrirJanelas(consultarQuarto);
		} else if (nomeEvento.equals("mitem_cons_tipo_quarto")) {
			if (consultarTipoQuarto == null) {
				consultarTipoQuarto = new ConsultarTipoQuarto();
			}
			abrirJanelas(consultarTipoQuarto);
		}
	}

	public static Pessoa getPessoaLogada() {
		if (MainWindow.hospedeLogado == null) {
			return funcionarioLogado;
		} else {
			return hospedeLogado;
		}
	}
}
