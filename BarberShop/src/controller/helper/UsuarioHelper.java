package controller.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import view.UsuarioView;

public class UsuarioHelper {
	
	private UsuarioView view;

	public UsuarioHelper(UsuarioView view) {
		super();
		this.view = view;
	}

	//Converte formulário em objeto do tipo usuário
	public Usuario getUsuarioFormulario() {
		
		//Obtém o índice da linha selecionada da tabela
		//int linha = view.getTableUsuario().getSelectedRow();
				
		//Obtém os atributos do usuário
		//int id = Integer.parseInt(view.getTableUsuario().getModel().getValueAt(linha, 0).toString());
		String nome = view.getTextNome().getText();
		String sexo = view.getComboBoxSexo().getSelectedItem().toString();
		String dataNascimento = view.getTextDataNasc().getText();
		String telefone = view.getTextTelefone().getText();
		String email = view.getTextEmail().getText();
		String rg = view.getTextRG().getText();
		String login = view.getTextLogin().getText();
		@SuppressWarnings("deprecation")
		String senha = view.getTextSenha().getText();
		String nivelAcesso = view.getComboBoxNivelAcesso().getSelectedItem().toString();
		
		Usuario usuario = new Usuario(0,nome,sexo,dataNascimento,telefone,email,rg,login,senha,nivelAcesso);
		return usuario;
	}

	//Verifica se os campos obrigatórios do cadastro de usuário foram preenchidos
	public boolean camposObrigatoriosPreenchidos(Usuario usuario) {
		if( usuario.getNome().equals("")||usuario.getLogin().equals("")||usuario.getSenha().equals("")||usuario.getNivelAcesso().equals("")) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean formatoDataCorreto(String data) {
		try {
			new SimpleDateFormat("dd/MM/yyyy").parse(data);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Transforma um objeto do tipo Date em String no formato dd/mm/aaaa
	public String DateToString(Date dataNascimento) {
		if(dataNascimento!=null) {
			@SuppressWarnings("deprecation")
			int dia = dataNascimento.getDate();
			@SuppressWarnings("deprecation")
			int mes = dataNascimento.getMonth();
			mes++;
			@SuppressWarnings("deprecation")
			int ano = dataNascimento.getYear();
			ano+=1900;
			
			if(dia<10) {
				return "0"+dia+"/"+mes+"/"+ano;
			}
			else {
				return dia+"/"+mes+"/"+ano;
			}
			
		}
		else {
			return "";
		}
	}

	public void preencherTabela(ArrayList<Usuario> usuarios) {
		//Obtém da view o objeto Table
		DefaultTableModel tableModel = (DefaultTableModel) this.view.getTableUsuario().getModel();
		tableModel.setNumRows(0);
		
		//Percorre a lista de usuários inserindo cada usuário na tabela
		for(Usuario usuario: usuarios) {
			tableModel.addRow(new Object[] {
				usuario.getId(),
				usuario.getNome(),
				usuario.getSexo(),
				usuario.dataNascimentoToString(),
				usuario.getTelefone(),
				usuario.getEmail(),
				usuario.getRg(),
				usuario.getLogin(),
				usuario.getNivelAcesso()
			});
		}
	}

	public Usuario getUsuarioTabela() {
		
		//Obtém o índice da linha selecionada da tabela
		int linha = view.getTableUsuario().getSelectedRow();
		
		//Obtém os atributos do usuário que estão no item selecionado da tabela
		int id = Integer.parseInt(view.getTableUsuario().getModel().getValueAt(linha, 0).toString());
		String nome = view.getTableUsuario().getModel().getValueAt(linha, 1).toString();
		String sexo = view.getTableUsuario().getModel().getValueAt(linha, 2).toString();
		String dataNascimento = view.getTableUsuario().getModel().getValueAt(linha, 3).toString();
		String telefone = view.getTableUsuario().getModel().getValueAt(linha, 4).toString();
		String email = view.getTableUsuario().getModel().getValueAt(linha, 5).toString();
		String rg = view.getTableUsuario().getModel().getValueAt(linha, 6).toString();
		String login = view.getTableUsuario().getModel().getValueAt(linha, 7).toString();
		String nivelAcesso = view.getTableUsuario().getModel().getValueAt(linha, 8).toString();
		
		//Cria um objeto do tipo usuário e o inicializa com os dados da linha selecionada da tabela
		Usuario usuario = new Usuario(id,nome,sexo,dataNascimento,telefone,email,rg,login,"",nivelAcesso);
		return usuario;
	}

}