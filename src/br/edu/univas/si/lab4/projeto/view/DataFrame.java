package br.edu.univas.si.lab4.projeto.view;

import java.awt.BorderLayout;
import javax.swing.JDialog;

import br.edu.univas.si.lab4.projeto.model.Entrada;
import br.edu.univas.si.lab4.projeto.model.Fornecedor;
import br.edu.univas.si.lab4.projeto.model.Modificadores;
import br.edu.univas.si.lab4.projeto.model.Produto;
import br.edu.univas.si.lab4.projeto.model.Saida;
import br.edu.univas.si.lab4.projeto.model.Usuario;

public class DataFrame extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private Fornecedor fornecedor;
	private Entrada entrada;
	private Saida saida;
	private Usuario usuario;
	
	private ProdDataPanel prodDataPanel;
	private FornecDataPanel fornecDataPanel;
	private EntradaDataPanel entradaDataPanel;
	private SaidaDataPanel saidaDataPanel;
	private UsuarioDataPanel usuarioDataPanel;
	private CadUsuarioDataPanel cadUsuarioDataPanel;
	private ButtonPanel buttonPanel;
	private Integer modificador;
	
	public DataFrame(Produto produto, Integer modificador){
		this.produto = produto;
		this.modificador = modificador;
		setTitle("Produto");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initialize();
		setResizable(false);
		setModal(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	public DataFrame(Fornecedor fornecedor, Integer modificador){
		this.fornecedor = fornecedor;
		this.modificador = modificador;
		setTitle("Fornecedor");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initialize();
		setResizable(false);
		setModal(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	public DataFrame(Integer modificador){
		this.modificador = modificador;
		if(this.modificador == Modificadores.ADDENTRADA)
			setTitle("Cadastro de entrada no estoque");
		if(this.modificador == Modificadores.ADDPRODUTO)
			setTitle("Cadastro de produto");
		if(this.modificador == Modificadores.ADDSAIDA)
			setTitle("Baixas no estoque");
		if(this.modificador == Modificadores.ADDFORNECEDOR)
			setTitle("Cadastro de fornecedor");
		if(this.modificador == Modificadores.ADDUSUARIO)
			setTitle("Cadastro de usuário");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initialize();
		setResizable(false);
		setModal(true);
		pack();
		setLocationRelativeTo(null);
	}

	public DataFrame(Entrada entrada, Integer modificador) {
		this.entrada = entrada;
		this.modificador = modificador;
		setTitle("Entrada");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initialize();
		setResizable(false);
		setModal(true);
		pack();
		setLocationRelativeTo(null);
	}

	public DataFrame(Saida saida, Integer modificador) {
		this.saida = saida;
		this.modificador = modificador;
		setTitle("Saida");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initialize();
		setResizable(false);
		setModal(true);
		pack();
		setLocationRelativeTo(null);
	}

	public DataFrame(Usuario usuario, Integer modificador) {
		this.usuario = usuario;
		this.modificador = modificador;
		setTitle("Usuário");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initialize();
		setResizable(false);
		setModal(true);
		pack();
		setLocationRelativeTo(null);
	}

	private void initialize() {
		if(modificador == Modificadores.FRAMEPRODUTOS || modificador == Modificadores.ADDPRODUTO)
			add(getProdDataPanel(), BorderLayout.CENTER);
		else{
			if(modificador == Modificadores.ADDFORNECFRAME || modificador == Modificadores.ADDFORNECEDOR)
				add(getFornecDataPanel(), BorderLayout.CENTER);
			else{
				if(modificador == Modificadores.ADDENTRADA || modificador == Modificadores.FRAMEENTRADAS)
					add(getEntradaDataPanel(), BorderLayout.CENTER);
				else{
					if(modificador == Modificadores.FRAMESAIDAS || modificador == Modificadores.ADDSAIDA)
						add(getSaidaDataPanel(), BorderLayout.CENTER);
					else{
						if(modificador == Modificadores.FRAMEUSUARIO)
							add(getUsuarioDataPanel(), BorderLayout.CENTER);
						else{
							if(modificador == Modificadores.ADDUSUARIO)
								add(getCadUsuarioDataPanel(), BorderLayout.CENTER);
						}
					}
				}
			}
		}
		add(getButtonsPanel(), BorderLayout.SOUTH);
		
	}
	
	public CadUsuarioDataPanel getCadUsuarioDataPanel(){
		if(cadUsuarioDataPanel == null)
			cadUsuarioDataPanel = new CadUsuarioDataPanel();
		return cadUsuarioDataPanel;
	}
	
	public UsuarioDataPanel getUsuarioDataPanel(){
		if(usuarioDataPanel == null)
			usuarioDataPanel = new UsuarioDataPanel(usuario, modificador);
		return usuarioDataPanel;
	}
	
	public SaidaDataPanel getSaidaDataPanel(){
		if(saidaDataPanel == null)
			saidaDataPanel = new SaidaDataPanel(saida, modificador);
		return saidaDataPanel;
	}
	
	public EntradaDataPanel getEntradaDataPanel(){
		if(entradaDataPanel == null){
			entradaDataPanel = new EntradaDataPanel(entrada, modificador);
		}
		return entradaDataPanel;
	}

	public FornecDataPanel getFornecDataPanel() {
		if(fornecDataPanel == null){
			fornecDataPanel = new FornecDataPanel(fornecedor, modificador);
		}
		return fornecDataPanel;
	}

	public ProdDataPanel getProdDataPanel() {
		if(prodDataPanel == null){
			prodDataPanel = new ProdDataPanel(produto, modificador);
		}
		return prodDataPanel;
	}

	public ButtonPanel getButtonsPanel() {
		if(buttonPanel == null){
			buttonPanel = new ButtonPanel();
		}
		return buttonPanel;
	}
	
	public Produto getProdData(){
		Produto produto = new Produto();
		
		produto.setNome(prodDataPanel.getNomeField().getText());
		produto.setFornecedor(prodDataPanel.getFornecedorCombo().getSelectedItem().toString());
		produto.setTipo(prodDataPanel.getTipoCombo().getSelectedItem().toString());
		produto.setValor_compra(prodDataPanel.getValorCompraField().getText());
		produto.setValor_venda(prodDataPanel.getValorVendaField().getText());
		produto.setDescricao(prodDataPanel.getDescricaoField().getText());
		produto.setCodigo(prodDataPanel.produto.getCodigo());
		
		return produto;
	}

	public Fornecedor getFornecedorData() {
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setNome(fornecDataPanel.getNomeField().getText());
		fornecedor.setBairro(fornecDataPanel.getBairroField().getText());
		fornecedor.setCep(fornecDataPanel.getCepField().getText());
		fornecedor.setCidade(fornecDataPanel.getCidadeField().getText());
		fornecedor.setEmail(fornecDataPanel.getEmailField().getText());
		fornecedor.setEndereco(fornecDataPanel.getEnderecoField().getText());
		fornecedor.setEstado(fornecDataPanel.getUfCombo().getSelectedItem().toString());
		fornecedor.setTelefone(fornecDataPanel.getTelefoneField().getText());
		fornecedor.setCodigo(fornecDataPanel.fornecedor.getCodigo());
		
		return fornecedor;
	}

	public Entrada getEntradaData() {
		Entrada entrada = new Entrada();
		
		entrada.setProduto(entradaDataPanel.getProdutoField().getText());
		entrada.setData(entradaDataPanel.getCalendarDate());
		entrada.setFornecedor(entradaDataPanel.getFornecedorCombo().getSelectedItem().toString());
		entrada.setQuantidade(entradaDataPanel.getQtdField().getText());
		
		return entrada;
	}
	
	public Saida getSaidaData(){
		Saida saida = new Saida();
		
		saida.setProduto(saidaDataPanel.getProdutoField().getText());
		saida.setData(saidaDataPanel.getCalendarDate());
		saida.setFornecedor(saidaDataPanel.getFornecedorCombo().getSelectedItem().toString());
		saida.setQuantidade(saidaDataPanel.getQtdField().getText());
		
		return saida;
	}

	public Usuario getUsuarioData(Usuario usuario) {
		
		usuario.setNome(usuarioDataPanel.getNomeField().getText());
		usuario.setPermissao(usuarioDataPanel.getPermissaoCombo().getSelectedItem().toString());
		usuario.setPassword(new String(usuarioDataPanel.getSenhaField().getPassword()));
		
		return usuario;
	}

	public Fornecedor getFornecedorData2() {
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setNome(fornecDataPanel.getNomeField().getText());
		fornecedor.setBairro(fornecDataPanel.getBairroField().getText());
		fornecedor.setCep(fornecDataPanel.getCepField().getText());
		fornecedor.setCidade(fornecDataPanel.getCidadeField().getText());
		fornecedor.setEmail(fornecDataPanel.getEmailField().getText());
		fornecedor.setEndereco(fornecDataPanel.getEnderecoField().getText());
		fornecedor.setEstado(fornecDataPanel.getUfCombo().getSelectedItem().toString());
		fornecedor.setTelefone(fornecDataPanel.getTelefoneField().getText());
		
		return fornecedor;
	}
	
	public Produto getProdData2(){
		Produto produto = new Produto();
		
		produto.setNome(prodDataPanel.getNomeField().getText());
		produto.setFornecedor(prodDataPanel.getFornecedorCombo().getSelectedItem().toString());
		produto.setTipo(prodDataPanel.getTipoCombo().getSelectedItem().toString());
		produto.setValor_compra(prodDataPanel.getValorCompraField().getText());
		produto.setValor_venda(prodDataPanel.getValorVendaField().getText());
		produto.setDescricao(prodDataPanel.getDescricaoField().getText());
		
		return produto;
	}

	public Usuario getUsuarioData2() {
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(cadUsuarioDataPanel.getNomeField().getText());
		usuario.setPermissao(cadUsuarioDataPanel.getPermissaoCombo().getSelectedItem().toString());
		usuario.setPassword(new String(cadUsuarioDataPanel.getSenhaField().getPassword()));
		usuario.setUserLogin(cadUsuarioDataPanel.getLoginField().getText());
		
		return usuario;
	}

}
