package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Saida;
import br.edu.univas.si.lab4.projeto.view.DataFrame;
import br.edu.univas.si.lab4.projeto.view.ListFrame;

public class ControllerAlteraSaida {
	
	private Saida saida;
	private DataFrame dataFrame;
	private ListFrame listFrame;
	private Boolean qtdFlag;
	
	public ControllerAlteraSaida(Saida saida, ListFrame listFrame){
		this.saida = saida;
		this.listFrame = listFrame;
		showDataFrame();
	}

	private void showDataFrame() {
		dataFrame = new DataFrame(saida, Modificadores.FRAMESAIDAS);
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
					Saida saida = dataFrame.getSaidaData();
					saida.setCodigo(ControllerAlteraSaida.this.saida.getCodigo());
					new DAO().atualizaSaida(saida);
					listFrame.getSaidaTablePanel().atualizaTabela();
					dataFrame.dispose();
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
