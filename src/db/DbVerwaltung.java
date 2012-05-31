package db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Die Klasse verwaltet alle schreibenden und lesenden Zugriffe auf die Bibliotheks-Datenbank
 * über die mit JPA erstellten Entity-Klassen
 * letzte Aenderung: 30.05.2012
 * @author Christian.Kauf
 * @version 0.02
 */

public class DbVerwaltung {
    
    public  EntityManager em;
    public  EntityManagerFactory factory;
	public EntityTransaction trans;
    
    public DbVerwaltung() {
        super();
    }

    /**
     * Die Methode erstellt eine Verbindung zur Datenbank
     * @return boolean true=Verbindung zur Db aufgebaut false=Verbindung fehlgeschlagen
     */
    private boolean open(){
        try {
            
            this.factory = Persistence.createEntityManagerFactory("jpa");
            this.em = factory.createEntityManager();
            
            trans = this.em.getTransaction();
            trans.begin();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Die Methode schließt die Verbindung zur Datenbank
     * @return boolean true=Verbindung beendet  false=Fehler beim schließen der Verbindung
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
    
    /**
     * Auslesen aller Benutzer aus der DB
     * @return List<Benutzer> Liste aller Benutzer
     */
    public List<Benutzer> selectAll_Benutzer(){
    
            open();
        
            Query query = this.em.createNamedQuery("Benutzer.findAll");
           
            @SuppressWarnings("unchecked")
            List<Benutzer>resultList = query.getResultList();
            close();
            return resultList;
                
    }

    /**
     * Auslesen aller Buecher aus der DB
     * @return List<Buch> Liste von Buchobjekten
     */
    public List<Buch> selectAll_Buecher(){
        
            open();
        
            Query query = this.em.createNamedQuery("Buch.findAll");
           
            @SuppressWarnings("unchecked")
            List<Buch>resultList = query.getResultList();
            close();
            return resultList;
                
    }
    
    /**
     * Suche nach Buch mit einer bestimmten ISBN
     * @param isbn ISBN des gesuchten Buchs
     * @return Buch Gesuchtes Buchobjekt
     */
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
    
    
    /**
     * Suche nach Buch mit einer bestimmten ID
     * @param id ID des gesuchten Buchs
     * @return Buch Gesuchtes Buchobjekt
     */
    public Benutzer select_BenutzerUeberID(Long id){
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

    /**
     * Speichern eines Benutzerobjekts in die Datenbank
     * @param user User Objekt das gespeichert wird
     * @return boolean true=Erfolgreich gespeichert  false=Fehler beim Speichern in die Db
     */
    public boolean insertBenutzer(Benutzer user){
        
            open();
            String queryString = "SELECT MAX(b.benutzerId) from Benutzer b";
            Query query = this.em.createQuery(queryString);
            
            @SuppressWarnings("unchecked")
            List resultList = query.getResultList();
            user.setBenutzerId(new Long(Integer.parseInt(resultList.get(0).toString())+1));
         try {
             this.em.persist(user);
             this.trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    /**
     * Benutzer Objekt in der Db updaten
     * @param user User Objekt das geupdatet wird
     * @return boolean  true=update erfolgreich  false=update fehler
     */
    public boolean updateBenutzer(Benutzer user){
              open();
                 
         try {
             em.merge(user);
             this.trans.commit();
             this.em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }

    /**
     * Benutzer aus der Datenbank loeschen
     * @param user User objekt das geloescht wird
     * @return boolean true=Loeschen erfolgreich  false= Loeschen fehlgeschlagen
     */
    public boolean deleteBenutzer(Benutzer user){

         open();
       
         try {
             Benutzer userx = em.find(Benutzer.class, user.getBenutzerId());
             em.remove(userx);
             this.trans.commit();
             
             this.em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    /**
     * Auslesen aller Medien aus der DB
     * @return List<Medium> Liste der Medienobjekte
     */
    public List<Medium> selectAll_Medium(){
            open();
        
            Query query = this.em.createNamedQuery("Medium.findAll");
           
            @SuppressWarnings("unchecked")
            List<Medium>resultList = query.getResultList();
            close();
            return resultList;
    }
    
	/**
	 * Suche einer Benutzergruppe ueber ID 
	 * @param id Id der gesuchten Benutzergruppe
	 * @return Benutzergruppe gesuchte Benutzergruppe
	 */
	public Benutzergruppe selectBenutzerGruppeUeberID(Long id) {
		open();

		Query query = this.em
				.createNamedQuery("Benutzergruppe.findByGruppenId");
		query.setParameter("gruppenId", id);

		@SuppressWarnings("unchecked")
		List<Benutzergruppe> resultList = query.getResultList();
		close();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList.get(0);
		}
	}
    
    /**
     * Auslesen der Beziehungstabelle ExemplarBenutzer
     * @return List<ExemplarBenutzer> Liste mit allen ExemplarBenutzerobjekten
     */
    public List<ExemplarBenutzer> selectAll_ExemplarBenutzer(){
    
           open();
        
            Query query = this.em.createNamedQuery("ExemplarBenutzer.findAll");
           
            @SuppressWarnings("unchecked")
            List<ExemplarBenutzer>resultList = query.getResultList();
            close();
            return resultList;
    }
    
    /**
     * Speichern eines ExemplarBenutzers in die Db
     * @param exBe ExemplarBenutzer Objekt das gespeichert werden soll
     * @return boolean true=Speichern erfolgreich false=Speichern fehlgeschlagen
     */
    public  boolean insertExemplarBenutzer(ExemplarBenutzer exBe){

         open();
        
         try {
             this.em.persist(exBe);
             this.trans.commit();
             
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         this.em.close();
        return true;
    }
    
    /**
     * ExemplarBenutzer Objekt in der Db updaten
     * @param exBe ExemplarBenutzer Objekt das geupdatet wird
     * @return boolean  true=update erfolgreich  false=update fehler
     */
    public boolean updateExemplarBenutzer(ExemplarBenutzer exBe){

         open();

         try {
             this.em.persist(exBe);
             this.trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    /**
     * Loeschen eines ExemplarBenutzer Objekts
     * @param exBe ExemplarBenutzer Objekt das geloescht wird
     * @return boolean true=Loeschen erfolgreich false=Loeschen fehlgeschlagen
     */
    public boolean deleteExemplarBenutzer(ExemplarBenutzer exBe){
         open();

         try {
             ExemplarBenutzer exBex = em.find(ExemplarBenutzer.class,exBe.getId());
             em.remove(exBex);
             this.trans.commit();
             this.em.persist(exBe);
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
        
    }
    
    /**
     * Auslesen der Tabelle Exemplar
     * @return List<Exemplar> Liste von Exemplarobjekten
     */
    public List<Exemplar> selectAll_Exemplar(){

            open();
        
            Query query = this.em.createNamedQuery("Exemplar.findAll");
           
            @SuppressWarnings("unchecked")
            List<Exemplar>resultList = query.getResultList();
            close();
            return resultList;
    }
    
    /**
     * Speichern eines Exemplarobjekts in die Db
     * @param ex Exemplar Objekt das gespeichert werden soll
     * @return boolean true=Speichern erfolgreich false=Speichern fehlgeschlagen
     */
    public boolean insertExemplar(ExemplarBenutzer ex){

        open();
        
         try {
             this.em.persist(ex);
             this.trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.close();
            return false;
        }
         this.em.close();
        return true;
    }
    
	/**
	 * Ermittelt die Verfügbarkeit von Exemplaren eines bestimmten Buches
	 * @param buch
	 * @return int Anzahl verfuegbarer Buecher
	 */
	public int getBuchStatus(Buch buch){
		
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

		return anzahlEx - anzahlVerliehen;
	}
	
	 /**
	 * Laedt alle verliehenen Exemplare pro Benutzer aus der Db
	 * @param benutzerId ID des Benutzers
	 * @return List<Exemplar> Liste mit den gesuchten Exemplaren
	 */
	public List<Exemplar> selectVerlieheneExemplareProBenutzer(int benutzerId){
		 open();
		 Benutzer benutzer=null;
		 Query query = this.em.createNamedQuery("Benutzer.findByBenutzerId");
		 query.setParameter("benutzerId", benutzerId);
		 @SuppressWarnings("unchecked")
		 List<Benutzer> beList = query.getResultList();
		 
		 if(beList.isEmpty()){
			  close();
			  return null;
		 }
		 benutzer = beList.get(0);
		 close();
         Set<ExemplarBenutzer> set =benutzer.getExemplarBenutzers();
        
         if(set!=null){
 	         List<Exemplar> resultList = new ArrayList<Exemplar>();
	         for ( Iterator<ExemplarBenutzer> i = set.iterator(); i.hasNext();)
	         {
	           ExemplarBenutzer e = i.next();
	           resultList.add(e.getExemplar());
	           
	         }
	         return resultList;
         }else
        	 return null;
 }
    
	 
}
