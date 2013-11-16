package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Entrada;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.view.DataFrame;

public class ControllerCadEntrada {
	
	private DataFrame dataFrame;
	
	private Boolean qtdFlag;
	
	public ControllerCadEntrada(){
		showEntradaFrame();
	}

	private void showEntradaFrame() {
		dataFrame = new DataFrame(Modificadores.ADDENTRADA);
		dataFrame.getButtonsPanel().addButtonListener(new ButtonListeners() {
			
			@Override
			public void okPerfomed() {
				String produto = dataFrame.getEntradaDataPanel().getProdutoField().getText();
				
				String data = dataFrame.getEntradaDataPanel().getCalendarDate();
				if(data.equals("__/__/____"))
					data = "";
				
				String fornecedor = dataFrame.getEntradaDataPanel().getFornecedorCombo().getSelectedItem().toString();
				
				getInt(dataFrame.getEntradaDataPanel().getQtdField().getText());
				
				if(produto.isEmpty() || data.isEmpty() || fornecedor.isEmpty() || qtdFlag)
					JOptionPane.showMessageDialog(dataFrame, "Existem campos vazios ou dados incorretos");
				else{
					Entrada entrada = dataFrame.getEntradaData();
					Boolean produtoExiste = new DAO().testaProduto(entrada.getProduto());
					
					if(produtoExiste){
						entrada.setQuantidade(dataFrame.getEntradaDataPanel().getQtdField().getText());
						new DAO().addEntrada(entrada);
						dataFrame.dispose();
					}
					else{
						int escolha = JOptionPane.showConfirmDialog(dataFrame, "Produto não cadastrado!\nDeseja cadastra-lo?","",JOptionPane.YES_NO_OPTION);
						if(escolha == JOptionPane.YES_NO_OPTION){
							dataFrame.dispose();
							new ControllerCadProduto();
						}		
					}
				}
				
			}
			
			@Override
			public void cancelPerfomed() {
				dataFrame.dispose();
				
			}
		});
		dataFrame.setVisible(true);
	}
	
	private Integer getInt(String str){
		Integer strInt = null;
		try {
			strInt = Integer.parseInt(str);
			if(strInt < 0)
				qtdFlag = true;
			else
				qtdFlag = false;
		} catch (Exception e) {
			
			qtdFlag = true;
		}
		
		return strInt;
	}
	

}
