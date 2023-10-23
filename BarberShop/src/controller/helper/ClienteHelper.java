package controller.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import view.ClienteView;

public class ClienteHelper {
	
	private ClienteView view;

	public ClienteHelper(ClienteView view) {
		super();
		this.view = view;
	}

	public void preencherTabela(ArrayList<Cliente> clientes) {
		//Obtém da view o objeto Table
		DefaultTableModel tableModel = (DefaultTableModel) this.view.getTableCliente().getModel();
		tableModel.setNumRows(0);
		
		//Percorre a lista de usuários inserindo cada cliente na tabela
		for(Cliente cliente: clientes) {
			tableModel.addRow(new Object[] {
				cliente.getId(),
				cliente.getNome(),
				cliente.getSexo(),
				cliente.dataNascimentoToString(),
				cliente.getTelefone(),
				cliente.getEmail(),
				cliente.getRg(),
				cliente.getEndereco(),
				cliente.getCep()
			});
		}
	}

	//Converte formulário em objeto do tipo cliente
	public Cliente getClienteFormulario() {

		//Obtém os atributos do cliente
		String nome = view.getTextNome().getText();
		String sexo = view.getComboBoxSexo().getSelectedItem().toString();
		String dataNascimento = view.getTextDataNasc().getText();
		String telefone = view.getTextTelefone().getText();
		String email = view.getTextEmail().getText();
		String rg = view.getTextRG().getText();
		String endereco = view.getTextEndereco().getText();
		int cep;
		if(view.getTextCEP().getText().equals("")) {
			cep = 0;
		}
		else {
			cep = Integer.parseInt(view.getTextCEP().getText());
		}

		Cliente cliente = new Cliente(0,nome,sexo,dataNascimento,telefone,email,rg,endereco,cep);
		return cliente;
	}

	//Verifica se os campos obrigatórios do cadastro de cliente foram preenchidos
	public boolean camposObrigatoriosPreenchidos(Cliente cliente) {
		if( cliente.getNome().equals("")) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean formatoDataCorreto(String data) {
		try {
			new SimpleDateFormat("dd/MM/yyyy").parse(data);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Cliente getClienteTabela() {
		
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableCliente().getSelectedRow();
		
		//Obtém os atributos do cliente que estão no item selecionado da tabela
		int id = Integer.parseInt(view.getTableCliente().getModel().getValueAt(linha, 0).toString());
		String nome = view.getTableCliente().getModel().getValueAt(linha, 1).toString();
		String sexo = view.getTableCliente().getModel().getValueAt(linha, 2).toString();
		String dataNascimento = view.getTableCliente().getModel().getValueAt(linha, 3).toString();
		String telefone = view.getTableCliente().getModel().getValueAt(linha, 4).toString();
		String email = view.getTableCliente().getModel().getValueAt(linha, 5).toString();
		String rg = view.getTableCliente().getModel().getValueAt(linha, 6).toString();
		String endereco = view.getTableCliente().getModel().getValueAt(linha, 7).toString();
		int cep = Integer.parseInt(view.getTableCliente().getModel().getValueAt(linha, 8).toString());
		
		//Cria um objeto do tipo cliente e o inicializa com os dados da linha selecionada da tabela
		Cliente cliente = new Cliente(id,nome,sexo,dataNascimento,telefone,email,rg,endereco,cep);
		return cliente;
	}
}
