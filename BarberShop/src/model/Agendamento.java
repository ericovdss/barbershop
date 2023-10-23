package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Agendamento {

	private int id;
	private Cliente cliente;
	private Servico servico;
	private float valor;
	private Date data;
	private String observacao;
	
	//Construtor completo
	public Agendamento(int id, Cliente cliente, Servico servico, float valor, String data, String observacao) {
		this(id,cliente,servico,valor,data);
		this.observacao = observacao;
	}
	
	//Construtor sem o atributo 'observacao'
	public Agendamento(int id, Cliente cliente, Servico servico, float valor, String data) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.servico = servico;
		this.valor = valor;
		try {
			this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}
	
	public String getDataFormatada() {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	public String getHoraFormatada() {
		return new SimpleDateFormat("HH:mm").format(data);
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
