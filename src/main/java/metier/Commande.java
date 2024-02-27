package metier;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCommande")
    private int idCommande;

    @ManyToOne
    @JoinColumn(name = "IdClient")
    private Client client;

    @Column(name = "DateCommande")
    private Date dateCommande;

    @Column(name = "MontantTotal")
    private double montantTotal;

    @ManyToOne
    @JoinColumn(name = "IdStatutCommande")
    private StatutCommande statutCommande;

    @ManyToOne
    @JoinColumn(name = "IdMagasin")
    private Magasin magasin;

	public Commande(Client client, Date dateCommande, double montantTotal, StatutCommande statutCommande,
			Magasin magasin) {
		super();
		this.client = client;
		this.dateCommande = dateCommande;
		this.montantTotal = montantTotal;
		this.statutCommande = statutCommande;
		this.magasin = magasin;
	}
	
	public Commande() {
		
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public StatutCommande getStatutCommande() {
		return statutCommande;
	}

	public void setStatutCommande(StatutCommande statutCommande) {
		this.statutCommande = statutCommande;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}
    
    

}

