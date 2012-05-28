package db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the BENUTZER database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Benutzer.findAll", query = "SELECT b FROM Benutzer b"),
    @NamedQuery(name = "Benutzer.findByBenutzerId", query = "SELECT b FROM Benutzer b WHERE b.benutzerId = :benutzerId"),
    @NamedQuery(name = "Benutzer.findByNachname", query = "SELECT b FROM Benutzer b WHERE b.nachname = :nachname"),
    @NamedQuery(name = "Benutzer.findByVorname", query = "SELECT b FROM Benutzer b WHERE b.vorname = :vorname"),
    @NamedQuery(name = "Benutzer.findByEmail", query = "SELECT b FROM Benutzer b WHERE b.email = :email"),
    @NamedQuery(name = "Benutzer.findByTelefonnr", query = "SELECT b FROM Benutzer b WHERE b.telefonnr = :telefonnr"),
    @NamedQuery(name = "Benutzer.findByPlz", query = "SELECT b FROM Benutzer b WHERE b.plz = :plz"),
    @NamedQuery(name = "Benutzer.findByOrt", query = "SELECT b FROM Benutzer b WHERE b.ort = :ort"),
    @NamedQuery(name = "Benutzer.findByPasswort", query = "SELECT b FROM Benutzer b WHERE b.passwort = :passwort")})
public class Benutzer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BENUTZER_ID")
	private long benutzerId;

	private String email;

	private String nachname;

	private String ort;

	private String passwort;

	private String plz;

	@Column(name="\"STRAßE\"")
	private String STRAßE;

	private String telefonnr;

	private String vorname;

	//bi-directional many-to-one association to Benutzergruppe
    @ManyToOne
	@JoinColumn(name="GRUPPEN_ID")
	private Benutzergruppe benutzergruppe;

	//bi-directional many-to-one association to ExemplarBenutzer
	@OneToMany(mappedBy="benutzer")
	private Set<ExemplarBenutzer> exemplarBenutzers;

    public Benutzer() {
    }

	public long getBenutzerId() {
		return this.benutzerId;
	}

	public void setBenutzerId(long benutzerId) {
		this.benutzerId = benutzerId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPlz() {
		return this.plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getSTRAßE() {
		return this.STRAßE;
	}

	public void setSTRAßE(String STRAßE) {
		this.STRAßE = STRAßE;
	}

	public String getTelefonnr() {
		return this.telefonnr;
	}

	public void setTelefonnr(String telefonnr) {
		this.telefonnr = telefonnr;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Benutzergruppe getBenutzergruppe() {
		return this.benutzergruppe;
	}

	public void setBenutzergruppe(Benutzergruppe benutzergruppe) {
		this.benutzergruppe = benutzergruppe;
	}
	
	public Set<ExemplarBenutzer> getExemplarBenutzers() {
		return this.exemplarBenutzers;
	}

	public void setExemplarBenutzers(Set<ExemplarBenutzer> exemplarBenutzers) {
		this.exemplarBenutzers = exemplarBenutzers;
	}
	
}