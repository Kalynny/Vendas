package br.com.Vendas.Bean;

import java.sql.Connection;
//import java.sql.SQLException;
import java.util.ArrayList;
//import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.util.HibernateUtil;
import br.com.Vendas.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private Fornecedor fornecedores;

	 private ArrayList<Fornecedor>itens;
	 private ArrayList<Fornecedor>itensFiltrados;
	 private String acao;
	 private Long codigo;
	 
	 
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
	
	public Fornecedor getFornecedores() {
		
		return fornecedores;
	}

	//
	public void setFornecedores(Fornecedor fornecedores) {
		this.fornecedores = fornecedores;
	}

	 public ArrayList<Fornecedor> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Fornecedor> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Fornecedor> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

	// @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
	 FornecedoresDAO fdao = new FornecedoresDAO();
	 itens = (ArrayList<Fornecedor>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 
			FornecedoresDAO fdao = new FornecedoresDAO();	
			
			fornecedores = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				fornecedores = new Fornecedor();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 fornecedores = new Fornecedor();
	 }

	public void salvar() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			
			fornecedores = new Fornecedor();

			//itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
	 FornecedoresDAO fdao = new FornecedoresDAO();
	 fdao.excluir(fornecedores);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("N�o � poss�vel excluir um fornecedor que tenha um produto associado!");
	 e.printStackTrace();
	 }
	 }
	
	 public void editar(){
	 try {
	 FornecedoresDAO fdao = new FornecedoresDAO();
	 fdao.editar(fornecedores);
	
	
	 JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	 
	 @SuppressWarnings("deprecation")
	 public void impfor(){
		 
		 try {
		 
		 String caminho = Faces.getRealPath("/reports/Fornecedor.jasper");
		 
		 //Map<String, Object> paramentros = new HashMap<>();
		 
		 Connection conexao = HibernateUtil.getConexao();
		 		 
		 JasperPrint relatorio = JasperFillManager.fillReport(caminho, null, conexao);
		 JasperViewer view = new JasperViewer(relatorio, false);
		 view.show();
		 
		// JasperPrintManager.printReport(relatorio, true);
		 
		 } catch ( JRException erro) {
			 Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relat�rio");
			 	erro.printStackTrace();
			 	}
		      }
	 
	 
	
}
