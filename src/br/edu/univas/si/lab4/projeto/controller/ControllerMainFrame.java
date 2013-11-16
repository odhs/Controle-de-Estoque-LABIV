package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JInternalFrame;

import br.edu.univas.si.lab4.projeto.listeners.CadastroListener;
import br.edu.univas.si.lab4.projeto.listeners.MenuButtonsListener;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerMainFrame {
	
	private MainFrame mainFrame;
	
	public ControllerMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		showMainFrame();
	}

	public void showMainFrame(){
		new Runner().setLookAndFeel(mainFrame);
		mainFrame.initialize();
		mainFrame.validate();
		mainFrame.addCadastrListeners(new CadastroListener() {
			
			@Override
			public void cadastraSaida() {
				new ControllerCadSaida();
			}
			
			@Override
			public void cadastraEntrada() {
				new ControllerCadEntrada();
			}

			@Override
			public void cadastraProduto() {
				new ControllerCadProduto();
				
			}

			@Override
			public void cadastraFornecedor() {
				new ControllerCadFornecedor();
				
			}

			@Override
			public void cadastraUsuario() {
				new ControllerCadUsuario();
				
			}
		});
		mainFrame.buttons.addMenuButtonsListener(new MenuButtonsListener() {
			
			@Override
			public void showProdutos() {
				fechaInternalFrames();
				new ControllerProdutoFrame(mainFrame);
			}
			
			@Override
			public void showGraficos() {
				fechaInternalFrames();
				new ControllerGraficoFrame(mainFrame);
			}
			
			@Override
			public void showFornecedores() {
				fechaInternalFrames();
				new ControllerFornecedorFrame(mainFrame);
			}

			@Override
			public void showEntradas() {
				fechaInternalFrames();
				new ControllerEntradaFrame(mainFrame);
				
			}

			@Override
			public void showSaidas() {
				fechaInternalFrames();
				new ControllerSaidaFrame(mainFrame);
				
			}

			@Override
			public void showUsuarios() {
				fechaInternalFrames();
				new ControllerUsuarioFrame(mainFrame);
				
			}
		});
	}
	
	public void fechaInternalFrames(){    
	     JInternalFrame[] frames = mainFrame.desktop.getAllFrames();     
	     for (int i = 0; i < frames.length; i++){     
	          if( frames[i] != null && !frames[i].isClosed() ) {  
	                frames[i].dispose(); 
	          }  
	     }    
	} 

}
