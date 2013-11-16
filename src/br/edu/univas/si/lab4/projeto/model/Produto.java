package br.edu.univas.si.lab4.projeto.model;

public class Produto {

	private String codigo;
	private String nome;
	private String fornecedor;
	private String valor_compra;
	private String valor_venda;
	private String quantidade;
	private String tipo;
	private String descricao;
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getValor_compra() {
		return valor_compra;
	}
	
	public void setValor_compra(String valor_compra) {
		this.valor_compra = valor_compra;
	}
	
	public String getValor_venda() {
		return valor_venda;
	}
	
	public void setValor_venda(String valor_venda) {
		this.valor_venda = valor_venda;
	}
	
	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
