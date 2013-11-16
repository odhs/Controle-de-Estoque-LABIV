package br.edu.univas.si.lab4.projeto.model;

public class Entrada {
	
	private String fornecedor;
	private String quantidade;
	private String data;
	private String codigo;
	private String codigoFornecedor;
	private String codigoProduto;
	private String produto;
	private String precoEntrada;
	
	
	public String getPrecoEntrada() {
		return precoEntrada;
	}

	public void setPrecoEntrada(String string) {
		this.precoEntrada = string;
	}

	public String getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(String forneedor) {
		this.fornecedor = forneedor;
	}
	
	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String string) {
		this.quantidade = string;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getProduto() {
		return produto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoFornecedor(String codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}

	public String getCodigoFornecedor() {
		return codigoFornecedor;
	}
	
}
