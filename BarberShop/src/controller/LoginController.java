package controller;

import java.sql.Connection;
import java.sql.SQLException;
import controller.helper.LoginHelper;
import model.Usuario;
import model.dao.Conexao;
import model.dao.UsuarioDAO;
import view.CaixaDialogo;
import view.LoginView;
import view.MenuPrincipalView;

public class LoginController {
	
	private LoginView view;
	private LoginHelper helper;

	public LoginController(LoginView view) {
		super();
		this.view = view;
		this.helper = new LoginHelper(view);
	}

	public LoginView getView() {
		return view;
	}

	public void setView(LoginView view) {
		this.view = view;
	}
	
	public LoginHelper getHelper() {
		return helper;
	}

	public void setHelper(LoginHelper helper) {
		this.helper = helper;
	}

	public void entrar() {
		//Converte formulário em objeto do tipo Usuário
		Usuario usuarioFormulario = helper.getUsuario();
		
		Connection conexao;
		try {
			//Abre uma conexão com o banco de dados
			conexao = new Conexao().getConnection();
			
			//Busca Usuário no banco de dados
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			Usuario usuarioBanco = usuarioDAO.selectPorLoginESenha(usuarioFormulario);
			
			//Se o Usuário do formulário tiver login e senha igual ao do banco de dados, entra no sistema
			if(usuarioBanco!=null){
				MenuPrincipalView menuPrincipal = new MenuPrincipalView(usuarioFormulario);
				menuPrincipal.setVisible(true);
				view.dispose();
			}
			//Senão exibe mensagem de login ou senha inválidos
			else {
				CaixaDialogo.exibeMensagem("Usuário ou senha inválidos!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean tabelaUsuarioVazia() {
		
		Connection conexao;
		try {
			//Abre uma conexão com o banco de dados
			conexao = new Conexao().getConnection();
			
			//Verifica se a tabela usuário do banco de dados está vazia
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			return usuarioDAO.isEmpty();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
