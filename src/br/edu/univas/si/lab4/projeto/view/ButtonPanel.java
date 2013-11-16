package br.edu.univas.si.lab4.projeto.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.univas.si.lab4.projeto.listeners.ButtonListeners;

public class ButtonPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public ArrayList<ButtonListeners> listeners = new ArrayList<ButtonListeners>();
	
	private JButton okButton;
	private JButton cancelButton;
	
	public ButtonPanel(){
		initialize();
	}

	private void initialize() {
		add(getOkButton());
		add(getCancelButton());
		
	}

	private JButton getOkButton() {
		if(okButton == null){
			ImageIcon icon = createImagemIcon("/OK.png");
			okButton = new JButton();
			okButton.setIcon(icon);
			okButton.setText("Ok");
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					if(listeners != null){
						for (ButtonListeners listener : listeners) {
							listener.okPerfomed();
						}
					}
					
				}
			});
		}
		return okButton;
	}


	private JButton getCancelButton() {
		if(cancelButton == null){
			ImageIcon icon = createImagemIcon("/cancel.png");
			cancelButton = new JButton();
			cancelButton.setIcon(icon);
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					if(listeners != null){
						for (ButtonListeners listener : listeners) {
							listener.cancelPerfomed();
						}
					}
					
				}
			});
		}
		return cancelButton;
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

	public void addButtonListener(ButtonListeners listener) {
		listeners.add(listener);
	}

}
