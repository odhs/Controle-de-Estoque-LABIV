package br.edu.univas.si.lab4.projeto.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.univas.si.lab4.projeto.model.Fornecedor;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.TextDocument;

public class FornecDataPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public Fornecedor fornecedor;
	
	private JLabel nomeLabel;
	private JLabel enderecoLabel;
	private JLabel bairroLabel;
	private JLabel cidadeLabel;
	private JLabel ufLabel;
	private JLabel cepLabel;
	private JLabel emailLabel;
	private JLabel telefoneLabel;
	
	private JTextField nomeField;
	private JTextField enderecoField;
	private JTextField bairroField;
	private JTextField cidadeField;
	private JTextField emailField;
	
	private JFormattedTextField cepField;
	private JFormattedTextField telefoneField;
	
	private JComboBox<?> ufCombo;
	
	private GridBagConstraints nomeLabelConstraints;
	private GridBagConstraints nomeFieldConstraints;
	private GridBagConstraints enderecoLabelConstraints;
	private GridBagConstraints enderecoFieldConstraints;
	private GridBagConstraints bairroLabelConstraints;
	private GridBagConstraints bairroFieldConstraints;
	private GridBagConstraints cidadeLabelConstraints;
	private GridBagConstraints cidadeFieldConstraints;
	private GridBagConstraints ufLabelConstraints;
	private GridBagConstraints ufComboConstraints;
	private GridBagConstraints cepLabelConstraints;
	private GridBagConstraints cepFieldConstraints;
	private GridBagConstraints emailLabelConstraints;
	private GridBagConstraints emailFieldConstraints;
	private GridBagConstraints telefoneLabelConstraints;
	private GridBagConstraints telefoneFieldConstraints;
	
	private Integer modificador;
	
	public FornecDataPanel(Fornecedor fornecedor, Integer modificador) {
		this.modificador = modificador;
		this.fornecedor = fornecedor;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		add(getNomeLabel(), getNomeLabelConstraints());
		add(getNomeField(), getNomeFieldConstraints());
		
		add(getEnderecoLabel(), getEnderecoLabelConstraints());
		add(getEnderecoField(), getEnderecoFieldConstraints());
		
		add(getBairroLabel(), getBairroLabelConstraints());
		add(getBairroField(), getBairroFieldConstraints());
		
		add(getCidadeLabel(), getCidadeLabelConstraints());
		add(getCidadeField(), getCidadeFieldConstraints());
		
		add(getUfLabel(), getUfLabelConstraints());
		add(getUfCombo(), getUfComboConstraints());
		
		add(getCepLabel(), getCepLabelConstraints());
		add(getCepField(), getCepFieldConstraints());
		
		add(getEmailLabel(), getEmailLabelConstraints());
		add(getEmailField(), getEmailFieldConstraints());
		
		add(getTelefoneLabel(), getTelefoneLabelConstraints());
		add(getTelefoneField(), getTelefoneFieldConstraints());
		
	}
	
	public JFormattedTextField getTelefoneField(){
		if(telefoneField == null){
			telefoneField = new JFormattedTextField(getMask("(##)####-####"));
			if(modificador != Modificadores.ADDFORNECEDOR)
				telefoneField.setText(fornecedor.getTelefone());
			telefoneField.setToolTipText("Informe o telefone");
		}
		return telefoneField;
	}
	
	private GridBagConstraints getTelefoneFieldConstraints(){
		if(telefoneFieldConstraints == null){
			telefoneFieldConstraints = createConstraintsPrototype();
			
			telefoneFieldConstraints.gridx = 4;
			telefoneFieldConstraints.gridy = 3;
			
		}
		return telefoneFieldConstraints;
	}
	
	private JLabel getTelefoneLabel(){
		if(telefoneLabel == null){
			telefoneLabel = new JLabel();
			telefoneLabel.setText("Telefone");
		}
		return telefoneLabel;
	}
	
	private GridBagConstraints getTelefoneLabelConstraints(){
		if(telefoneLabelConstraints == null){
			telefoneLabelConstraints = createConstraintsPrototype();
			
			telefoneLabelConstraints.gridx = 3;
			telefoneLabelConstraints.gridy = 3;
			
			telefoneLabelConstraints.anchor = GridBagConstraints.CENTER;
		}
		return telefoneLabelConstraints;
	}
	
	public JTextField getEmailField(){
		if(emailField == null){
			emailField = new JTextField();
			int strSize = 50;
			emailField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDFORNECEDOR)
				emailField.setText(fornecedor.getEmail());
			emailField.setColumns(8);
			emailField.setToolTipText("Informe e-mail");
		}
		return emailField;
	}
	
	private GridBagConstraints getEmailFieldConstraints(){
		if(emailFieldConstraints == null){
			emailFieldConstraints = createConstraintsPrototype();
			
			emailFieldConstraints.gridx = 1;
			emailFieldConstraints.gridy = 3;
			
			emailFieldConstraints.gridwidth = 2;
		}
		return emailFieldConstraints;
	}
	
	private JLabel getEmailLabel(){
		if(emailLabel == null){
			emailLabel = new JLabel();
			emailLabel.setText("E-mail");
		}
		return emailLabel;
	}
	
	private GridBagConstraints getEmailLabelConstraints(){
		if(emailLabelConstraints == null){
			emailLabelConstraints = createConstraintsPrototype();
			
			emailLabelConstraints.gridx = 0;
			emailLabelConstraints.gridy = 3;
			
			emailLabelConstraints.anchor = GridBagConstraints.WEST;
		}
		return emailLabelConstraints;
	}
	
	public JFormattedTextField getCepField(){
		if(cepField == null){
			cepField = new JFormattedTextField(getMask("#####-###"));
			if(modificador != Modificadores.ADDFORNECEDOR)
				cepField.setText(fornecedor.getCep());
			cepField.setToolTipText("Informe o cep");
		}
		return cepField;
	}
	
	private GridBagConstraints getCepFieldConstraints(){
		if(cepFieldConstraints == null){
			cepFieldConstraints = createConstraintsPrototype();
			
			cepFieldConstraints.gridx = 2;
			cepFieldConstraints.gridy = 2;
			
			cepFieldConstraints.gridwidth = 2;
			
			cepFieldConstraints.fill = GridBagConstraints.NONE;
			
			cepFieldConstraints.anchor = GridBagConstraints.CENTER;
		}
		return cepFieldConstraints;
	}
	
	private JLabel getCepLabel(){
		if(cepLabel == null){
			cepLabel = new JLabel();
			cepLabel.setText("CEP");
		}
		return cepLabel;
	}
	
	private GridBagConstraints getCepLabelConstraints(){
		if(cepLabelConstraints == null){
			cepLabelConstraints = createConstraintsPrototype();
			
			cepLabelConstraints.gridx = 2;
			cepLabelConstraints.gridy = 2;
			
			cepLabelConstraints.fill = GridBagConstraints.NONE;
			
			cepLabelConstraints.anchor = GridBagConstraints.WEST;
		}
		return cepLabelConstraints;
	}
	
	public JComboBox<?> getUfCombo(){
		if(ufCombo == null){
			ufCombo = new JComboBox<Object>(getEstados());
			ufCombo.setToolTipText("Selecione o estado");
			if(modificador != Modificadores.ADDFORNECEDOR)
				ufCombo.setSelectedItem(fornecedor.getEstado());
		}
		return ufCombo;
	}
	
	private GridBagConstraints getUfComboConstraints(){
		if(ufComboConstraints == null){
			ufComboConstraints = createConstraintsPrototype();
			
			ufComboConstraints.gridx = 4;
			ufComboConstraints.gridy = 2;
			
			ufComboConstraints.ipadx = 15;
			ufComboConstraints.anchor = GridBagConstraints.EAST;
			ufComboConstraints.gridwidth = 2;
		}
		return ufComboConstraints;
	}
	
	private JLabel getUfLabel(){
		if(ufLabel == null){
			ufLabel = new JLabel();
			ufLabel.setText("UF");
		}
		return ufLabel;
	}
	
	private GridBagConstraints getUfLabelConstraints(){
		if(ufLabelConstraints == null){
			ufLabelConstraints = createConstraintsPrototype();
			
			ufLabelConstraints.gridx = 3;
			ufLabelConstraints.gridy = 2;
			
			ufLabelConstraints.ipadx = 5;
			
			ufLabelConstraints.fill = GridBagConstraints.NONE;
			
			ufLabelConstraints.anchor = GridBagConstraints.EAST;
		}
		return ufLabelConstraints;
	}
	
	public JTextField getCidadeField(){
		if(cidadeField == null){
			cidadeField = new JTextField();
			int strSize = 50;
			cidadeField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDFORNECEDOR)
				cidadeField.setText(fornecedor.getCidade());
			cidadeField.setColumns(8);
			cidadeField.setToolTipText("Informe cidade do fornecedor");
		}
		return cidadeField;
	}
	
	private GridBagConstraints getCidadeFieldConstraints(){
		if(cidadeFieldConstraints == null){
			cidadeFieldConstraints = createConstraintsPrototype();
			
			cidadeFieldConstraints.gridx = 1;
			cidadeFieldConstraints.gridy = 2;
			
			cidadeFieldConstraints.anchor = GridBagConstraints.WEST;
		}
		return cidadeFieldConstraints;
	}
	
	private JLabel getCidadeLabel(){
		if(cidadeLabel == null){
			cidadeLabel = new JLabel();
			cidadeLabel.setText("Cidade");
		}
		return cidadeLabel;
	}
	
	private GridBagConstraints getCidadeLabelConstraints(){
		if(cidadeLabelConstraints == null){
			cidadeLabelConstraints = createConstraintsPrototype();
			
			cidadeLabelConstraints.gridx = 0;
			cidadeLabelConstraints.gridy = 2;
			
			cidadeLabelConstraints.anchor = GridBagConstraints.WEST;
		}
		return cidadeLabelConstraints;
	}
	
	public JTextField getBairroField(){
		if(bairroField == null){
			bairroField = new JTextField();
			int strSize = 50;
			bairroField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDFORNECEDOR)
				bairroField.setText(fornecedor.getBairro());
			bairroField.setColumns(5);
			bairroField.setToolTipText("Informe o bairro do fornecedor");
		}
		return bairroField;
	}
	
	private GridBagConstraints getBairroFieldConstraints(){
		if(bairroFieldConstraints == null){
			bairroFieldConstraints = createConstraintsPrototype();
			
			bairroFieldConstraints.gridx = 4;
			bairroFieldConstraints.gridy = 1;
			
		}
		return bairroFieldConstraints;
	}
	
	private JLabel getBairroLabel(){
		if(bairroLabel == null){
			bairroLabel = new JLabel();
			bairroLabel.setText("Bairro");
		}
		return bairroLabel;
	}
	
	private GridBagConstraints getBairroLabelConstraints(){
		if(bairroLabelConstraints == null){
			bairroLabelConstraints = createConstraintsPrototype();
			
			bairroLabelConstraints.gridx = 3;
			bairroLabelConstraints.gridy = 1;
			
			bairroLabelConstraints.fill = GridBagConstraints.NONE;
			
			bairroLabelConstraints.anchor = GridBagConstraints.CENTER;;
		}
		return bairroLabelConstraints;
	}
	
	public JTextField getEnderecoField() {
		if(enderecoField == null){
			enderecoField = new JTextField();
			int strSize = 50;
			enderecoField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDFORNECEDOR)
				enderecoField.setText(fornecedor.getEndereco());
			enderecoField.setColumns(15);
			enderecoField.setToolTipText("Informe endereço do fornecedor");
		}
		return enderecoField;
	}
	
	private GridBagConstraints getEnderecoFieldConstraints(){
		if(enderecoFieldConstraints == null){
			enderecoFieldConstraints = createConstraintsPrototype();
			
			enderecoFieldConstraints.gridx = 1;
			enderecoFieldConstraints.gridy = 1;
			
			enderecoFieldConstraints.gridwidth = 2;
			
		}
		return enderecoFieldConstraints;
	}

	private JLabel getEnderecoLabel() {
		if(enderecoLabel == null){
			enderecoLabel = new JLabel();
			enderecoLabel.setText("Endereço");
		}
		return enderecoLabel;
	}

	private GridBagConstraints getEnderecoLabelConstraints() {
		if(enderecoLabelConstraints == null){
			enderecoLabelConstraints = createConstraintsPrototype();
			
			enderecoLabelConstraints.gridx = 0;
			enderecoLabelConstraints.gridy = 1;
		}
		return enderecoLabelConstraints;
	}
	
	private JLabel getNomeLabel(){
		if ( nomeLabel == null){
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
			
			nomeLabelConstraints.anchor = GridBagConstraints.WEST;
		}
		return nomeLabelConstraints;
	}
	
	public JTextField getNomeField(){
		if(nomeField == null){
			nomeField = new JTextField();
			int strSize = 50;
			nomeField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDFORNECEDOR)
				nomeField.setText(fornecedor.getNome());
			nomeField.setColumns(10);
			nomeField.setToolTipText("Informe nome do fornecedor");
		}
		return nomeField;
	}
	
	private GridBagConstraints getNomeFieldConstraints(){
		if(nomeFieldConstraints == null){
			nomeFieldConstraints = createConstraintsPrototype();
			
			nomeFieldConstraints.gridx = 1;
			nomeFieldConstraints.gridy = 0;
			
			nomeFieldConstraints.gridwidth = 4;
			
			//nomeFieldConstraints.weightx = 0.0;
			
			//nomeFieldConstraints.ipadx = 285;
			
		}
		return nomeFieldConstraints;
	}

	private GridBagConstraints createConstraintsPrototype() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		int gap = 4;
		
		gbc.insets = new Insets(gap, gap, gap, gap);
		return gbc;
	}
	
   private String[] getEstados() {
		
		String[] estados = new String[]{
				"",
				"AC",
				"AL",
				"AP",
				"AM",
				"BA",
				"CE",
				"DF",
				"ES",
				"GO",
				"MA",
				"MT",
				"MS",
				"MG",
				"PA",
				"PB",
				"PR",
				"PE",
				"PI",
				"RJ",
				"RN",
				"RS",
				"RO",
				"RR",
				"SC",
				"SP",
				"SE",
				"TO"
		};
		return estados;
	}

	private MaskFormatter getMask(String maskString) {
		MaskFormatter mask = new MaskFormatter();
	
		try {
			mask.setMask(maskString);
			mask.setPlaceholderCharacter('_');
		} 
		catch (ParseException e) {
			System.out.println("Erro: " + maskString);
		}
	
		return mask;
	}

}
