package br.edu.univas.si.lab4.projeto.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import br.edu.univas.si.lab4.projeto.listeners.ProdutosListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Produto;

public class ProdutoTablePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JScrollPane scrollTable;
	private DefaultTableModel tableModel;
	
	private JButton sairButton;
	private JButton findButton;
	private JButton atualizaButton;
	private JButton deletaButton;
	
	private JLabel titulo;
	
	private JComboBox<?> findCombo;
	
	private JTextField pesqField;
	private ArrayList<Produto> produtos;
	private ArrayList<ProdutosListeners> listeners = new ArrayList<ProdutosListeners>();
	
	private GridBagConstraints scrollTableConstraints;
	private GridBagConstraints pesqFieldConstraints;
	private GridBagConstraints sairButtonConstraints;
	private GridBagConstraints findButtonConstraints;
	private GridBagConstraints findComboConstraints;
	private GridBagConstraints atualizaButtonConstraints;
	private GridBagConstraints deletaButtonConstraints;
	private GridBagConstraints tituloConstraints;
	
	private Integer tableWidth;
	private Integer tableHeight;
	
	public ProdutoTablePanel(Integer tableHeight, Integer tableWidth){
		this.tableHeight = tableHeight;
		this.tableWidth = tableWidth;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		add(getPesqField(), getPesqFieldConstraints());
		add(getScrollTable(), getScrollTableConstraints());
		add(getSairButton(), getSairButtonConstraints());
		add(getFindButton(), getFindButtonConstraints());
		add(getFindCombo(), getFindComboConstraints());
		add(getAtualizaButton(), getAtualizaButtonConstraints());
		add(getDeletaButton(), getDeletaButtonConstraints());
		add(getTitulo(), getTituloConstraints());
	}
	
	private JLabel getTitulo(){
		if(titulo == null){
			titulo = new JLabel();
			titulo.setText("Lista de Produtos");
			titulo.setFont(new Font("Castellar", Font.BOLD, 20));
		}
		return titulo;
	}
	
	private GridBagConstraints getTituloConstraints(){
		if(tituloConstraints == null){
			tituloConstraints = createConstraintsPrototype();
			
			tituloConstraints.gridx = 4;
			tituloConstraints.gridy = 0;
			
			tituloConstraints.gridwidth = 2;
			tituloConstraints.anchor = GridBagConstraints.WEST;
			
			tituloConstraints.ipadx = 30;
		}
		return tituloConstraints;
	}
	
	private JButton getDeletaButton(){
		if(deletaButton == null){
			ImageIcon icon = createImagemIcon("/Trash.png");
			deletaButton = new JButton();
			deletaButton.setToolTipText("Deleta linha selecionada!");
			deletaButton.setIcon(icon);
			deletaButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Produto produto = getRowInfo();
					for(ProdutosListeners listener : listeners)
						listener.deletaProduto(produto);
					
				}
			});
		}
		return deletaButton;
	}
	
	private GridBagConstraints getDeletaButtonConstraints(){
		if(deletaButtonConstraints == null){
			deletaButtonConstraints = createConstraintsPrototype();
			
			deletaButtonConstraints.gridx = 4;
			deletaButtonConstraints.gridy = 2;
		}
		return deletaButtonConstraints;
	}

	private JButton getSairButton(){
		if(sairButton == null){
			ImageIcon icon = createImagemIcon("/Exit.png");
			sairButton = new JButton();
			sairButton.setBorderPainted(false);
			sairButton.setToolTipText("Fechar estoque");
			sairButton.setContentAreaFilled(false);
			sairButton.setIcon(icon);
			sairButton.setFocusPainted(false);
			sairButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for(ProdutosListeners listener : listeners)
						listener.fecharTelaprodutos();
					
				}
			});
		}
		return sairButton;
	}
	
	private GridBagConstraints getSairButtonConstraints(){
		if(sairButtonConstraints ==null){
			sairButtonConstraints = createConstraintsPrototype();
			
			sairButtonConstraints.gridx = 7;
			sairButtonConstraints.gridy = 1;
		}
		return sairButtonConstraints;
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
	
	private JButton getFindButton(){
		if(findButton == null){
			ImageIcon icon = createImagemIcon("/Find.png");
			findButton = new JButton();
			findButton.setToolTipText("Clique para buscar produto!");
			findButton.setIcon(icon);
			findButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String campoPesq = findCombo.getSelectedItem().toString();
					String pesqString = pesqField.getText();
					for(ProdutosListeners listener : listeners)
						listener.pesquisarProdutos(campoPesq, pesqString);
				}
			});
		}
		return findButton;
	}
	
	private GridBagConstraints getFindButtonConstraints(){
		if(findButtonConstraints == null){
			findButtonConstraints = createConstraintsPrototype();
			
			findButtonConstraints.gridx = 2;
			findButtonConstraints.gridy = 2;
			
		}
		return findButtonConstraints;
	}
	
	public JComboBox<?> getFindCombo(){
		if(findCombo == null){
			findCombo = new JComboBox<Object>(getFindList());
			findCombo.setToolTipText("Escolha qual campo pesquisar!");
		}
		return findCombo;
	}
	
	private String[] getFindList(){
		String[] list = {
				"Codigo", 
				"Nome", 
				"Fornecedor", 
				"Quantidade", 
				"Valor de compra", 
				"Valor de venda", 
				"Tipo", 
				"Descrição"
		};
		return list;
	}
	
	private GridBagConstraints getFindComboConstraints(){
		if(findComboConstraints == null){
			findComboConstraints = createConstraintsPrototype();
			
			findComboConstraints.gridx = 0;
			findComboConstraints.gridy = 1;
			
			findComboConstraints.ipadx = 30;
		}
		return findComboConstraints;
	}
	
	private JButton getAtualizaButton(){
		if(atualizaButton == null){
			ImageIcon icon = createImagemIcon("/Update.png");
			atualizaButton = new JButton();
			atualizaButton.setToolTipText("Atualiza Tabela");
			atualizaButton.setIcon(icon);
			atualizaButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for(ProdutosListeners listener : listeners)
						listener.atualizaTable();
				}
			});
		}
		return atualizaButton;
	}
	
	private GridBagConstraints getAtualizaButtonConstraints(){
		if(atualizaButtonConstraints == null){
			atualizaButtonConstraints = createConstraintsPrototype();
			
			atualizaButtonConstraints.gridx = 3;
			atualizaButtonConstraints.gridy = 2;
			
		}
		return atualizaButtonConstraints;
	}

	private JScrollPane getScrollTable() {
		if(scrollTable == null) {
			scrollTable = new JScrollPane(getTable());
			scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			scrollTable.setHorizontalScrollBarPolicy(JScrollPane .HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		}
		return scrollTable;
	}

	public JTable getTable() {
		if(table == null) {
			table = new JTable(){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int rowIndex, int vColIndex) {
			        return false;
			    }
			};
			table.setModel(getTableModel());	
			table.addMouseListener(new MouseAdapter() {
				
				
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() > 1) { 
						Produto produto = getRowInfo();
						for(ProdutosListeners listener : listeners)
							listener.showUpdateFrame(produto);
					}
					
				}
			});
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
			for( int i = 0; i < 8; i++)
				table.getColumnModel().getColumn(i).setPreferredWidth(210);  
		}
		return table;
	}

	public DefaultTableModel getTableModel() {
		if(tableModel == null) {
			tableModel = new DefaultTableModel();
			tableModel.setColumnIdentifiers(new String[]{
					"Codigo", "Nome", "Fornecedor", "Quantidade", "Valor de compra", "Valor de venda", "Tipo", "Descrição"
			});
			DAO dao = new DAO();
			produtos = dao.produtosList();
			for (Produto produto : produtos) {
				addProduto(produto);
			}
		}
		return tableModel;
	}

	private JTextField getPesqField() {
		if(pesqField == null){
			pesqField = new JTextField();
			pesqField.setToolTipText("Digite a pesquisa");
		}
		return pesqField;
	}

	private GridBagConstraints getPesqFieldConstraints() {
		if(pesqFieldConstraints == null){
			pesqFieldConstraints = createConstraintsPrototype();
			pesqFieldConstraints.gridx = 0;
			pesqFieldConstraints.gridy = 2;
			pesqFieldConstraints.gridwidth = 2;
			pesqFieldConstraints.ipadx = 230;
		}
		return pesqFieldConstraints;
	}
	
	private GridBagConstraints getScrollTableConstraints() {
		if(scrollTableConstraints == null){
			scrollTableConstraints = createConstraintsPrototype();
			scrollTableConstraints.gridx = 0;
			scrollTableConstraints.gridy = 3;
			scrollTableConstraints.gridwidth = 6;
			scrollTableConstraints.ipadx = tableWidth;
			scrollTableConstraints.ipady = tableHeight;
		}
		return scrollTableConstraints;
	}
	
	private GridBagConstraints createConstraintsPrototype() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.NONE;
		
		int gap = 4;
		
		gbc.insets = new Insets(gap, gap, gap, gap);
		return gbc;
	}
	
	public void addProduto(Produto produto) {
		if(produto != null) {
			String[] row = new String[] {
					produto.getCodigo().toString(),
					produto.getNome(),
					produto.getFornecedor(),
					produto.getQuantidade(),
					produto.getValor_compra(),
					produto.getValor_venda(),
					produto.getTipo(),
					produto.getDescricao()
			};
			getTableModel().addRow(row);
		}
	}
	
	public Produto getRowInfo(){
		Produto produto = new Produto();
		String defaultValue = "0";
		produto.setCodigo(defaultValue);
		try {
			int row = table.getSelectedRow();
			
			
			produto.setCodigo((String) table.getValueAt(row, 0));
			produto.setNome((String) table.getValueAt(row, 1));
			produto.setFornecedor((String) table.getValueAt(row, 2));
			produto.setQuantidade((String) table.getValueAt(row, 3));
			produto.setValor_compra((String) table.getValueAt(row, 4));
			produto.setValor_venda((String) table.getValueAt(row, 5));
			produto.setTipo((String) table.getValueAt(row, 6));
			produto.setDescricao((String) table.getValueAt(row, 7));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return produto;
	}

	public void addProdutosListeners(ProdutosListeners listener) {
		listeners.add(listener);
	}
	
	public void atualizaTabela() {  

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[]{
				"Codigo", "Nome", "Fornecedor", "Quantidade", "Valor de compra", "Valor de venda", "Tipo", "Descrição"
		});
		DAO dao = new DAO();
		produtos = dao.produtosList();
		for (Produto produto : produtos) {
			addProduto(produto);
		}
		table.setModel(tableModel);  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		for( int i = 0; i < 8; i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(200); 
	}

	public void mostraResultadoPesq(ArrayList<Produto> produtos) {
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[]{
				"Codigo", "Nome", "Fornecedor", "Quantidade", "Valor de compra", "Valor de venda", "Tipo", "Descrição"
		});
		for (Produto produto : produtos) {
			addProduto(produto);
		}
		table.setModel(tableModel);  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		for( int i = 0; i < 8; i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(200); 
		
	}

}
