package db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Die JPA Persistenz Klasse fuer die Tabelle MEDIUM.
 * letzte Aenderung: 30.05.2012
 * @author Christian.Kauf
 * @version 0.01
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Medium.findAll", query = "SELECT m FROM Medium m"),
    @NamedQuery(name = "Medium.findByMediumId", query = "SELECT m FROM Medium m WHERE m.mediumId = :mediumId"),
    @NamedQuery(name = "Medium.findByBezeichnung", query = "SELECT m FROM Medium m WHERE m.bezeichnung = :bezeichnung")})
public class Medium implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MEDIUM_ID")
	private long mediumId;

	private String bezeichnung;

    public Medium() {
    }

	public long getMediumId() {
		return this.mediumId;
	}

	public void setMediumId(long mediumId) {
		this.mediumId = mediumId;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}