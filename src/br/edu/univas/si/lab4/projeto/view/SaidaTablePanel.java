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

import br.edu.univas.si.lab4.projeto.listeners.SaidasListener;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Saida;

public class SaidaTablePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
private JScrollPane scrollTable;

	
	private JTable table;
	
	private DefaultTableModel tableModel;
	
	private JTextField pesqField;
	
	private JLabel titulo;
	
	private JButton sairButton;
	private JButton findButton;
	private JButton atualizaButton;
	private JButton deletaButton;
	
	private JComboBox<?> findCombo;
	
	private ArrayList<Saida> saidas;
	
	private GridBagConstraints scrollConstraints;
	private GridBagConstraints pesqFieldConstraints;
	private GridBagConstraints sairButtonConstraints;
	private GridBagConstraints findButtonConstraints;
	private GridBagConstraints findComboConstraints;
	private GridBagConstraints atualizaButtonConstraints;
	private GridBagConstraints deletaButtonConstraints;
	private GridBagConstraints tituloConstraints;
	
	private Integer tableWidth;
	private Integer tableHeight;
	
	ArrayList<SaidasListener> listeners = new ArrayList<SaidasListener>();
	
	public SaidaTablePanel(Integer tableHeight, Integer tableWidth) {
		this.tableHeight = tableHeight;
		this.tableWidth = tableWidth;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		add(getScrollTable(), getScrollConstraints());
		add(getPesqField(), getPesqFieldConstraints());
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
			titulo.setText("Lista de Saidas");
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
					Saida saida = getRowInfo();
					for(SaidasListener listener : listeners)
						listener.deletaSaida(saida);
					
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
	
	private JButton getAtualizaButton(){
		if(atualizaButton == null){
			ImageIcon icon = createImagemIcon("/Update.png");
			atualizaButton = new JButton();
			atualizaButton.setToolTipText("Atualiza Tabela");
			atualizaButton.setIcon(icon);
			atualizaButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for(SaidasListener listener : listeners)
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
				"Codigo Fornecedor", 
				"Codigo Produto", 
				"Quantidade Saida", 
				"Data Saida", 
				"Preço Saida"
				
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
	
	private JButton getFindButton(){
		if(findButton == null){
			ImageIcon icon = createImagemIcon("/Find.png");
			findButton = new JButton();
			findButton.setToolTipText("Clique para buscar saidas!");
			findButton.setIcon(icon);
			findButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String campoPesq = findCombo.getSelectedItem().toString();
					String pesqString = pesqField.getText();
					for(SaidasListener listener : listeners)
						listener.pesquisarSaidas(campoPesq, pesqString);
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
	
	private JButton getSairButton(){
		if(sairButton == null){
			ImageIcon icon = createImagemIcon("/Exit.png");
			sairButton = new JButton();
			sairButton.setBorderPainted(false);
			sairButton.setToolTipText("Fechar lista de saidas");
			sairButton.setContentAreaFilled(false);
			sairButton.setIcon(icon);
			sairButton.setFocusPainted(false);
			sairButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for(SaidasListener listener : listeners)
						listener.fecharTelaSaidas();
					
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

	private JScrollPane getScrollTable() {
		if(scrollTable == null){
			scrollTable = new JScrollPane(getTable());
			scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			scrollTable.setHorizontalScrollBarPolicy(JScrollPane .HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		}
		return scrollTable;
	}
	
	private GridBagConstraints getScrollConstraints(){
		if(scrollConstraints == null){
			scrollConstraints = createConstraintsPrototype();
			scrollConstraints.gridx = 0;
			scrollConstraints.gridy = 3;
			
			scrollConstraints.gridwidth = 6;
			scrollConstraints.ipadx = tableWidth;
			scrollConstraints.ipady = tableHeight;
		}
		return scrollConstraints;
	}

	private JTable getTable() {
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
						Saida saida = getRowInfo();
						for(SaidasListener listener : listeners)
							listener.showUpdateFrame(saida);
					}
					
				}
			});
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
			for( int i = 0; i < 6; i++)
				table.getColumnModel().getColumn(i).setPreferredWidth(210);  
		}
		return table;
	}

	private DefaultTableModel getTableModel() {
		if(tableModel == null) {
			tableModel = new DefaultTableModel();
			tableModel.setColumnIdentifiers(new String[]{
					"Codigo", "Codigo Fornecedor", "Codigo Produto", "Qtd Saida", "Data Saida", "Preço Saida"
			});
			DAO dao = new DAO();
			saidas = dao.saidasList();
			for (Saida saida : saidas) {
				addSaida(saida);
			}
		}
		return tableModel;
	}
	
	private void addSaida(Saida saida) {
		if(saida != null){
			String[] row = new String[] {
					saida.getCodigo().toString(),
					saida.getCodigoFornecedor(),
					saida.getCodigoProduto(),
					saida.getQuantidade(),
					saida.getData(),
					saida.getPrecoSaida()
					
			};
			getTableModel().addRow(row);
		}	
	}

	private Saida getRowInfo(){
		Saida saida = new Saida();
		String defaultValue = "0";
		saida.setCodigo(defaultValue);
		try {
			int row = table.getSelectedRow();
			
			saida.setCodigo((String) table.getValueAt(row, 0));
			saida.setCodigoFornecedor((String) table.getValueAt(row, 1));
			saida.setCodigoProduto((String) table.getValueAt(row, 2));
			saida.setQuantidade((String) table.getValueAt(row, 3));
			saida.setData((String) table.getValueAt(row, 4));
			saida.setPrecoSaida((String) table.getValueAt(row, 5));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saida;
	}
	
	private GridBagConstraints createConstraintsPrototype() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.NONE;
		
		int gap = 4;
		
		gbc.insets = new Insets(gap, gap, gap, gap);
		return gbc;
	}

	public void addSaidasListener(SaidasListener listener) {
		listeners.add(listener);
	}

	public void mostraResultadoPesq(ArrayList<Saida> saidas) {
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[]{
				"Codigo", "Codigo Fornecedor", "Codigo Produto", "Qtd Saida", "Data Saida", "Preço Saida"
		});
		for (Saida saida : saidas) {
			addSaida(saida);
		}
		table.setModel(tableModel);  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		for( int i = 0; i < 6; i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(200); 
		
	}

	public void atualizaTabela() {
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[]{
				"Codigo", "Codigo Fornecedor", "Codigo Produto", "Qtd Saida", "Data Saida", "Preço Saida"
		});
		DAO dao = new DAO();
		saidas = dao.saidasList();
		for (Saida saida : saidas) {
			addSaida(saida);
		}
		table.setModel(tableModel);  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		for( int i = 0; i < 6; i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(200); 
		
	}
	

}
