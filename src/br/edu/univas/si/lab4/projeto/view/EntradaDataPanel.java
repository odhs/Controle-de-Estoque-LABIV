package br.edu.univas.si.lab4.projeto.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Entrada;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.TextDocument;

public class EntradaDataPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel produtoLabel;
	private JLabel dataLabel;
	private JLabel fornecedorLabel;
	private JLabel qtdLabel;
	
	private JCalendar calendar;
	private JComboBox<?> fornecedorCombo;
	
	private JTextField produtoField;
	private JTextField qtdField;
	
	private GridBagConstraints produtoLabelConstraints;
	private GridBagConstraints produtoFieldConstraints;
	private GridBagConstraints dataLabelConstraints;
	private GridBagConstraints calendarConstraints;
	private GridBagConstraints fornecedorLabelConstraints;
	private GridBagConstraints fornecedorComboConstraints;
	private GridBagConstraints qtdLabelConstraints;
	private GridBagConstraints qtdFieldConstraints;
	
	private Entrada entrada;
	private Integer modificador;
	
	public EntradaDataPanel(Entrada entrada, Integer modificador){
		this.modificador = modificador;
		this.entrada = entrada;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		add(getProdutoLabel(), getProdutoLabelConstraints());
		add(getProdutoField(), getProdutoFieldConstraints());
		
		add(getDataLabel(), getDataLabelConstraints());
		add(getCalendar(), getCalendarConstraints());
		
		add(getFornecedorLabel(), getFornecedorLabelConstraints());
		add(getFornecedorCombo(), getFornecedorComboConstraints());
		
		add(getQtdlabel(), getQtdLabelConstraints());
		add(getQtdField(), getQtdFieldConstraints());
	}
	
	public JTextField getQtdField(){
		if(qtdField == null){
			qtdField = new JTextField();
			int strSize = 50;
			qtdField.setDocument(new TextDocument(strSize));
			qtdField.setToolTipText("Informe quantidade de produtos");
			if(modificador == Modificadores.FRAMEENTRADAS)
				qtdField.setText(entrada.getQuantidade());
			qtdField.setColumns(8);
		}
		return qtdField;
	}
	
	private GridBagConstraints getQtdFieldConstraints(){
		if(qtdFieldConstraints == null){
			qtdFieldConstraints = createConstraintsPrototype();
			
			qtdFieldConstraints.gridx = 4;
			qtdFieldConstraints.gridy = 1;
		}
		return qtdFieldConstraints;
	}
	
	private JLabel getQtdlabel(){
		if(qtdLabel == null){
			qtdLabel = new JLabel();
			qtdLabel.setText("Quantidade");
		}
		return qtdLabel;
	}
	
	private GridBagConstraints getQtdLabelConstraints(){
		if(qtdLabelConstraints == null){
			qtdLabelConstraints = createConstraintsPrototype();
			
			qtdLabelConstraints.gridx = 3;
			qtdLabelConstraints.gridy = 1;
		}
		return qtdLabelConstraints;
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
			fornecedorCombo.setToolTipText("Selecione o fornecedor");
			if(modificador == Modificadores.FRAMEENTRADAS)
				fornecedorCombo.setSelectedItem(new DAO().getFornecedorName(entrada));
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
			
			fornecedorComboConstraints.gridwidth = 2;
			fornecedorComboConstraints.ipadx = 80;
		}
		
		return fornecedorComboConstraints;
	}
	
	public JCalendar getCalendar(){
		if(calendar == null){
			calendar = new JCalendar(false);
			calendar.setToolTipText("Selecione a data");
			if(modificador == Modificadores.FRAMEENTRADAS)
				calendar.setSelectedItemString(entrada.getData());
			
		}
		return calendar;
	}
	
	private GridBagConstraints getCalendarConstraints(){
		if(calendarConstraints == null){
			calendarConstraints = createConstraintsPrototype();
			
			calendarConstraints.gridx = 4;
			calendarConstraints.gridy = 0;
		}
		return calendarConstraints;
	}
	
	private JLabel getDataLabel(){
		if(dataLabel == null){
			dataLabel = new JLabel();
			dataLabel.setText("Data");
		}
		return dataLabel;
	}
	
	private GridBagConstraints getDataLabelConstraints(){
		if(dataLabelConstraints == null){
			dataLabelConstraints = createConstraintsPrototype();
			
			dataLabelConstraints.gridx = 3;
			dataLabelConstraints.gridy = 0;
		}
		return dataLabelConstraints;
	}
	
	public JTextField getProdutoField(){
		if(produtoField == null){
			produtoField = new JTextField();
			int strSize = 50;
			produtoField.setDocument(new TextDocument(strSize));
			if(modificador == Modificadores.FRAMEENTRADAS){
				produtoField.setText(new DAO().getProdutoName(entrada));
			}
			produtoField.setToolTipText("Informe o produto");
			produtoField.setColumns(20);
			
		}
		return produtoField;
	}
	
	private GridBagConstraints getProdutoFieldConstraints(){
		if(produtoFieldConstraints == null){
			produtoFieldConstraints = createConstraintsPrototype();
			
			produtoFieldConstraints.gridx = 1;
			produtoFieldConstraints.gridy = 0;
			
			produtoFieldConstraints.gridwidth = 2;
			
		}
		return produtoFieldConstraints;
	}
	
	private JLabel getProdutoLabel(){
		if(produtoLabel == null){
			produtoLabel = new JLabel();
			produtoLabel.setText("Produto");
		}
		return produtoLabel;
	}
	
	private GridBagConstraints getProdutoLabelConstraints(){
		if(produtoLabelConstraints == null){
			produtoLabelConstraints = createConstraintsPrototype();
			
			produtoLabelConstraints.gridx = 0;
			produtoLabelConstraints.gridy = 0;
		}
		return produtoLabelConstraints;
	}
	
	private GridBagConstraints createConstraintsPrototype() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		int gap = 4;
		
		gbc.insets = new Insets(gap, gap, gap, gap);
		return gbc;
	}
	
	public String getCalendarDate(){
		String date = ((JCalendar)calendar).getText();
		
		return date;
	}

}
