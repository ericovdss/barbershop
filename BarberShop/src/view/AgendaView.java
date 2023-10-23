package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.AgendaController;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgendaView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383937576472799496L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textValor;
	private JTextField textData;
	private JTextField textHora;
	private JTextArea textObservacao;
	private JTable tableAgenda;
	private AgendaController controller;
	private JComboBox<Object> comboBoxCliente;
	private JComboBox<Object> comboBoxServico;

	/**
	 * Create the frame.
	 */
	public AgendaView() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgendaView.class.getResource("/view/imagens/ícone-tesoura.png")));
		setTitle("Barber Shop - Agenda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1300, 735);
		//setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(200, 395, 963, 280);
		contentPane.add(scrollPaneTable);
		
		tableAgenda = new JTable();
		tableAgenda.setToolTipText("");
		tableAgenda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Cliente", "Servi\u00E7o", "Valor R$", "Data", "Hora", "Observa\u00E7\u00E3o"
			}
		));
		scrollPaneTable.setViewportView(tableAgenda);
		
		JButton buttonAgendar = new JButton("Agendar");
		buttonAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.agendar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonAgendar.setBounds(613, 355, 550, 29);
		buttonAgendar.setBackground(new Color(0, 255, 128));
		buttonAgendar.setForeground(new Color(255, 255, 255));
		buttonAgendar.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(buttonAgendar);
		
		JScrollPane scrollPaneTextArea = new JScrollPane();
		scrollPaneTextArea.setBounds(613, 155, 550, 189);
		contentPane.add(scrollPaneTextArea);
		
		textObservacao = new JTextArea();
		scrollPaneTextArea.setViewportView(textObservacao);
		
		JLabel labelObservacao = new JLabel("Observação");
		labelObservacao.setBounds(506, 162, 91, 22);
		labelObservacao.setForeground(Color.WHITE);
		labelObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(labelObservacao);
		
		comboBoxServico = new JComboBox<Object>();
		comboBoxServico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				controller.atualizaValor();
			}
		});
		comboBoxServico.setBounds(200, 235, 250, 29);
		contentPane.add(comboBoxServico);
		
		comboBoxCliente = new JComboBox<Object>();
		comboBoxCliente.setBounds(200, 195, 250, 29);
		contentPane.add(comboBoxCliente);
		
		textHora = new JTextField();
		textHora.setBounds(200, 355, 250, 29);
		textHora.setColumns(10);
		contentPane.add(textHora);
		
		textData = new JTextField();
		textData.setBounds(200, 315, 250, 29);
		textData.setColumns(10);
		contentPane.add(textData);
		
		textValor = new JTextField();
		textValor.setBounds(200, 275, 250, 29);
		textValor.setColumns(10);
		contentPane.add(textValor);
		
		textId = new JTextField();
		textId.setBounds(200, 155, 250, 29);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JLabel labelHora = new JLabel("Hora");
		labelHora.setBounds(101, 362, 37, 22);
		labelHora.setForeground(Color.WHITE);
		labelHora.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(labelHora);
		
		JLabel labelData = new JLabel("Data");
		labelData.setBounds(101, 322, 36, 22);
		labelData.setForeground(Color.WHITE);
		labelData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(labelData);
		
		JLabel labelValor = new JLabel("Valor R$");
		labelValor.setBounds(101, 282, 67, 22);
		labelValor.setForeground(Color.WHITE);
		labelValor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(labelValor);
		
		JLabel labelServico = new JLabel("Serviço");
		labelServico.setBounds(101, 242, 56, 22);
		labelServico.setForeground(Color.WHITE);
		labelServico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(labelServico);
		
		JLabel labelCliente = new JLabel("Cliente");
		labelCliente.setBounds(101, 202, 53, 22);
		labelCliente.setForeground(Color.WHITE);
		labelCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(labelCliente);
		
		JLabel labelId = new JLabel("Id");
		labelId.setBounds(101, 162, 17, 22);
		labelId.setForeground(new Color(255, 255, 255));
		labelId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(labelId);
		
		JLabel labelAgenda = new JLabel("Agenda");
		labelAgenda.setBounds(613, 77, 89, 29);
		labelAgenda.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelAgenda.setForeground(new Color(255, 255, 255));
		contentPane.add(labelAgenda);
		
		JLabel labelPainel = new JLabel("");
		labelPainel.setBounds(-16, -62, 1300, 1000);
		labelPainel.setIcon(new ImageIcon(AgendaView.class.getResource("/view/imagens/Agenda-PainelFundo.png")));
		contentPane.add(labelPainel);
		
		JLabel labelFundo = new JLabel("");
		labelFundo.setBounds(0, 0, 1300, 1000);
		labelFundo.setHorizontalAlignment(SwingConstants.CENTER);
		labelFundo.setIcon(new ImageIcon(AgendaView.class.getResource("/view/imagens/AgendaFundo.png")));
		contentPane.add(labelFundo);
		
		//Inicializa os componentes tabela e combobox
		this.controller = new AgendaController(this);
		try {
			this.controller.atualizaTabela();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			this.controller.atualizaCliente();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.controller.atualizaServico();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.controller.atualizaValor();
	}

	public JTextField getTextId() {
		return textId;
	}

	public void setTextId(JTextField textId) {
		this.textId = textId;
	}

	public JTextField getTextValor() {
		return textValor;
	}

	public void setTextValor(JTextField textValor) {
		this.textValor = textValor;
	}

	public JTextField getTextData() {
		return textData;
	}

	public void setTextData(JTextField textData) {
		this.textData = textData;
	}

	public JTextField getTextHora() {
		return textHora;
	}

	public void setTextHora(JTextField textHora) {
		this.textHora = textHora;
	}

	public JTextArea getTextObservacao() {
		return textObservacao;
	}

	public void setTextObservacao(JTextArea textAreaObservacao) {
		this.textObservacao = textAreaObservacao;
	}

	public JTable getTable() {
		return tableAgenda;
	}

	public void setTable(JTable table) {
		this.tableAgenda = table;
	}

	public JComboBox<Object> getComboBoxCliente() {
		return comboBoxCliente;
	}

	public void setComboBoxCliente(JComboBox<Object> comboBoxCliente) {
		this.comboBoxCliente = comboBoxCliente;
	}

	public JComboBox<Object> getComboBoxServico() {
		return comboBoxServico;
	}

	public void setComboBoxServico(JComboBox<Object> comboBoxServico) {
		this.comboBoxServico = comboBoxServico;
	}
}
