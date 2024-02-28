package metier;

import javax.persistence.*;

@Entity
@Table(name = "LigneListeCourse")
public class LigneListeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLigneListeCourse")
    private int idLigneListeCourse;

    @ManyToOne
    @JoinColumn(name = "IdListeCourse")
    private ListeCourse listeCourse;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "Quantite")
    private int quantite;

	public LigneListeCourse(ListeCourse listeCourse, Produit produit, int quantite) {
		super();
		this.listeCourse = listeCourse;
		this.produit = produit;
		this.quantite = quantite;
	}
	
	public LigneListeCourse() {
		
	}

	public int getIdLigneListeCourse() {
		return idLigneListeCourse;
	}

	public void setIdLigneListeCourse(int idLigneListeCourse) {
		this.idLigneListeCourse = idLigneListeCourse;
	}

	public ListeCourse getListeCourse() {
		return listeCourse;
	}

	public void setListeCourse(ListeCourse listeCourse) {
		this.listeCourse = listeCourse;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

    
}
