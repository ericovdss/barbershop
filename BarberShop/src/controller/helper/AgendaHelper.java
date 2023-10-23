package controller.helper;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import view.AgendaView;

public class AgendaHelper {
	
	private AgendaView view;

	public AgendaHelper(AgendaView view) {
		super();
		this.view = view;
	}
	
	public void preencherTabela(ArrayList<Agendamento> agendamentos) {
		//Obtém da view o objeto Table
		DefaultTableModel tableModel = (DefaultTableModel) this.view.getTable().getModel();
		tableModel.setNumRows(0);
		
		//Percorre a lista de agendamentos inserindo cada agendamento na tabela
		for(Agendamento agendamento: agendamentos) {
			tableModel.addRow(new Object[] {
				agendamento.getId(),
				agendamento.getCliente().getNome(),
				agendamento.getServico().getDescricao(),
				agendamento.getValor(),
				agendamento.getDataFormatada(),
				agendamento.getHoraFormatada(),
				agendamento.getObservacao()
			});
		}
	}

	public void preencherClientes(ArrayList<Cliente> clientes) {
		//Obtém da view o objeto ComboBoxCliente
		DefaultComboBoxModel<Object> comboBoxModel = (DefaultComboBoxModel<Object>) view.getComboBoxCliente().getModel();
		
		//Percorre a lista de clientes inserindo cada cliente no combobox
		for(Cliente cliente: clientes) {
			comboBoxModel.addElement(cliente);
		}
	}
	
	public void preencherServicos(ArrayList<Servico> servicos) {
		//Obtém da view o objeto ComboBoxServico
		DefaultComboBoxModel<Object> comboBoxModel = (DefaultComboBoxModel<Object>) view.getComboBoxServico().getModel();
		
		//Percorre a lista de servico inserindo cada serviço no combobox
		for(Servico servico: servicos) {
			comboBoxModel.addElement(servico);
		}
	}

	public Servico obterServico() {
		return (Servico) view.getComboBoxServico().getSelectedItem();
	}
	
	public Cliente obterCliente() {
		return (Cliente) view.getComboBoxCliente().getSelectedItem();
	}

	public void editarValor(Servico servico) {
		view.getTextValor().setText(servico.getValor()+"");
	}

	public Agendamento obterAgendamento() {
		String idString = view.getTextId().getText();
		int id = Integer.parseInt(idString);
		Cliente cliente = obterCliente();
		Servico servico = obterServico();
		String valorString = view.getTextValor().getText();
		float valor = Float.parseFloat(valorString);
		String data = view.getTextData().getText();
		String hora = view.getTextHora().getText();
		String dataHora = data+" "+hora;
		String observacao = view.getTextObservacao().getText();
		Agendamento agendamento = new Agendamento(id,cliente,servico,valor,dataHora,observacao);
		
		return agendamento;
	}

	public void limparTela() {
		view.getTextId().setText("0");
		view.getTextValor().setText("");
		view.getTextData().setText("");
		view.getTextHora().setText("");
		view.getTextObservacao().setText("");
	}
}