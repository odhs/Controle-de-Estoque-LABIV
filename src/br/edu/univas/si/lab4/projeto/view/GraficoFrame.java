package br.edu.univas.si.lab4.projeto.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class GraficoFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	private GraficoTiposPanel graficoTiposPanel;
	private Integer tableWidth;
	private Integer tableHeight;
	
	public GraficoFrame(){
		BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
		ui.getNorthPane().setPreferredSize(new Dimension(0, 0));
		int upperSize = 165;
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - upperSize);
		tableWidth = (Toolkit.getDefaultToolkit().getScreenSize().width / 100) * 78;
		tableHeight = ((Toolkit.getDefaultToolkit().getScreenSize().height - upperSize) / 100) * 70;
		setBorder(null);
		initialize();
	}

	private void initialize() {
		add(getGraficoTiposPanel(), BorderLayout.CENTER);
		
	}

	public GraficoTiposPanel getGraficoTiposPanel() {
		if(graficoTiposPanel == null)
			graficoTiposPanel = new GraficoTiposPanel(tableHeight, tableWidth);
		return graficoTiposPanel;
	}

}
