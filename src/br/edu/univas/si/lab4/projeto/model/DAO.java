package br.edu.univas.si.lab4.projeto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class DAO {
	
	private Connection connection;
	private PreparedStatement stm;

	public Boolean testaUsuario(Usuario loginData){
		String sql = "";
		String userOK = null;
		String userPass = null;
		
		sql = "SELECT senha FROM usuarios WHERE login = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, loginData.getUserLogin());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				userOK = "";
				userPass = rs.getString("senha");
			}
			
			stm.close();
			connection.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userOK == null)
			return false;
		else
			if(loginData.getPassword().equals(userPass))
				return true;
			else
				return false;
	}
	
	public ArrayList<Produto>  produtosList(){
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "";
		
		sql = "SELECT * FROM produtos ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			Integer i;
			
			while(rs.next()){
				
				Produto produto = new Produto();
				
				i = 1;
				
				produto.setCodigo(rs.getString(i++));
				produto.setNome(rs.getString(i++));
				produto.setFornecedor(rs.getString(i++));
				produto.setQuantidade(rs.getString(i++));
				produto.setValor_compra(rs.getString(i++));
				produto.setValor_venda(rs.getString(i++));
				produto.setTipo(rs.getString(i++));
				produto.setDescricao(rs.getString(i++));
				
		
				produtos.add(produto);
				
				
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
		
	}

	public String[] getFornecedorList() {
		
		String sql = "";
		
		int i = 0;
		
		sql = "SELECT * FROM fornecedores";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] list = new String[i+1];
		
		list[0] = "";
		
		i = 1;
		
		sql = "SELECT nome FROM fornecedores";
		
		try {
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				list[i++] = rs.getString("nome");
			
			stm.close();
			connection.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void atualizaProduto(Produto produto){
		String sql = "";
		
		sql = "UPDATE produtos " +
			  "SET nome = ? , " +
			  "fornecedor = ? , " +
			  "valor_compra = ? , " +
			  "valor_venda = ? , " +
			  "tipo = ? , " +
			  "descricao = ? " +
			  "WHERE codigo = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getFornecedor());
			stm.setString(3, produto.getValor_compra());
			stm.setString(4, produto.getValor_venda());
			stm.setString(5, produto.getTipo());
			stm.setString(6, produto.getDescricao());
			stm.setInt(7, getInt(produto.getCodigo()));
			
			stm.executeUpdate();
			
			stm.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private Integer getInt(String str){
		Integer strInt = null;
		try {
			strInt = Integer.parseInt(str);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Digite um numero inteiro por favor!");
		}
		
		return strInt;
	}


	public ArrayList<Produto> pesquisaProduto(String campoPesq, String pesqString) {
		String sql = "";
		String pesq = pesqString;
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		if(campoPesq.equals("Codigo"))
			sql = "SELECT * FROM produtos WHERE codigo = ?";
		if(campoPesq.equals("Nome")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM produtos WHERE nome LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Fornecedor")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM produtos WHERE fornecedor LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Quantidade")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM produtos WHERE quantidade LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Valor de compra")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM produtos WHERE valor_compra LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Valor de venda")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM produtos WHERE valor_venda LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Tipo")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM produtos WHERE tipo LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Descrição")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM produtos WHERE descricao LIKE ? ORDER BY codigo";
		}
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			if(campoPesq.equals("Codigo")){
				try {
					stm.setInt(1, getInt(pesq));
				} catch (Exception e) {
					int defaultValue = 0;
					stm.setInt(1, defaultValue);
				}
			}
			else
				stm.setString(1, pesq);
			
			ResultSet rs = stm.executeQuery();
			
			Integer i;
			
			while(rs.next()){
				
				Produto produto = new Produto();
				
				i = 1;
				
				produto.setCodigo(rs.getString(i++));
				produto.setNome(rs.getString(i++));
				produto.setFornecedor(rs.getString(i++));
				produto.setQuantidade(rs.getString(i++));
				produto.setValor_compra(rs.getString(i++));
				produto.setValor_venda(rs.getString(i++));
				produto.setTipo(rs.getString(i++));
				produto.setDescricao(rs.getString(i++));
				
		
				produtos.add(produto);
				
			}
			rs.close();
			stm.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
		
	}

	public void deletaProduto(Produto produto) {
		String sql = "";
		
		sql = "DELETE FROM produtos WHERE codigo = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(produto.getCodigo()));
			
			stm.executeUpdate();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Fornecedor> fornecedoresList() {
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		String sql = "";
		
		sql = "SELECT * FROM fornecedores ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			Integer i;
			
			while(rs.next()){
				
				Fornecedor fornecedor = new Fornecedor();
				
				i = 1;
				
				fornecedor.setCodigo(rs.getString(i++));
				fornecedor.setNome(rs.getString(i++));
				fornecedor.setCidade(rs.getString(i++));
				fornecedor.setEstado(rs.getString(i++));
				fornecedor.setCep(rs.getString(i++));
				fornecedor.setEndereco(rs.getString(i++));
				fornecedor.setBairro(rs.getString(i++));
				fornecedor.setEmail(rs.getString(i++));
				fornecedor.setTelefone(rs.getString(i++));
				
		
				fornecedores.add(fornecedor);
				
				
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fornecedores;
	}

	public ArrayList<Fornecedor> pesquisaFornecedores(String campoPesq, String pesqString) {
		String sql = "";
		String pesq = pesqString;
		
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		if(campoPesq.equals("Codigo"))
			sql = "SELECT * FROM fornecedores WHERE codigo = ?";
		if(campoPesq.equals("Nome")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE nome LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Endereço")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE endereco LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Bairro")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE bairro LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Cidade")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE cidade LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Estado")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE uf LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("CEP")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE cep LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("E-mail")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE email LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Telefone")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM fornecedores WHERE telefone LIKE ? ORDER BY codigo";
		}
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			if(campoPesq.equals("Codigo")){
				try {
					stm.setInt(1, getInt(pesq));
				} catch (Exception e) {
					int defaultValue = 0;
					stm.setInt(1, defaultValue);
				}
			}
			else
				stm.setString(1, pesq);
			
			ResultSet rs = stm.executeQuery();
			
			Integer i;
			
			while(rs.next()){
				
				Fornecedor fornecedor = new Fornecedor();
				
				i = 1;
				
				fornecedor.setCodigo(rs.getString(i++));
				fornecedor.setNome(rs.getString(i++));
				fornecedor.setCidade(rs.getString(i++));
				fornecedor.setEstado(rs.getString(i++));
				fornecedor.setCep(rs.getString(i++));
				fornecedor.setEndereco(rs.getString(i++));
				fornecedor.setBairro(rs.getString(i++));
				fornecedor.setEmail(rs.getString(i++));
				fornecedor.setTelefone(rs.getString(i++));
				
		
				fornecedores.add(fornecedor);
				
			}
			rs.close();
			stm.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fornecedores;
	}

	public void deletaFornecedor(Fornecedor fornecedor) {
		String sql = "";
		
		sql = "DELETE FROM fornecedores WHERE codigo = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(fornecedor.getCodigo()));
			
			stm.executeUpdate();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<String> getProdutosTipos() {
		ArrayList<String> listaTipos = new ArrayList<String>();
		
		String sql = "";
		
		sql = "SELECT * FROM produtos";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			Integer bebidaQuant = 0;
			Integer alimenticioQuant = 0;
			Integer friosQuant = 0;
			Integer hortifrutiQuant = 0;
			Integer laticiniosQuant = 0;
			
			while(rs.next()){
				if(rs.getString("tipo").equals("Bebida")){
					bebidaQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Alimenticio")){
					alimenticioQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Frios")){
					friosQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Horti Fruti")){
					hortifrutiQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Laticinios")){
					laticiniosQuant += getInt(rs.getString("quantidade"));
				}
			}
			
			if(bebidaQuant != 0)
				listaTipos.add("Bebida");
			
			if(alimenticioQuant != 0)
				listaTipos.add("Alimenticio");
			
			if(friosQuant != 0)
				listaTipos.add("Frios");
			
			if(hortifrutiQuant != 0)
				listaTipos.add("Horti Fruti");
			
			if(laticiniosQuant != 0)
				listaTipos.add("Laticinios");
			
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTipos;
	}

	public ArrayList<Integer> getQuantProdutos() {
		ArrayList<Integer> quantProd = new ArrayList<Integer>();
		
		String sql = "";
		
		sql = "SELECT * FROM produtos";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			Integer bebidaQuant = 0;
			Integer alimenticioQuant = 0;
			Integer friosQuant = 0;
			Integer hortifrutiQuant = 0;
			Integer laticiniosQuant = 0;
			
			while(rs.next()){
				if(rs.getString("tipo").equals("Bebida")){
					bebidaQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Alimenticio")){
					alimenticioQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Frios")){
					friosQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Horti Fruti")){
					hortifrutiQuant += getInt(rs.getString("quantidade"));
				}
				
				if(rs.getString("tipo").equals("Laticinios")){
					laticiniosQuant += getInt(rs.getString("quantidade"));
				}
			}
			
			if(bebidaQuant != 0)
				quantProd.add(bebidaQuant);
			
			if(alimenticioQuant != 0)
				quantProd.add(alimenticioQuant);
			
			if(friosQuant != 0)
				quantProd.add(friosQuant);
			
			if(hortifrutiQuant != 0)
				quantProd.add(hortifrutiQuant);
			
			if(laticiniosQuant != 0)
				quantProd.add(laticiniosQuant);
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quantProd;
	}

	public void atualizaFornecedor(Fornecedor fornecedor) {
		String sql = "";
		
		sql = "UPDATE fornecedores " +
			  "SET nome = ? , " +
			  "cidade = ? , " +
			  "uf = ? , " +
			  "cep = ? , " +
			  "endereco = ? , " +
			  "bairro = ? , " +
			  "email = ? , " +
			  "telefone = ? " +
			  "WHERE codigo = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, fornecedor.getNome());
			stm.setString(2, fornecedor.getCidade());
			stm.setString(3, fornecedor.getEstado());
			stm.setString(4, fornecedor.getCep());
			stm.setString(5, fornecedor.getEndereco());
			stm.setString(6, fornecedor.getBairro());
			stm.setString(7, fornecedor.getEmail());
			stm.setString(8, fornecedor.getTelefone());
			stm.setInt(9, getInt(fornecedor.getCodigo()));
			
			stm.executeUpdate();
			
			stm.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Boolean verificaPermissao(Usuario loginData) {
		String sql = "";
		Boolean isAdmin = false;
		Integer permissao = null;
		Integer admin = 100;
		
		sql = "SELECT permissao FROM usuarios WHERE login = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, loginData.getUserLogin());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				permissao = rs.getInt("permissao");
			}
			
			if(permissao == admin)
				isAdmin = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdmin;
	}

	public Boolean testaProduto(String produto) {
		String sql = "";
		
		sql = "SELECT nome FROM produtos";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				if(rs.getString("nome").equals(produto))
					return true;
			}
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addEntrada(Entrada entrada) {
		String sql = "";
		String produto = entrada.getProduto();
		String fornecedor = entrada.getFornecedor();
		Integer qtd = getInt(entrada.getQuantidade());
		Integer codigo_produto = null, codigo_fornecedor = null;
		Float precoEntrada = null;
		Float valorCompra = null;
		
		sql = "SELECT valor_compra, codigo FROM produtos WHERE nome = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, produto);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				valorCompra = getFloat(rs.getString("valor_compra"));
				codigo_produto = rs.getInt("codigo");
			}
			
			precoEntrada = valorCompra * qtd;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "SELECT codigo FROM fornecedores WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, fornecedor);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				codigo_fornecedor = rs.getInt("codigo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "INSERT INTO estoque (codigo_fornecedor, codigo_produto, qtd_entrada, data_entrada, preco_entrada, data_saida, qtd_saida, preco_saida) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, codigo_fornecedor);
			stm.setInt(2, codigo_produto);
			stm.setInt(3, qtd);
			stm.setString(4, entrada.getData());
			stm.setFloat(5, precoEntrada);
			stm.setString(6, "null");
			stm.setInt(7, 0);
			stm.setString(8, "null");
			
			stm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Integer qtdProdutos = 0;
		
		sql = "SELECT quantidade FROM produtos WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, produto);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				qtdProdutos = getInt(rs.getString("quantidade"));
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		qtdProdutos += qtd;
		
		sql = "UPDATE produtos " +
			  "SET quantidade = ? " +
			  "WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, qtdProdutos.toString());
			stm.setString(2, produto);
			
			stm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Float getFloat(String strFloat){
		Float floatNumber = null;
		
		try {
			
			floatNumber = Float.parseFloat(strFloat);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return floatNumber;
	}

	public ArrayList<Entrada> entradasList() {
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();
		
		String sql = "";
		
		sql = "SELECT * FROM estoque WHERE data_saida LIKE 'null' ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				Entrada entrada = new Entrada();
				
				
				entrada.setCodigo(rs.getString("codigo"));
				entrada.setCodigoFornecedor(rs.getString("codigo_fornecedor"));
				entrada.setCodigoProduto(rs.getString("codigo_produto"));
				entrada.setData(rs.getString("data_entrada"));
				entrada.setQuantidade(rs.getString("qtd_entrada"));
				entrada.setPrecoEntrada(rs.getString("preco_entrada"));
				
				
		
				entradas.add(entrada);
				
				
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entradas;
	}

	public String getProdutoName(Entrada entrada) {
		String sql = "";
		
		String produto = null;
		
		sql = "SELECT nome FROM produtos WHERE codigo = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(entrada.getCodigoProduto()));
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				produto = rs.getString("nome");
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produto;
	}

	public String getFornecedorName(Entrada entrada) {
        String sql = "";
		
		String fornecedor = null;
		
		sql = "SELECT nome FROM fornecedores WHERE codigo = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(entrada.getCodigoFornecedor()));
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				fornecedor = rs.getString("nome");
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fornecedor;
	}

	public void atualizaEntrada(Entrada entrada) {
		String sql = "";
		
		Integer codProduto = null;
		Integer codFornecedor = null;
		
		Integer qtd = getInt(entrada.getQuantidade());
		
		Float precoEntrada;
		Float valorCompra = null;
		
		sql = "SELECT codigo FROM produtos WHERE nome = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, entrada.getProduto());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				codProduto = rs.getInt("codigo");
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "SELECT codigo FROM fornecedores WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			stm.setString(1, entrada.getFornecedor());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				codFornecedor = rs.getInt("codigo");
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "SELECT * FROM produtos WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			stm.setString(1, entrada.getProduto());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				valorCompra = getFloat(rs.getString("valor_compra"));
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		precoEntrada = valorCompra * qtd;
		
		sql = "UPDATE estoque " +
			  "SET codigo_fornecedor = ? ," +
			  "codigo_produto = ? ," +
			  "data_entrada = ? ," +
			  "preco_entrada = ? ," +
			  "qtd_entrada = ? " +
			  "WHERE codigo = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, codFornecedor);
			stm.setInt(2, codProduto);
			stm.setString(3, entrada.getData());
			stm.setString(4, precoEntrada.toString());
			stm.setInt(5, qtd);
			stm.setInt(6, getInt(entrada.getCodigo()));
			
			stm.executeUpdate();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Entrada> pesquisaEntradas(String campoPesq, String pesqString) {
		String sql = "";
		String pesq = pesqString;
	
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();
		
		if(campoPesq.equals("Codigo"))
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_entrada, qtd_entrada, preco_entrada FROM estoque WHERE codigo = ? AND data_saida LIKE 'null'";
		if(campoPesq.equals("Codigo Fornecedor")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_entrada, qtd_entrada, preco_entrada FROM estoque WHERE codigo_fornecedor = ? AND data_saida LIKE 'null'";
		}
		if(campoPesq.equals("Codigo Produto")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_entrada, qtd_entrada, preco_entrada FROM estoque WHERE codigo_produto = ? AND data_saida LIKE 'null'";
		}
		if(campoPesq.equals("Quantidade Entrada")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_entrada, qtd_entrada, preco_entrada FROM estoque WHERE qtd_entrada = ? AND data_saida LIKE 'null'";
		}
		if(campoPesq.equals("Data Entrada")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_entrada, qtd_entrada, preco_entrada FROM estoque WHERE data_entrada = ? AND data_saida LIKE 'null'";
		}
		if(campoPesq.equals("Preço Entrada")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_entrada, qtd_entrada, preco_entrada FROM estoque WHERE preco_entrada = ? AND data_saida LIKE 'null'";
		}
	
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			if(campoPesq.equals("Codigo") || campoPesq.equals("Quantidade Entrada") || campoPesq.equals("Codigo Fornecedor") || campoPesq.equals("Codigo Produto")){
				try {
					stm.setInt(1, getInt(pesq));
				} catch (Exception e) {
					int defaultValue = 0;
					stm.setInt(1, defaultValue);
				}
			}
			else
				stm.setString(1, pesq);
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				Entrada entrada = new Entrada();
				
				
				entrada.setCodigo(rs.getString("codigo"));
				entrada.setCodigoFornecedor(rs.getString("codigo_fornecedor"));
				entrada.setCodigoProduto(rs.getString("codigo_produto"));
				entrada.setData(rs.getString("data_entrada"));
				entrada.setQuantidade(rs.getString("qtd_entrada"));
				entrada.setPrecoEntrada(rs.getString("preco_entrada"));
				
		
				entradas.add(entrada);
				
			}
			rs.close();
			stm.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entradas;
	}

	public void deletaEntrada(Entrada entrada) {
		String sql = "";
		
		sql = "DELETE FROM estoque WHERE codigo = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(entrada.getCodigo()));
			
			stm.executeUpdate();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Saida> saidasList() {
		ArrayList<Saida> saidas = new ArrayList<Saida>();
		
		String sql = "";
		
		sql = "SELECT * FROM estoque WHERE data_entrada LIKE 'null' ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				Saida saida = new Saida();
				
				
				saida.setCodigo(rs.getString("codigo"));
				saida.setCodigoFornecedor(rs.getString("codigo_fornecedor"));
				saida.setCodigoProduto(rs.getString("codigo_produto"));
				saida.setData(rs.getString("data_saida"));
				saida.setQuantidade(rs.getString("qtd_saida"));
				saida.setPrecoSaida(rs.getString("preco_saida"));
				
				
		
				saidas.add(saida);
				
				
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saidas;
	}

	public String getFornecedorName2(Saida saida) {
		String sql = "";
		
		String fornecedor = null;
		
		sql = "SELECT nome FROM fornecedores WHERE codigo = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(saida.getCodigoFornecedor()));
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				fornecedor = rs.getString("nome");
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fornecedor;
	}

	public String getProdutoName2(Saida saida) {
		String sql = "";
		
		String produto = null;
		
		sql = "SELECT nome FROM produtos WHERE codigo = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(saida.getCodigoProduto()));
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				produto = rs.getString("nome");
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produto;
	}

	public void atualizaSaida(Saida saida) {
		String sql = "";
		
		Integer codProduto = null;
		Integer codFornecedor = null;
		
		Integer qtd = getInt(saida.getQuantidade());
		
		Float precoSaida;
		Float valorVenda = null;
		
		sql = "SELECT codigo FROM produtos WHERE nome = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, saida.getProduto());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				codProduto = rs.getInt("codigo");
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "SELECT codigo FROM fornecedores WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			stm.setString(1, saida.getFornecedor());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				codFornecedor = rs.getInt("codigo");
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "SELECT * FROM produtos WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			stm.setString(1, saida.getProduto());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				valorVenda = getFloat(rs.getString("valor_venda"));
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		precoSaida = valorVenda * qtd;
		
		sql = "UPDATE estoque " +
			  "SET codigo_fornecedor = ? ," +
			  "codigo_produto = ? ," +
			  "data_saida = ? ," +
			  "preco_saida = ? ," +
			  "qtd_saida = ? " +
			  "WHERE codigo = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, codFornecedor);
			stm.setInt(2, codProduto);
			stm.setString(3, saida.getData());
			stm.setString(4, precoSaida.toString());
			stm.setInt(5, qtd);
			stm.setInt(6, getInt(saida.getCodigo()));
			
			stm.executeUpdate();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Saida> pesquisaSaidas(String campoPesq, String pesqString) {
		String sql = "";
		String pesq = pesqString;
	
		ArrayList<Saida> saidas = new ArrayList<Saida>();
		
		if(campoPesq.equals("Codigo"))
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_saida, qtd_saida, preco_saida FROM estoque WHERE codigo = ? AND data_entrada LIKE 'null'";
		if(campoPesq.equals("Codigo Fornecedor")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_saida, qtd_saida, preco_saida FROM estoque WHERE codigo_fornecedor = ? AND data_entrada LIKE 'null'";
		}
		if(campoPesq.equals("Codigo Produto")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_saida, qtd_saida, preco_saida FROM estoque WHERE codigo_produto = ? AND data_entrada LIKE 'null'";
		}
		if(campoPesq.equals("Quantidade Saida")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_saida, qtd_saida, preco_saida FROM estoque WHERE qtd_saida = ? AND data_entrada LIKE 'null'";
		}
		if(campoPesq.equals("Data Saida")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_saida, qtd_saida, preco_saida FROM estoque WHERE data_saida = ? AND data_entrada LIKE 'null'";
		}
		if(campoPesq.equals("Preço Saida")){
			sql = "SELECT codigo, codigo_fornecedor, codigo_produto, data_saida, qtd_saida, preco_saida FROM estoque WHERE preco_saida = ? AND data_entrada LIKE 'null'";
		}
	
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			if(campoPesq.equals("Codigo") || campoPesq.equals("Quantidade Saida") || campoPesq.equals("Codigo Fornecedor") || campoPesq.equals("Codigo Produto")){
				try {
					stm.setInt(1, getInt(pesq));
				} catch (Exception e) {
					int defaultValue = 0;
					stm.setInt(1, defaultValue);
				}
			}
			else
				stm.setString(1, pesq);
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				Saida saida = new Saida();
				
				
				saida.setCodigo(rs.getString("codigo"));
				saida.setCodigoFornecedor(rs.getString("codigo_fornecedor"));
				saida.setCodigoProduto(rs.getString("codigo_produto"));
				saida.setData(rs.getString("data_saida"));
				saida.setQuantidade(rs.getString("qtd_saida"));
				saida.setPrecoSaida(rs.getString("preco_saida"));
				
		
				saidas.add(saida);
				
			}
			rs.close();
			stm.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saidas;
	}

	public void deletaSaida(Saida saida) {
		String sql = "";
		
		sql = "DELETE FROM estoque WHERE codigo = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(saida.getCodigo()));
			
			stm.executeUpdate();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public ArrayList<Usuario> usuariosList() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		String sql = "";
		
		sql = "SELECT * FROM usuarios ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				Usuario usuario = new Usuario();
				
				usuario.setCodigo(rs.getString("codigo"));
				usuario.setPassword(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setUserLogin(rs.getString("login"));
				if(rs.getInt("permissao") == 100)
					usuario.setPermissao("Administrador");
				else
					usuario.setPermissao("Comum");
				
				usuarios.add(usuario);
				
				
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public String getUserPassword(Usuario user) {
		String sql = "";
		
		String senha = null;
		
		sql = "SELECT * FROM usuarios WHERE login = ? ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, user.getUserLogin());
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				senha = rs.getString("senha");
					
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return senha;
	}

	public Boolean verificaNomeUser(String nome) {
		String sql = "";
		
		sql = "SELECT * FROM usuarios ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				if(rs.getString("nome").equals(nome))
					return true;
					
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void atualizaUsuario(Usuario usuario) {
		String sql = "";
		
		sql = "UPDATE usuarios " +
			  "SET nome = ?, " +
			  "permissao = ?, "+
			  "senha = ? " +
			  "WHERE login = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, usuario.getNome());
			if(usuario.getPermissao().equals("Administrador"))
				stm.setInt(2, 100);
			else
				stm.setInt(2, 0);
			
			stm.setString(3, usuario.getPassword());
			
			stm.setString(4, usuario.getUserLogin());
			
			stm.executeUpdate();
			
			stm.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> pesquisaUsuarios(String campoPesq, String pesqString) {
		String sql = "";
		String pesq = pesqString;
		
		Integer permissao = 1;
		if(pesqString.equals("Administrador"))
			permissao = 100;
		if(pesqString.equals("Comum"))
			permissao = 0;
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		if(campoPesq.equals("Codigo"))
			sql = "SELECT * FROM usuarios WHERE codigo = ?";
		if(campoPesq.equals("Nome")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM usuarios WHERE nome LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Login")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM usuarios WHERE login LIKE ? ORDER BY codigo";
		}
		if(campoPesq.equals("Permissão")){
			pesq = pesqString + "%";
			sql = "SELECT * FROM usuarios WHERE permissao = ? ORDER BY codigo";
		}
		
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			if(campoPesq.equals("Codigo")){
				try {
					stm.setInt(1, getInt(pesq));
				} catch (Exception e) {
					int defaultValue = 0;
					stm.setInt(1, defaultValue);
				}
			}
			else
				if(campoPesq.equals("Permissão")){
					try {
						stm.setInt(1, permissao);
					} catch (Exception e) {
						int defaultValue = 1;
						stm.setInt(1, defaultValue);
					}
				}
				else
					stm.setString(1, pesq);
			
			ResultSet rs = stm.executeQuery();
	
			
			while(rs.next()){
				
				Usuario usuario = new Usuario();
				
				
				
				usuario.setCodigo(rs.getString("codigo"));
				usuario.setNome(rs.getString("nome"));
				usuario.setUserLogin(rs.getString("login"));
				usuario.setPassword(rs.getString("senha"));
				if(rs.getInt("permissao") == 100)
					usuario.setPermissao("Administrador");
				else
					usuario.setPermissao("Comum");
				
		
				usuarios.add(usuario);
				
			}
			rs.close();
			stm.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void deletaUsuario(Usuario usuario) {
		String sql = "";
		
		sql = "DELETE FROM usuarios WHERE codigo = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, getInt(usuario.getCodigo()));
			
			stm.executeUpdate();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addSaida(Saida saida) {
		String sql = "";
		String produto = saida.getProduto();
		String fornecedor = saida.getFornecedor();
		Integer qtd = getInt(saida.getQuantidade());
		Integer codigo_produto = null, codigo_fornecedor = null;
		Float precoSaida = null;
		Float valorVenda = null;
		
		sql = "SELECT valor_venda, codigo FROM produtos WHERE nome = ?";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, produto);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				valorVenda = getFloat(rs.getString("valor_venda"));
				codigo_produto = rs.getInt("codigo");
			}
			
			precoSaida = valorVenda * qtd;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "SELECT codigo FROM fornecedores WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, fornecedor);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				codigo_fornecedor = rs.getInt("codigo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "INSERT INTO estoque (codigo_fornecedor, codigo_produto, qtd_saida, data_saida, preco_saida, data_entrada, qtd_entrada, preco_entrada) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setInt(1, codigo_fornecedor);
			stm.setInt(2, codigo_produto);
			stm.setInt(3, qtd);
			stm.setString(4, saida.getData());
			stm.setFloat(5, precoSaida);
			stm.setString(6, "null");
			stm.setInt(7, 0);
			stm.setString(8, "null");
			
			stm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Integer qtdProdutos = null;
		
		sql = "SELECT quantidade FROM produtos WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, produto);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				qtdProdutos = getInt(rs.getString("quantidade"));
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		qtdProdutos -= qtd;
		
		sql = "UPDATE produtos " +
			  "SET quantidade = ? " +
			  "WHERE nome = ?";
		
		try {
			
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, qtdProdutos.toString());
			stm.setString(2, produto);
			
			stm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Boolean testaQuantidadeSaida(Saida saida) {
		String sql = "";
		
		Integer qtdProduto = null;
		
		sql = "SELECT quantidade FROM produtos WHERE nome = ?";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, saida.getProduto());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next())
				qtdProduto = getInt(rs.getString("quantidade"));
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(getInt(saida.getQuantidade()) > qtdProduto)
			return false;
		else 
			return true;
	}

	public Boolean testaProduto2(String nome) {
		String sql = "";
		
		sql = "SELECT nome FROM produtos";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				if(rs.getString("nome").equals(nome))
					return true;
			}
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addProduto(Produto produto) {
		String sql = "";
		
		sql = "INSERT INTO produtos (nome , fornecedor, quantidade, valor_compra, valor_venda, tipo, descricao) VALUES (?,?,?,?,?,?,?)";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getFornecedor());
			stm.setString(3, "0");
			stm.setString(4, produto.getValor_compra());
			stm.setString(5, produto.getValor_venda());
			stm.setString(6, produto.getTipo());
			stm.setString(7, produto.getDescricao());
			
			stm.execute();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Boolean testaFornecedor(String nome) {
		String sql = "";
		
		sql = "SELECT nome FROM fornecedores";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				if(rs.getString("nome").equals(nome))
					return true;
			}
			
			connection.close();
			stm.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addFornecedor(Fornecedor fornecedor) {
		String sql = "";
		
		sql = "INSERT INTO fornecedores (nome , cidade, uf, cep, endereco, bairro, email, telefone) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, fornecedor.getNome());
			stm.setString(2, fornecedor.getCidade());
			stm.setString(3, fornecedor.getEstado());
			stm.setString(4, fornecedor.getCep());
			stm.setString(5, fornecedor.getEndereco());
			stm.setString(6, fornecedor.getBairro());
			stm.setString(7, fornecedor.getEmail());
			stm.setString(8, fornecedor.getTelefone());
			
			stm.execute();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Boolean verificaLoginUser(String login) {
		String sql = "";
		
		sql = "SELECT * FROM usuarios ORDER BY codigo";
		
		try {
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			
			
			while(rs.next()){
				
				if(rs.getString("login").equals(login))
					return true;
					
			}
			stm.close();
			connection.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addUsuario(Usuario usuario) {
		String sql = "";
		
		sql = "INSERT INTO usuarios (nome , login, senha, permissao) VALUES (?,?,?,?)";
		
		try {
			
			connection = new FactoryConnection().getConnection();
			stm = connection.prepareStatement(sql);
			
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getUserLogin());
			stm.setString(3, usuario.getPassword());
			if(usuario.getPermissao().equals("Administrador"))
				stm.setInt(4, 100);
			else
				stm.setInt(4, 0);
			
			stm.execute();
			
			connection.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
