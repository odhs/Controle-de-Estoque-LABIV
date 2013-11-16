package br.edu.univas.si.lab4.projeto.controller;

import java.util.ArrayList;

import br.edu.univas.si.lab4.projeto.listeners.SaidasListener;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Saida;
import br.edu.univas.si.lab4.projeto.view.ListFrame;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerSaidaFrame {
	
	private MainFrame mainFrame;
	private ListFrame listFrame;

	public ControllerSaidaFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		showSaidaFrame();
	}

	private void showSaidaFrame() {
		listFrame = new ListFrame(Modificadores.FRAMESAIDAS, mainFrame);
		mainFrame.desktop.add(listFrame);
		listFrame.getSaidaTablePanel().addSaidasListener(new SaidasListener() {
			
			@Override
			public void showUpdateFrame(Saida saida) {
				new ControllerAlteraSaida(saida, listFrame);
				
			}
			
			@Override
			public void pesquisarSaidas(String campoPesq, String pesqString) {
				ArrayList<Saida> saidas = new DAO().pesquisaSaidas(campoPesq, pesqString);
				listFrame.getSaidaTablePanel().mostraResultadoPesq(saidas);
				
			}
			
			@Override
			public void fecharTelaSaidas() {
				listFrame.dispose();
				
			}
			
			@Override
			public void deletaSaida(Saida saida) {
				new DAO().deletaSaida(saida);
				listFrame.getSaidaTablePanel().atualizaTabela();
				
			}
			
			@Override
			public void atualizaTable() {
				listFrame.getSaidaTablePanel().atualizaTabela();
				
			}
		});
		listFrame.setVisible(true);
		
	}

}
