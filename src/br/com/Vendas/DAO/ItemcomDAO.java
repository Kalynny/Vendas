package br.com.Vendas.DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.Vendas.domain.Itemcom;
import br.com.Vendas.util.HibernateUtil;


public class ItemcomDAO {

	public void salvar(Itemcom itemcom) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transa��o
			sessao.save(itemcom);
			transacao.commit(); // confirmando a transa��o

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transa��o
			}

		}

		finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Itemcom> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Itemcom> itemcom = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Itemcom.listar");
			itemcom = consulta.list();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return itemcom;
	}
	
	
	
	public Itemcom buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Itemcom itemcom = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Itemcom.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			itemcom = (Itemcom) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return itemcom;
	}
	
	
	public void excluir(Itemcom itemcom) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transa��o
			sessao.delete(itemcom);
			transacao.commit(); // confirmando a transa��o

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transa��o
			}

		}

		finally {
			sessao.close();
		}

	}
	
	
	
	public void editar(Itemcom itemcom) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transa��o
			
			sessao.update(itemcom);
			transacao.commit(); // confirmando a transa��o

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transa��o
			}

		}

		finally {
			sessao.close();
		}

	}
	
	
}
