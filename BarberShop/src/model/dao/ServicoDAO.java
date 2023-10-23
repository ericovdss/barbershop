/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicoDAO {
    
	private Connection conexao;
	
	public ServicoDAO(Connection conexao) {
		super();
		this.conexao = conexao;
	}
	
    /**
     * Insere um servico dentro do banco de dados
     * @param servico exige que seja passado um objeto do tipo servico
     * @throws SQLException 
     */
    public void insert(Servico servico) throws SQLException{
        String sql = "insert into servico (descricao,valor) values (?,?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1,servico.getDescricao());
        statement.setFloat(2,servico.getValor());
        statement.execute();
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param servico
     * @return 
     * @throws SQLException 
     */
    public void update(Servico servico) throws SQLException{
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "update servico set descricao = ?, valor = ? where id = ?;";
    			
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setString(1,servico.getDescricao());
    	statement.setFloat(2,servico.getValor());
    	statement.setInt(3,servico.getId());
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do servico passado
     * @param servico
     * @return 
     * @throws SQLException 
     */
    public void delete(Servico servico) throws SQLException{
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "delete from servico where id = ?;";
    			
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setInt(1,servico.getId());
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    }
    
    /**
     * Retorna um arraylist com todos os servicos do banco de dados
     * @return uma lista com todos os registros do banco
     * @throws SQLException 
     */
    public ArrayList<Servico> selectAll() throws SQLException{
        String sql = "select * from servico;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        ArrayList<Servico> servicos = new ArrayList<Servico>();
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
        	int id = resultSet.getInt("id");
    		String descricao = resultSet.getString("descricao");
    		float valor = resultSet.getFloat("valor");
    		Servico servico = new Servico(id,descricao,valor);
    		servicos.add(servico);
        }
    	return servicos;
    }

	public Servico selectPorId(int id) throws SQLException {
		String sql = "select * from servico where id =?;";
		
		//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setInt(1,id);
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Obtém o resultado da consulta ao banco de dados
    	ResultSet resultSet = statement.getResultSet();
    	
    	if(resultSet.next()) {
    		String descricao = resultSet.getString("descricao");
    		float valor = resultSet.getFloat("valor");
    		Servico servico = new Servico(id,descricao,valor);
    		return servico;
    	} else {
    		return null;
    	}
	}
}
