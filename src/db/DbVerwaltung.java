package db;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Die Klasse verwaltet alle schreibenden und lesenden Zugriffe auf die Bibliotheks-Datenbank
 * über die mit JPA erstellten Entity-Klassen
 * letzte Änderung: 09.05.2012
 * @author Christian.Kauf
 * @version 0.01
 */
public class DbVerwaltung {
    
    public  EntityManager em;
    public  EntityManagerFactory factory;
    
    public DbVerwaltung() {
        super();
    }

    /**
     * Die Methode erstellt eine Verbindung zur Datenbank
     * @return boolean
     */
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
    /**
     * Die Methode schließt die Verbindung zur Datenbank
     * @return boolean
     */
    
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
    
            open();
        
            Query query = this.em.createNamedQuery("Benutzer.findAll");
           
            @SuppressWarnings("unchecked")
            List<Benutzer>resultList = query.getResultList();
            close();
            return resultList;
                
    }

    public List<Buch> selectAll_Buecher(){
        
            open();
        
            Query query = this.em.createNamedQuery("Buch.findAll");
           
            @SuppressWarnings("unchecked")
            List<Buch>resultList = query.getResultList();
            close();
            return resultList;
                
    }
    
    public Buch select_BuchUeberISBN(String isbn){
        open();
    
        Query query = this.em.createNamedQuery("Buch.findByIsbn");
        query.setParameter("isbn", isbn);
       
        @SuppressWarnings("unchecked")
        List<Buch>resultList = query.getResultList();
        close();
        if(resultList.isEmpty()){
            return null;
        }
            
        return resultList.get(0);
            
}
    
    
    public Benutzer select_BenutzerUeberID(String id){
        open();
    
        Query query = this.em.createNamedQuery("Benutzer.findByBenutzerId");
        query.setParameter("benutzerId", id);
       
        @SuppressWarnings("unchecked")
        List<Benutzer>resultList = query.getResultList();
        close();
        if(resultList.isEmpty()){
            return null;
        }
            
        return resultList.get(0);
            
}    

    public boolean insertBenutzer(Benutzer user){
        
            open();

            String queryString = "SELECT b FROM Benutzer WHERE b.benutzerId < SELECT MAX(b.benutzerId) FROM b)";
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
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    public boolean updateBenutzer(Benutzer user){
              open();
                 
         try {
             //Benutzer userx = em.find(Benutzer.class, user.getBenutzerId());
             em.merge(user);
             em.getTransaction().commit();
             this.em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }

    public boolean deleteBenutzer(Benutzer user){

         open();
        
         try {
             Benutzer userx = em.find(Benutzer.class, user.getBenutzerId());
             em.remove(userx);
             em.getTransaction().commit();
             this.em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    public List<Medium> selectAll_Medium(){
            open();
        
            Query query = this.em.createNamedQuery("Medium.findAll");
           
            @SuppressWarnings("unchecked")
            List<Medium>resultList = query.getResultList();
            close();
            return resultList;
    }
    
    public List<ExemplarBenutzer> selectAll_ExemplarBenutzer(){
    
           open();
        
            Query query = this.em.createNamedQuery("ExemplarBenutzer.findAll");
           
            @SuppressWarnings("unchecked")
            List<ExemplarBenutzer>resultList = query.getResultList();
            close();
            return resultList;
    }
    
    public  boolean insertExemplarBenutzer(ExemplarBenutzer exBe){

         open();
        
         try {
             this.em.persist(exBe);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         this.em.close();
        return true;
    }
    
    public boolean updateExemplarBenutzer(ExemplarBenutzer exBe){

         open();

         try {
             em.getTransaction().commit();
             this.em.persist(exBe);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    public boolean deleteExemplarBenutzer(ExemplarBenutzer exBe){
         open();

         try {
             ExemplarBenutzer exBex = em.find(ExemplarBenutzer.class,exBe.getId());
             em.remove(exBex);
             em.getTransaction().commit();
             this.em.persist(exBe);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    public List<Exemplar> selectAll_Exemplar(){

            open();
        
            Query query = this.em.createNamedQuery("Exemplar.findAll");
           
            @SuppressWarnings("unchecked")
            List<Exemplar>resultList = query.getResultList();
            close();
            return resultList;
    }
    
    public  boolean insertExemplar(ExemplarBenutzer ex){

        open();
        
         try {
             this.em.persist(ex);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
    }
    
	public int getBuchStatus(Buch buch){
        //open();
		
	   int anzahlEx;
       int anzahlVerliehen = 0;

        Set <Exemplar> set =buch.getExemplars();
        anzahlEx = set.size();
        for ( Iterator<Exemplar> i = set.iterator(); i.hasNext(); )
        {
          Exemplar e = i.next();
          Set<ExemplarBenutzer>  eb =e.getExemplarBenutzers();
          anzahlVerliehen= eb.size();
        }

         // close();

		return anzahlEx - anzahlVerliehen;
	}
    
}
