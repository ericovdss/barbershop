package controller;

import view.ClienteView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.helper.ClienteHelper;
import model.Cliente;
import model.Sexo;
import model.dao.ClienteDAO;
import model.dao.Conexao;
import view.CaixaDialogo;

public class ClienteController {
	
	private ClienteView view;
	private ClienteHelper helper;

	public ClienteController(ClienteView view) {
		this.view = view;
		this.helper = new ClienteHelper(view);
	}

	public void cadastrarCliente() {
		//Converte formulário em objeto do tipo cliente
		Cliente clienteFormulario = helper.getClienteFormulario();
		
		//Verifica se os campos obrigatórios foram preenchidos (tanto textfield quanto combobox)
		if(helper.camposObrigatoriosPreenchidos(clienteFormulario)) {
			
			String dataNascimento = clienteFormulario.dataNascimentoToString();
			//Verifica se o formato dos dados está correto (exemplo: a data deve ser dd/mm/aaaa)
			if (helper.formatoDataCorreto(dataNascimento)) {
				
				//Abre uma conexão com o banco de dados
				try {
					Connection conexao = new Conexao().getConnection();
					ClienteDAO clienteDAO = new ClienteDAO(conexao);
						
					//O DAO converte objeto em query e insere no banco de dados
					clienteDAO.insert(clienteFormulario);
						
					//Atualiza a tabela cliente da view
					atualizaTabela();
						
					//Exibe mensagem de sucesso de inserção
					CaixaDialogo.exibeMensagem("Novo cliente cadastrado com sucesso!");
					
				} catch (SQLException e) {
					CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados!");
					e.printStackTrace();
				}
			}
			else {
				CaixaDialogo.exibeMensagem("O formato da data deve ser: dd/mm/aaaa");
			}
		}
		else {
			CaixaDialogo.exibeMensagem("Campo(s) obrigatório(s) não preenchido(s)!");
		}
	}

	public ClienteView getView() {
		return view;
	}

	public void setView(ClienteView view) {
		this.view = view;
	}
	
	//Habilita os botões de atualizar cliente e excluir cliente
	public void habilitarBotoes() {
		view.getButtonAtualizar().setEnabled(true);
		view.getButtonExcluir().setEnabled(true);
	}
	
	//Desabilita os botões de atualizar cliente e excluir cliente
	public void desabilitarBotoes() {
		view.getButtonAtualizar().setEnabled(false);
		view.getButtonExcluir().setEnabled(false);
	}

	public void atualizarCliente() {
		//Pergunta se o usuário tem certeza que ele quer atualizar o item
		if(CaixaDialogo.perguntaSimNao("Você tem certeza que quer atualizar o cliente selecionado?")) {
			
			//Converte formulário em objeto do tipo cliente
			Cliente clienteFormulario = helper.getClienteFormulario();
			//Atualiza o id do usuário
			clienteFormulario = atualizaId(clienteFormulario);
			
			//Verifica se os campos obrigatórios foram preenchidos
			if(helper.camposObrigatoriosPreenchidos(clienteFormulario)) {
				
				String dataNascimento = clienteFormulario.dataNascimentoToString();
				//Verifica se o formato dos dados está correto (exemplo: a data deve ser dd/mm/aaaa)
				if (helper.formatoDataCorreto(dataNascimento)) {
					
					//Abre uma conexão com o banco de dados
					Connection conexao;
					try {
						conexao = new Conexao().getConnection();
						ClienteDAO usuarioDAO = new ClienteDAO(conexao);

						//O DAO converte objeto em query e atualiza o cliente no banco de dados
						usuarioDAO.update(clienteFormulario);

						//Atualiza a tabela cliente da view
						atualizaTabela();

						//Exibe mensagem de sucesso de atualização
						CaixaDialogo.exibeMensagem("Cliente atualizado com sucesso!");

					} catch (SQLException e) {
						CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados!");
						e.printStackTrace();
					}
				}
				else {
					CaixaDialogo.exibeMensagem("O formato da data deve ser: dd/mm/aaaa");
				}
			}
			else {
				CaixaDialogo.exibeMensagem("Campo(s) obrigatório(s) não preenchido(s)!");
			}
		}
	}

	public void excluirCliente() {
		//Pergunta se o usuário tem certeza que ele quer excluir o item
		if(CaixaDialogo.perguntaSimNao("Você tem certeza que quer excluir o cliente selecionado?")) {
		
			//Obtém do helper o objeto selecionado na tabela de clientes
			Cliente clienteTabela = helper.getClienteTabela();

			try {
				//Abre uma conexão com o banco de dados
				Connection conexao = new Conexao().getConnection();
				ClienteDAO clienteDAO = new ClienteDAO(conexao);

				//O DAO converte objeto em query e exclui o cliente do banco de dados
				clienteDAO.delete(clienteTabela);
				
				//Atualiza a tabela cliente da view
				atualizaTabela();
				
				//Verifica se a tabela está vazia para desabilitar os botões
				if(view.getTableCliente().getRowCount() == 0) {
					desabilitarBotoes();
				}
				
				//Exibe mensagem de sucesso de exclusão
				CaixaDialogo.exibeMensagem("Cliente excluído com sucesso!");

			} catch (SQLException e) {
				CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados!");
				e.printStackTrace();
			}
		}
	}

	public void atualizaTabela(){
		
		Connection conexao;
		try {
			//Abre uma conexão com o banco de dados
			conexao = new Conexao().getConnection();
			
			//Busca uma lista de clientes no banco de dados
			ClienteDAO clienteDAO = new ClienteDAO(conexao);
			ArrayList <Cliente> clientes = clienteDAO.selectAll();
			
			//Exibe a lista de clientes na view UsuarioView
			helper.preencherTabela(clientes);
			
		} catch (SQLException e) {
			CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados para atualizar a tabela!");
			e.printStackTrace();
		}		
	}

	public void preencherFormulario() {
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableCliente().getSelectedRow();

		//Obtém os atributos do cliente que estão no item selecionado da tabela
		String nome = view.getTableCliente().getModel().getValueAt(linha, 1).toString();
		String sexo = view.getTableCliente().getModel().getValueAt(linha, 2).toString();
		String dataNascimento = view.getTableCliente().getModel().getValueAt(linha, 3).toString();
		String telefone = view.getTableCliente().getModel().getValueAt(linha, 4).toString();
		String email = view.getTableCliente().getModel().getValueAt(linha, 5).toString();
		String rg = view.getTableCliente().getModel().getValueAt(linha, 6).toString();
		String endereco = view.getTableCliente().getModel().getValueAt(linha, 7).toString();
		String cep = view.getTableCliente().getModel().getValueAt(linha, 8).toString();
		
		//Preenche o formulário com os dados obtidos da linha selecionada da tabela
		view.getTextNome().setText(nome);
		if(sexo.equals(Sexo.MASCULINO.toString())) {
			view.getComboBoxSexo().setSelectedIndex(1);
		} else if(sexo.equals(Sexo.FEMININO.toString())) {
			view.getComboBoxSexo().setSelectedIndex(2);
		} else {
			view.getComboBoxSexo().setSelectedIndex(0);
		}
		view.getTextDataNasc().setText(dataNascimento);
		view.getTextTelefone().setText(telefone);
		view.getTextEmail().setText(email);
		view.getTextRG().setText(rg);
		view.getTextEndereco().setText(endereco);
		view.getTextCEP().setText(cep);
	}

	public Cliente atualizaId(Cliente cliente) {
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableCliente().getSelectedRow();
				
		//Obtém os atributos do usuário
		int id = Integer.parseInt(view.getTableCliente().getModel().getValueAt(linha, 0).toString());
		
		//Atualiza o id do usuário
		cliente.setId(id);
		
		return cliente;
	}
}
