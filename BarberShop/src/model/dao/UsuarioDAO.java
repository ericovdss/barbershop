/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
	
	private Connection conexao;
	
	public UsuarioDAO(Connection conexao) {
		super();
		this.conexao = conexao;
	}

	/**
     * Insere um usuario dentro do banco de dados
     * @param usuario exige que seja passado um objeto do tipo usuario
	 * @throws SQLException 
     */
    public void insert(Usuario usuario) throws SQLException{
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "insert into usuario (nome,sexo,data_nascimento,telefone,email,rg,login,senha,nivel_acesso) values (?,?,?,?,?,?,?,?,?);";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setString(1,usuario.getNome());
    	statement.setString(2,usuario.getSexo());
    	statement.setString(3,usuario.dataNascimentoToString());
    	statement.setString(4,usuario.getTelefone());
    	statement.setString(5,usuario.getEmail());
    	statement.setString(6,usuario.getRg());
    	statement.setString(7,usuario.getLogin());
    	statement.setString(8,usuario.getSenha());
    	statement.setString(9,usuario.getNivelAcesso());

    	//Envia a ordem para executar a query
    	statement.execute();
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param usuario
     * @return 
     * @throws SQLException 
     */
    public void update(Usuario usuario) throws SQLException{
        
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "update usuario set nome = ?, sexo = ?, data_nascimento = ?, telefone = ?, email = ?, rg = ?, login = ?, senha =?, nivel_acesso = ? where id = ?;";
    			
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setString(1,usuario.getNome());
    	statement.setString(2,usuario.getSexo());
    	statement.setString(3,usuario.dataNascimentoToString());
    	statement.setString(4,usuario.getTelefone());
    	statement.setString(5,usuario.getEmail());
    	statement.setString(6,usuario.getRg());
    	statement.setString(7,usuario.getLogin());
    	statement.setString(8,usuario.getSenha());
    	statement.setString(9,usuario.getNivelAcesso());
    	statement.setInt(10,usuario.getId());
    	
    	//Envia a ordem para executar a query
    	statement.execute();

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do usuario passado
     * @param usuario
     * @return 
     * @throws SQLException 
     */
    public void delete(Usuario usuario) throws SQLException{
    	//Armazena a string da query a ser executada na variável 'sql'
    	String sql = "delete from usuario where id = ?;";
    			
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setInt(1,usuario.getId());
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    }
    
    /**
     * Retorna um arraylist com todos os usuarios do banco de dados
     * @return uma lista com todos os registros do banco
     * @throws SQLException 
     */
    public ArrayList<Usuario> selectAll() throws SQLException{
    	//Cria uma string com a query para selecionar todos os itens da tabela usuário
    	String sql = "select * from usuario;";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Cria uma arraylist de usuários
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    	
    	//Obtém o resultado da consulta ao banco de dados
    	ResultSet resultSet = statement.getResultSet();
    	
    	//Enquanto houver um resultado da consulta, adiciona-o à lista de usuários
    	while(resultSet.next()) {
    		int id = resultSet.getInt("id");
    		String nome = resultSet.getString("nome");
    		String sexo = resultSet.getString("sexo");
    		String dataNascimento = resultSet.getString("data_nascimento");
    		String telefone = resultSet.getString("telefone");
    		String email = resultSet.getString("email");
    		String rg = resultSet.getString("rg");
    		String login = resultSet.getString("login");
    		String senha = resultSet.getString("senha");
    		String nivelAcesso = resultSet.getString("nivel_acesso");
    		if(dataNascimento==null) {
    			dataNascimento = "";
    		}
    		Usuario usuario = new Usuario(id,nome,sexo,dataNascimento,telefone,email,rg,login,senha,nivelAcesso);
    		usuarios.add(usuario);
    	}
    	
        return usuarios;
    }
    
    /**
     * Retorna um Objeto do tipo usuario se a funcao encontrar o usuario passado como parâmetro no banco, para considerar sao usados login e senha
     * @param usuario
     * @return Usuario encontrado no banco de dados
     * @throws SQLException 
     */
    public Usuario selectPorLoginESenha(Usuario usuario) throws SQLException{
    	
    	//Faz a query que consulta um usuario com login e senha iguais
    	String sql = "select * from usuario where login = ? and senha = ?;";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setString(1,usuario.getLogin());
    	statement.setString(2,usuario.getSenha());
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Pega o resultado da query
    	ResultSet resultSet = statement.getResultSet();
    	
    	if(resultSet.next()) {
    		int id = resultSet.getInt("id");
    		String nome = resultSet.getString("nome");
    		String sexo = resultSet.getString("sexo");
    		String dataNascimento = resultSet.getString("data_nascimento");
    		String telefone = resultSet.getString("telefone");
    		String email = resultSet.getString("email");
    		String rg = resultSet.getString("rg");
    		String login = resultSet.getString("login");
    		String senha = resultSet.getString("senha");
    		String nivelAcesso = resultSet.getString("nivel_acesso");
    		if(dataNascimento==null) {
    			dataNascimento = "";
    		}
    		usuario = new Usuario(id,nome,sexo,dataNascimento,telefone,email,rg,login,senha,nivelAcesso);
    		return usuario;
    	} else {
    		return null;
    	}
    }
    
    /**
     * Retorna um Objeto do tipo usuario se a funcao encontrar o usuario passado como parâmetro no banco, para considerar é usado o login
     * @param usuario
     * @return Usuario encontrado no banco de dados
     * @throws SQLException 
     */
    public Usuario selectPorLogin(Usuario usuario) throws SQLException{
    	
    	//Faz a query que consulta um usuario com login e senha iguais
    	String sql = "select * from usuario where login = ?;";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	statement.setString(1,usuario.getLogin());
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Pega o resultado da query
    	ResultSet resultSet = statement.getResultSet();
    	
    	if(resultSet.next()) {
    		int id = resultSet.getInt("id");
    		String nome = resultSet.getString("nome");
    		String sexo = resultSet.getString("sexo");
    		String dataNascimento = resultSet.getString("data_nascimento");
    		String telefone = resultSet.getString("telefone");
    		String email = resultSet.getString("email");
    		String rg = resultSet.getString("rg");
    		String login = resultSet.getString("login");
    		String senha = resultSet.getString("senha");
    		String nivelAcesso = resultSet.getString("nivel_acesso");
    		if(dataNascimento==null) {
    			dataNascimento = "";
    		}
    		usuario = new Usuario(id,nome,sexo,dataNascimento,telefone,email,rg,login,senha,nivelAcesso);
    		return usuario;
    	} else {
    		return null;
    	}
    }

	public boolean isEmpty() throws SQLException {
		//Faz a query que retorna a primeira linha da tabela usuário
    	String sql = "select * from usuario limit 1;";
    	
    	//Prepara (compila) a query para ser executada
    	PreparedStatement statement = conexao.prepareStatement(sql);
    	
    	//Envia a ordem para executar a query
    	statement.execute();
    	
    	//Pega o resultado da query
    	ResultSet resultSet = statement.getResultSet();
    	
    	//Retorna se verdadeiro se não houver resultado e falso caso haja
    	return !resultSet.next();
	} 
}
