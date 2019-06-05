package br.com.Vendas.Bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.DAO.EstoquesDAO;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Estoque;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBEstoques")
@ViewScoped
public class EstoquesBean {
	private Estoque estoque;

	 private ArrayList<Estoque>itens;
	 private ArrayList<Estoque>itensFiltrados;
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
	
	public Estoque getEstoque() {
		
		return estoque;
	}

	//
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	 public ArrayList<Estoque> getItens() {
	 return itens;
	 }
	
	 public void setItens(ArrayList<Estoque> itens) {
	 this.itens = itens;
	 }
	
	
	 public ArrayList<Estoque> getItensFiltrados() {
	 return itensFiltrados;
	 }
	
	 public void setItensFiltrados(ArrayList<Estoque> itensFiltrados) {
	 this.itensFiltrados = itensFiltrados;
	 }

	// @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 EstoquesDAO fdao = new EstoquesDAO();
	 itens = (ArrayList<Estoque>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 
				 EstoquesDAO fdao = new EstoquesDAO();	
			
				 estoque = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				 estoque = new Estoque();
				
			 }
			 
			 FornecedoresDAO dao = new FornecedoresDAO();
			 listaFornecedor = dao.listar();
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
		 estoque = new Estoque();
	 }

	public void salvar() {

		try {
			EstoquesDAO fdao = new EstoquesDAO();
			fdao.salvar(estoque);
			
			estoque = new Estoque();

			

			JSFUtil.adicionarMensagemSucesso("Estoque salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 EstoquesDAO fdao = new EstoquesDAO();
	 fdao.excluir(estoque);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Estoque excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
		 EstoquesDAO fdao = new EstoquesDAO();
	 fdao.editar(estoque);
	
	
	 JSFUtil.adicionarMensagemSucesso("Estoque editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
}
