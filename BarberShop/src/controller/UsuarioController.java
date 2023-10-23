package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.helper.UsuarioHelper;
import model.NivelAcesso;
import model.Sexo;
import model.Usuario;
import model.dao.Conexao;
import model.dao.UsuarioDAO;
import view.UsuarioView;
import view.CaixaDialogo;

public class UsuarioController {
	
	private UsuarioView view;
	private UsuarioHelper helper;
	
	public UsuarioController(UsuarioView view) {
		super();
		this.view = view;
		this.helper = new UsuarioHelper(view);
	}
	
	public void cadastrarUsuario() {
		//Converte formulário em objeto do tipo usuário
		Usuario usuarioFormulario = helper.getUsuarioFormulario();
		
		//Verifica se os campos obrigatórios foram preenchidos (tanto textfield quanto combobox)
		if(helper.camposObrigatoriosPreenchidos(usuarioFormulario)) {
			
			String dataNascimento = helper.DateToString(usuarioFormulario.getDataNascimento());
			//Verifica se o formato dos dados está correto (exemplo: a data deve ser dd/mm/aaaa)
			if (helper.formatoDataCorreto(dataNascimento)) {
				
				//Abre uma conexão com o banco de dados
				try {
					Connection conexao = new Conexao().getConnection();
					UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
					
					//Verifica se não existe outro usuário no banco de dados com o mesmo login do formulário
					if(usuarioDAO.selectPorLogin(usuarioFormulario) == null) {
						
						//O DAO converte objeto em query e insere no banco de dados
						usuarioDAO.insert(usuarioFormulario);
						
						//Atualiza a tabela usuário da view
						atualizaTabela();
						
						//Exibe mensagem de sucesso de inserção
						CaixaDialogo.exibeMensagem("Novo usuário cadastrado com sucesso!");
						
						//Fecha a tela de cadastro
						if(view.isFecharAoCadastrar()) {
							view.dispose();
						}
					}
					else {
						CaixaDialogo.exibeMensagem("Já existe outro usuário com esse login!");
					}
					
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

	public void atualizarUsuario() {
		//Pergunta se o usuário tem certeza que ele quer atualizar o item
		if(CaixaDialogo.perguntaSimNao("Você tem certeza que quer atualizar o usuário selecionado?")) {
			
			//Converte formulário em objeto do tipo usuário
			Usuario usuarioFormulario = helper.getUsuarioFormulario();
			//Atualiza o id do usuário
			usuarioFormulario = atualizaId(usuarioFormulario);
			
			//Verifica se os campos obrigatórios foram preenchidos
			if(helper.camposObrigatoriosPreenchidos(usuarioFormulario)) {
				
				String dataNascimento = helper.DateToString(usuarioFormulario.getDataNascimento());
				//Verifica se o formato dos dados está correto (exemplo: a data deve ser dd/mm/aaaa)
				if (helper.formatoDataCorreto(dataNascimento)) {
					
					//Abre uma conexão com o banco de dados
					Connection conexao;
					try {
						conexao = new Conexao().getConnection();
						UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

						//O DAO converte objeto em query e atualiza o usuário no banco de dados
						usuarioDAO.update(usuarioFormulario);

						//Atualiza a tabela usuário da view
						atualizaTabela();

						//Exibe mensagem de sucesso de atualização
						CaixaDialogo.exibeMensagem("Usuário atualizado com sucesso!");

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
	
	public void excluirUsuario() {
		//Pergunta se o usuário tem certeza que ele quer excluir o item
		if(CaixaDialogo.perguntaSimNao("Você tem certeza que quer excluir o usuário selecionado?")) {
		
			//Obtém do helper o objeto selecionado na tabela de usuários
			Usuario usuarioTabela = helper.getUsuarioTabela();

			try {
				//Abre uma conexão com o banco de dados
				Connection conexao = new Conexao().getConnection();
				UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

				//O DAO converte objeto em query e exclui o usuário do banco de dados
				usuarioDAO.delete(usuarioTabela);
				
				//Atualiza a tabela usuário da view
				atualizaTabela();
				
				//Verifica se a tabela está vazia para desabilitar os botões
				if(view.getTableUsuario().getRowCount() == 0) {
					desabilitarBotoes();
				}
				
				//Exibe mensagem de sucesso de exclusão
				CaixaDialogo.exibeMensagem("Usuário excluído com sucesso!");

			} catch (SQLException e) {
				CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados!");
				e.printStackTrace();
			}
		}
	}

	public UsuarioView getView() {
		return view;
	}

	public void setView(UsuarioView view) {
		this.view = view;
	}

	public UsuarioHelper getHelper() {
		return helper;
	}

	public void setHelper(UsuarioHelper helper) {
		this.helper = helper;
	}

	public void habilitarBotoes() {
		view.getButtonAtualizar().setEnabled(true);
		view.getButtonExcluir().setEnabled(true);
	}
	
	public void desabilitarBotoes() {
		view.getButtonAtualizar().setEnabled(false);
		view.getButtonExcluir().setEnabled(false);
	}

	public void atualizaTabela(){
		
		Connection conexao;
		try {
			//Abre uma conexão com o banco de dados
			conexao = new Conexao().getConnection();
			
			//Busca uma lista de usuários no banco de dados
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			ArrayList <Usuario> usuarios = usuarioDAO.selectAll();
			
			//Exibe a lista de usuários na view UsuarioView
			helper.preencherTabela(usuarios);
			
		} catch (SQLException e) {
			CaixaDialogo.exibeMensagem("Não foi possível conectar com o banco de dados para atualizar a tabela!");
			e.printStackTrace();
		}		
	}

	public void preencherFormulario() {
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableUsuario().getSelectedRow();

		//Obtém os atributos do usuário que estão no item selecionado da tabela
		String nome = view.getTableUsuario().getModel().getValueAt(linha, 1).toString();
		String sexo = view.getTableUsuario().getModel().getValueAt(linha, 2).toString();
		String dataNascimento = view.getTableUsuario().getModel().getValueAt(linha, 3).toString();
		String telefone = view.getTableUsuario().getModel().getValueAt(linha, 4).toString();
		String email = view.getTableUsuario().getModel().getValueAt(linha, 5).toString();
		String rg = view.getTableUsuario().getModel().getValueAt(linha, 6).toString();
		String login = view.getTableUsuario().getModel().getValueAt(linha, 7).toString();
		String nivelAcesso = view.getTableUsuario().getModel().getValueAt(linha, 8).toString();
		
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
		view.getTextLogin().setText(login);
		view.getTextSenha().setText("");
		if(nivelAcesso.equals(NivelAcesso.ADMINISTRADOR.toString())) {
			view.getComboBoxNivelAcesso().setSelectedIndex(1);
		} else if(nivelAcesso.equals(NivelAcesso.FUNCIONARIO.toString())) {
			view.getComboBoxNivelAcesso().setSelectedIndex(2);
		} else {
			view.getComboBoxNivelAcesso().setSelectedIndex(0);
		}
	}
	
	public Usuario atualizaId(Usuario usuario) {
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableUsuario().getSelectedRow();
				
		//Obtém os atributos do usuário
		int id = Integer.parseInt(view.getTableUsuario().getModel().getValueAt(linha, 0).toString());
		
		//Atualiza o id do usuário
		usuario.setId(id);
		
		return usuario;
	}
}




















