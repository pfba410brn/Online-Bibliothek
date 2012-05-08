package db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the EXEMPLAR database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Exemplar.findAll", query = "SELECT e FROM Exemplar e"),
    @NamedQuery(name = "Exemplar.findByInventarnr", query = "SELECT e FROM Exemplar e WHERE e.inventarnr = :inventarnr"),
    @NamedQuery(name = "Exemplar.findByZustand", query = "SELECT e FROM Exemplar e WHERE e.zustand = :zustand")})
public class Exemplar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String inventarnr;

	private String zustand;

	//bi-directional many-to-one association to Buch
    @ManyToOne
	@JoinColumn(name="ISBN")
	private Buch buch;

	//bi-directional many-to-one association to ExemplarBenutzer
	@OneToMany(mappedBy="exemplar")
	private Set<ExemplarBenutzer> exemplarBenutzers;

    public Exemplar() {
    }

	public String getInventarnr() {
		return this.inventarnr;
	}

	public void setInventarnr(String inventarnr) {
		this.inventarnr = inventarnr;
	}

	public String getZustand() {
		return this.zustand;
	}

	public void setZustand(String zustand) {
		this.zustand = zustand;
	}

	public Buch getBuch() {
		return this.buch;
	}

	public void setBuch(Buch buch) {
		this.buch = buch;
	}
	
	public Set<ExemplarBenutzer> getExemplarBenutzers() {
		return this.exemplarBenutzers;
	}

	public void setExemplarBenutzers(Set<ExemplarBenutzer> exemplarBenutzers) {
		this.exemplarBenutzers = exemplarBenutzers;
	}
	
}