package br.com.cadastro.model;

public class Cliente {

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String id, String nome, String cpf, String peso, String altura) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.peso = peso;
		this.altura = altura;
	}

	private String id;
	private String nome;
	private String cpf;
	private String peso;
	private String altura;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", peso=" + peso + ", altura=" + altura + "]";
	}

}
