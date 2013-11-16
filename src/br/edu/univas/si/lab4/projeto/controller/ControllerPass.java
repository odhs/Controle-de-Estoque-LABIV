package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Usuario;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.view.LoginFrame;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerPass {
	
	private LoginFrame login;
	private MainFrame mainFrame;

	public ControllerPass() {
		showLoginFrame();
	}

	private void showLoginFrame() {
		mainFrame = new MainFrame(Modificadores.NOTLOG);
		login = mainFrame.getLoginFrame();
		
		login.getLoginPanel().addButtonListener(new ButtonListeners() {
			
			@Override
			public void okPerfomed() {
				Usuario loginData = login.getLoginData();
	
				if(loginData.getUserLogin().isEmpty() || loginData.getPassword().isEmpty())
					JOptionPane.showMessageDialog(login, "Existe campo vazio!!!");
				else
					verificaUser(loginData);
			}
			
			@Override
			public void cancelPerfomed() {
				login.dispose();
				mainFrame.dispose();
			}
		});
		mainFrame.setVisible(true);
		login.setVisible(true);
		
	}
	
	private void verificaUser(Usuario loginData) {
		Boolean userOk = new DAO().testaUsuario(loginData);
		
		if(userOk){
			Boolean isAdmin = new DAO().verificaPermissao(loginData);
			if(isAdmin){
				mainFrame.modificador = Modificadores.LOGADMIN;
				new ControllerMainFrame(mainFrame);
				login.dispose();
			}
			else{
				mainFrame.modificador = Modificadores.LOGNORMAL;
				new ControllerMainFrame(mainFrame);
				login.dispose();
			}
		}
		else
			JOptionPane.showMessageDialog(login, "Usuário ou senha inválidos!!");
		
	}

}
