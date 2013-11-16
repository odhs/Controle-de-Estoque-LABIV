package br.edu.univas.si.lab4.projeto.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.univas.si.lab4.projeto.model.TextDocument;

public class CadUsuarioDataPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel nomeLabel;
	private JLabel permissaoLabel;
	private JLabel senhaLabel;
	private JLabel senhaConfLabel;
	private JLabel loginLabel;
	
	private JComboBox<?> permissaoCombo;
	
	private JTextField nomeField;
	private JTextField loginField;
	
	private JPasswordField senhaField;
	private JPasswordField senhaConfField;
	
	private GridBagConstraints nomeLabelConstraints;
	private GridBagConstraints nomeFieldConstraints;
	private GridBagConstraints permissaoLabelConstraints;
	private GridBagConstraints permissaoComboConstraints;
	private GridBagConstraints senhaConfLabelConstraints;
	private GridBagConstraints senhaConfFieldConstraints;
	private GridBagConstraints senhaLabelConstraints;
	private GridBagConstraints senhaFieldConstraints;
	private GridBagConstraints loginLabelConstraints;
	private GridBagConstraints loginFieldConstraints;
	
	public CadUsuarioDataPanel() {
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		add(getNomeLabel(), getNomeLabelConstraints());
		add(getNomeField(), getNomeFieldConstraints());
		
		add(getLoginLabel(), getLoginLabelConstraints());
		add(getLoginField(), getLoginFieldCOnstraints());
		
		add(getPermissaoLabel(), getPermissaoLabelConstraints());
		add(getPermissaoCombo(), getPermissaoComboConstraints());
		
		add(getSenhaLabel(), getSenhaLabelConstraints());
		add(getSenhaField(), getSenhaFieldConstraints());
		
		add(getSenhaConfLabel(), getSenhaConfLabelConstraints());
		add(getSenhaConfField(), getSenhaConfFieldConstraints());
		
		
		
	}
	
	public JTextField getLoginField(){
		if(loginField == null){
			loginField = new JTextField();
			int strSize = 50;
			loginField.setDocument(new TextDocument(strSize));
			loginField.setToolTipText("Informe o login do usuário");
			loginField.setColumns(10);
		}
		return loginField;
	}
	
	private GridBagConstraints getLoginFieldCOnstraints(){
		if(loginFieldConstraints == null){
			loginFieldConstraints = createConstraintsPrototype();
			
			loginFieldConstraints.gridx = 1;
			loginFieldConstraints.gridy = 1;
			
			loginFieldConstraints.gridwidth = 2;
		}
		return loginFieldConstraints;
	}
	
	public JLabel getLoginLabel(){
		if(loginLabel == null){
			loginLabel = new JLabel();
			loginLabel.setText("Login");
		
		}
		return loginLabel;
	}
	
	private GridBagConstraints getLoginLabelConstraints(){
		if(loginLabelConstraints == null){
			loginLabelConstraints = createConstraintsPrototype();
			
			loginLabelConstraints.gridx = 0;
			loginLabelConstraints.gridy = 1;
			
		}
		return loginLabelConstraints;
	}
	
	public JPasswordField getSenhaConfField(){
		if(senhaConfField == null){
			senhaConfField = new JPasswordField();
			int strSize = 50;
			senhaConfField.setDocument(new TextDocument(strSize));
			senhaConfField.setToolTipText("Confirme a senha");
			senhaConfField.setColumns(10);
		}
		return senhaConfField;
	}
	
	private GridBagConstraints getSenhaConfFieldConstraints(){
		if(senhaConfFieldConstraints == null){
			senhaConfFieldConstraints = createConstraintsPrototype();
			
			senhaConfFieldConstraints.gridx = 4;
			senhaConfFieldConstraints.gridy = 2;
			
			senhaConfFieldConstraints.gridwidth = 2;
		}
		return senhaConfFieldConstraints;
	}
	
	private JLabel getSenhaConfLabel(){
		if(senhaConfLabel == null){
			senhaConfLabel = new JLabel();
			senhaConfLabel.setText("Senha Conf");
		}
		return senhaConfLabel;
	}
	
	private GridBagConstraints getSenhaConfLabelConstraints(){
		if(senhaConfLabelConstraints == null){
			senhaConfLabelConstraints = createConstraintsPrototype();
			
			senhaConfLabelConstraints.gridx = 3;
			senhaConfLabelConstraints.gridy = 2;
		}
		return senhaConfLabelConstraints;
	}
	
	public JPasswordField getSenhaField(){
		if(senhaField == null){
			senhaField = new JPasswordField();
			int strSize = 50;
			senhaField.setDocument(new TextDocument(strSize));
			senhaField.setToolTipText("Informe a nova senha");
			senhaField.setColumns(10);
		}
		return senhaField;
	}
	
	private GridBagConstraints getSenhaFieldConstraints(){
		if(senhaFieldConstraints == null){
			senhaFieldConstraints = createConstraintsPrototype();
			
			senhaFieldConstraints.gridx = 1;
			senhaFieldConstraints.gridy = 2;
			
			senhaFieldConstraints.gridwidth = 2;
		}
		return senhaFieldConstraints;
	}
	
	private JLabel getSenhaLabel(){
		if(senhaLabel == null){
			senhaLabel = new JLabel();
			senhaLabel.setText("Senha");
		}
		return senhaLabel;
	}
	
	private GridBagConstraints getSenhaLabelConstraints(){
		if(senhaLabelConstraints == null){
			senhaLabelConstraints = createConstraintsPrototype();
			
			senhaLabelConstraints.gridx = 0;
			senhaLabelConstraints.gridy = 2;
		}
		return senhaLabelConstraints;
	}
	
	public JComboBox<?> getPermissaoCombo(){
		if(permissaoCombo == null){
			permissaoCombo = new JComboBox<Object>(getList());
			permissaoCombo.setToolTipText("Selecione a permissão");
		}
		return permissaoCombo;
	}
	
	private GridBagConstraints getPermissaoComboConstraints(){
		if(permissaoComboConstraints == null){
			permissaoComboConstraints = createConstraintsPrototype();
			
			permissaoComboConstraints.gridx = 4;
			permissaoComboConstraints.gridy = 1;
			
			permissaoComboConstraints.gridwidth = 2;
		}
		return permissaoComboConstraints;
	}
	
	private String[] getList(){
		String[] list = new String[]{
				"",
				"Administrador",
				"Comum"
		};
		return list;
	}
	
	private JLabel getPermissaoLabel(){
		if(permissaoLabel == null){
			permissaoLabel = new JLabel();
			permissaoLabel.setText("Permissão");
		}
		return permissaoLabel;
	}
	
	private GridBagConstraints getPermissaoLabelConstraints(){
		if(permissaoLabelConstraints == null){
			permissaoLabelConstraints = createConstraintsPrototype();
			
			permissaoLabelConstraints.gridx = 3;
			permissaoLabelConstraints.gridy = 1;
		}
		return permissaoLabelConstraints;
	}
	
	public JTextField getNomeField(){
		if(nomeField == null){
			nomeField = new JTextField();
			int strSize = 50;
			nomeField.setDocument(new TextDocument(strSize));
			nomeField.setToolTipText("Informe nome do usuário");
			nomeField.setColumns(15);
		}
		return nomeField;
	}
	
	private GridBagConstraints getNomeFieldConstraints(){
		if(nomeFieldConstraints == null){
			nomeFieldConstraints = createConstraintsPrototype();
			
			nomeFieldConstraints.gridx = 1;
			nomeFieldConstraints.gridy = 0;
			
			nomeFieldConstraints.gridwidth = 5;
		}
		return nomeFieldConstraints;
	}
	
	private JLabel getNomeLabel(){
		if(nomeLabel == null){
			nomeLabel = new JLabel();
			nomeLabel.setText("Nome");
		}
		return nomeLabel;
	}
	
	private GridBagConstraints getNomeLabelConstraints(){
		if(nomeLabelConstraints == null){
			nomeLabelConstraints = createConstraintsPrototype();
			
			nomeLabelConstraints.gridx = 0;
			nomeLabelConstraints.gridy = 0;
		}
		return nomeLabelConstraints;
	}
	
	
	private GridBagConstraints createConstraintsPrototype() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		int gap = 4;
		
		gbc.insets = new Insets(gap, gap, gap, gap);
		return gbc;
	}

	public Boolean verificaSenhas() {
		if(new String(getSenhaField().getPassword()).equals(new String(getSenhaConfField().getPassword())))
			return true;
		else
			return false;
	}

}
