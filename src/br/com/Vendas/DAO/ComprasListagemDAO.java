package br.com.Vendas.DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Compras;
import br.com.Vendas.util.HibernateUtil;

public class ComprasListagemDAO {
	
	public Long salvar(Compras compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;
		Long codigo = null;
		
		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			codigo = (Long) sessao.save(compra);
			transacao.commit(); // confirmando a transação

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transação
			}
		
		}

		finally {
			sessao.close();
		}

		return codigo;
		
	}

	@SuppressWarnings("unchecked")
	public List<Compras> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Compras> compra = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Compra.listar");
			compra = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return compra;
	}
	
	
	
	public Compras buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Compras compra = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Compra.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			compra = (Compras) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return compra;
	}
	
	
	public void excluir(Compras compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(compra);
			transacao.commit(); // confirmando a transação

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transação
			}

		}

		finally {
			sessao.close();
		}

	}
	
	
	
	public void editar(Compras compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			
			sessao.update(compra);
			transacao.commit(); // confirmando a transação

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transação
			}

		}

		finally {
			sessao.close();
		}

	}
	
	
}
