package controller.helper;

import model.Usuario;
import view.LoginView;

public class LoginHelper {
	
	private LoginView view;

	public LoginHelper(LoginView view) {
		super();
		this.view = view;
	}

	public LoginView getView() {
		return view;
	}

	public void setView(LoginView view) {
		this.view = view;
	}
	
	public Usuario getUsuario() {
		@SuppressWarnings("deprecation")
		Usuario usuario = new Usuario(0,null,view.getTextUsuario().getText(),view.getTextSenha().getText(),null);
		return usuario;
	}
}
