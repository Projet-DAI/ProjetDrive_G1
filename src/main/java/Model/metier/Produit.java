package Model.metier;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProduit")
    private int idProduit;

    @Column(name = "NomProduit")
    private String nomProduit;

    @Column(name = "PrixProduit")
    private double prixProduit;

    @Column(name = "MarqueProduit")
    private String marqueProduit;

    @Column(name = "Promotion")
    private boolean promotion;

    @Column(name = "PourcentagePromotion")
    private double pourcentagePromotion;

    @Column(name = "AdresseImageProduit")
    private String adresseImageProduit;

    @Column(name = "Nutriscore")
    private String nutriscore;

    @ManyToOne
    @JoinColumn(name = "IdCategorie")
    private Categories categorie;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Produit_Approvisionnement",
               joinColumns = @JoinColumn(name = "produit_id"),
               inverseJoinColumns = @JoinColumn(name = "approvisionnement_id"))
    private List<Approvisionnement> approvisionnements;
    
	public Produit(String nomProduit, double prixProduit, String marqueProduit, boolean promotion,
			double pourcentagePromotion, String adresseImageProduit, String nutriscore, Categories categorie) {
		super();
		this.nomProduit = nomProduit;
		this.prixProduit = prixProduit;
		this.marqueProduit = marqueProduit;
		this.promotion = promotion;
		this.pourcentagePromotion = pourcentagePromotion;
		this.adresseImageProduit = adresseImageProduit;
		this.nutriscore = nutriscore;
		this.categorie = categorie;
	}
    
    
    public Produit() {
    	
    }


	public int getIdProduit() {
		return idProduit;
	}


	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}


	public String getNomProduit() {
		return nomProduit;
	}


	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}


	public double getPrixProduit() {
		return prixProduit;
	}


	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}


	public String getMarqueProduit() {
		return marqueProduit;
	}


	public void setMarqueProduit(String marqueProduit) {
		this.marqueProduit = marqueProduit;
	}


	public boolean isPromotion() {
		return promotion;
	}


	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}


	public double getPourcentagePromotion() {
		return pourcentagePromotion;
	}


	public void setPourcentagePromotion(double pourcentagePromotion) {
		this.pourcentagePromotion = pourcentagePromotion;
	}


	public String getAdresseImageProduit() {
		return adresseImageProduit;
	}


	public void setAdresseImageProduit(String adresseImageProduit) {
		this.adresseImageProduit = adresseImageProduit;
	}


	public String getNutriscore() {
		return nutriscore;
	}


	public void setNutriscore(String nutriscore) {
		this.nutriscore = nutriscore;
	}


	public Categories getCategorie() {
		return categorie;
	}


	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}
    
    

}
