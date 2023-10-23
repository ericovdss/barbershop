package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.UsuarioController;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import model.Sexo;
import model.NivelAcesso;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class UsuarioView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6509008898875529133L;
	private boolean fecharAoCadastrar;
	private UsuarioController controller;
	private JPanel contentPane;
	private JTextField textNome;
	private JComboBox<Object> comboBoxSexo;
	private JTextField textDataNasc;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textRG;
	private JTextField textLogin;
	private JPasswordField textSenha;
	private JComboBox<Object> comboBoxNivelAcesso;
	private JTable tableUsuario;
	private JButton buttonCadastrar;
	private JButton buttonAtualizar;
	private JButton buttonExcluir;

	/**
	 * Create the frame.
	 */
	public UsuarioView(boolean fecharAoCadastrar) {
		
		this.fecharAoCadastrar = fecharAoCadastrar;
		controller = new UsuarioController(this);
		
		setResizable(false);
		setTitle("BarberShop - Usuário");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UsuarioView.class.getResource("/view/imagens/ícone-tesoura.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelFormulario = new JLabel("Formulário do usuário");
		labelFormulario.setHorizontalAlignment(SwingConstants.CENTER);
		labelFormulario.setIcon(new ImageIcon(UsuarioView.class.getResource("/view/imagens/usuario-icon.png")));
		labelFormulario.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelFormulario.setBounds(29, 11, 378, 33);
		contentPane.add(labelFormulario);
		
		JLabel labelNome = new JLabel("Nome*");
		labelNome.setBounds(29, 55, 46, 14);
		contentPane.add(labelNome);
		
		textNome = new JTextField();
		textNome.setBounds(87, 52, 320, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel labelSexo = new JLabel("Sexo");
		labelSexo.setBounds(29, 80, 46, 14);
		contentPane.add(labelSexo);
		
		comboBoxSexo = new JComboBox<Object>();
		comboBoxSexo.setModel(new DefaultComboBoxModel<Object>(Sexo.values()));
		comboBoxSexo.setToolTipText("");
		comboBoxSexo.setBounds(87, 76, 320, 22);
		contentPane.add(comboBoxSexo);
		
		JLabel labelDataNasc = new JLabel("Data de nascimento");
		labelDataNasc.setBounds(29, 108, 140, 14);
		contentPane.add(labelDataNasc);
		
		textDataNasc = new JTextField();
		textDataNasc.setText("dd/mm/aaaa");
		textDataNasc.setBounds(157, 105, 250, 20);
		contentPane.add(textDataNasc);
		textDataNasc.setColumns(10);
		
		JLabel labelTelefone = new JLabel("Telefone");
		labelTelefone.setBounds(29, 130, 102, 14);
		contentPane.add(labelTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(87, 130, 320, 20);
		contentPane.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(29, 155, 46, 14);
		contentPane.add(labelEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(87, 152, 320, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel labelRG = new JLabel("RG");
		labelRG.setBounds(29, 177, 46, 14);
		contentPane.add(labelRG);
		
		textRG = new JTextField();
		textRG.setBounds(87, 174, 320, 20);
		contentPane.add(textRG);
		textRG.setColumns(10);
		
		JLabel labelLogin = new JLabel("Login*");
		labelLogin.setBounds(29, 199, 46, 14);
		contentPane.add(labelLogin);
		
		textLogin = new JTextField();
		textLogin.setBounds(87, 196, 320, 20);
		contentPane.add(textLogin);
		textLogin.setColumns(10);
		
		JLabel labelSenha = new JLabel("Senha*");
		labelSenha.setBounds(29, 221, 46, 14);
		contentPane.add(labelSenha);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(87, 218, 320, 20);
		contentPane.add(textSenha);
		
		JLabel labelNivelAcesso = new JLabel("Nível de acesso*");
		labelNivelAcesso.setBounds(29, 244, 102, 14);
		contentPane.add(labelNivelAcesso);
		
		comboBoxNivelAcesso = new JComboBox<Object>();
		comboBoxNivelAcesso.setModel(new DefaultComboBoxModel<Object>(NivelAcesso.values()));
		comboBoxNivelAcesso.setBounds(141, 240, 266, 22);
		contentPane.add(comboBoxNivelAcesso);
		
		buttonCadastrar = new JButton("Cadastrar usuário");
		buttonCadastrar.setForeground(new Color(255, 255, 255));
		buttonCadastrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		buttonCadastrar.setBackground(new Color(128, 255, 128));
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cadastrarUsuario();
			}
		});
		buttonCadastrar.setBounds(442, 51, 320, 71);
		contentPane.add(buttonCadastrar);
		
		JLabel labelCampoObrigatorio = new JLabel("* campo obrigatório");
		labelCampoObrigatorio.setForeground(new Color(255, 0, 0));
		labelCampoObrigatorio.setBounds(29, 269, 140, 14);
		contentPane.add(labelCampoObrigatorio);
		
		JLabel labelOperacao = new JLabel("Operação");
		labelOperacao.setHorizontalAlignment(SwingConstants.CENTER);
		labelOperacao.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelOperacao.setBounds(442, 11, 320, 33);
		contentPane.add(labelOperacao);
		
		buttonAtualizar = new JButton("Atualizar usuário");
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.atualizarUsuario();
			}
		});
		buttonAtualizar.setEnabled(false);
		buttonAtualizar.setForeground(new Color(255, 255, 255));
		buttonAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonAtualizar.setBackground(new Color(128, 128, 255));
		buttonAtualizar.setBounds(442, 142, 320, 54);
		contentPane.add(buttonAtualizar);
		
		buttonExcluir = new JButton("Excluir usuário");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.excluirUsuario();
			}
		});
		buttonExcluir.setEnabled(false);
		buttonExcluir.setForeground(new Color(255, 255, 255));
		buttonExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonExcluir.setBackground(new Color(255, 128, 128));
		buttonExcluir.setBounds(442, 217, 320, 54);
		contentPane.add(buttonExcluir);
		
		JLabel labelUsuarios = new JLabel("Usuários cadastrados");
		labelUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuarios.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelUsuarios.setBounds(242, 282, 320, 33);
		contentPane.add(labelUsuarios);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(29, 317, 733, 233);
		contentPane.add(scrollPaneTable);
		
		tableUsuario = new JTable();
		tableUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.habilitarBotoes();
				controller.preencherFormulario();
			}
		});
		tableUsuario.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Sexo", "Data de nascimento", "Telefone", "Email", "RG", "Login", "N\u00EDvel de acesso"
			}
		));
		scrollPaneTable.setViewportView(tableUsuario);
		
		//Inicializa a tabela com os registros do banco de dados
		controller.atualizaTabela();
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public void setTextNome(JTextField textNome) {
		this.textNome = textNome;
	}

	public JComboBox<Object> getComboBoxSexo() {
		return comboBoxSexo;
	}

	public void setComboBoxSexo(JComboBox<Object> comboBoxSexo) {
		this.comboBoxSexo = comboBoxSexo;
	}

	public JTextField getTextDataNasc() {
		return textDataNasc;
	}

	public void setTextDataNasc(JTextField textDataNasc) {
		this.textDataNasc = textDataNasc;
	}

	public JTextField getTextTelefone() {
		return textTelefone;
	}

	public void setTextTelefone(JTextField textTelefone) {
		this.textTelefone = textTelefone;
	}

	public JTextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(JTextField textEmail) {
		this.textEmail = textEmail;
	}

	public JTextField getTextRG() {
		return textRG;
	}

	public void setTextRG(JTextField textRG) {
		this.textRG = textRG;
	}

	public JTextField getTextLogin() {
		return textLogin;
	}

	public void setTextLogin(JTextField textLogin) {
		this.textLogin = textLogin;
	}

	public JPasswordField getTextSenha() {
		return textSenha;
	}

	public void setTextSenha(JPasswordField textSenha) {
		this.textSenha = textSenha;
	}

	public JComboBox<Object> getComboBoxNivelAcesso() {
		return comboBoxNivelAcesso;
	}

	public void setComboBoxNivelAcesso(JComboBox<Object> comboBoxNivelAcesso) {
		this.comboBoxNivelAcesso = comboBoxNivelAcesso;
	}

	public JButton getButtonAtualizar() {
		return buttonAtualizar;
	}

	public void setButtonAtualizar(JButton buttonAtualizar) {
		this.buttonAtualizar = buttonAtualizar;
	}

	public JButton getButtonExcluir() {
		return buttonExcluir;
	}

	public void setButtonExcluir(JButton buttonExcluir) {
		this.buttonExcluir = buttonExcluir;
	}

	public JTable getTableUsuario() {
		return tableUsuario;
	}

	public void setTableUsuario(JTable tableUsuario) {
		this.tableUsuario = tableUsuario;
	}

	public boolean isFecharAoCadastrar() {
		return fecharAoCadastrar;
	}

	public void setFecharAoCadastrar(boolean fecharAoCadastrar) {
		this.fecharAoCadastrar = fecharAoCadastrar;
	}
}
