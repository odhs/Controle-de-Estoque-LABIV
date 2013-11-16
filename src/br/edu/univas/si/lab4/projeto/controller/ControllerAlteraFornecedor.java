package br.edu.univas.si.lab4.projeto.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;
import br.edu.univas.si.lab4.projeto.model.Fornecedor;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.view.DataFrame;
import br.edu.univas.si.lab4.projeto.view.ListFrame;

public class ControllerAlteraFornecedor {
	
	private Fornecedor fornecedor;
	private DataFrame dataFrame;
	private ListFrame listFrame;

	public ControllerAlteraFornecedor(Fornecedor fornecedor, ListFrame listFrame) {
		this.fornecedor = fornecedor;
		this.listFrame = listFrame;
		showDataFrame();
	}
	
	private void showDataFrame() {
		dataFrame = new DataFrame(fornecedor, Modificadores.ADDFORNECFRAME);
		dataFrame.getButtonsPanel().addButtonListener(new ButtonListeners() {
			
			@Override
			public void okPerfomed() {
				
				String nome = dataFrame.getFornecDataPanel().getNomeField().getText();
				
				String endereco = dataFrame.getFornecDataPanel().getEnderecoField().getText();
				
				String bairro = dataFrame.getFornecDataPanel().getBairroField().getText();
				
				String cidade = dataFrame.getFornecDataPanel().getCidadeField().getText();
				
				String cep = dataFrame.getFornecDataPanel().getCepField().getText();
				if(cep.equals("_____-___"))
					cep = "";
				
				String uf = dataFrame.getFornecDataPanel().getUfCombo().getSelectedItem().toString();
				
				String email = dataFrame.getFornecDataPanel().getEmailField().getText();
				
				String telefone =dataFrame.getFornecDataPanel().getTelefoneField().getText();
				if(telefone.equals("(__)____-____"))
					telefone = "";
				
				if(nome.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || cep.isEmpty() || uf.isEmpty() || email.isEmpty() || telefone.isEmpty())
					JOptionPane.showMessageDialog(dataFrame, "Existem campos vazios");
				else{
					Fornecedor fornecedor = dataFrame.getFornecedorData();
					Boolean fornecedorExiste = new DAO().testaFornecedor(fornecedor.getNome());
					if(!fornecedorExiste || nome.equals(ControllerAlteraFornecedor.this.fornecedor.getNome())){
						new DAO().atualizaFornecedor(fornecedor);
						listFrame.getFornecedorTablePanel().atualizaTabela();
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
