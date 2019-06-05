package br.com.Vendas.DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Orcamentos;
import br.com.Vendas.util.HibernateUtil;

public class OrcamentosDAO {
	
	public Long salvar(Orcamentos orcamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;
		Long codigo = null;
		
		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			codigo = (Long) sessao.save(orcamento);
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
	public List<Orcamentos> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Orcamentos> orcamento = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Orcamento.listar");
			orcamento = consulta.list();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return orcamento;
	}
	
	
	
	public Orcamentos buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Orcamentos orcamento = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Orcamento.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			orcamento = (Orcamentos) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			sessao.close();
		}
		
		return orcamento;
	}
	
	
	public void excluir(Orcamentos orcamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			sessao.delete(orcamento);
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
	
	
	
	public void editar(Orcamentos orcamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transação
			
			sessao.update(orcamento);
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
