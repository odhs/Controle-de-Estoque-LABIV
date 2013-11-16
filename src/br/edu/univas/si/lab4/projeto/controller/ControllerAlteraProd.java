package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Produto;
import br.edu.univas.si.lab4.projeto.view.DataFrame;
import br.edu.univas.si.lab4.projeto.view.ListFrame;

public class ControllerAlteraProd {
	
	private Produto produto;
	private DataFrame dataFrame;
	private ListFrame listFrame;
	
	public ControllerAlteraProd(Produto produto, ListFrame listFrame){
		this.produto = produto;
		this.listFrame = listFrame;
		showDataFrame();
	}

	private void showDataFrame() {
		dataFrame = new DataFrame(produto, Modificadores.FRAMEPRODUTOS);
		dataFrame.getButtonsPanel().addButtonListener(new ButtonListeners() {
			
			@Override
			public void okPerfomed() {
				
				String nome = dataFrame.getProdDataPanel().getNomeField().getText();
				
				String fornecedor = dataFrame.getProdDataPanel().getFornecedorCombo().getSelectedItem().toString();
				
				String tipo = dataFrame.getProdDataPanel().getTipoCombo().getSelectedItem().toString();
				
				String valor_compra = dataFrame.getProdDataPanel().getValorCompraField().getText();
				
				String valor_venda = dataFrame.getProdDataPanel().getValorVendaField().getText();
				
				String descricao = dataFrame.getProdDataPanel().getDescricaoField().getText();
				
				if(nome.isEmpty() || fornecedor.isEmpty() || tipo.isEmpty() || valor_compra.isEmpty() || valor_venda.isEmpty() || descricao.isEmpty())
					JOptionPane.showMessageDialog(dataFrame, "Existem campos vazios");
				else{
					Produto produto = dataFrame.getProdData();
					Boolean produtoExiste = new DAO().testaProduto2(produto.getNome());
					if(!produtoExiste || nome.equals(ControllerAlteraProd.this.produto.getNome())){
						new DAO().atualizaProduto(produto);
						listFrame.getProdutoTablePanel().atualizaTabela();
						dataFrame.dispose();
					}
					else
						JOptionPane.showMessageDialog(dataFrame, "Nome ja existente no banco");
				}
			}
			
			@Override
			public void cancelPerfomed() {
				dataFrame.dispose();
				
			}
		});
		dataFrame.setVisible(true);
	}

}
