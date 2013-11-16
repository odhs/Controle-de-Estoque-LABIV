package br.edu.univas.si.lab4.projeto.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import br.edu.univas.si.lab4.projeto.listeners.CadastroListener;
import br.edu.univas.si.lab4.projeto.model.Modificadores;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar mainMenuBar;
	
	private JMenu cadastroMenu;
	private JMenu controleMenu;
	
	private JMenuItem entradaMenu;
	private JMenuItem saidaMenu;
	private JMenuItem produtosMenu;
	private JMenuItem fornecedoresMenu;
	private JMenuItem usuariosMenu;
	
	private JToolBar toolBar;
	
	public MenuButtons buttons = new MenuButtons();
	
	private LoginFrame loginFrame;
	
	public Dimension size;
	
	public JDesktopPane desktop;
	
	public Integer modificador;
	
	ArrayList<CadastroListener> listeners = new ArrayList<CadastroListener>();
	
	
	public MainFrame(Integer modificador){
		setTitle("Controle de Estoque");
		this.modificador = modificador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		initialize();
	}

	public void initialize() {
		
		if(modificador != Modificadores.NOTLOG){
			setJMenuBar(getMainMenuBar());
			add(getToolBar(), BorderLayout.PAGE_START);
		}
		getContentPane().add(getDesktopPane());
		
	}
	
	public JDesktopPane getDesktopPane(){
		if(desktop == null){
			desktop = new JDesktopPane();
			size = desktop.getSize();
			desktop.setSize(1200, 700);
		}
		return desktop;
	}

	public JToolBar getToolBar() {
		if(toolBar == null){
			toolBar = new JToolBar();
			toolBar.setBackground(Color.white);
			toolBar.add(buttons.getProdutoButton());
			toolBar.add(buttons.getEntradaButton());
			toolBar.add(buttons.getSaidaButton());
			toolBar.add(buttons.getFornecButton());
			if(modificador == Modificadores.LOGADMIN)
				toolBar.add(buttons.getUsuarioButton());
			toolBar.add(buttons.getGraficoButton());
		}
		return toolBar;
	}
	
	private JMenuBar getMainMenuBar(){
		if(mainMenuBar == null){
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getCadastroMenu());
			mainMenuBar.add(getControleMenu());
		
		}
		return mainMenuBar;
	}
	
	private JMenu getControleMenu(){
		if(controleMenu == null){
			controleMenu = new JMenu();
			controleMenu.setText("Controle E/S");
			controleMenu.add(getEntradaMenu());
			controleMenu.add(getSaidaMenu());
		}
		return controleMenu;
	}
	
	private JMenuItem getEntradaMenu(){
		if(entradaMenu == null){
			entradaMenu = new JMenuItem();
			entradaMenu.setText("Entrada");
			entradaMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(listeners != null){
						for(CadastroListener listener : listeners)
							listener.cadastraEntrada();
					}
				}
			});
		}
		return entradaMenu;
	}
	
	private JMenuItem getSaidaMenu(){
		if(saidaMenu == null){
			saidaMenu = new JMenuItem();
			saidaMenu.setText("Saida");
			saidaMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(listeners != null){
						for(CadastroListener listener : listeners)
							listener.cadastraSaida();
					}
					
				}
			});
		}
		return saidaMenu;
	}
	
	private JMenuItem getProdutosMenu(){
		if(produtosMenu == null){
			produtosMenu = new JMenuItem();
			produtosMenu.setText("Produtos");
			produtosMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(listeners != null){
						for(CadastroListener listener : listeners)
							listener.cadastraProduto();
					}
					
				}
			});
		}
		return produtosMenu;
	}
	
	private JMenuItem getFornecedoresMenu(){
		if(fornecedoresMenu == null){
			fornecedoresMenu = new JMenuItem();
			fornecedoresMenu.setText("Fornecedores");
			fornecedoresMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(listeners != null){
						for(CadastroListener listener : listeners)
							listener.cadastraFornecedor();
					}
					
				}
			});
		}
		return fornecedoresMenu;
	}
	
	private JMenuItem getUsuarioCadastro(){
		if(usuariosMenu == null){
			usuariosMenu = new JMenuItem();
			usuariosMenu.setText("Usuários");
			usuariosMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(listeners != null){
						for(CadastroListener listener : listeners)
							listener.cadastraUsuario();
					}
					
				}
			});
		}
		return usuariosMenu;
	}

	private JMenu getCadastroMenu(){
		if(cadastroMenu == null){
			cadastroMenu = new JMenu();
			cadastroMenu.setText("Cadastro");
			cadastroMenu.add(getProdutosMenu());
			cadastroMenu.add(getFornecedoresMenu());
			if(modificador == Modificadores.LOGADMIN)
				cadastroMenu.add(getUsuarioCadastro());
		}
		return cadastroMenu;
	}
	
	public void addProdutoFrame(ListFrame listFrame){
		add(listFrame);
	}
	
	public LoginFrame getLoginFrame(){
		loginFrame = new LoginFrame(this);
		return loginFrame;
	}

	public void addCadastrListeners(CadastroListener listener) {
		listeners.add(listener);
	}

}
