package Model.metier;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategorie")
    private int idCategorie;

    @Column(name = "NomCategorie")
    private String nomCategorie;

    @ManyToOne
    @JoinColumn(name = "IdRayon")
    private Rayon rayon;
    
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Produit> produits;

	public Categories(String nomCategorie, Rayon rayon) {
		super();
		this.nomCategorie = nomCategorie;
		this.rayon = rayon;
	}

    public Categories() {
    	
    }

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}
    
}
