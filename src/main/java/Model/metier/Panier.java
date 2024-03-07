package Model.metier;

import javax.persistence.*;


import org.hibernate.Hibernate;

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

    @ManyToOne
    @JoinColumn(name = "IdClient")
    private Client client;

    @Column(name = "DateCreation")
    private Date dateCreation;
    
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LignePanier> lignesPanier;


	public Panier(Date dateCreation,Client client ) {
		super();
		this.client = client;
		this.dateCreation = dateCreation;
        this.lignesPanier = new ArrayList<>();

	}
	
    public Panier() {
        this.lignesPanier = new ArrayList<>();
        }

        
   /* public void ajouterProduit(Produit produit, int quantite) {
        // Créez une nouvelle ligne de panier avec le produit et la quantité
        LignePanier nouvelleLigne = new LignePanier(produit, quantite);
        
        // Ajoutez la nouvelle ligne de panier à la liste des lignes de panier du panier
        lignesPanier.add(nouvelleLigne);
    }*/



    	
    

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
