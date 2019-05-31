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
//import src.com.fatec.gerenciamentohotel.boundary.window.consulta.ConsultarServicos;
import src.com.fatec.gerenciamentohotel.boundary.window.consulta.ConsultarTipoQuarto;
import src.com.fatec.gerenciamentohotel.boundary.window.reserva.ReservasFrame;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -5038047263946063083L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel contentPane, StatusBar;
	private JMenuBar menuBar;
	private JMenu menuFuncionarios, menuHospedes, menuReservas, menuQuartos,
			/* menuServicoQuarto, */ menuTipoQuarto;
	private JLabel lblData, lblHora;
	private JDesktopPane desktopPane;
	private JMenuItem mItemCadastroFuncionario, mItemConsultarFuncionario;
	private JMenuItem mItemCadastroDeHospede, mItemConsultaDeHospede;
	private JMenuItem mItemNovaReserva, mItemConsultarReserva;
	private JMenuItem mItemCadastrarQuartos, mItemConsultarQuartos,
			mItemCadastrarTipo, mItemConsultarTipos;
//	private JMenuItem mntmSolicitarServico, mntmConsultarServicos;
	private JSeparator separatorQuartos;
	private CadastroFuncionario cadastroFuncionario;
	private ConsultaFuncionarios consultaFuncionarios;
	private CadastroHospede cadadastroHospede;
	private ConsultarHospedes consultarHospedes;
	private CadastroQuarto cadastroQuarto;
	private ConsultarQuarto consultarQuarto;
	private CadastroTipoQuarto cadastroTipoQuarto;
	private ConsultarTipoQuarto consultarTipoQuarto;
//	private ConsultarServicos consultarServicos;
	private ReservasFrame reservas;
//	private SolicitarServico servico;
	private Timer timer;

	public MainWindow() {
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

		menuFuncionarios = new JMenu("Funcionários");
		menuBar.add(menuFuncionarios);
		mItemCadastroFuncionario = new JMenuItem("Cadastro de Funcionário");
		mItemCadastroFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cadastroFuncionario == null) {
					cadastroFuncionario = new CadastroFuncionario();
				}
				abrirJanelas(cadastroFuncionario);
			}
		});
		menuFuncionarios.add(mItemCadastroFuncionario);
		mItemConsultarFuncionario = new JMenuItem("Consultar Funcionário");
		mItemConsultarFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (consultaFuncionarios == null) {
					consultaFuncionarios = new ConsultaFuncionarios();
				}
				abrirJanelas(consultaFuncionarios);
			}
		});
		menuFuncionarios.add(mItemConsultarFuncionario);
		menuHospedes = new JMenu("Hóspedes");
		menuBar.add(menuHospedes);
		mItemCadastroDeHospede = new JMenuItem("Cadastro de Hóspede");
		mItemCadastroDeHospede.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cadadastroHospede == null) {
					cadadastroHospede = new CadastroHospede();
				}
				abrirJanelas(cadadastroHospede);
			}
		});
		menuHospedes.add(mItemCadastroDeHospede);
		mItemConsultaDeHospede = new JMenuItem("Consulta de Hóspede");
		mItemConsultaDeHospede.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (consultarHospedes == null) {
					consultarHospedes = new ConsultarHospedes();
				}
				abrirJanelas(consultarHospedes);
			}
		});
		menuHospedes.add(mItemConsultaDeHospede);

		menuReservas = new JMenu("Reservas");
		menuBar.add(menuReservas);
		mItemNovaReserva = new JMenuItem("Nova Reserva");
		mItemNovaReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (reservas == null) {
					reservas = new ReservasFrame();
				}
				abrirJanelas(reservas);
			}
		});
		menuReservas.add(mItemNovaReserva);
		mItemConsultarReserva = new JMenuItem("Consultar Reserva");
		menuReservas.add(mItemConsultarReserva);

		menuQuartos = new JMenu("Quartos");
		menuBar.add(menuQuartos);
		mItemCadastrarQuartos = new JMenuItem("Cadastrar Quartos");
		mItemCadastrarQuartos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cadastroQuarto == null) {
					cadastroQuarto = new CadastroQuarto();
				}
				abrirJanelas(cadastroQuarto);
			}
		});
		menuQuartos.add(mItemCadastrarQuartos);
		mItemConsultarQuartos = new JMenuItem("Consultar Quartos");
		mItemConsultarQuartos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (consultarQuarto == null) {
					consultarQuarto = new ConsultarQuarto();
				}
				abrirJanelas(consultarQuarto);
			}
		});
		menuQuartos.add(mItemConsultarQuartos);
		separatorQuartos = new JSeparator();
		menuQuartos.add(separatorQuartos);

		menuTipoQuarto = new JMenu("Tipos de Quarto");
		menuQuartos.add(menuTipoQuarto);
		mItemCadastrarTipo = new JMenuItem("Cadastrar Tipo");
		mItemCadastrarTipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cadastroTipoQuarto == null) {
					cadastroTipoQuarto = new CadastroTipoQuarto();
				}
				abrirJanelas(cadastroTipoQuarto);
			}
		});
		menuTipoQuarto.add(mItemCadastrarTipo);
		mItemConsultarTipos = new JMenuItem("Consultar Tipos");
		mItemConsultarTipos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (consultarTipoQuarto == null) {
					consultarTipoQuarto = new ConsultarTipoQuarto();
				}
				abrirJanelas(consultarTipoQuarto);
			}
		});
		menuTipoQuarto.add(mItemConsultarTipos);

		/*
		 * menuServicoQuarto = new JMenu("Serviço de Quarto");
		 * menuBar.add(menuServicoQuarto); mntmSolicitarServico = new
		 * JMenuItem("Solicitar Serviços");
		 * mntmSolicitarServico.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { if (servico ==
		 * null) { servico = new SolicitarServico(); } abrirJanelas(servico); }
		 * }); menuServicoQuarto.add(mntmSolicitarServico);
		 * mntmConsultarServicos = new JMenuItem("Consultar Serviços");
		 * mntmConsultarServicos.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { if
		 * (consultarServicos == null) { consultarServicos = new
		 * ConsultarServicos(); } abrirJanelas(consultarServicos); } });
		 * menuServicoQuarto.add(mntmConsultarServicos);
		 */

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
}
