package br.edu.univas.si.lab4.projeto.controller;

import br.edu.univas.si.lab4.projeto.listeners.GraficosListeners;
import br.edu.univas.si.lab4.projeto.view.GraficoFrame;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerGraficoFrame {
	
	private MainFrame mainFrame;
	private GraficoFrame graficoFrame;

	public ControllerGraficoFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		showGraficoFrame();
	}

	private void showGraficoFrame() {
		graficoFrame = new GraficoFrame();
		mainFrame.desktop.add(graficoFrame);
		graficoFrame.getGraficoTiposPanel().addGraficosListeners(new GraficosListeners() {
			
			@Override
			public void fecharTelaGrafico() {
				graficoFrame.dispose();
			}
		});
		graficoFrame.setVisible(true);
		
	}

}
