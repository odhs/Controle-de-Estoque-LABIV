package br.edu.univas.si.lab4.projeto.controller;

import java.util.ArrayList;

import br.edu.univas.si.lab4.projeto.listeners.FornecedoresListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Fornecedor;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.view.ListFrame;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerFornecedorFrame {

	private MainFrame mainFrame;
	private ListFrame listFrame;
	
	public ControllerFornecedorFrame(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		showFornecedorFrame();
	}

	private void showFornecedorFrame() {
		listFrame = new ListFrame(Modificadores.FRAMEFORNECEDORES, mainFrame);
		mainFrame.desktop.add(listFrame);
		listFrame.getFornecedorTablePanel().addFornecedorListener(new FornecedoresListeners() {
			
			@Override
			public void showUpdateFrame(Fornecedor fornecedor) {
				new ControllerAlteraFornecedor(fornecedor, listFrame);
			}
			
			@Override
			public void pesquisarFornecedores(String campoPesq, String pesqString) {
				ArrayList<Fornecedor> fornecedores = new DAO().pesquisaFornecedores(campoPesq, pesqString);
				listFrame.getFornecedorTablePanel().mostraResultadoPesq(fornecedores);
			}
			
			@Override
			public void fecharTelaFornecedores() {
				listFrame.dispose();
				
			}
			
			@Override
			public void deletaFornecedor(Fornecedor fornecedor) {
				new DAO().deletaFornecedor(fornecedor);
				listFrame.getFornecedorTablePanel().atualizaTabela();
			}
			
			@Override
			public void atualizaTable() {
				listFrame.getFornecedorTablePanel().atualizaTabela();
			}
		});
		listFrame.setVisible(true);
	}
	
}
