package Model.metier;

import javax.persistence.*;

@Entity
@Table(name = "LignePanier")
public class LignePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLignePanier")
    private int idLignePanier;

    @ManyToOne
    @JoinColumn(name = "IdPanier")
    private  Panier panier;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "Quantite")
    private int quantite;

	public LignePanier(Panier panier, Produit produit, int quantite) {
		super();
		this.panier = panier;
		this.produit = produit;
		this.quantite = quantite;
	}

    public LignePanier() {
    	
    }

	public int getIdLignePanier() {
		return idLignePanier;
	}

	public void setIdLignePanier(int idLignePanier) {
		this.idLignePanier = idLignePanier;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
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
