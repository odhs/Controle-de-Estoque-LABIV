package br.edu.univas.si.lab4.projeto.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.MenuButtonsListener;

public class MenuButtons {
	
	private JButton produtoButton;
	private JButton fornecedorButton;
	private JButton graficoButton;
	private JButton entradaButton;
	private JButton saidaButton;
	private JButton usuarioButton;
	
	private ArrayList<MenuButtonsListener> listeners = new ArrayList<MenuButtonsListener>();

	public JButton getProdutoButton(){
		if(produtoButton == null){
			ImageIcon icon = createImagemIcon("/Produtos.png");
			produtoButton = new JButton();
			produtoButton.setToolTipText("Mostra estoque");
			produtoButton.setIcon(icon);
			produtoButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					for (MenuButtonsListener listener : listeners) {
						if(listeners != null)
							listener.showProdutos();
					}
					
				}
			});
			
		}
		return produtoButton;
	}
	
	public JButton getFornecButton(){
		if(fornecedorButton == null){
			ImageIcon icon = createImagemIcon("/Fornecedor.png");
			fornecedorButton = new JButton();
			fornecedorButton.setToolTipText("Mostra lista de fornecedores");
			fornecedorButton.setIcon(icon);
			fornecedorButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					for (MenuButtonsListener listener : listeners) {
						if(listeners != null)
							listener.showFornecedores();
					}
					
				}
			});
		}
		return fornecedorButton;
	}
	
	public JButton getGraficoButton(){
		if(graficoButton == null){
			ImageIcon icon = createImagemIcon("/Grafico.png");
			graficoButton = new JButton();
			graficoButton.setToolTipText("Mostra grafico");
			graficoButton.setIcon(icon);
			graficoButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					for (MenuButtonsListener listener : listeners) {
						if(listeners != null)
							listener.showGraficos();
					}
					
				}
			});
			
		}
		return graficoButton;
	}
	
	public JButton getEntradaButton(){
		if(entradaButton == null){
			ImageIcon icon = createImagemIcon("/Box_in.png");
			entradaButton = new JButton();
			entradaButton.setToolTipText("Mostra entradas no estoque");
			entradaButton.setIcon(icon);
			entradaButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					for (MenuButtonsListener listener : listeners) {
						if(listeners != null)
							listener.showEntradas();
					}
					
				}
			});
			
		}
		return entradaButton;
	}
	
	public JButton getSaidaButton(){
		if(saidaButton == null){
			ImageIcon icon = createImagemIcon("/Box_out.png");
			saidaButton = new JButton();
			saidaButton.setToolTipText("Mostra saidas no estoque");
			saidaButton.setIcon(icon);
			saidaButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					for (MenuButtonsListener listener : listeners) {
						if(listeners != null)
							listener.showSaidas();
					}
					
				}
			});
			
		}
		return saidaButton;
	}
	
	public JButton getUsuarioButton(){
		if(usuarioButton == null){
			ImageIcon icon = createImagemIcon("/User.png");
			usuarioButton = new JButton();
			usuarioButton.setToolTipText("Mostra usuários");
			usuarioButton.setIcon(icon);
			usuarioButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					for (MenuButtonsListener listener : listeners) {
						if(listeners != null)
							listener.showUsuarios();
					}
					
				}
			});
			
		}
		return usuarioButton;
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
	
	public void addMenuButtonsListener(MenuButtonsListener listener){
		listeners.add(listener);
	}
}
