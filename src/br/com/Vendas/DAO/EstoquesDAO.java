package br.com.Vendas.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.Vendas.domain.Estoque;
import br.com.Vendas.util.HibernateUtil;

public class EstoquesDAO {
	
	
	public void salvar(Estoque estoque) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(estoque);
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

	@SuppressWarnings("unchecked")
	public List<Estoque> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Estoque> estoque = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Estoque.listar");
			estoque = consulta.list();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return estoque;
	}
	
	
	
	public Estoque buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Estoque estoque = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Estoque.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			estoque = (Estoque) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return estoque;
	}
	
	
	public void excluir(Estoque estoque) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(estoque);
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
	
	
	
	public void editar(Estoque estoque) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			
			sessao.update(estoque);
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
