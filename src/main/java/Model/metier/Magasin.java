package Model.metier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Magasin")
public class Magasin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMagasin")
    private int idMagasin;

    @Column(name = "NomMagasin")
    private String nomMagasin;

    @Column(name = "AdresseMagasin")
    private String adresseMagasin;
    
    @ManyToMany(mappedBy = "magasins")
    private Set<TempsRetait> tempsRetaits = new HashSet<>();

    public Magasin() {
    	
    }

	public Magasin(int idMagasin, String nomMagasin, String adresseMagasin, Set<TempsRetait> tempsRetaits) {
		super();
		this.idMagasin = idMagasin;
		this.nomMagasin = nomMagasin;
		this.adresseMagasin = adresseMagasin;
		this.tempsRetaits = tempsRetaits;
	}

	public int getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(int idMagasin) {
		this.idMagasin = idMagasin;
	}

	public String getNomMagasin() {
		return nomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		this.nomMagasin = nomMagasin;
	}

	public String getAdresseMagasin() {
		return adresseMagasin;
	}

	public void setAdresseMagasin(String adresseMagasin) {
		this.adresseMagasin = adresseMagasin;
	}

	public Set<TempsRetait> getTempsRetaits() {
		return tempsRetaits;
	}

	public void setTempsRetaits(Set<TempsRetait> tempsRetaits) {
		this.tempsRetaits = tempsRetaits;
	}

	
    
}

