package br.edu.univas.si.lab4.projeto.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import br.edu.univas.si.lab4.projeto.listeners.GraficosListeners;
import br.edu.univas.si.lab4.projeto.model.DAO;

public class GraficoTiposPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JScrollPane graficoScroll;
	
	private JButton sairButton;
	
	private JLabel titulo;
	
	private GridBagConstraints graficoScrollConstraints;
	private GridBagConstraints sairButtonConstraints;
	private GridBagConstraints tituloConstraints;
	
	private Integer tableHeight;
	private Integer tableWidth;
	
	private ArrayList<GraficosListeners> listeners = new ArrayList<GraficosListeners>();
	
	public GraficoTiposPanel(Integer tableHeight, Integer tableWidth){
		this.tableHeight = tableHeight;
		this.tableWidth = tableWidth;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		add(getGraficoScroll(), getGraficoScrollConstraints());
		add(getSairButton(), getSairButtonConstraints());
		add(getTitulo(), getTituloConstraints());
	}
	
	private JLabel getTitulo(){
		if(titulo == null){
			titulo = new JLabel();
			titulo.setText("Gráfico");
			titulo.setFont(new Font("Castellar", Font.BOLD, 20));
		}
		return titulo;
	}
	
	private GridBagConstraints getTituloConstraints(){
		if(tituloConstraints == null){
			tituloConstraints = createConstraintsPrototype();
			
			tituloConstraints.gridx = 4;
			tituloConstraints.gridy = 0;
			
			tituloConstraints.gridwidth = 2;
			tituloConstraints.anchor = GridBagConstraints.CENTER;
			
			tituloConstraints.ipadx = 30;
		}
		return tituloConstraints;
	}
	
	private JButton getSairButton(){
		if(sairButton == null){
			ImageIcon icon = createImagemIcon("/Exit.png");
			sairButton = new JButton();
			sairButton.setBorderPainted(false);
			sairButton.setToolTipText("Fechar grafico");
			sairButton.setContentAreaFilled(false);
			sairButton.setIcon(icon);
			sairButton.setFocusPainted(false);
			sairButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for(GraficosListeners listener : listeners)
						listener.fecharTelaGrafico();
					
				}
			});
		}
		return sairButton;
	}
	
	private GridBagConstraints getSairButtonConstraints(){
		if(sairButtonConstraints ==null){
			sairButtonConstraints = createConstraintsPrototype();
			
			sairButtonConstraints.gridx = 7;
			sairButtonConstraints.gridy = 1;
		}
		return sairButtonConstraints;
	}
	
	private GridBagConstraints getGraficoScrollConstraints() {
		if(graficoScrollConstraints == null){
			graficoScrollConstraints = createConstraintsPrototype();
			
			graficoScrollConstraints.gridx = 0;
			graficoScrollConstraints.gridy = 2;
			
			graficoScrollConstraints.gridwidth = 6;
			
			graficoScrollConstraints.ipadx = tableWidth;
			graficoScrollConstraints.ipady = tableHeight;
		}
		return graficoScrollConstraints;
	}

	private JScrollPane getGraficoScroll(){
		if(graficoScroll == null){
			graficoScroll = new JScrollPane(getGrafico());
			graficoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			graficoScroll.setHorizontalScrollBarPolicy(JScrollPane .HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		}
		return graficoScroll;
	}
	
	private JPanel getGrafico(){
		ArrayList<String> tipos = new DAO().getProdutosTipos();
		ArrayList<Integer> quantProd = new DAO().getQuantProdutos();
		
		String tituloGrafico = "Divisão do estoque por tipo de produto";
		
		float transparencia = 0.5f;
		
		return pizza3D(tipos, quantProd, tituloGrafico, transparencia, "Static");
	}

	private JPanel pizza3D(ArrayList<String> tipos,ArrayList<Integer> quantProd, String tituloGrafico,float transparencia, String string) {
		DefaultPieDataset data = new DefaultPieDataset();

		for(int i = 0; i < tipos.toArray().length; i++){
			
			data.setValue("" + tipos.get(i).toString(), new Double(quantProd.get(i).toString()));
			
		}

		JFreeChart chart = ChartFactory.createPieChart3D (tituloGrafico, data, true, true, true);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelLinksVisible(true);
		plot.setNoDataMessage("Não existem dados para serem exibidos no gráfico");
		
		plot.setCircular(true);

		plot.setStartAngle(90);
		plot.setDirection(Rotation.CLOCKWISE);

		plot.setForegroundAlpha(transparencia);
		
		float gap = 0.20f;
		plot.setInteriorGap(gap);

		ChartPanel chartPanel = new ChartPanel(chart);

		return chartPanel;
	}
	
	private GridBagConstraints createConstraintsPrototype() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.NONE;
		
		int gap = 4;
		
		gbc.insets = new Insets(gap, gap, gap, gap);
		return gbc;
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

	public void addGraficosListeners(GraficosListeners listener) {
		listeners.add(listener);
	}

}
