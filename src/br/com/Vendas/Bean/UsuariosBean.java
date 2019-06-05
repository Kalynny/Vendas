package br.com.Vendas.Bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.DAO.UsuariosDAO;
import br.com.Vendas.domain.Usuario;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBUsuarios")
@ViewScoped
public class UsuariosBean {
	private Usuario usuario;

	 private ArrayList<Usuario>itensusu;
	 private ArrayList<Usuario>itensusuFiltrados;
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
	
	public Usuario getUsuario() {
		
		return usuario;
	}

	//
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	 public ArrayList<Usuario> getItensusu() {
	 return itensusu;
	 }
	
	 public void setItensusu(ArrayList<Usuario> itensusu) {
	 this.itensusu = itensusu;
	 }
	
	
	 public ArrayList<Usuario> getItensusuFiltrados() {
	 return itensusuFiltrados;
	 }
	
	 public void setItensusuFiltrados(ArrayList<Usuario> itensusuFiltrados) {
	 this.itensusuFiltrados = itensusuFiltrados;
	 }

  // @PostConstruct
	 public void prepararPesquisa(){
		
	 try {
		 UsuariosDAO fdao = new UsuariosDAO();
	 itensusu = (ArrayList<Usuario>) fdao.listar();
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	
	 }
	 
	 
	 public void carregarCadastro(){

		 try {
		     
			
			 if(codigo != null){
				
				 
				 UsuariosDAO fdao = new UsuariosDAO();	
			
			usuario = fdao.buscarPorCodigo(codigo);
				 
			 }
			 else
				 {
				 usuario = new Usuario();
				
			 }
			 
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		
		 } 
	 

	 public void novo(){
		 usuario = new Usuario();
	 }

	public void salvar() {

		try {
			UsuariosDAO fdao = new UsuariosDAO();
			fdao.salvar(usuario);
			
			usuario = new Usuario();

			

			JSFUtil.adicionarMensagemSucesso("Usuario salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	
	
	 public void excluir(){
	 try {
		 UsuariosDAO fdao = new UsuariosDAO();
	 fdao.excluir(usuario);
	
	
	
	 JSFUtil.adicionarMensagemSucesso("Usuario excluido com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("Não é possível excluir um usuario!");
	 e.printStackTrace();
	 }
	 }
	
	
	
	 public void editar(){
	 try {
		 UsuariosDAO fdao = new UsuariosDAO();
	 fdao.editar(usuario);
	
	
	 JSFUtil.adicionarMensagemSucesso("Usuario editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	 
	 public void enviar() {
	 
	 try {
		 UsuariosDAO fdao = new UsuariosDAO();
	 fdao.enviar(usuario);
	
	
	 JSFUtil.adicionarMensagemSucesso("Usuario editado com sucesso!");
	
	 } catch (RuntimeException e) {
	 JSFUtil.adicionarMensagemErro("ex.getMessage()");
	 e.printStackTrace();
	 }
	 }
	 
	 
	 
}
