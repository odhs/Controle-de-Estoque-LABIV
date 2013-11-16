package br.edu.univas.si.lab4.projeto.listeners;

import br.edu.univas.si.lab4.projeto.model.Fornecedor;

public interface FornecedoresListeners {
	
	public void showUpdateFrame(Fornecedor fornecedor);
	public void fecharTelaFornecedores();
	public void pesquisarFornecedores(String campoPesq, String pesqString);
	public void atualizaTable();
	public void deletaFornecedor(Fornecedor fornecedor);

}
