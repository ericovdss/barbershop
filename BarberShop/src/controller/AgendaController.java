package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.helper.AgendaHelper;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import model.dao.AgendamentoDAO;
import model.dao.ClienteDAO;
import model.dao.Conexao;
import model.dao.ServicoDAO;
import view.AgendaView;

public class AgendaController {
	
	private AgendaView view;
	private AgendaHelper helper;

	public AgendaController(AgendaView view) {
		super();
		this.view = view;
		this.helper = new AgendaHelper(view);
	}

	public AgendaView getView() {
		return view;
	}

	public void setView(AgendaView view) {
		this.view = view;
	}

	public void atualizaTabela() throws SQLException {
		Connection conexao = new Conexao().getConnection();
		//Busca uma lista de agendamentos no banco de dados
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO(conexao);
		ArrayList <Agendamento> agendamentos = agendamentoDAO.selectAll();
		
		//Exibe a lista de agendamentos na view Agenda
		helper.preencherTabela(agendamentos);
	}
	
	public void atualizaCliente() throws SQLException {
		
		Connection conexao = new Conexao().getConnection();
		//Busca a lista de clientes no banco de dados
		ClienteDAO clienteDAO = new ClienteDAO(conexao);
		ArrayList <Cliente> clientes = clienteDAO.selectAll();
		
		//Exibe esses clientes no combobox
		helper.preencherClientes(clientes);
	}
	
	public void atualizaServico() throws SQLException {
		
		Connection conexao = new Conexao().getConnection();
		//Busca a lista de serviços no banco de dados
		ServicoDAO servicoDAO = new ServicoDAO(conexao);
		ArrayList <Servico> servicos = servicoDAO.selectAll();
		
		//Exibe esses serviços no combobox
		helper.preencherServicos(servicos);
	}

	public void atualizaValor() {
		//Pede para o helper obter o objeto do tipo Servico da view
		Servico servico = helper.obterServico();
		
		//Edita o campo 'valor' do textField com o valor do serviço
		helper.editarValor(servico);
	}

	public void agendar() throws SQLException {
		Connection conexao = new Conexao().getConnection();
		//Busca da view o formulário e converte para objeto do tipo agendamento
		Agendamento agendamento = helper.obterAgendamento();
		
		//Insere o agendamento no banco de dados
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO(conexao);
		agendamentoDAO.insert(agendamento);
		
		atualizaTabela();
		helper.limparTela();
	}
}
