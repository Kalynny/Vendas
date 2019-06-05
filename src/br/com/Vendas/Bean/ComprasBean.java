package br.com.Vendas.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.DAO.ItemcomDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.Relatorio.Relatorio;
import br.com.Vendas.DAO.ComprasDAO;
//import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Itemcom;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Compras;
//import br.com.Vendas.util.HibernateUtil;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBCompras")
@ViewScoped
public class ComprasBean {

	 private Produto produto;
	 private Compras compraCadastro;
	 private List<Itemcom>Itenscom;
	 private List<Itemcom>ItenscomFiltrados;
	 private List<Produto>produtos;
	 private List<Produto>produtosFiltrados;
	
	 
	 public Compras getCompraCadastro() {
		 if(compraCadastro == null){
			compraCadastro = new Compras();
			compraCadastro.setValor_total(new BigDecimal("0.00"));
		 }
		return compraCadastro;
	}
	 
	 public void setCompraCadastro(Compras compraCadastro) {
		this.compraCadastro = compraCadastro;
	}
	
	public List<Itemcom> getItenscom() {
		if(Itenscom == null){
			Itenscom = new ArrayList<>();
		}
		return Itenscom;
	}
	
	public void setItenscom(List<Itemcom> Itenscom) {
		this.Itenscom = Itenscom;
	}
	
	
	public List<Itemcom> getItenscomFiltrados() {
		return ItenscomFiltrados;
	}
	
	public void setItenscomFiltrados(List<Itemcom> ItenscomFiltrados) {
		this.ItenscomFiltrados = ItenscomFiltrados;
	}
	 
		 
	 public Produto getProduto() {
		return produto;
	}
	 
	 public void setProduto(Produto produto) {
		this.produto = produto;
	}
	 
	 
	 public List<Produto> getProdutos() {
		return produtos;
	}
	 
	 public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	 
	 
	 public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}
	 
	 public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}
	 

	 
	 public void carregarProdutos(){
			
		 try {
			 ProdutosDAO fdao = new ProdutosDAO();
		 produtos = (ArrayList<Produto>) fdao.listar();
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 }
	
	 
	 public void adicionar(Produto produto){
		 
		 int posicaoEncontrada = -1;
		 
		 for(int pos = 0; pos < Itenscom.size() && posicaoEncontrada < 0; pos++){
			Itemcom ItemcomTemp = Itenscom.get(pos);
			
			if(ItemcomTemp.getProduto().equals(produto)){
				posicaoEncontrada = pos;
			}
		 }
		 
		 
		 Itemcom Itemcom = new Itemcom();
		 Itemcom.setProduto(produto);
		 
		 
		 if(posicaoEncontrada < 0){
		 Itemcom.setQuantidade(1);
		 Itemcom.setValor_parcial(produto.getPreco());
		 Itenscom.add(Itemcom);
		 } else{		 
		
			 Itemcom ItemcomTemp = Itenscom.get(posicaoEncontrada);
			 Itemcom.setQuantidade(ItemcomTemp.getQuantidade() + 1);
			 Itemcom.setValor_parcial(produto.getPreco().multiply(new BigDecimal(Itemcom.getQuantidade())));
			 Itenscom.set(posicaoEncontrada, Itemcom);
		 
		 }
		 
		 compraCadastro.setValor_total(compraCadastro.getValor_total().add(produto.getPreco()));
		 
	 }
	 
	 public void subtrair(Produto produto){
		 
		 int posicaoEncontrada = -1;
		 
		 for(int pos = 0; pos < Itenscom.size() && posicaoEncontrada < 0; pos++){
			Itemcom ItemcomTemp = Itenscom.get(pos);
			
			if(ItemcomTemp.getProduto().equals(produto)){
				posicaoEncontrada = pos;
			}
		 }
		 		 
		 Itemcom Itemcom = new Itemcom();
		 Itemcom.setProduto(produto);
		 
		 
		 if(posicaoEncontrada < 0){
		 Itemcom.setQuantidade(1);
		 Itemcom.setValor_parcial(produto.getPreco());
		 Itenscom.add(Itemcom);
		 } else{		 
		
			 Itemcom ItemcomTemp = Itenscom.get(posicaoEncontrada);
			 Itemcom.setQuantidade(ItemcomTemp.getQuantidade() - 1);
			 Itemcom.setValor_parcial(produto.getPreco().multiply(new BigDecimal(Itemcom.getQuantidade())));
			 Itenscom.set(posicaoEncontrada, Itemcom);
			 if(Itemcom.getQuantidade() == 0){
			 Itenscom.remove(posicaoEncontrada);
		 
			 }
		 }
		 
			 compraCadastro.setValor_total(compraCadastro.getValor_total().subtract(produto.getPreco()));
	}
		 
	 
	 
	
	 public void remover(Itemcom Itemcom){
		 
      int posicaoEncontrada = -1;
		 
		 for(int pos = 0; pos < Itenscom.size() && posicaoEncontrada < 0; pos++){
			Itemcom ItemcomTemp = Itenscom.get(pos);
			
			if(ItemcomTemp.getProduto().equals(Itemcom.getProduto())){
				posicaoEncontrada = pos;
			}
		 }
		 
		 if(posicaoEncontrada > -1){
			 Itenscom.remove(posicaoEncontrada);
			 compraCadastro.setValor_total(compraCadastro.getValor_total().subtract(Itemcom.getValor_parcial()));
		 }
		 
	 }
	 
	 public void carregarDadosCompra(){
		 compraCadastro.setHorario(new Date());
		 
		 FuncionariosDAO dao = new FuncionariosDAO();
		 Funcionario funcionario = dao.buscarPorCodigo(1L);
		 compraCadastro.setFuncionario(funcionario);
	 }
	 
	 	 
	 public void salvar(){
		 try {
			
			 
			 ComprasDAO vdao = new ComprasDAO();
			 Long codigoCompra = vdao.salvar(compraCadastro);
			 
			 Compras compraFK = vdao.buscarPorCodigo(codigoCompra);
			 
			 for(Itemcom Itemcom : Itenscom){
				 Itemcom.setCompra(compraFK);
				 ItemcomDAO Itemcomdao = new ItemcomDAO();
				 Itemcomdao.salvar(Itemcom);
			 }
			 
			 compraCadastro = new Compras();
			 compraCadastro.setValor_total(new BigDecimal("0.00"));
			 Itenscom = new ArrayList<Itemcom>();
		 
		 JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso");
		 
		//String title = null;
			
			// int resposta = JOptionPane.showConfirmDialog( null, "Deseja imprimir ", title, JOptionPane.YES_NO_OPTION);

			// if (resposta == JOptionPane.YES_OPTION) {
			    //Usuário clicou em Sim. Executar o código correspondente.
				 
				 Relatorio re = new Relatorio();
				 //int i = codigoVenda.intValue() ;
				 re.relCompras(codigoCompra.intValue());
			// } else if (resposta == JOptionPane.NO_OPTION) {
			    //Usuário clicou em não. Executar o código correspondente.
			 //}		 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		 
	 }
	 
	 
	 public void editar(){
		 try {
			
			 ComprasDAO vdao = new ComprasDAO();
			 Long codigocompra = vdao.salvar(compraCadastro);
			 
			 Compras compraFK = vdao.buscarPorCodigo(codigocompra);
			 
			 for(Itemcom Itemcom : Itenscom){
				 Itemcom.setCompra(compraFK);
				 ItemcomDAO Itemcomdao = new ItemcomDAO();
				 Itemcomdao.salvar(Itemcom);
			 }
			 
			 compraCadastro = new Compras();
			 compraCadastro.setValor_total(new BigDecimal("0.00"));
			 Itenscom = new ArrayList<Itemcom>();
		 
		 JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso");
					
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		 
	 }
	
}
