package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ServicoController;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServicoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8275178607641995919L;
	private ServicoController controller;
	private JPanel contentPane;
	private JTextField textDescricao;
	private JTextField textValor;
	private JTable tableServico;
	private JButton buttonCadastrar;
	private JButton buttonAtualizar;
	private JButton buttonExcluir;

	/**
	 * Create the frame.
	 */
	public ServicoView() {
		
		controller = new ServicoController(this);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ServicoView.class.getResource("/view/imagens/ícone-tesoura.png")));
		setResizable(false);
		setTitle("BarberShop - Serviço");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelFormulario = new JLabel("Formulário do Serviço");
		labelFormulario.setIcon(new ImageIcon(ServicoView.class.getResource("/view/imagens/tesoura-icon.png")));
		labelFormulario.setBounds(110, 19, 285, 22);
		labelFormulario.setHorizontalAlignment(SwingConstants.CENTER);
		labelFormulario.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(labelFormulario);
		
		JLabel labelOperacao = new JLabel("Operação");
		labelOperacao.setHorizontalAlignment(SwingConstants.CENTER);
		labelOperacao.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelOperacao.setBounds(459, 19, 255, 22);
		contentPane.add(labelOperacao);
		
		JLabel labelDescricao = new JLabel("Descrição*");
		labelDescricao.setBounds(35, 63, 65, 14);
		contentPane.add(labelDescricao);
		
		textDescricao = new JTextField();
		textDescricao.setBounds(110, 60, 285, 20);
		contentPane.add(textDescricao);
		textDescricao.setColumns(10);
		
		JLabel labelValor = new JLabel("Valor*");
		labelValor.setBounds(35, 89, 65, 14);
		contentPane.add(labelValor);
		
		textValor = new JTextField();
		textValor.setBounds(110, 86, 285, 20);
		contentPane.add(textValor);
		textValor.setColumns(10);
		
		JLabel labelCampoObrigatorio = new JLabel("* campo obrigatório");
		labelCampoObrigatorio.setForeground(Color.RED);
		labelCampoObrigatorio.setBounds(37, 148, 115, 14);
		contentPane.add(labelCampoObrigatorio);
		
		buttonCadastrar = new JButton("Cadastrar serviço");
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cadastrarServico();
			}
		});
		buttonCadastrar.setForeground(new Color(255, 255, 255));
		buttonCadastrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		buttonCadastrar.setBackground(new Color(128, 255, 128));
		buttonCadastrar.setBounds(459, 59, 255, 44);
		contentPane.add(buttonCadastrar);
		
		buttonAtualizar = new JButton("Atualizar serviço");
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.atualizarServico();
			}
		});
		buttonAtualizar.setForeground(new Color(255, 255, 255));
		buttonAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonAtualizar.setBackground(new Color(128, 128, 255));
		buttonAtualizar.setEnabled(false);
		buttonAtualizar.setBounds(459, 114, 255, 23);
		contentPane.add(buttonAtualizar);
		
		buttonExcluir = new JButton("Excluir serviço");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.excluirServico();
			}
		});
		buttonExcluir.setEnabled(false);
		buttonExcluir.setForeground(new Color(255, 255, 255));
		buttonExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonExcluir.setBackground(new Color(255, 128, 128));
		buttonExcluir.setBounds(459, 144, 255, 23);
		contentPane.add(buttonExcluir);
		
		JLabel labelServico = new JLabel("Serviços cadastrados");
		labelServico.setHorizontalAlignment(SwingConstants.CENTER);
		labelServico.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelServico.setBounds(35, 200, 715, 22);
		contentPane.add(labelServico);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(10, 233, 764, 317);
		contentPane.add(scrollPaneTable);
		
		tableServico = new JTable();
		tableServico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.habilitarBotoes();
				controller.preencherFormulario();
			}
		});
		tableServico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descri\u00E7\u00E3o", "Valor R$"
			}
		));
		scrollPaneTable.setViewportView(tableServico);
		
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

	public JTextField getTextDescricao() {
		return textDescricao;
	}

	public void setTextDescricao(JTextField textDescricao) {
		this.textDescricao = textDescricao;
	}

	public JTextField getTextValor() {
		return textValor;
	}

	public void setTextValor(JTextField textValor) {
		this.textValor = textValor;
	}

	public JTable getTableServico() {
		return tableServico;
	}

	public void setTableServico(JTable tableServico) {
		this.tableServico = tableServico;
	}
}
