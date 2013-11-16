package br.edu.univas.si.lab4.projeto.listeners;


import br.edu.univas.si.lab4.projeto.model.Usuario;

public interface UsuariosListeners {
	
	public void showUpdateFrame(Usuario usuario);
	public void fecharTelaUsuarios();
	public void pesquisarUsuarios(String campoPesq, String pesqString);
	public void atualizaTable();
	public void deletaUsuario(Usuario usuario);

}
