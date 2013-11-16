package br.edu.univas.si.lab4.projeto.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import br.edu.univas.si.lab4.projeto.model.Modificadores;

public class ListFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	private ProdutoTablePanel produtoTablePanel;
	private FornecedorTablePanel fornecedorTablePanel;
	private EntradaTablePanel entradaTablePanel;
	private SaidaTablePanel saidaTablePanel;
	private UsuarioTablePanel usuarioTablePanel;
	private Integer modificador;
	private Integer tableWidth;
	private Integer tableHeight;
	
	public ListFrame(Integer frame, MainFrame mainFrame){
		this.modificador = frame;
		BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
		ui.getNorthPane().setPreferredSize(new Dimension(0, 0));
		int upperSize = 165;
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width , Toolkit.getDefaultToolkit().getScreenSize().height - upperSize);
		tableWidth = (Toolkit.getDefaultToolkit().getScreenSize().width / 100) * 78;
		tableHeight = ((Toolkit.getDefaultToolkit().getScreenSize().height - upperSize) / 100) * 70;
		setBorder(null);
		initialize();
		
		
		
	}

	private void initialize() {
		if(modificador == Modificadores.FRAMEPRODUTOS)
			add(getProdutoTablePanel(), BorderLayout.CENTER);
		if(modificador == Modificadores.FRAMEFORNECEDORES)
			add(getFornecedorTablePanel(), BorderLayout.CENTER);
		if(modificador == Modificadores.FRAMEENTRADAS)
			add(getEntradaTablePanel(), BorderLayout.CENTER);
		if(modificador == Modificadores.FRAMESAIDAS)
			add(getSaidaTablePanel(), BorderLayout.CENTER);
		if(modificador == Modificadores.FRAMEUSUARIO)
			add(getUsuarioTablePanel(), BorderLayout.CENTER);
		
	}
	
	public UsuarioTablePanel getUsuarioTablePanel(){
		if(usuarioTablePanel == null)
			usuarioTablePanel = new UsuarioTablePanel(tableHeight,tableWidth);
		return usuarioTablePanel;
	}
	
	public SaidaTablePanel getSaidaTablePanel(){
		if(saidaTablePanel == null)
			saidaTablePanel = new SaidaTablePanel(tableHeight,tableWidth);
		return saidaTablePanel;
	}
	
	public EntradaTablePanel getEntradaTablePanel(){
		if(entradaTablePanel == null)
			entradaTablePanel = new EntradaTablePanel(tableHeight,tableWidth);
		return entradaTablePanel;
	}

	public ProdutoTablePanel getProdutoTablePanel() {
		if(produtoTablePanel == null)
			produtoTablePanel = new ProdutoTablePanel(tableHeight, tableWidth);
		return produtoTablePanel;
	}
	
	public FornecedorTablePanel getFornecedorTablePanel(){
		if(fornecedorTablePanel == null)
			fornecedorTablePanel = new FornecedorTablePanel(tableHeight, tableWidth);
		return fornecedorTablePanel;
	}


}
