package model;

import java.text.SimpleDateFormat;

public class Cliente extends Pessoa{
	
	private String endereco;
	private int cep;
	
	public Cliente(int id, String nome, String sexo, String dataNascimento, String telefone, String email, String rg,
			String endereco, int cep) {
		super(id, nome, sexo, dataNascimento, telefone, email, rg);
		this.endereco = endereco;
		this.cep = cep;
	}

	public Cliente(int id, String nome, String endereco, int cep) {
		super(id, nome);
		this.endereco = endereco;
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
	public String dataNascimentoToString() {
		return new SimpleDateFormat("dd/MM/yyyy").format(this.dataNascimento);
	}
}
