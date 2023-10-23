package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.helper.ServicoHelper;
import model.Servico;
import model.dao.Conexao;
import model.dao.ServicoDAO;
import view.CaixaDialogo;
import view.ServicoView;

public class ServicoController {
	
	private ServicoView view;
	private ServicoHelper helper;

	public ServicoController(ServicoView view) {
		super();
		this.view = view;
		this.helper = new ServicoHelper(view);
	}

	public void cadastrarServico() {
		
		//Obtém do helper um objeto do tipo serviço vindo do formulário
		Servico servicoFormulario = helper.getServicoFormulario();
		
		//Verifica se os campos obrigatórios foram preenchidos
		if(helper.camposObrigatoriosPreenchidos(servicoFormulario)) {
			try {
				//Abre uma conexão com o banco de dados
				Connection conexao = new Conexao().getConnection();
				
				ServicoDAO servicoDAO = new ServicoDAO(conexao);
				servicoDAO.insert(servicoFormulario);
				
				atualizaTabela();
				
				CaixaDialogo.exibeMensagem("Serviço cadastrado com sucesso!");
				
			} catch (SQLException e) {
				CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados!");
				e.printStackTrace();
			}
		} else {
			CaixaDialogo.exibeMensagem("Campo(s) obrigatório(s) não preenchido(s)!");
		}
	}

	public void atualizaTabela(){
		
		Connection conexao;
		try {
			//Abre uma conexão com o banco de dados
			conexao = new Conexao().getConnection();
			
			//Busca uma lista de serviços no banco de dados
			ServicoDAO servicoDAO = new ServicoDAO(conexao);
			ArrayList <Servico> servicos = servicoDAO.selectAll();
			
			//Exibe a lista de usuários na view ServicoView
			helper.preencherTabela(servicos);
			
		} catch (SQLException e) {
			CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados para atualizar a tabela!");
			e.printStackTrace();
		}		
	}

	public void preencherFormulario() {
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableServico().getSelectedRow();

		//Obtém os atributos do serviço que estão no item selecionado da tabela
		String descricao = view.getTableServico().getModel().getValueAt(linha, 1).toString();
		String valor = view.getTableServico().getModel().getValueAt(linha, 2).toString();
		
		//Preenche o formulário com os dados obtidos da linha selecionada da tabela
		view.getTextDescricao().setText(descricao);
		view.getTextValor().setText(valor);
	}

	public void atualizarServico() {
		//Pergunta se o usuário tem certeza que ele quer atualizar o item
		if(CaixaDialogo.perguntaSimNao("Você tem certeza que quer atualizar o serviço selecionado?")) {
			
			//Converte formulário em objeto do tipo serviço
			Servico servicoFormulario = helper.getServicoFormulario();
			//Atualiza o id do servico
			servicoFormulario = atualizaId(servicoFormulario);
			
			//Verifica se os campos obrigatórios foram preenchidos
			if(helper.camposObrigatoriosPreenchidos(servicoFormulario)) {
				
				//Abre uma conexão com o banco de dados
				Connection conexao;
				try {
					conexao = new Conexao().getConnection();
					ServicoDAO servicoDAO = new ServicoDAO(conexao);
					
					//O DAO converte objeto em query e atualiza o serviço no banco de dados
					servicoDAO.update(servicoFormulario);
					
					//Atualiza a tabela serviço da view
					atualizaTabela();
					
					//Exibe mensagem de sucesso de atualização
					CaixaDialogo.exibeMensagem("Serviço atualizado com sucesso!");
					
				} catch (SQLException e) {
					CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados!");
					e.printStackTrace();
				}
			}
			else {
				CaixaDialogo.exibeMensagem("Campo(s) obrigatório(s) não preenchido(s)!");
			}
		}
	}

	public void excluirServico() {
		//Pergunta se o usuário tem certeza que ele quer excluir o item
		if(CaixaDialogo.perguntaSimNao("Você tem certeza que quer excluir o serviço selecionado?")) {
			
			//Obtém do helper o objeto selecionado na tabela de serviços
			Servico servicoTabela = helper.getServicoTabela();
			
			try {
				//Abre uma conexão com o banco de dados
				Connection conexao = new Conexao().getConnection();
				ServicoDAO servicoDAO = new ServicoDAO(conexao);
				
				//O DAO converte objeto em query e exclui o serviço do banco de dados
				servicoDAO.delete(servicoTabela);
				
				//Atualiza a tabela serviço da view
				atualizaTabela();
				
				//Verifica se a tabela está vazia para desabilitar os botões
				if(view.getTableServico().getRowCount() == 0) {
					desabilitarBotoes();
				}
				
				//Exibe mensagem de sucesso de exclusão
				CaixaDialogo.exibeMensagem("Serviço excluído com sucesso!");
				
			} catch (SQLException e) {
				CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados!");
				e.printStackTrace();
			}
		}
	}
	
	//Habilita os botões de atualizar serviço e excluir serviço
	public void habilitarBotoes() {
		view.getButtonAtualizar().setEnabled(true);
		view.getButtonExcluir().setEnabled(true);
	}
	
	//Desabilita os botões de atualizar serviço e excluir serviço
	public void desabilitarBotoes() {
		view.getButtonAtualizar().setEnabled(false);
		view.getButtonExcluir().setEnabled(false);
	}
	
	public Servico atualizaId(Servico servico) {
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableServico().getSelectedRow();
		
		//Obtém o id do serviço vindo da tabela
		int id = Integer.parseInt(view.getTableServico().getModel().getValueAt(linha, 0).toString());
		
		//Atualiza o id do serviço
		servico.setId(id);
		
		return servico;
	}
}
