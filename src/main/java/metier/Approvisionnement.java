package metier;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Approvisionnement")
public class Approvisionnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdApprovisionnement")
    private int idApprovisionnement;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "DateApprovisionnement")
    private Date dateApprovisionnement;

    @Column(name = "QuantiteApprovisionnement")
    private int quantiteApprovisionnement;

	public Approvisionnement(Produit produit, Date dateApprovisionnement, int quantiteApprovisionnement) {
		super();
		this.produit = produit;
		this.dateApprovisionnement = dateApprovisionnement;
		this.quantiteApprovisionnement = quantiteApprovisionnement;
	}

    public Approvisionnement() {
    	
    }

	public int getIdApprovisionnement() {
		return idApprovisionnement;
	}

	public void setIdApprovisionnement(int idApprovisionnement) {
		this.idApprovisionnement = idApprovisionnement;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Date getDateApprovisionnement() {
		return dateApprovisionnement;
	}

	public void setDateApprovisionnement(Date dateApprovisionnement) {
		this.dateApprovisionnement = dateApprovisionnement;
	}

	public int getQuantiteApprovisionnement() {
		return quantiteApprovisionnement;
	}

	public void setQuantiteApprovisionnement(int quantiteApprovisionnement) {
		this.quantiteApprovisionnement = quantiteApprovisionnement;
	}
    
    
}
