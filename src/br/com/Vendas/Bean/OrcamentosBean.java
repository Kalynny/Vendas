package br.com.Vendas.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import org.hibernate.Session;
//import org.hibernate.Transaction;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.DAO.ItemorcDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.DAO.OrcamentosDAO;

import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Itemorc;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Orcamentos;
//import br.com.Vendas.util.HibernateUtil;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBOrcamentos")
@ViewScoped
public class OrcamentosBean {

	 private Produto produto;
	 private Orcamentos orcamentoCadastro;
	 private List<Itemorc>Itensorc;
	 private List<Itemorc>ItensorcFiltrados;
	 private List<Produto>produtos;
	 private List<Produto>produtosFiltrados;
	
	 
	 public Orcamentos getOrcamentoCadastro() {
		 if(orcamentoCadastro == null){
			orcamentoCadastro = new Orcamentos();
			orcamentoCadastro.setValor_total(new BigDecimal("0.00"));
		 }
		return orcamentoCadastro;
	}
	 
	 public void setOrcamentoCadastro(Orcamentos orcamentoCadastro) {
		this.orcamentoCadastro = orcamentoCadastro;
	}
	
	public List<Itemorc> getItensorc() {
		if(Itensorc == null){
			Itensorc = new ArrayList<>();
		}
		return Itensorc;
	}
	
	public void setItensorc(List<Itemorc> Itensorc) {
		this.Itensorc = Itensorc;
	}
	
	
	public List<Itemorc> getItensorcFiltrados() {
		return ItensorcFiltrados;
	}
	
	public void setItensorcFiltrados(List<Itemorc> ItensorcFiltrados) {
		this.ItensorcFiltrados = ItensorcFiltrados;
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
		 
		 for(int pos = 0; pos < Itensorc.size() && posicaoEncontrada < 0; pos++){
			Itemorc ItemorcTemp = Itensorc.get(pos);
			
			if(ItemorcTemp.getProduto().equals(produto)){
				posicaoEncontrada = pos;
			}
		 }
		 
		 
		 Itemorc Itemorc = new Itemorc();
		 Itemorc.setProduto(produto);
		 
		 
		 if(posicaoEncontrada < 0){
		 Itemorc.setQuantidade(1);
		 Itemorc.setValor_parcial(produto.getPreco());
		 Itensorc.add(Itemorc);
		 } else{		 
		
			 Itemorc ItemorcTemp = Itensorc.get(posicaoEncontrada);
			 Itemorc.setQuantidade(ItemorcTemp.getQuantidade() + 1);
			 Itemorc.setValor_parcial(produto.getPreco().multiply(new BigDecimal(Itemorc.getQuantidade())));
			 Itensorc.set(posicaoEncontrada, Itemorc);
		 
		 }
		 
		 orcamentoCadastro.setValor_total(orcamentoCadastro.getValor_total().add(produto.getPreco()));
		 
	 }
	 
	 public void subtrair(Produto produto){
		 
		 int posicaoEncontrada = -1;
		 
		 for(int pos = 0; pos < Itensorc.size() && posicaoEncontrada < 0; pos++){
			Itemorc ItemorcTemp = Itensorc.get(pos);
			
			if(ItemorcTemp.getProduto().equals(produto)){
				posicaoEncontrada = pos;
			}
		 }
		 		 
		 Itemorc Itemorc = new Itemorc();
		 Itemorc.setProduto(produto);
		 
		 
		 if(posicaoEncontrada < 0){
		 Itemorc.setQuantidade(1);
		 Itemorc.setValor_parcial(produto.getPreco());
		 Itensorc.add(Itemorc);
		 } else{		 
		
			 Itemorc ItemorcTemp = Itensorc.get(posicaoEncontrada);
			 Itemorc.setQuantidade(ItemorcTemp.getQuantidade() - 1);
			 Itemorc.setValor_parcial(produto.getPreco().multiply(new BigDecimal(Itemorc.getQuantidade())));
			 Itensorc.set(posicaoEncontrada, Itemorc);
			 if(Itemorc.getQuantidade() == 0){
			 Itensorc.remove(posicaoEncontrada);
		 
			 }
		 }
		 
			 orcamentoCadastro.setValor_total(orcamentoCadastro.getValor_total().subtract(produto.getPreco()));
	}
		 
	 
	 
	
	 public void remover(Itemorc Itemorc){
		 
      int posicaoEncontrada = -1;
		 
		 for(int pos = 0; pos < Itensorc.size() && posicaoEncontrada < 0; pos++){
			Itemorc ItemorcTemp = Itensorc.get(pos);
			
			if(ItemorcTemp.getProduto().equals(Itemorc.getProduto())){
				posicaoEncontrada = pos;
			}
		 }
		 
		 if(posicaoEncontrada > -1){
			 Itensorc.remove(posicaoEncontrada);
			 orcamentoCadastro.setValor_total(orcamentoCadastro.getValor_total().subtract(Itemorc.getValor_parcial()));
		 }
		 
	 }
	 
	 public void carregarDadosOrcamento(){
		 orcamentoCadastro.setHorario(new Date());
		 
		 FuncionariosDAO dao = new FuncionariosDAO();
		 Funcionario funcionario = dao.buscarPorCodigo(4L);
		 orcamentoCadastro.setFuncionario(funcionario);
	 }
	 
	 	 
	 public void salvar(){
		 try {
						 
			 OrcamentosDAO vdao = new OrcamentosDAO();
			 Long codigoOrcamento = vdao.salvar(orcamentoCadastro);
			 
			 Orcamentos orcamentoFK = vdao.buscarPorCodigo(codigoOrcamento);
			 
			 for(Itemorc Itemorc : Itensorc){
				 Itemorc.setOrcamento(orcamentoFK);
				 ItemorcDAO Itemorcdao = new ItemorcDAO();
				 Itemorcdao.salvar(Itemorc);
			 }
			 
			 orcamentoCadastro = new Orcamentos();
			 orcamentoCadastro.setValor_total(new BigDecimal("0.00"));
			 Itensorc = new ArrayList<Itemorc>();
		 
		 JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso");
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage155()");
		 e.printStackTrace();
		 }
		 
	 }
	 
	 
	 public void editar(){
		 try {
			
			 OrcamentosDAO vdao = new OrcamentosDAO();
			 Long codigoorcamento = vdao.salvar(orcamentoCadastro);
			 
			 Orcamentos orcamentoFK = vdao.buscarPorCodigo(codigoorcamento);
			 
			 for(Itemorc Itemorc : Itensorc){
				 Itemorc.setOrcamento(orcamentoFK);
				 ItemorcDAO Itemorcdao = new ItemorcDAO();
				 Itemorcdao.salvar(Itemorc);
			 }
			 
			 orcamentoCadastro = new Orcamentos();
			 orcamentoCadastro.setValor_total(new BigDecimal("0.00"));
			 Itensorc = new ArrayList<Itemorc>();
		 
		 JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso");
		
		 } catch (RuntimeException e) {
		 JSFUtil.adicionarMensagemErro("ex.getMessage()");
		 e.printStackTrace();
		 }
		 
	 }
	
}
