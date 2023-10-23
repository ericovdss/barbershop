package model;

import java.text.SimpleDateFormat;

public class Usuario extends Pessoa{

	private String login;
	private String senha;
	private String nivelAcesso;
	
	public Usuario(int id, String nome, String sexo, String dataNascimento, String telefone, String email, String rg,
			String login, String senha, String nivelAcesso) {
		super(id, nome, sexo, dataNascimento, telefone, email, rg);
		this.login = login;
		this.senha = senha;
		this.nivelAcesso = nivelAcesso;
	}

	public Usuario(int id, String nome, String login, String senha, String nivelAcesso) {
		super(id, nome);
		this.login = login;
		this.senha = senha;
		this.nivelAcesso = nivelAcesso;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public String dataNascimentoToString() {
		return new SimpleDateFormat("dd/MM/yyyy").format(this.dataNascimento);
	}
	
//	@Override
//	public String toString() {
//		return getId()+" "+getNome()+" "+getSexo()+" "+DataNascimentoToString()+" "+getTelefone()+" "+getEmail()+" "+getRg()+" "+getLogin()+" "+getNivelAcesso();
//	}
}
