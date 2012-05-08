package db;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DbVerwaltung {
	
	private  EntityManager em;
	private  EntityManagerFactory factory;
	
	protected DbVerwaltung() {
		super();
	}

	private boolean open(){
		try {
			this.factory = Persistence.createEntityManagerFactory("jpa");
			this.em = factory.createEntityManager();

			this.em.getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean close(){
		
		try {
			this.em.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<Benutzer> selectAll_Benutzer(){
		 if(this.em==null){
			 open();
		 }
			Query query = this.em.createNamedQuery("Benutzer.findAll");
	       
	        @SuppressWarnings("unchecked")
			List<Benutzer>resultList = query.getResultList();
	        close();
			return resultList;
				
	}

	public  boolean insertBenutzer(Benutzer user){
		 if(this.em==null){
			 open();
		 }
		 
		    String queryString = "SELECT b FROM Benutzer WHERE b.benutzerId < SELECT MAX(x.benutzerId) FROM b)";
		 	System.out.println(queryString);
			Query query = this.em.createQuery(queryString);
	        
	        @SuppressWarnings("unchecked")
			List <Benutzer> resultList = query.getResultList();
	        Iterator<Benutzer> pIterator=resultList.iterator();
	        
	        while(pIterator.hasNext()){
	        	
		        Benutzer be=(Benutzer)pIterator.next();
		        System.out.println(be.getBenutzerId());
		        user.setBenutzerId(be.getBenutzerId()+1);
	        }

		 
		 try {
			 this.em.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		 this.em.close();
		return true;
		
	}
	
	public List<Medium> selectAll_Medium(){
		if(this.em==null){
			 open();
		 }
			Query query = this.em.createNamedQuery("Medium.findAll");
	       
	        @SuppressWarnings("unchecked")
			List<Medium>resultList = query.getResultList();
	        close();
			return resultList;
	}
	
	public List<ExemplarBenutzer> selectAll_ExemplarBenutzer(){
		if(this.em==null){
			 open();
		 }
			Query query = this.em.createNamedQuery("ExemplarBenutzer.findAll");
	       
	        @SuppressWarnings("unchecked")
			List<ExemplarBenutzer>resultList = query.getResultList();
	        close();
			return resultList;
	}
	
	public  boolean insertExemplarBenutzer(ExemplarBenutzer exBe){
		 if(this.em==null){
			 open();
		 }
		 try {
			 this.em.persist(exBe);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		 this.em.close();
		return true;
	}
	
	public List<Exemplar> selectAll_Exemplar(){
		if(this.em==null){
			 open();
		 }
			Query query = this.em.createNamedQuery("Exemplar.findAll");
	       
	        @SuppressWarnings("unchecked")
			List<Exemplar>resultList = query.getResultList();
	        close();
			return resultList;
	}
	
	public  boolean insertExemplar(ExemplarBenutzer ex){
		 if(this.em==null){
			 open();
		 }
		 try {
			 this.em.persist(ex);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		 this.em.close();
		return true;
	}
	

	
}

