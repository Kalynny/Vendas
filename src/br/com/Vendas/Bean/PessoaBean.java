package br.com.Vendas.Bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.DAO.PessoaDAO;

import br.com.Vendas.domain.Pessoa;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBPessoas")
@ViewScoped
public class PessoaBean {
	private Pessoa pessoa;

	 private ArrayList<Pessoa>itenpes;
	 private ArrayList<Pessoa>itenpesFiltrados;
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
	
	public Pessoa getPessoa() {
		
		return pessoa;
	}

	//
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	 public ArrayList<Pessoa> getItenpes() {
	 return itenpes;
	 }
	
	 public void setItenpes(ArrayList<Pessoa> itenpes) {
	 this.itenpes = itenpes;
	 }
	
	
	 public ArrayList<Pessoa> getItenpesFiltrados() {
	 return itenpesFiltrados;
	 }
	
	 public void setItenpesFiltrados(ArrayList<Pessoa> itenpesFiltrados) {
	 this.itenpesFiltrados = itenpesFiltrados;
	 }

	// @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 PessoaDAO fdao = new PessoaDAO();
	 itenpes = (ArrayList<Pessoa>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 
				 PessoaDAO fdao = new PessoaDAO();	
			
				 pessoa = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				 pessoa = new Pessoa();
				
			 }
			 
			 		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
		 pessoa = new Pessoa();
	 }

	public void salvar() {

		try {
			PessoaDAO fdao = new PessoaDAO();
			fdao.salvar(pessoa);
			
			pessoa = new Pessoa();

			

			JSFUtil.adicionarMensagemSucesso("Pessoa salva com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 PessoaDAO fdao = new PessoaDAO();
	 fdao.excluir(pessoa);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Pessoa excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	
	
	
	
	
	 public void editar(){
	 try {
		 PessoaDAO fdao = new PessoaDAO();
	 fdao.editar(pessoa);
	
	
	 JSFUtil.adicionarMensagemSucesso("Pessoa editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
}
