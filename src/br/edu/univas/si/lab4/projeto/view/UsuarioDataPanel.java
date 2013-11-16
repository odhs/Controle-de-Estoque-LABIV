package br.edu.univas.si.lab4.projeto.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.TextDocument;
import br.edu.univas.si.lab4.projeto.model.Usuario;

public class UsuarioDataPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel nomeLabel;
	private JLabel permissaoLabel;
	private JLabel senhaLabel;
	private JLabel senhaConfLabel;
	
	private JComboBox<?> permissaoCombo;
	
	private JTextField nomeField;
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
	
	private Usuario usuario;
	private Integer modificador;
	
	public UsuarioDataPanel(Usuario usuario, Integer modificador) {
		this.modificador = modificador;
		this.usuario = usuario;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		add(getNomeLabel(), getNomeLabelConstraints());
		add(getNomeField(), getNomeFieldConstraints());
		
		add(getPermissaoLabel(), getPermissaoLabelConstraints());
		add(getPermissaoCombo(), getPermissaoComboConstraints());
		
		add(getSenhaLabel(), getSenhaLabelConstraints());
		add(getSenhaField(), getSenhaFieldConstraints());
		
		add(getSenhaConfLabel(), getSenhaConfLabelConstraints());
		add(getSenhaConfField(), getSenhaConfFieldConstraints());
		
	}
	
	public JPasswordField getSenhaConfField(){
		if(senhaConfField == null){
			senhaConfField = new JPasswordField();
			senhaConfField.setToolTipText("Confirme a senha");
			senhaConfField.setColumns(10);
			int strSize = 50;
			senhaConfField.setDocument(new TextDocument(strSize));
			if(modificador == Modificadores.FRAMEUSUARIO)
				senhaConfField.setText(usuario.getPassword());
		}
		return senhaConfField;
	}
	
	private GridBagConstraints getSenhaConfFieldConstraints(){
		if(senhaConfFieldConstraints == null){
			senhaConfFieldConstraints = createConstraintsPrototype();
			
			senhaConfFieldConstraints.gridx = 4;
			senhaConfFieldConstraints.gridy = 1;
			
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
			senhaConfLabelConstraints.gridy = 1;
		}
		return senhaConfLabelConstraints;
	}
	
	public JPasswordField getSenhaField(){
		if(senhaField == null){
			senhaField = new JPasswordField();
			senhaField.setToolTipText("Informe a nova senha");
			senhaField.setColumns(10);
			int strSize = 50;
			senhaField.setDocument(new TextDocument(strSize));
			if(modificador == Modificadores.FRAMEUSUARIO)
				senhaField.setText(usuario.getPassword());
		}
		return senhaField;
	}
	
	private GridBagConstraints getSenhaFieldConstraints(){
		if(senhaFieldConstraints == null){
			senhaFieldConstraints = createConstraintsPrototype();
			
			senhaFieldConstraints.gridx = 1;
			senhaFieldConstraints.gridy = 1;
			
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
			senhaLabelConstraints.gridy = 1;
		}
		return senhaLabelConstraints;
	}
	
	public JComboBox<?> getPermissaoCombo(){
		if(permissaoCombo == null){
			permissaoCombo = new JComboBox<Object>(getList());
			permissaoCombo.setToolTipText("Selecione a permissão");
			if(modificador == Modificadores.FRAMEUSUARIO)
				permissaoCombo.setSelectedItem(usuario.getPermissao());
		}
		return permissaoCombo;
	}
	
	private GridBagConstraints getPermissaoComboConstraints(){
		if(permissaoComboConstraints == null){
			permissaoComboConstraints = createConstraintsPrototype();
			
			permissaoComboConstraints.gridx = 4;
			permissaoComboConstraints.gridy = 0;
			
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
			permissaoLabelConstraints.gridy = 0;
		}
		return permissaoLabelConstraints;
	}
	
	public JTextField getNomeField(){
		if(nomeField == null){
			nomeField = new JTextField();
			nomeField.setToolTipText("Informe nome do usuário");
			nomeField.setColumns(10);
			int strSize = 50;
			nomeField.setDocument(new TextDocument(strSize));
			if(modificador == Modificadores.FRAMEUSUARIO)
				nomeField.setText(usuario.getNome());
		}
		return nomeField;
	}
	
	private GridBagConstraints getNomeFieldConstraints(){
		if(nomeFieldConstraints == null){
			nomeFieldConstraints = createConstraintsPrototype();
			
			nomeFieldConstraints.gridx = 1;
			nomeFieldConstraints.gridy = 0;
			
			nomeFieldConstraints.gridwidth = 2;
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
