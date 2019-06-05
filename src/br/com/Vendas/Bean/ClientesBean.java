package br.com.Vendas.Bean;

import java.sql.Connection;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.Vendas.DAO.ClientesDAO;
import br.com.Vendas.domain.Cliente;
import br.com.Vendas.util.HibernateUtil;
import br.com.Vendas.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name = "MBClientes")
@ViewScoped
public class ClientesBean {
	private Cliente cliente;

	 private ArrayList<Cliente>itens;
	 private ArrayList<Cliente>itensFiltrados;
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
	
	public Cliente getCliente() {
		
		return cliente;
	}

	//
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	 public ArrayList<Cliente> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Cliente> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Cliente> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Cliente> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

  // @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 ClientesDAO fdao = new ClientesDAO();
	 itens = (ArrayList<Cliente>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 
				 ClientesDAO fdao = new ClientesDAO();	
			
			cliente = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				 cliente = new Cliente();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
		 cliente = new Cliente();
	 }

	public void salvar() {

		try {
			ClientesDAO fdao = new ClientesDAO();
			fdao.salvar(cliente);
			
			cliente = new Cliente();

			

			JSFUtil.adicionarMensagemSucesso("Cliente salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 ClientesDAO fdao = new ClientesDAO();
	 fdao.excluir(cliente);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Cliente excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("Não é possível excluir um cliente!");
	 e.printStackTrace();
	 }
	 }
	
	
	
	 public void editar(){
	 try {
		 ClientesDAO fdao = new ClientesDAO();
	 fdao.editar(cliente);
	
	
	 JSFUtil.adicionarMensagemSucesso("Cliente editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	 
	 @SuppressWarnings("deprecation")
	 public void impcli(){
		 
		 try {
		 
		 String caminho = Faces.getRealPath("/reports/Cliente.jasper");
		 
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
