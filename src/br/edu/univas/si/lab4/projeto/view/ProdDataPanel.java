package br.edu.univas.si.lab4.projeto.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Produto;
import br.edu.univas.si.lab4.projeto.model.TextDocument;

public class ProdDataPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public Produto produto;
	
	private JLabel nomeLabel;
	private JLabel fornecedorLabel;
	private JLabel valorCompraLabel;
	private JLabel valorVendaLabel;
	private JLabel tipoLabel;
	private JLabel descricaoLabel;
	
	private JTextField nomeField;
	private JTextField valorCompraField;
	private JTextField valorVendaField;
	
	private JTextArea descricaoField;
	
	private JComboBox<?> fornecedorCombo;
	private JComboBox<?> tipoCombo;
	
	private JScrollPane descricaoScroll;
	
	private GridBagConstraints nomeLabelConstraints;
	private GridBagConstraints fornecedorLabelConstraints;
	private GridBagConstraints valorCompraLabelConstraints;
	private GridBagConstraints valorVendaLabelConstraints;
	private GridBagConstraints nomeFieldConstraints;
	private GridBagConstraints fornecedorComboConstraints;
	private GridBagConstraints valorCompraFieldConstraints;
	private GridBagConstraints valorVendaFieldConstraints;
	private GridBagConstraints tipoLabelConstraints;
	private GridBagConstraints tipoComboConstraints;
	private GridBagConstraints descricaoLabelConstraints;
	private GridBagConstraints descricaoFieldConstraints;
	
	private Integer modificador;
	

	public ProdDataPanel(Produto produto, Integer modificador) {
		this.produto = produto;
		this.modificador = modificador;
		setLayout(new GridBagLayout());
		initialize();
	}
	
	private void initialize() {
		add(getNomeLabel(), getNomeLabelConstraints());
		add(getNomeField(), getNomeFieldConstraints());
		
		add(getFornecedorLabel(), getFornecedorLabelConstraints());
		add(getFornecedorCombo(), getFornecedorComboConstraints());
		
		add(getValorCompraLabel(), getValorCompraLabelConstraints());
		add(getValorCompraField(), getValorCompraFieldContraints());
		
		add(getValorVendaLabel(), getValorVendaLabelConstraints());
		add(getValorVendaField(), getValorVendaFieldConstraints());
		
		add(getTipoLabel(), getTipoLabelConstraints());
		add(getTipoCombo(), getTipoComboConstraints());
		
		add(getDescricaoLabel(), getDescricaoLabelConstraints());
		add(getDescricaoScroll(), getDescricaoScrollConstraints());
		
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
		}
		return nomeLabelConstraints;
	}
	
	public JTextField getNomeField(){
		if(nomeField == null){
			nomeField = new JTextField();
			nomeField.setColumns(25);
			int strSize = 50;
			nomeField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDPRODUTO)
				nomeField.setText(produto.getNome());
			nomeField.setToolTipText("Informe nome do produto");
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
	
	private JLabel getFornecedorLabel(){
		if(fornecedorLabel == null){
			fornecedorLabel = new JLabel();
			fornecedorLabel.setText("Fornecedor");
		}
		return fornecedorLabel;
	}
	
	public GridBagConstraints getFornecedorLabelConstraints(){
		if(fornecedorLabelConstraints == null){
			fornecedorLabelConstraints = createConstraintsPrototype();
			
			fornecedorLabelConstraints.gridx = 0;
			fornecedorLabelConstraints.gridy = 1;
		}
		return fornecedorLabelConstraints;
	}
	
	public JComboBox<?> getFornecedorCombo(){
		if(fornecedorCombo == null){
			fornecedorCombo = new JComboBox<Object>(getComboList());
			if(modificador != Modificadores.ADDPRODUTO)
				fornecedorCombo.setSelectedItem(produto.getFornecedor());
			fornecedorCombo.setToolTipText("Selecione o fornecedor");
		}
		return fornecedorCombo;
	}
	
	private String[] getComboList(){
		String[] list = new DAO().getFornecedorList();
		
		return list;
	}
	
	private GridBagConstraints getFornecedorComboConstraints(){
		if(fornecedorComboConstraints == null){
			fornecedorComboConstraints = createConstraintsPrototype();
			
			fornecedorComboConstraints.gridx = 1;
			fornecedorComboConstraints.gridy = 1;
			
			fornecedorComboConstraints.gridwidth = 3;
			fornecedorComboConstraints.ipadx = 80;
		}
		
		return fornecedorComboConstraints;
	}
	
	private JLabel getValorCompraLabel(){
		if(valorCompraLabel == null){
			valorCompraLabel = new JLabel();
			valorCompraLabel.setText("Valor Compra");
		}
		return valorCompraLabel;
	}
	
	private GridBagConstraints getValorCompraLabelConstraints(){
		if(valorCompraLabelConstraints == null){
			valorCompraLabelConstraints = createConstraintsPrototype();
			
			valorCompraLabelConstraints.gridx = 0;
			valorCompraLabelConstraints.gridy = 2;
		}
		return valorCompraLabelConstraints;
	}
	
	public JTextField getValorCompraField(){
		if(valorCompraField == null){
			valorCompraField = new JTextField();
			valorCompraField.setColumns(10);
			int strSize = 50;
			valorCompraField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDPRODUTO)
				valorCompraField.setText(produto.getValor_compra());
			valorCompraField.setToolTipText("Informe valor de compra");
		}
		return valorCompraField;
	}
	
	private GridBagConstraints getValorCompraFieldContraints(){
		if(valorCompraFieldConstraints == null){
			valorCompraFieldConstraints = createConstraintsPrototype();
			
			valorCompraFieldConstraints.gridx = 1;
			valorCompraFieldConstraints.gridy = 2;
			
			valorCompraFieldConstraints.gridwidth = 2;
			valorCompraFieldConstraints.ipadx = 90;
		}
		return valorCompraFieldConstraints;
	}
	
	private JLabel getValorVendaLabel(){
		if(valorVendaLabel == null){
			valorVendaLabel = new JLabel();
			valorVendaLabel.setText("Valor Venda");
		}
		return valorVendaLabel;
	}
	
	private GridBagConstraints getValorVendaLabelConstraints(){
		if(valorVendaLabelConstraints == null){
			valorVendaLabelConstraints = createConstraintsPrototype();
			
			valorVendaLabelConstraints.gridx = 3;
			valorVendaLabelConstraints.gridy = 2;
		}
		return valorVendaLabelConstraints;
	}
	
	public JTextField getValorVendaField(){
		if(valorVendaField == null){
			valorVendaField = new JTextField();
			valorVendaField.setColumns(10);
			int strSize = 50;
			valorVendaField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDPRODUTO)
				valorVendaField.setText(produto.getValor_venda());
			valorVendaField.setToolTipText("Informe valor de venda");
		}
		return valorVendaField;
	}
	
	private GridBagConstraints getValorVendaFieldConstraints(){
		if(valorVendaFieldConstraints == null){
			valorVendaFieldConstraints = createConstraintsPrototype();
			
			valorVendaFieldConstraints.gridx = 4;
			valorVendaFieldConstraints.gridy = 2;
			
			valorVendaFieldConstraints.gridwidth = 2;
		}
		return valorVendaFieldConstraints;
	}
	
	private JLabel getTipoLabel(){
		if(tipoLabel == null){
			tipoLabel = new JLabel();
			tipoLabel.setText("Tipo");
		}
		return tipoLabel;
	}
	
	private GridBagConstraints getTipoLabelConstraints(){
		if(tipoLabelConstraints == null){
			tipoLabelConstraints = createConstraintsPrototype();
			
			tipoLabelConstraints.gridx = 4;
			tipoLabelConstraints.gridy = 1;
		}
		return tipoLabelConstraints;
	}
	
	public JComboBox<?> getTipoCombo(){
		if(tipoCombo == null){
			tipoCombo = new JComboBox<Object>(getTipoList());
			tipoCombo.setToolTipText("Selecione o tipo do produto");
			if(modificador != Modificadores.ADDPRODUTO)
				tipoCombo.setSelectedItem(produto.getTipo());
		}
		return tipoCombo;
	}

	private String[] getTipoList(){
		String[] list = {
				"",
				"Bebida",
				"Alimenticio",
				"Frios",
				"Horti Fruti",
				"Laticinios"
		};
		
		return list;
	}
	
	private GridBagConstraints getTipoComboConstraints(){
		if(tipoComboConstraints == null){
			tipoComboConstraints = createConstraintsPrototype();
			
			tipoComboConstraints.gridx = 5;
			tipoComboConstraints.gridy = 1;
			
		}
		return tipoComboConstraints;
	}
	
	private JLabel getDescricaoLabel(){
		if(descricaoLabel == null){
			descricaoLabel = new JLabel();
			descricaoLabel.setText("Descrição");
		}
		return descricaoLabel;
	}
	
	private GridBagConstraints getDescricaoLabelConstraints(){
		if(descricaoLabelConstraints == null){
			descricaoLabelConstraints = createConstraintsPrototype();
			
			descricaoLabelConstraints.gridx = 0;
			descricaoLabelConstraints.gridy = 3;
			
		}
		return descricaoLabelConstraints;
	}
	
	private JScrollPane getDescricaoScroll(){
		if(descricaoScroll == null){
			descricaoScroll = new JScrollPane(getDescricaoField());
			descricaoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		}
		return descricaoScroll;
	}
	
	public JTextArea getDescricaoField(){
		if(descricaoField == null){
			descricaoField = new JTextArea();
			int strSize = 200;
			descricaoField.setDocument(new TextDocument(strSize));
			if(modificador != Modificadores.ADDPRODUTO)
				descricaoField.setText(produto.getDescricao());
			descricaoField.setToolTipText("Informe descrição. OBS: max 200 caracteres!");
			descricaoField.setLineWrap(true);  
			descricaoField.setWrapStyleWord(true);  
		}
		return descricaoField;
	}
	
	private GridBagConstraints getDescricaoScrollConstraints(){
		if(descricaoFieldConstraints == null){
			descricaoFieldConstraints = createConstraintsPrototype();
			
			descricaoFieldConstraints.gridx = 1;
			descricaoFieldConstraints.gridy = 3;
			
			descricaoFieldConstraints.gridwidth = 5;
			descricaoFieldConstraints.gridheight = 3;
			
			descricaoFieldConstraints.ipadx = 200;
			descricaoFieldConstraints.ipady = 60;
		}
		return descricaoFieldConstraints;
	}
	
	private GridBagConstraints createConstraintsPrototype() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		int gap = 4;
		
		gbc.insets = new Insets(gap, gap, gap, gap);
		return gbc;
	}
}
