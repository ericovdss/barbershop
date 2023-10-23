package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.MenuPrincipalController;
import model.Usuario;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipalView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3033657413370679917L;
	private JPanel contentPane;
	private MenuPrincipalController controller;
	private Usuario sessao;

	/**
	 * Create the frame.
	 */
	public MenuPrincipalView(Usuario sessao) {
		this.sessao = sessao;
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipalView.class.getResource("/view/imagens/ícone-tesoura.png")));
		setTitle("Barber Shop - Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		this.controller = new MenuPrincipalController(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFormularios = new JMenu("Formulários");
		menuBar.add(menuFormularios);
		
		JMenuItem menuItemCliente = new JMenuItem("Cliente");
		menuItemCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.abrirFormularioCliente();
			}
		});
		menuItemCliente.setIcon(new ImageIcon(MenuPrincipalView.class.getResource("/view/imagens/cliente-icon.png")));
		menuFormularios.add(menuItemCliente);
		
		JMenuItem menuItemServico = new JMenuItem("Serviço");
		menuItemServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.abrirFormularioServico();
			}
		});
		menuItemServico.setIcon(new ImageIcon(MenuPrincipalView.class.getResource("/view/imagens/tesoura-icon.png")));
		menuFormularios.add(menuItemServico);
		
		JMenuItem menuItemUsuario = new JMenuItem("Usuário");
		menuItemUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.abrirFormularioUsuario();
			}
		});
		menuItemUsuario.setIcon(new ImageIcon(MenuPrincipalView.class.getResource("/view/imagens/usuario-icon.png")));
		menuFormularios.add(menuItemUsuario);
		
		JMenu menuOperacao = new JMenu("Operação");
		menuBar.add(menuOperacao);
		
		JMenuItem menuItemAgenda = new JMenuItem("Agenda");
		menuItemAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.abrirAgenda();
			}
		});
		menuItemAgenda.setIcon(new ImageIcon(MenuPrincipalView.class.getResource("/view/imagens/agenda-icon.png")));
		menuOperacao.add(menuItemAgenda);
		
		JMenu menuRelatorio = new JMenu("Relatório");
		menuBar.add(menuRelatorio);
		
		JMenuItem menuItemRelatorio = new JMenuItem("Relatório de trabalho");
		menuItemRelatorio.setIcon(new ImageIcon(MenuPrincipalView.class.getResource("/view/imagens/relatorioTrabalho-icon.png")));
		menuRelatorio.add(menuItemRelatorio);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelFundo = new JLabel("");
		labelFundo.setIcon(new ImageIcon(MenuPrincipalView.class.getResource("/view/imagens/fundo-MenuPrincipal.jpg")));
		labelFundo.setHorizontalAlignment(SwingConstants.CENTER);
		labelFundo.setBounds(0, 0, 1366, 683);
		contentPane.add(labelFundo);
	}

	public Usuario getSessao() {
		return sessao;
	}

	public void setSessao(Usuario sessao) {
		this.sessao = sessao;
	}
}
