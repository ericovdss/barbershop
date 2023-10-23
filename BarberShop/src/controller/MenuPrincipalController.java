package controller;

import view.AgendaView;
import view.ClienteView;
import view.UsuarioView;
import view.MenuPrincipalView;
import view.ServicoView;

public class MenuPrincipalController {
	
	private MenuPrincipalView view;

	public MenuPrincipalController(MenuPrincipalView view) {
		super();
		this.view = view;
	}

	public MenuPrincipalView getView() {
		return view;
	}

	public void setView(MenuPrincipalView view) {
		this.view = view;
	}
	
	public void abrirAgenda() {
		AgendaView agendaView = new AgendaView();
		agendaView.setVisible(true);
	}

	public void abrirFormularioUsuario() {
		UsuarioView usuarioView = new UsuarioView(false);
		usuarioView.setVisible(true);
	}

	public void abrirFormularioCliente() {
		ClienteView clienteView = new ClienteView();
		clienteView.setVisible(true);
	}

	public void abrirFormularioServico() {
		ServicoView servicoView = new ServicoView();
		servicoView.setVisible(true);
	}
}
