package controller.helper;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Servico;
import view.ServicoView;

public class ServicoHelper {
	
	private ServicoView view;

	public ServicoHelper(ServicoView view) {
		super();
		this.view = view;
	}
	
	//Converte de formulário para objeto do tipo serviço
	public Servico getServicoFormulario() {
		String descricao = view.getTextDescricao().getText();
		float valor = 0;
		if(!view.getTextValor().getText().equals("")) {
			valor = Float.parseFloat(view.getTextValor().getText());
		}
		Servico servico = new Servico(0, descricao, valor);
		return servico;
	}
	
	//Verifica se os campos obrigatórios de cadastro de serviço foram preenchidos
	public boolean camposObrigatoriosPreenchidos(Servico servico) {
		if (servico.getDescricao().equals("") || servico.getValor()<=0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void preencherTabela(ArrayList<Servico> servicos) {
		//Obtém da view o objeto Table
		DefaultTableModel tableModel = (DefaultTableModel) this.view.getTableServico().getModel();
		tableModel.setNumRows(0);
		
		//Percorre a lista de usuários inserindo cada usuário na tabela
		for(Servico servico: servicos) {
			tableModel.addRow(new Object[] {
				servico.getId(),
				servico.getDescricao(),
				servico.getValor()
			});
		}
	}

	public Servico getServicoTabela() {
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableServico().getSelectedRow();
		
		//Obtém os atributos do serviço que estão no item selecionado da tabela
		int id = Integer.parseInt(view.getTableServico().getModel().getValueAt(linha, 0).toString());
		String descricao = view.getTableServico().getModel().getValueAt(linha, 1).toString();
		float valor = Float.parseFloat(view.getTableServico().getModel().getValueAt(linha, 2).toString());
		
		//Cria um objeto do tipo serviço e o inicializa com os dados da linha selecionada da tabela
		Servico servico = new Servico(id,descricao,valor);
		return servico;
	}
}
