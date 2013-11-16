package br.edu.univas.si.lab4.projeto.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import br.edu.univas.si.lab4.projeto.model.Usuario;

public class LoginFrame extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private LoginPanel loginPanel;
	private MainFrame mainFrame;
	
	public LoginFrame(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		setTitle("Login Window");
		initialize();
		setSize(518, 496);
		this.setUndecorated(true);
		setModal(true);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
		
			@Override
			public void windowClosing(WindowEvent arg0) {
				LoginFrame.this.mainFrame.dispose();
			
			}	
		});
	}

	private void initialize() {
		add(getLoginPanel(), BorderLayout.CENTER);
		
	}

	public LoginPanel getLoginPanel() {
		if(loginPanel == null){
			loginPanel = new LoginPanel();
		}
		return loginPanel;
	}

	public Usuario getLoginData(){
		Usuario usuario = new Usuario();
		
		String userLogin = getLoginPanel().getLoginField().getText();
		if(userLogin.isEmpty())
			getLoginPanel().getLoginField().setBorder(getLoginPanel().getRedlineBorder());
		usuario.setUserLogin(userLogin);
		
		String password = new String(getLoginPanel().getPasswordField().getPassword());
		if(password.isEmpty())
			getLoginPanel().getPasswordField().setBorder(getLoginPanel().getRedlineBorder());
		usuario.setPassword(password);
		
		return usuario;
	}

}
