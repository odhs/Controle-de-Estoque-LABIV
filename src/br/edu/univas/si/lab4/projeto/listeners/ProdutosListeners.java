package br.edu.univas.si.lab4.projeto.listeners;

import br.edu.univas.si.lab4.projeto.model.Produto;

public interface ProdutosListeners {

	public void showUpdateFrame(Produto produto);
	public void fecharTelaprodutos();
	public void pesquisarProdutos(String campoPesq, String pesqString);
	public void atualizaTable();
	public void deletaProduto(Produto produto);
	
}
