package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.LoginController;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8473289070103167272L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textSenha;
	private LoginController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Abre a tela de login
					LoginView frame = new LoginView();
					frame.setVisible(true);
					
					//Verifica no banco de dados se a tabela usuário está vazia
					if(frame.controller.tabelaUsuarioVazia()) {
						
						//Abre a tela de cadastro de novo usuário
						UsuarioView usuarioView = new UsuarioView(true);
						usuarioView.setVisible(true);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/view/imagens/ícone-tesoura.png")));
		setResizable(false);
		setTitle("Barber Shop - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 474, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.controller = new LoginController(this);
		
		JButton buttonEntrar = new JButton("Entrar");
		buttonEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.entrar();
			}
		});
		buttonEntrar.setBounds(130, 443, 202, 29);
		contentPane.add(buttonEntrar);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(130, 390, 202, 29);
		contentPane.add(textSenha);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(130, 313, 202, 29);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setHorizontalAlignment(SwingConstants.CENTER);
		labelSenha.setForeground(Color.WHITE);
		labelSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelSenha.setBounds(202, 357, 59, 22);
		contentPane.add(labelSenha);
		
		JLabel labelUsuario = new JLabel("Usuário");
		labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuario.setForeground(Color.WHITE);
		labelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelUsuario.setBounds(202, 280, 59, 22);
		contentPane.add(labelUsuario);
		
		JLabel labelLogin = new JLabel("Login");
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelLogin.setForeground(new Color(255, 255, 255));
		labelLogin.setBounds(195, 228, 66, 29);
		contentPane.add(labelLogin);
		
		JLabel labelPainelLogin = new JLabel("");
		labelPainelLogin.setIcon(new ImageIcon(LoginView.class.getResource("/view/imagens/painel-login.png")));
		labelPainelLogin.setBounds(29, 177, 400, 350);
		contentPane.add(labelPainelLogin);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setBounds(0, 0, 459, 666);
		labelLogo.setIcon(new ImageIcon(LoginView.class.getResource("/view/imagens/Logo.png")));
		contentPane.add(labelLogo);
	}
	
	public JTextField getTextUsuario() {
		return textUsuario;
	}

	public void setTextUsuario(JTextField textUsuario) {
		this.textUsuario = textUsuario;
	}

	public JPasswordField getTextSenha() {
		return textSenha;
	}

	public void setTextSenha(JPasswordField textSenha) {
		this.textSenha = textSenha;
	}
}
