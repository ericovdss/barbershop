/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
	
	private Connection conexao;    
    
	public ClienteDAO(Connection conexao) {
		super();
		this.conexao = conexao;
	}

	/**
     * Insere um cliente dentro do banco de dados
     * @param cliente exige que seja passado um objeto do tipo cliente
	 * @throws SQLException 
     */
    public void insert(Cliente cliente) throws SQLException{
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "insert into cliente (nome,sexo,data_nascimento,telefone,email,rg,endereco,cep) values (?,?,?,?,?,?,?,?);";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setString(1,cliente.getNome());
    	statement.setString(2,cliente.getSexo());
    	statement.setString(3,cliente.dataNascimentoToString());
    	statement.setString(4,cliente.getTelefone());
    	statement.setString(5,cliente.getEmail());
    	statement.setString(6,cliente.getRg());
    	statement.setString(7,cliente.getEndereco());
    	statement.setInt(8,cliente.getCep());

    	//Envia a ordem para executar a query
    	statement.execute();
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param cliente
     * @return 
     * @throws SQLException 
     */
    public void update(Cliente cliente) throws SQLException{
        
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "update cliente set nome = ?, sexo = ?, data_nascimento = ?, telefone = ?, email = ?, rg = ?, endereco = ?, cep =? where id = ?;";
    			
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setString(1,cliente.getNome());
    	statement.setString(2,cliente.getSexo());
    	statement.setString(3,cliente.dataNascimentoToString());
    	statement.setString(4,cliente.getTelefone());
    	statement.setString(5,cliente.getEmail());
    	statement.setString(6,cliente.getRg());
    	statement.setString(7,cliente.getEndereco());
    	statement.setInt(8,cliente.getCep());
    	statement.setInt(9,cliente.getId());
    	
    	//Envia a ordem para executar a query
    	statement.execute();

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do cliente passado
     * @param cliente
     * @return 
     * @throws SQLException 
     */
    public void delete(Cliente cliente) throws SQLException{
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "delete from cliente where id = ?;";
    			
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setInt(1,cliente.getId());
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    }
    
    /**
     * Retorna um arraylist com todos os clientes do banco de dados
     * @return uma lista com todos os registros do banco
     * @throws SQLException 
     */
    public ArrayList<Cliente> selectAll() throws SQLException{
    	//Cria uma string com a query para selecionar todos os itens da tabela cliente
    	String sql = "select * from cliente;";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Cria uma arraylist de clientes
    	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    	
    	//Obtém o resultado da consulta ao banco de dados
    	ResultSet resultSet = statement.getResultSet();
    	
    	//Enquanto houver um resultado da consulta, adiciona-o à lista de clientes
    	while(resultSet.next()) {
    		int id = resultSet.getInt("id");
    		String nome = resultSet.getString("nome");
    		String sexo = resultSet.getString("sexo");
    		String dataNascimento = resultSet.getString("data_nascimento");
    		String telefone = resultSet.getString("telefone");
    		String email = resultSet.getString("email");
    		String rg = resultSet.getString("rg");
    		String endereco = resultSet.getString("endereco");
    		int cep = resultSet.getInt("cep");
    		if(dataNascimento==null) {
    			dataNascimento = "";
    		}
    		Cliente cliente = new Cliente(id,nome,sexo,dataNascimento,telefone,email,rg,endereco,cep);
    		clientes.add(cliente);
    	}
    	
        return clientes;
    }

	public Cliente selectPorId(int id) throws SQLException {
		String sql = "select * from cliente where id =?;";
		
		//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setInt(1,id);
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Obtém o resultado da consulta ao banco de dados
    	ResultSet resultSet = statement.getResultSet();
    	
    	if(resultSet.next()) {
    		String nome = resultSet.getString("nome");
    		String sexo = resultSet.getString("sexo");
    		String dataNascimento = resultSet.getString("data_nascimento");
    		String telefone = resultSet.getString("telefone");
    		String email = resultSet.getString("email");
    		String rg = resultSet.getString("rg");
    		String endereco = resultSet.getString("endereco");
    		int cep = resultSet.getInt("cep");
    		if(dataNascimento==null) {
    			dataNascimento = "";
    		}
    		Cliente cliente = new Cliente(id,nome,sexo,dataNascimento,telefone,email,rg,endereco,cep);
    		return cliente;
    	} else {
    		return null;
    	}
	}   
}