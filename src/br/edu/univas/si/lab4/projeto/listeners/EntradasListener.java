package br.edu.univas.si.lab4.projeto.listeners;

import br.edu.univas.si.lab4.projeto.model.Entrada;

public interface EntradasListener {
	
	public void showUpdateFrame(Entrada entrada);
	public void fecharTelaEntradas();
	public void pesquisarEntradas(String campoPesq, String pesqString);
	public void atualizaTable();
	public void deletaEntrada(Entrada entrada);

}
