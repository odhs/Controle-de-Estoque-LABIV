package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Usuario;
import br.edu.univas.si.lab4.projeto.view.DataFrame;

public class ControllerCadUsuario {
	
	private DataFrame dataFrame;
	
	public ControllerCadUsuario(){
		showCadUsuarioFrame();
	}

	private void showCadUsuarioFrame() {
		dataFrame = new DataFrame(Modificadores.ADDUSUARIO);
		dataFrame.getButtonsPanel().addButtonListener(new ButtonListeners() {
			
			@Override
			public void okPerfomed() {
				Boolean senhasIguais = dataFrame.getCadUsuarioDataPanel().verificaSenhas();
				if(senhasIguais){
					String senha = new String(dataFrame.getCadUsuarioDataPanel().getSenhaField().getPassword());
					
					String senhaConf = new String(dataFrame.getCadUsuarioDataPanel().getSenhaConfField().getPassword());
					
					String nome = dataFrame.getCadUsuarioDataPanel().getNomeField().getText();
					
					String permissao = dataFrame.getCadUsuarioDataPanel().getPermissaoCombo().getSelectedItem().toString();
					
					String login = dataFrame.getCadUsuarioDataPanel().getLoginField().getText();
					
					if(senha.isEmpty() || senhaConf.isEmpty() || nome.isEmpty() || permissao.isEmpty() || login.isEmpty())
						JOptionPane.showMessageDialog(dataFrame, "Existem campos vazios");
					else{
						Boolean nomeExiste = new DAO().verificaNomeUser(nome);
						if(!nomeExiste){
							Boolean loginExiste = new DAO().verificaLoginUser(login);
							if(!loginExiste){
								
								Usuario usuario = dataFrame.getUsuarioData2();
								new DAO().addUsuario(usuario);
								dataFrame.dispose();
							}
							else
								JOptionPane.showMessageDialog(dataFrame, "Login ja existente no banco");
						}
						else
							JOptionPane.showMessageDialog(dataFrame, "Nome ja existente no banco");
					}
				}
				else
					JOptionPane.showMessageDialog(dataFrame, "As senhas não conferem");
				
			}
			
			@Override
			public void cancelPerfomed() {
				dataFrame.dispose();
				
			}
		});
		dataFrame.setVisible(true);
	}

}
