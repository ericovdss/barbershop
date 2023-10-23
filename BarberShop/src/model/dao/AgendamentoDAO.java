package model.dao;

import model.Agendamento;
import model.Cliente;
import model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendamentoDAO {
	
	private Connection conexao;
    
    public AgendamentoDAO(Connection conexao) {
		super();
		this.conexao = conexao;
	}

	/**
     * Insere um agendamento dentro do banco de dados
     * @param agendamento exige que seja passado um objeto do tipo agendamento
	 * @throws SQLException 
     */
    public void insert(Agendamento agendamento) throws SQLException{
        
    	String sql = "insert into agendamento (id_cliente,id_servico,valor,data,hora,observacao) values (?,?,?,?,?,?);";
    	
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setInt(1,agendamento.getCliente().getId());
    	statement.setInt(2,agendamento.getServico().getId());
    	statement.setFloat(3,agendamento.getValor());
    	statement.setString(4,agendamento.getDataFormatada());
    	statement.setString(5,agendamento.getHoraFormatada());
    	statement.setString(6,agendamento.getObservacao());

    	statement.execute(); 
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param agendamento
     * @return 
     */
    public boolean update(Agendamento agendamento){
        
        //TODO
    	return false;

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do agendamento passado
     * @param agendamento
     * @return 
     */
    public boolean delete(Agendamento agendamento){
        
    	//TODO
        return false;
    }
    
    /**
     * Retorna um arraylist com todos os agendamentos do banco de dados
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Agendamento> selectAll() throws SQLException{
    	//Cria uma string com a query para selecionar todos os itens da tabela agendamento
    	String sql = "select * from agendamento;";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Cria uma arraylist de agendamentos
    	ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
    	
    	//Obtém o resultado da consulta ao banco de dados
    	ResultSet resultSet = statement.getResultSet();
    	
    	//Enquanto houver um resultado da consulta, adiciona-o à lista de agendamentos
    	while(resultSet.next()) {
    		int id = resultSet.getInt("id");
    		int id_cliente = resultSet.getInt("id_cliente");
    		int id_servico = resultSet.getInt("id_servico");
    		float valor = resultSet.getFloat("valor");
    		String data = resultSet.getString("data");
    		String hora = resultSet.getString("hora");
    		String observacao = resultSet.getString("observacao");
    		
    		//Fazer a conversão da data e da hora em uma string só
    		String dataHora = data+" "+hora;
    		
    		//Busca no banco de dados cliente com aquele id
    		ClienteDAO clienteDAO = new ClienteDAO(conexao);
    		Cliente cliente = clienteDAO.selectPorId(id_cliente);
    		
    		//Busca no banco de dados servico com aquele id
    		ServicoDAO servicoDAO = new ServicoDAO(conexao);
    		Servico servico = servicoDAO.selectPorId(id_servico);
    		
    		Agendamento agendamento = new Agendamento(id,cliente,servico,valor,dataHora,observacao);
    		agendamentos.add(agendamento);
    	}
    	
        return agendamentos;
    }
}
