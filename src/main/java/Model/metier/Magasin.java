package Model.metier;

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

	public Magasin(String nomMagasin, String adresseMagasin) {
		super();
		this.nomMagasin = nomMagasin;
		this.adresseMagasin = adresseMagasin;
	}

    public Magasin() {
    	
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
    
    
}

