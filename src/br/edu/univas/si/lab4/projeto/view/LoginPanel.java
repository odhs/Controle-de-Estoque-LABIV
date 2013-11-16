package br.edu.univas.si.lab4.projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.TextDocument;

public class LoginPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel userLabel;
	private JLabel passwordLabel;
	
	private JTextField loginField;
	private JPasswordField passwordField;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private Border defaultBorder;
	
	private Image img; 
	
	private ArrayList<ButtonListeners> listeners = new ArrayList<ButtonListeners>();
	
	public LoginPanel(){
		setLayout(null);
		initialize();
		
	}
	
	private void initialize(){
		add(getUserLabel());
		add(getLoginField());
		
		add(getPasswordLabel());
		add(getPasswordField());
		
		add(getOkButton());
		add(getCancelButton());
	}
	
	public JLabel getUserLabel(){
		if(userLabel == null){
			userLabel = new JLabel();
			userLabel.setText("User");
			userLabel.setBounds(new Rectangle(120, 198, 168, 25));
			userLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		}
		return userLabel;
	}

	public JTextField getLoginField() {
		if(loginField == null){
			loginField = new JTextField();
			int strSize = 50;
			loginField.setDocument(new TextDocument(strSize));
			defaultBorder = loginField.getBorder();
			loginField.setToolTipText("Informe seu login!");
			loginField.setText("");
			loginField.setBounds(new Rectangle(220, 198, 168, 25));
			loginField.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent event) {
					if(loginField.getText().isEmpty())
						loginField.setBorder(getRedlineBorder());
					else
						loginField.setBorder(defaultBorder);
				}
				
				@Override
				public void focusGained(FocusEvent event) {
					loginField.setBorder(getGreenLineBorder());
					
				}
			});
		}
		return loginField;
	}
	
	public JLabel getPasswordLabel(){
		if(passwordLabel == null){
			passwordLabel = new JLabel();
			passwordLabel.setText("Password");
			passwordLabel.setBounds(new Rectangle(120, 230, 168, 25));
			passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		}
		return passwordLabel;
	}

	public JPasswordField getPasswordField() {
		if(passwordField == null){
			passwordField = new JPasswordField();
			int strSize = 50;
			passwordField.setDocument(new TextDocument(strSize));
			passwordField.setToolTipText("Informe sua senha");
			passwordField.setText("");
			passwordField.setBounds(new Rectangle(220, 230, 168, 25));
			passwordField.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent event) {
					String password = new String(passwordField.getPassword());
					if(password.isEmpty())
						passwordField.setBorder(getRedlineBorder());
					else
						passwordField.setBorder(defaultBorder);
				}
				
				@Override
				public void focusGained(FocusEvent event) {
					passwordField.setBorder(getGreenLineBorder());
					
				}
			});
		}
		return passwordField;
	}
	
	private JButton getOkButton() {
		if(okButton == null){
			ImageIcon icon = createImagemIcon("/Key.png");
			okButton = new JButton();
			okButton.setIcon(icon);
			okButton.setFocusPainted(false);
			okButton.setContentAreaFilled(false);
			okButton.setBounds(new Rectangle(190, 270, 50, 25));
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					if(listeners != null){
						for (ButtonListeners listener : listeners) {
							listener.okPerfomed();
						}
					}
					
				}
			});
		}
		return okButton;
	}
	
	private JButton getCancelButton() {
		if(cancelButton == null){
			ImageIcon icon = createImagemIcon("/X.png");
			cancelButton = new JButton();
			cancelButton.setIcon(icon);
			cancelButton.setFocusPainted(false);
			cancelButton.setContentAreaFilled(false);
			cancelButton.setBounds(new Rectangle(260, 270, 50, 25));
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					if(listeners != null){
						for (ButtonListeners listener : listeners) {
							listener.cancelPerfomed();
						}
					}
					
				}
			});
		}
		return cancelButton;
	}
	
	private Border getGreenLineBorder(){
		Border greenLine = BorderFactory.createLineBorder(Color.GREEN);
		
		return greenLine;
	}
	
	public Border getRedlineBorder(){
		Border redLine = BorderFactory.createLineBorder(Color.RED);
		
		return redLine;
	}
	
	private ImageIcon createImagemIcon(String path) {
		URL url = getClass().getResource(path);
		
		if(url != null){
			return new ImageIcon(url);
		}
		else{
			JOptionPane.showMessageDialog(null, "Não foi possivel encontrar path de imagens!");
			return null;
		}
	}
	
	public void addButtonListener(ButtonListeners listener){
		listeners.add(listener);
	}
	
	public void paintComponent ( Graphics g ) {  
		img = new ImageIcon ( getClass ( ).getResource ( "/Lock.png" ) ).getImage ( ); 
		super.paintComponent ( g );  
		int x = ( this.getWidth ( ) - img.getWidth ( null ) ) / 2;  
		int y = ( this.getHeight ( ) - img.getHeight ( null ) ) / 2;  
		g.drawImage ( img , x , y , this );  
	} 

}
