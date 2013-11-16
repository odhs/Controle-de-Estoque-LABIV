package br.edu.univas.si.lab4.projeto.controller;

import java.util.ArrayList;

import br.edu.univas.si.lab4.projeto.listeners.UsuariosListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Usuario;
import br.edu.univas.si.lab4.projeto.view.ListFrame;
import br.edu.univas.si.lab4.projeto.view.MainFrame;

public class ControllerUsuarioFrame {
	
	private MainFrame mainFrame;
	private ListFrame listFrame;

	public ControllerUsuarioFrame(MainFrame frame) {
		this.mainFrame = frame;
		showUsuarioFrame();
	}

	private void showUsuarioFrame() {
		listFrame = new ListFrame(Modificadores.FRAMEUSUARIO, mainFrame);
		mainFrame.desktop.add(listFrame);
		listFrame.getUsuarioTablePanel().addUsuariosListeners(new UsuariosListeners() {
			
			@Override
			public void showUpdateFrame(Usuario usuario) {
				new ControllerAlteraUsuario(usuario, listFrame);
				
			}
			
			@Override
			public void pesquisarUsuarios(String campoPesq, String pesqString) {
				ArrayList<Usuario> usuarios = new DAO().pesquisaUsuarios(campoPesq, pesqString);
				listFrame.getUsuarioTablePanel().mostraResultadoPesq(usuarios);
				
			}
			
			@Override
			public void fecharTelaUsuarios() {
				listFrame.dispose();
				
			}
			
			@Override
			public void deletaUsuario(Usuario usuario) {
				new DAO().deletaUsuario(usuario);
				listFrame.getUsuarioTablePanel().atualizaTabela();
				
			}
			
			@Override
			public void atualizaTable() {
				listFrame.getUsuarioTablePanel().atualizaTabela();
				
			}
		});
		
		listFrame.setVisible(true);
		
	}

}
