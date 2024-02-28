package Model.metier;

import javax.persistence.*;

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


	public Panier(Client client, Date dateCreation) {
		super();
		this.client = client;
		this.dateCreation = dateCreation;
	}

    public Panier() {
    	
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
