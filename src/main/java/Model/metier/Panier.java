package Model.metier;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.metier.*;

@Entity
@Table(name = "Panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPanier")
    private int idPanier;

    @OneToOne
    @JoinColumn(name = "IdClient")
    private Client client;

    @Column(name = "DateCreation")
    private Date dateCreation;
    
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<LignePanier> lignesPanier;


	public Panier(Date dateCreation,Client client ) {
		super();
		this.client = client;
		this.dateCreation = dateCreation;
	}
	
    public Panier() {
         List<LignePanier> lignesPanier;
        this.lignesPanier = new ArrayList<>();}

        
     // Méthode pour ajouter un produit avec une quantité au panier
        public boolean ajouterProduit(Produit produit, int quantite) {
            // Vérifier si le produit et la quantité sont valides
            if (produit != null && quantite > 0) {
                // Créer une nouvelle ligne de panier avec le produit et la quantité
                LignePanier lignePanier = new LignePanier(this, produit, quantite);
                
                // Ajouter la ligne de panier à la liste des lignes de panier du panier
                lignesPanier.add(lignePanier);
                
                // Retourner true pour indiquer que l'ajout a réussi
                return true;
            } else {
                // Retourner false pour indiquer que l'ajout a échoué
                return false;
            }
        }


    	
    

	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	 public List<LignePanier> getLignesPanier() {
	        return lignesPanier;
	    }
	 
	 public void setLignesPanier(List<LignePanier> lignesPanier) {
	        this.lignesPanier = lignesPanier;
	    }
	    

    
    
}
