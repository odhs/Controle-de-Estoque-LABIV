package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class Runner {
	
	public static void main(String[] args) {
		new ControllerPass();
	}
	
	public void setLookAndFeel(MainFrame mainFrame){
		try {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");	
				SwingUtilities.updateComponentTreeUI(mainFrame);  
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
