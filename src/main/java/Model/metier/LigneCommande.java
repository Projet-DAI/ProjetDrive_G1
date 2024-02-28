package Model.metier;

import javax.persistence.*;

@Entity
@Table(name = "LignesCommande")
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLigneCommande")
    private int idLigneCommande;

    @ManyToOne
    @JoinColumn(name = "IdCommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "Quantite")
    private int quantite;

    @Column(name = "PrixUnitaire")
    private double prixUnitaire;

	public LigneCommande( Commande commande, Produit produit, int quantite, double prixUnitaire) {
		super();
		this.commande = commande;
		this.produit = produit;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
	}
	
	public LigneCommande() {
		
	}

	public int getIdLigneCommande() {
		return idLigneCommande;
	}

	public void setIdLigneCommande(int idLigneCommande) {
		this.idLigneCommande = idLigneCommande;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
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

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
    
    

}

