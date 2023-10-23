package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ClienteController;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import model.Sexo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3748855030184731397L;
	private ClienteController controller;
	private JPanel contentPane;
	private JTextField textNome;
	private JComboBox<Object> comboBoxSexo;
	private JTextField textDataNascimento;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textRG;
	private JTextField textEndereco;
	private JTextField textCEP;
	private JTable tableCliente;
	private JButton buttonCadastrar;
	private JButton buttonAtualizar;
	private JButton buttonExcluir;

	/**
	 * Create the frame.
	 */
	public ClienteView() {
		
		controller = new ClienteController(this);
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteView.class.getResource("/view/imagens/ícone-tesoura.png")));
		setTitle("BarberShop - Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelFormulario = new JLabel("Formulário do cliente");
		labelFormulario.setBounds(89, 11, 334, 40);
		labelFormulario.setHorizontalAlignment(SwingConstants.CENTER);
		labelFormulario.setIcon(new ImageIcon(ClienteView.class.getResource("/view/imagens/cliente-icon.png")));
		labelFormulario.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(labelFormulario);
		
		JLabel labelNome = new JLabel("Nome*");
		labelNome.setBounds(34, 65, 45, 14);
		contentPane.add(labelNome);
		
		textNome = new JTextField();
		textNome.setBounds(89, 62, 334, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel labelSexo = new JLabel("Sexo");
		labelSexo.setBounds(33, 90, 46, 14);
		contentPane.add(labelSexo);
		
		comboBoxSexo = new JComboBox<Object>();
		comboBoxSexo.setBounds(89, 86, 334, 22);
		comboBoxSexo.setModel(new DefaultComboBoxModel<Object>(Sexo.values()));
		contentPane.add(comboBoxSexo);
		
		JLabel labelDataNascimento = new JLabel("Data de nascimento");
		labelDataNascimento.setBounds(34, 115, 115, 14);
		contentPane.add(labelDataNascimento);
		
		textDataNascimento = new JTextField();
		textDataNascimento.setBounds(159, 112, 264, 20);
		textDataNascimento.setText("dd/mm/aaaa");
		contentPane.add(textDataNascimento);
		textDataNascimento.setColumns(10);
		
		JLabel labelTelefone = new JLabel("Telefone");
		labelTelefone.setBounds(34, 140, 57, 14);
		contentPane.add(labelTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(89, 137, 334, 20);
		contentPane.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(34, 165, 46, 14);
		contentPane.add(labelEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(89, 162, 334, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel labelRG = new JLabel("RG");
		labelRG.setBounds(34, 190, 46, 14);
		contentPane.add(labelRG);
		
		textRG = new JTextField();
		textRG.setBounds(89, 187, 334, 20);
		contentPane.add(textRG);
		textRG.setColumns(10);
		
		JLabel labelEndereco = new JLabel("Endereço");
		labelEndereco.setBounds(34, 215, 57, 17);
		contentPane.add(labelEndereco);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(89, 212, 334, 20);
		contentPane.add(textEndereco);
		textEndereco.setColumns(10);
		
		JLabel labelCEP = new JLabel("CEP");
		labelCEP.setBounds(34, 240, 46, 14);
		contentPane.add(labelCEP);
		
		textCEP = new JTextField();
		textCEP.setBounds(89, 237, 334, 20);
		contentPane.add(textCEP);
		textCEP.setColumns(10);
		
		JLabel labelCampoObrigatorio = new JLabel("* campo obrigatório");
		labelCampoObrigatorio.setBounds(34, 265, 115, 14);
		labelCampoObrigatorio.setForeground(new Color(255, 0, 0));
		contentPane.add(labelCampoObrigatorio);
		
		buttonCadastrar = new JButton("Cadastrar cliente");
		buttonCadastrar.setBounds(466, 60, 282, 66);
		buttonCadastrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		buttonCadastrar.setBackground(new Color(0, 255, 128));
		buttonCadastrar.setForeground(new Color(255, 255, 255));
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cadastrarCliente();
			}
		});
		
		JLabel labelOperacao = new JLabel("Operação");
		labelOperacao.setBounds(466, 11, 282, 40);
		labelOperacao.setHorizontalAlignment(SwingConstants.CENTER);
		labelOperacao.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(labelOperacao);
		contentPane.add(buttonCadastrar);
		
		buttonAtualizar = new JButton("Atualizar cliente");
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.atualizarCliente();
			}
		});
		buttonAtualizar.setBounds(466, 147, 282, 44);
		buttonAtualizar.setEnabled(false);
		buttonAtualizar.setBackground(new Color(128, 128, 255));
		buttonAtualizar.setForeground(new Color(255, 255, 255));
		buttonAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(buttonAtualizar);
		
		buttonExcluir = new JButton("Excluir cliente");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.excluirCliente();
			}
		});
		buttonExcluir.setBounds(466, 212, 282, 44);
		buttonExcluir.setEnabled(false);
		buttonExcluir.setBackground(new Color(255, 128, 128));
		buttonExcluir.setForeground(new Color(255, 255, 255));
		buttonExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(buttonExcluir);
		
		JLabel labelClientes = new JLabel("Clientes cadastrados");
		labelClientes.setHorizontalAlignment(SwingConstants.CENTER);
		labelClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelClientes.setBounds(231, 268, 334, 40);
		contentPane.add(labelClientes);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(34, 315, 714, 235);
		contentPane.add(scrollPaneTable);
		
		tableCliente = new JTable();
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.habilitarBotoes();
				controller.preencherFormulario();
			}
		});
		scrollPaneTable.setViewportView(tableCliente);
		tableCliente.setToolTipText("");
		tableCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Sexo", "Data de nascimento", "Telefone", "Email", "RG", "Endere\u00E7o", "CEP"
			}
		));
		
		//Inicializa a tabela com os registros do banco de dados
		controller.atualizaTabela();
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

	public JTable getTableCliente() {
		return tableCliente;
	}

	public void setTableCliente(JTable tableCliente) {
		this.tableCliente = tableCliente;
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

	public void setComboBoxSexo(JComboBox<Object> comboBoxDataNascimento) {
		this.comboBoxSexo = comboBoxDataNascimento;
	}

	public JTextField getTextDataNasc() {
		return textDataNascimento;
	}

	public void setTextDataNasc(JTextField textDataNascimento) {
		this.textDataNascimento = textDataNascimento;
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

	public JTextField getTextEndereco() {
		return textEndereco;
	}

	public void setTextEndereco(JTextField textEndereco) {
		this.textEndereco = textEndereco;
	}

	public JTextField getTextCEP() {
		return textCEP;
	}

	public void setTextCEP(JTextField textCEP) {
		this.textCEP = textCEP;
	}
}
