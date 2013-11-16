package br.edu.univas.si.lab4.projeto.controller;

import java.util.ArrayList;

import br.edu.univas.si.lab4.projeto.listeners.ProdutosListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Produto;
import br.edu.univas.si.lab4.projeto.view.ListFrame;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerProdutoFrame {
	
	private MainFrame mainFrame;
	private ListFrame listFrame;
	
	public ControllerProdutoFrame(MainFrame frame){
		this.mainFrame = frame;
		showProdutoFrame();
	}

	private void showProdutoFrame() {
		listFrame = new ListFrame(Modificadores.FRAMEPRODUTOS, mainFrame);
		mainFrame.desktop.add(listFrame);
		listFrame.getProdutoTablePanel().addProdutosListeners(new ProdutosListeners() {
			
			@Override
			public void showUpdateFrame(Produto produto) {
				new ControllerAlteraProd(produto,listFrame);
			}

			@Override
			public void fecharTelaprodutos() {
				listFrame.dispose();
				
			}

			@Override
			public void pesquisarProdutos(String campoPesq, String pesqString) {
				ArrayList<Produto> produtos = new DAO().pesquisaProduto(campoPesq, pesqString);
				listFrame.getProdutoTablePanel().mostraResultadoPesq(produtos);
	
				
			}

			@Override
			public void atualizaTable() {
				listFrame.getProdutoTablePanel().atualizaTabela();
				
			}

			@Override
			public void deletaProduto(Produto produto) {
				new DAO().deletaProduto(produto);
				listFrame.getProdutoTablePanel().atualizaTabela();
			}
		});
		
		listFrame.setVisible(true);
		
	}

}
