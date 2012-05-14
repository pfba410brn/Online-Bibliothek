
package db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EXEMPLAR_BENUTZER database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ExemplarBenutzer.findAll", query = "SELECT e FROM ExemplarBenutzer e"),
//    @NamedQuery(name = "ExemplarBenutzer.findByBenutzerId", query = "SELECT e FROM ExemplarBenutzer e WHERE e.exemplarBenutzerPK.benutzerId = :benutzerId"),
//    @NamedQuery(name = "ExemplarBenutzer.findByInventarnr", query = "SELECT e FROM ExemplarBenutzer e WHERE e.exemplarBenutzerPK.inventarnr = :inventarnr"),
    @NamedQuery(name = "ExemplarBenutzer.findByDauer", query = "SELECT e FROM ExemplarBenutzer e WHERE e.dauer = :dauer"),
    @NamedQuery(name = "ExemplarBenutzer.findByDatum", query = "SELECT e FROM ExemplarBenutzer e WHERE e.datum = :datum"),
    @NamedQuery(name = "ExemplarBenutzer.findByVerliehenVon", query = "SELECT e FROM ExemplarBenutzer e WHERE e.verliehenVon = :verliehenVon")})
@Table(name="EXEMPLAR_BENUTZER")
public class ExemplarBenutzer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExemplarBenutzerPK id;

    @Temporal( TemporalType.DATE)
	private Date datum;

	private BigDecimal dauer;

	@Column(name="VERLIEHEN_VON")
	private String verliehenVon;

	@JoinColumn(name = "INVENTARNR", referencedColumnName = "INVENTARNR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Exemplar exemplar;
    @JoinColumn(name = "BENUTZER_ID", referencedColumnName = "BENUTZER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Benutzer benutzer;
    public ExemplarBenutzer() {
    }

	public ExemplarBenutzerPK getId() {
		return this.id;
	}

	public void setId(ExemplarBenutzerPK id) {
		this.id = id;
	}
	
	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public BigDecimal getDauer() {
		return this.dauer;
	}

	public void setDauer(BigDecimal dauer) {
		this.dauer = dauer;
	}

	public String getVerliehenVon() {
		return this.verliehenVon;
	}

	public void setVerliehenVon(String verliehenVon) {
		this.verliehenVon = verliehenVon;
	}

	public Benutzer getBenutzer() {
		return this.benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}
	
	public Exemplar getExemplar() {
		return this.exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}
	
}