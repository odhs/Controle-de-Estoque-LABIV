package br.edu.univas.si.lab4.projeto.model;

public class Usuario {
	
	private String userLogin;
	private String password;
	private String nome;
	private String Codigo;
	private String Permissao;
	
	public String getCodigo() {
		return Codigo;
	}
	
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPermissao() {
		return Permissao;
	}

	public void setPermissao(String permissao) {
		Permissao = permissao;
	}

	public String getUserLogin() {
		return userLogin;
	}
	
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
