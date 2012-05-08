package db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the BENUTZERGRUPPE database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Benutzergruppe.findAll", query = "SELECT b FROM Benutzergruppe b"),
    @NamedQuery(name = "Benutzergruppe.findByGruppenId", query = "SELECT b FROM Benutzergruppe b WHERE b.gruppenId = :gruppenId"),
    @NamedQuery(name = "Benutzergruppe.findByGruppenname", query = "SELECT b FROM Benutzergruppe b WHERE b.gruppenname = :gruppenname"),
    @NamedQuery(name = "Benutzergruppe.findByBerechtigung", query = "SELECT b FROM Benutzergruppe b WHERE b.berechtigung = :berechtigung")})
public class Benutzergruppe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GRUPPEN_ID")
	private long gruppenId;

	private BigDecimal berechtigung;

	private String gruppenname;

	//bi-directional many-to-one association to Benutzer
	@OneToMany(mappedBy="benutzergruppe")
	private Set<Benutzer> benutzers;

    public Benutzergruppe() {
    }

	public long getGruppenId() {
		return this.gruppenId;
	}

	public void setGruppenId(long gruppenId) {
		this.gruppenId = gruppenId;
	}

	public BigDecimal getBerechtigung() {
		return this.berechtigung;
	}

	public void setBerechtigung(BigDecimal berechtigung) {
		this.berechtigung = berechtigung;
	}

	public String getGruppenname() {
		return this.gruppenname;
	}

	public void setGruppenname(String gruppenname) {
		this.gruppenname = gruppenname;
	}

	public Set<Benutzer> getBenutzers() {
		return this.benutzers;
	}

	public void setBenutzers(Set<Benutzer> benutzers) {
		this.benutzers = benutzers;
	}
	
}