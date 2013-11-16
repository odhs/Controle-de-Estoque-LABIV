package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Produto;
import br.edu.univas.si.lab4.projeto.view.DataFrame;

public class ControllerCadProduto {

	private DataFrame dataFrame;
	
	public ControllerCadProduto(){
		showCadProdutoFrame();
	}

	private void showCadProdutoFrame() {
		dataFrame = new DataFrame(Modificadores.ADDPRODUTO);
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
					Produto produto = dataFrame.getProdData2();
					Boolean produtoExiste = new DAO().testaProduto2(produto.getNome());
					
					if(!produtoExiste){
						new DAO().addProduto(produto);
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
