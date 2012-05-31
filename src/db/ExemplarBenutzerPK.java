package db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Die JPA Schlüssel Klasse fuer die Tabelle EXEMPLAR_BENUTZER.
 * letzte Aenderung: 30.05.2012
 * @author Christian.Kauf
 * @version 0.01
 */
@Embeddable
public class ExemplarBenutzerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BENUTZER_ID")
	private long benutzerId;
	@Column(name="INVENTARNR")
	private String inventarnr;

    public ExemplarBenutzerPK() {
    }
	public long getBenutzerId() {
		return this.benutzerId;
	}
	public void setBenutzerId(long benutzerId) {
		this.benutzerId = benutzerId;
	}
	public String getInventarnr() {
		return this.inventarnr;
	}
	public void setInventarnr(String inventarnr) {
		this.inventarnr = inventarnr;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExemplarBenutzerPK)) {
			return false;
		}
		ExemplarBenutzerPK castOther = (ExemplarBenutzerPK)other;
		return 
			(this.benutzerId == castOther.benutzerId)
			&& this.inventarnr.equals(castOther.inventarnr);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.benutzerId ^ (this.benutzerId >>> 32)));
		hash = hash * prime + this.inventarnr.hashCode();
		
		return hash;
    }
}