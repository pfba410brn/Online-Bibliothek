package db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
/**
 * Die JPA Persistenz Klasse fuer die Tabelle BUCH.
 * letzte Aenderung: 30.05.2012
 * @author Christian.Kauf
 * @version 0.01
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Buch.findAll", query = "SELECT b FROM Buch b"),
    @NamedQuery(name = "Buch.findByIsbn", query = "SELECT b FROM Buch b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Buch.findByTitel", query = "SELECT b FROM Buch b WHERE b.titel = :titel"),
    @NamedQuery(name = "Buch.findByAutor", query = "SELECT b FROM Buch b WHERE b.autor = :autor"),
    @NamedQuery(name = "Buch.findByKurzbeschreibung", query = "SELECT b FROM Buch b WHERE b.kurzbeschreibung = :kurzbeschreibung"),
    @NamedQuery(name = "Buch.findByVerlag", query = "SELECT b FROM Buch b WHERE b.verlag = :verlag"),
    @NamedQuery(name = "Buch.findByMediumId", query = "SELECT b FROM Buch b WHERE b.mediumId = :mediumId")})
public class Buch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String isbn;

	private String autor;

	private String kurzbeschreibung;

	@Column(name="MEDIUM_ID")
	private BigDecimal mediumId;

	private String titel;

	private String verlag;

	//bi-directional many-to-one association to Exemplar
	@OneToMany(mappedBy="buch")
	private Set<Exemplar> exemplars;

    public Buch() {
    }

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getKurzbeschreibung() {
		return this.kurzbeschreibung;
	}

	public void setKurzbeschreibung(String kurzbeschreibung) {
		this.kurzbeschreibung = kurzbeschreibung;
	}

	public BigDecimal getMediumId() {
		return this.mediumId;
	}

	public void setMediumId(BigDecimal mediumId) {
		this.mediumId = mediumId;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getVerlag() {
		return this.verlag;
	}

	public void setVerlag(String verlag) {
		this.verlag = verlag;
	}

	public Set<Exemplar> getExemplars() {
		return this.exemplars;
	}

	public void setExemplars(Set<Exemplar> exemplars) {
		this.exemplars = exemplars;
	}
	
}