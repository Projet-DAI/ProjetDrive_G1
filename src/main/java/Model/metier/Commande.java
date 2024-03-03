package Model.metier;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignesCommande;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdTempsRetrait", referencedColumnName = "IdTempsRetrait")
    private TempsRetait tempsRetait;
    
	public Commande() {
		
	}

	public Commande(int idCommande, Client client, Date dateCommande, double montantTotal,
			StatutCommande statutCommande, List<LigneCommande> lignesCommande, TempsRetait tempsRetait) {
		super();
		this.idCommande = idCommande;
		this.client = client;
		this.dateCommande = dateCommande;
		this.montantTotal = montantTotal;
		this.statutCommande = statutCommande;
		this.lignesCommande = lignesCommande;
		this.tempsRetait = tempsRetait;
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

	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	public TempsRetait getTempsRetait() {
		return tempsRetait;
	}

	public void setTempsRetait(TempsRetait tempsRetait) {
		this.tempsRetait = tempsRetait;
	}

	
}

