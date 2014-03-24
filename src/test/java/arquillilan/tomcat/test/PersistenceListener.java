package arquillilan.tomcat.test;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PersistenceListener implements ServletContextListener {

	 private static EntityManagerFactory entityManagerFactory;

	 private EntityManager entityManager;
	 
	 public void contextInitialized(ServletContextEvent sce){
	 //ServletContext context = sce.getServletContext();
	 entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-test");
	 createEntityManager();
	 }

	 public void contextDestroyed(ServletContextEvent sce) {
	 	entityManagerFactory.close();
	 }

	 @Produces
	 public EntityManager createEntityManager() {
	        if (entityManagerFactory == null) {
	            throw new IllegalStateException("Context is not initialized yet.");
	        }

	        entityManager =  entityManagerFactory.createEntityManager();
	        
	        return this.entityManager;
	    }
}