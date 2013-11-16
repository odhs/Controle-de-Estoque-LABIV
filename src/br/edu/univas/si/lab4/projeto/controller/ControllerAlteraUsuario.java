package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Usuario;
import br.edu.univas.si.lab4.projeto.view.DataFrame;
import br.edu.univas.si.lab4.projeto.view.ListFrame;

public class ControllerAlteraUsuario {
	
	private Usuario usuario;
	private DataFrame dataFrame;
	private ListFrame listFrame;

	public ControllerAlteraUsuario(Usuario usuario, ListFrame listFrame) {
		this.usuario = usuario;
		this.listFrame = listFrame;
		showDataFrame();
	}

	private void showDataFrame() {
		dataFrame = new DataFrame(usuario, Modificadores.FRAMEUSUARIO);
		dataFrame.getButtonsPanel().addButtonListener(new ButtonListeners() {
			
			@Override
			public void okPerfomed() {
				Boolean senhasIguais = dataFrame.getUsuarioDataPanel().verificaSenhas();
				if(senhasIguais){
					String senha = new String(dataFrame.getUsuarioDataPanel().getSenhaField().getPassword());
					
					String senhaConf = new String(dataFrame.getUsuarioDataPanel().getSenhaConfField().getPassword());
					
					String nome = dataFrame.getUsuarioDataPanel().getNomeField().getText();
					
					String permissao = dataFrame.getUsuarioDataPanel().getPermissaoCombo().getSelectedItem().toString();
					
					if(senha.isEmpty() || senhaConf.isEmpty() || nome.isEmpty() || permissao.isEmpty())
						JOptionPane.showMessageDialog(dataFrame, "Existem campos vazios");
					else{
						Boolean nomeExiste = new DAO().verificaNomeUser(nome);
						if(!nomeExiste || nome.equals(usuario.getNome())){
							usuario = dataFrame.getUsuarioData(usuario);
							new DAO().atualizaUsuario(usuario);
							listFrame.getUsuarioTablePanel().atualizaTabela();
							dataFrame.dispose();
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
