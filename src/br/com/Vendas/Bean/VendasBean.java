package br.com.Vendas.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
//import javax.swing.JOptionPane;
import javax.faces.context.FacesContext;


import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.mysql.jdbc.Connection;

import br.com.Vendas.DAO.ClientesDAO;
//import br.com.Vendas.DAO.FornecedoresDAO;
//import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.DAO.ItemDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.DAO.VendasDAO;
import br.com.Vendas.Relatorio.Relatorio;
import br.com.Vendas.domain.Cliente;
//import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Item;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Vendas;
import br.com.Vendas.util.HibernateUtil;
//import br.com.Vendas.util.HibernateUtil;
import br.com.Vendas.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name = "MBVendas")
@ViewScoped
public class VendasBean<Venda> {

	private Produto produto;
	private Vendas vendaCadastro;
	private List<Item> itens;
	private List<Item> itensFiltrados;
	private List<Produto> produtos;
	private List<Produto> produtosFiltrados;
	private List<Venda> vendas;
	private String acao;
	private Long codigo;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	public Vendas getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Vendas();
			vendaCadastro.setValor_total(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Vendas vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public List<Item> getItens() {
		if (itens == null) {
			itens = new ArrayList<>();
		}
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public List<Item> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<Item> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
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

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void carregarProdutos() {

		try {
			ProdutosDAO fdao = new ProdutosDAO();
			produtos = (ArrayList<Produto>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void adicionar(Produto produto) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = itens.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}

		Item item = new Item();
		item.setProduto(produto);

		if (posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValor_parcial(produto.getPreco());
			itens.add(item);
		} else {

			Item itemTemp = itens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValor_parcial(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			itens.set(posicaoEncontrada, item);

		}

		vendaCadastro.setValor_total(vendaCadastro.getValor_total().add(produto.getPreco()));

	}

	public void subtrair(Produto produto) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = itens.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}

		Item item = new Item();
		item.setProduto(produto);

		if (posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValor_parcial(produto.getPreco());
			itens.add(item);
		} else {

			Item itemTemp = itens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() - 1);
			item.setValor_parcial(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			itens.set(posicaoEncontrada, item);
			if (item.getQuantidade() == 0) {
				itens.remove(posicaoEncontrada);

			}
		}

		vendaCadastro.setValor_total(vendaCadastro.getValor_total().subtract(produto.getPreco()));
	}

	public void remover(Item item) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = itens.get(pos);

			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}

		if (posicaoEncontrada > -1) {
			itens.remove(posicaoEncontrada);
			vendaCadastro.setValor_total(vendaCadastro.getValor_total().subtract(item.getValor_parcial()));
		}

	}

	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());

		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario funcionario = dao.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());
		vendaCadastro.setFuncionario(funcionario);
	}

	public void salvar() {
		try {
			VendasDAO vdao = new VendasDAO();
			Long codigoVenda = vdao.salvar(vendaCadastro);

			Vendas vendaFK = vdao.buscarPorCodigo(codigoVenda);
			for (Item item : itens) {
				item.setVenda(vendaFK);
				ItemDAO itemdao = new ItemDAO();
				itemdao.salvar(item);

				if (item != null) {
					Produto produto = item.getProduto();
					produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
					ProdutosDAO.update(produto);
				}

			}

			vendaCadastro = new Vendas();
			vendaCadastro.setValor_total(new BigDecimal("0.00"));
			itens = new ArrayList<Item>();

			JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso");

			Relatorio re = new Relatorio();
			re.relVendas(codigoVenda.intValue());
			return;

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void editar() {
		try {

			VendasDAO vdao = new VendasDAO();
			Long codigoVenda = vdao.salvar(vendaCadastro);

			Vendas vendaFK = vdao.buscarPorCodigo(codigoVenda);

			for (Item item : itens) {
				item.setVenda(vendaFK);
				ItemDAO itemdao = new ItemDAO();
				itemdao.salvar(item);
			}

			vendaCadastro = new Vendas();
			vendaCadastro.setValor_total(new BigDecimal("0.00"));
			itens = new ArrayList<Item>();

			JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public void impven() {

		try {

			String caminho = Faces.getRealPath("/reports/venda.jasper");

			Connection conexao = (Connection) HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, null, conexao);
			JasperViewer view = new JasperViewer(relatorio, false);
			view.show();
			refresh();

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
			
		}
	}

	@SuppressWarnings("unchecked")
	public void prepararPesquisaVenda() {

		try {
			VendasDAO vdao = new VendasDAO();
			vendas = (ArrayList<Venda>) vdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void carregarVendas() {

		try {

			if (codigo == null) {

				VendasDAO vdao = new VendasDAO();
				vendas = (List<Venda>) vdao.buscarPorCodigo(codigo);

			} else {
				vendas = (List<Venda>) new Vendas();

			}

			ClientesDAO dao = new ClientesDAO();
			List<Cliente> listaCliente = dao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	public void cancelarPedidoVenda() {

		String test = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("id");
		
		int codigo = Integer.parseInt(test);

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction tx = null;
		try {
			tx = sessao.beginTransaction();
			
			String sql = "DELETE FROM tb_itens WHERE tb_vendas_ven_codigo = "+codigo+";";
			String sql1 = "DELETE FROM tb_vendas WHERE ven_codigo = "+codigo+";";
			
			SQLQuery query = sessao.createSQLQuery(sql);
			SQLQuery query1 = sessao.createSQLQuery(sql1);
			
			int result = query.executeUpdate();
			int result1 = query1.executeUpdate();
			
			tx.commit();
			System.out.println(result);
			System.out.println(result1);
			
			vendaCadastro = null;
			vendaCadastro = new Vendas();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();
			//return;
			
		}
		refresh();
	}
	
	public void refresh() { 
		
		prepararPesquisaVenda();
		FacesContext context = FacesContext.getCurrentInstance(); 
		Application application = context.getApplication(); 
		ViewHandler viewHandler = application.getViewHandler(); 
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId()); 
		context.setViewRoot(viewRoot); 
		context.renderResponse(); 
		
		}
}
