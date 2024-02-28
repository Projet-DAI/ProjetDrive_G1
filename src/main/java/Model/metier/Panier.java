package Model.metier;

import javax.persistence.*;

import java.util.Date;

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
    
    
}
