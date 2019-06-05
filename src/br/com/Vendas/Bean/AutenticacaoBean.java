package br.com.Vendas.Bean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.util.JSFUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	   private Funcionario funcionarioLogado;
		
	   public Funcionario getFuncionarioLogado() {
		   if(funcionarioLogado == null) {
			  funcionarioLogado = new Funcionario(); 
		   }
		return funcionarioLogado;
	}
	   
	   public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
			
	public String autenticar() {
		
		try {
			
			FuncionariosDAO funcionarioDAO = new FuncionariosDAO();
			funcionarioLogado = funcionarioDAO.autenticar(
			funcionarioLogado.getCpf(), funcionarioLogado.getSenha());
			
			if(funcionarioLogado == null) {
				JSFUtil.adicionarMensagemErro("CPF ou Senha inválidos:");
				return null;
			}else {
				JSFUtil.adicionarMensagemSucesso("Funcionario autenticar com sucesso:");
				return "/pages/principal.xhtml?faces-redirect=true";
				
				}
			} catch (RuntimeException ex) {
				JSFUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema:" 
				+ ex.getMessage());
				return null;
					
		}
		
	}
	
		
	public String sair() {
		funcionarioLogado = null;
		return "/pages/login.xhtml?faces-redirect=true";
		
					
	}

}
	   
