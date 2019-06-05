package br.com.Vendas.Bean;

import java.sql.Connection;
//import java.sql.SQLException;
import java.util.ArrayList;
//import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.Vendas.DAO.EmpresasDAO;
import br.com.Vendas.domain.Empresa;
import br.com.Vendas.util.HibernateUtil;
import br.com.Vendas.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name = "MBEmpresas")
@ViewScoped
public class EmpresasBean {

	private Empresa empresas;

	 private ArrayList<Empresa>itens;
	 private ArrayList<Empresa>itensFiltrados;
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
	
	public Empresa getEmpresas() {
		
		return empresas;
	}

	//
	public void setEmpresa(Empresa empresas) {
		this.empresas = empresas;
	}

	 public ArrayList<Empresa> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Empresa> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Empresa> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Empresa> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

	// @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
	 EmpresasDAO fdao = new EmpresasDAO();
	 itens = (ArrayList<Empresa>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 
			EmpresasDAO fdao = new EmpresasDAO();	
			
			empresas = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				empresas = new Empresa();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
	 empresas = new Empresa();
	 }

	public void salvar() {

		try {
			EmpresasDAO fdao = new EmpresasDAO();
			fdao.salvar(empresas);
			
			empresas = new Empresa();

			// itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Empresa salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
	 EmpresasDAO fdao = new EmpresasDAO();
	 fdao.excluir(empresas);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Empresa excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("Não é possível excluir um empresa!");
	 e.printStackTrace();
	 }
	 }
	
	 public void editar(){
	 try {
	 EmpresasDAO fdao = new EmpresasDAO();
	 fdao.editar(empresas);
	
	
	 JSFUtil.adicionarMensagemSucesso("Empresa editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	 
	 @SuppressWarnings("deprecation")
	 public void impemp(){
		 
		 try {
		 
		 String caminho = Faces.getRealPath("/reports/Empresa.jasper");
		 
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
