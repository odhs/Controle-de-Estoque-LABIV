package br.edu.univas.si.lab4.projeto.controller;

import java.util.ArrayList;

import br.edu.univas.si.lab4.projeto.listeners.EntradasListener;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Entrada;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.view.ListFrame;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerEntradaFrame {
	
	private ListFrame listFrame;
	private MainFrame mainFrame;
	
	public ControllerEntradaFrame(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		showEntrsdaFrame();
	}

	private void showEntrsdaFrame() {
		listFrame = new ListFrame(Modificadores.FRAMEENTRADAS, mainFrame);
		mainFrame.desktop.add(listFrame);
		listFrame.getEntradaTablePanel().addEntradasListener(new EntradasListener() {
			
			@Override
			public void showUpdateFrame(Entrada entrada) {
				new ControllerAlteraEntrada(entrada, listFrame);
				
			}
			
			@Override
			public void pesquisarEntradas(String campoPesq, String pesqString) {
				ArrayList<Entrada> entradas = new DAO().pesquisaEntradas(campoPesq, pesqString);
				listFrame.getEntradaTablePanel().mostraResultadoPesq(entradas);
			}
			
			@Override
			public void fecharTelaEntradas() {
				listFrame.dispose();
				
			}
			
			@Override
			public void deletaEntrada(Entrada entrada) {
				new DAO().deletaEntrada(entrada);
				listFrame.getEntradaTablePanel().atualizaTabela();
			}
			
			@Override
			public void atualizaTable() {
				listFrame.getEntradaTablePanel().atualizaTabela();
				
			}
		});
		listFrame.setVisible(true);
		
	}

}
