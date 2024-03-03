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
    
    @Column(name = "TempsRetaitCom")
    private String tempsRetaitCom;

    @ManyToOne
    @JoinColumn(name = "IdStatutCommande")
    private StatutCommande statutCommande;
    
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignesCommande;
    
    @ManyToOne
    @JoinColumn(name = "IdMagasin") 
    private Magasin magasin;

    
	public Commande() {
		
	}


	public Commande(int idCommande, Client client, Date dateCommande, double montantTotal, String tempsRetaitCom,
			StatutCommande statutCommande, List<LigneCommande> lignesCommande, Magasin magasin) {
		super();
		this.idCommande = idCommande;
		this.client = client;
		this.dateCommande = dateCommande;
		this.montantTotal = montantTotal;
		this.tempsRetaitCom = tempsRetaitCom;
		this.statutCommande = statutCommande;
		this.lignesCommande = lignesCommande;
		this.magasin = magasin;
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


	public String getTempsRetaitCom() {
		return tempsRetaitCom;
	}


	public void setTempsRetaitCom(String tempsRetaitCom) {
		this.tempsRetaitCom = tempsRetaitCom;
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


	public Magasin getMagasin() {
		return magasin;
	}


	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	

	
}

