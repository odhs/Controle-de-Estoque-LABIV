package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Saida;
import br.edu.univas.si.lab4.projeto.view.DataFrame;

public class ControllerCadSaida {
	
	private DataFrame dataFrame;
	private Boolean qtdFlag;
	
	public ControllerCadSaida(){
		showSaidaFrame();
	}

	private void showSaidaFrame() {
		dataFrame = new DataFrame(Modificadores.ADDSAIDA);
		dataFrame.getButtonsPanel().addButtonListener(new ButtonListeners() {
			
			@Override
			public void okPerfomed() {
				String produto = dataFrame.getSaidaDataPanel().getProdutoField().getText();
				
				String data = dataFrame.getSaidaDataPanel().getCalendarDate();
				if(data.equals("__/__/____"))
					data = "";
				
				String fornecedor = dataFrame.getSaidaDataPanel().getFornecedorCombo().getSelectedItem().toString();
				
				getInt(dataFrame.getSaidaDataPanel().getQtdField().getText());
				
				if(produto.isEmpty() || data.isEmpty() || fornecedor.isEmpty() || qtdFlag)
					JOptionPane.showMessageDialog(dataFrame, "Existem campos vazios ou dados incorretos");
				else{
					Saida saida = dataFrame.getSaidaData();
					Boolean produtoExiste = new DAO().testaProduto(saida.getProduto());
					
					if(produtoExiste){
						Boolean qtdValida = new DAO().testaQuantidadeSaida(saida);
						if(qtdValida){
							new DAO().addSaida(saida);
							dataFrame.dispose();
						}
						else
							JOptionPane.showMessageDialog(dataFrame, "A quantidade de saida não pode\nser maior que a quantidade no estoque!");
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
