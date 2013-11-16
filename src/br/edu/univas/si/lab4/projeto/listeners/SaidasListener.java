package br.edu.univas.si.lab4.projeto.listeners;

import br.edu.univas.si.lab4.projeto.model.Saida;

public interface SaidasListener {
	
	public void showUpdateFrame(Saida saida);
	public void fecharTelaSaidas();
	public void pesquisarSaidas(String campoPesq, String pesqString);
	public void atualizaTable();
	public void deletaSaida(Saida saida);

}
