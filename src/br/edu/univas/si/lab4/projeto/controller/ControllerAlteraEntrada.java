package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Entrada;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.view.DataFrame;
import br.edu.univas.si.lab4.projeto.view.ListFrame;

public class ControllerAlteraEntrada {
	
	private Entrada entrada;
	private DataFrame dataFrame;
	private ListFrame listFrame;
	private Boolean qtdFlag;
	
	public ControllerAlteraEntrada(Entrada entrada, ListFrame listFrame){
		this.entrada = entrada;
		this.listFrame = listFrame;
		showDataFrame();
	}

	private void showDataFrame() {
		dataFrame = new DataFrame(entrada, Modificadores.FRAMEENTRADAS);
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
					entrada.setCodigo(ControllerAlteraEntrada.this.entrada.getCodigo());
					new DAO().atualizaEntrada(entrada);
					listFrame.getEntradaTablePanel().atualizaTabela();
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
