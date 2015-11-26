package br.com.cadastro.model;

public class TipoBusca {

	public TipoBusca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoBusca(String codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	private String codigo;
	private String descricao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "TipoBusca [codigo=" + codigo + ", descricao=" + descricao + "]";
	}

}
