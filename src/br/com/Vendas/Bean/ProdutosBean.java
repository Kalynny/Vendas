package br.com.Vendas.Bean;

import java.sql.Connection;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.util.HibernateUtil;
import br.com.Vendas.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
//import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {
	private Produto produto;

	 private ArrayList<Produto>itens;
	 private ArrayList<Produto>itensFiltrados;
	 private String acao;
	 private Long codigo;
	 private List<Fornecedor>listaFornecedor;
	 
	 
	 public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}
	 
	 public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}
	 
	 public Long getCodigo() {
		return codigo;
	}
	 
	 
	 public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	 
	 
	 public String getAcao() {
		return acao;
	}
	 
	 
	 public void setAcao(String acao) {
		this.acao = acao;
	}
	
	public Produto getProduto() {
		
		return produto;
	}

	//
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	 public ArrayList<Produto> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Produto> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Produto> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

	// @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 ProdutosDAO fdao = new ProdutosDAO();
	 itens = (ArrayList<Produto>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 
				 ProdutosDAO fdao = new ProdutosDAO();	
			
				 produto = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				 produto = new Produto();
				
			 }
			 
			 FornecedoresDAO dao = new FornecedoresDAO();
			 listaFornecedor = dao.listar();
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
		 produto = new Produto();
	 }

	public void salvar() {

		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.salvar(produto);
			
			produto = new Produto();

			

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 ProdutosDAO fdao = new ProdutosDAO();
	 fdao.excluir(produto);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
		 ProdutosDAO fdao = new ProdutosDAO();
	 fdao.editar(produto);
	
	
	 JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	 
	 @SuppressWarnings("deprecation")
	 public void imprimir(){
		 
		 try {
		 
		 String caminho = Faces.getRealPath("/reports/Produto.jasper");
		 
		 //Map<String, Object> paramentros = new HashMap<>();
		 
		 Connection conexao = HibernateUtil.getConexao();
		 		 
		 JasperPrint relatorio = JasperFillManager.fillReport(caminho, null, conexao);
		 JasperViewer view = new JasperViewer(relatorio, false);
		 view.show();
		 
		// JasperPrintManager.printReport(relatorio, true);
		 
		 } catch ( JRException erro) {
			 Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			 	erro.printStackTrace();
			 	}
		      }
	  
  }
