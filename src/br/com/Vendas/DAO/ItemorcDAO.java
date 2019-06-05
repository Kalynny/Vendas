package br.com.Vendas.DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.Vendas.domain.Itemorc;
//import br.com.Vendas.domain.Produto;
import br.com.Vendas.util.HibernateUtil;


public class ItemorcDAO {

	public void salvar(Itemorc itemorc) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.save(itemorc);
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
	public List<Itemorc> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Itemorc> itemorc = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Itemorc.listar");
			itemorc = consulta.list();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return itemorc;
	}
	
	
	
	public Itemorc buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Itemorc itemorc = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Itemorc.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			itemorc = (Itemorc) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return itemorc;
	}
	
	
	public void excluir(Itemorc itemorc) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(itemorc);
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
	
	
	
	public void editar(Itemorc itemorc) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			
			sessao.update(itemorc);
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
