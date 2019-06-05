package br.com.Vendas.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Cria uma conexão a partir do hibernate.cfg.xml
           
        	Configuration configuration = new Configuration();
        	configuration.configure();
        	
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        			.applySettings(configuration.getProperties()).build();
        	
        	
        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	
        	return sessionFactory;
        	
        	
        	//return new Configuration().configure().buildSessionFactory(
			  //  new StandardServiceRegistryBuilder().build() );
        }
        catch (Throwable ex) {
            // Mensagem de erro ao conectar
            System.err.println("Erro na conexão." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Connection getConexao(){
    	Session sessao = sessionFactory.openSession();
    	
    	Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
    		@Override
    		public Connection execute(Connection conn) throws SQLException {
    			return conn;
    		}
		});
    	
    	return conexao;
    	
   }
    
    

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Connection getConnection1() {
		
		 Session session = fabricaDeSessoes.openSession();
	     Connection connection = session.doReturningWork(new ReturningWork<Connection>() {

			@Override
			public Connection execute(java.sql.Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				return (Connection) connection;
			}
	     });

		return connection;	
		
	}
	
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			Configuration configuracao = new Configuration().configure();
			
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			
			return fabrica;
		} catch (Throwable ex) {
			System.err.println("A conexão não foi criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}

