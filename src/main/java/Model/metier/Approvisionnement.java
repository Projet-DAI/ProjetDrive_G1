package Model.metier;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Approvisionnement")
public class Approvisionnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdApprovisionnement")
    private int idApprovisionnement;

    @ManyToMany(mappedBy = "approvisionnements")
    private List<Produit> produits;

    @Column(name = "DateApprovisionnement")
    private Date dateApprovisionnement;

    @Column(name = "QuantiteApprovisionnement")
    private int quantiteApprovisionnement;

	public Approvisionnement(List<Produit> produits, Date dateApprovisionnement, int quantiteApprovisionnement) {
		super();
		this.produits = produits;
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



	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
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
