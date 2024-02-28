package Model.metier;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdStock")
    private int idStock;

    @ManyToOne
    @JoinColumn(name = "IdMagasin")
    private Magasin magasin;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "QuantiteEnStock")
    private int quantiteEnStock;

    @Column(name = "DateDisponibilite")
    private Date dateDisponibilite;

	public Stock(Magasin magasin, Produit produit, int quantiteEnStock, Date dateDisponibilite) {
		super();
		this.magasin = magasin;
		this.produit = produit;
		this.quantiteEnStock = quantiteEnStock;
		this.dateDisponibilite = dateDisponibilite;
	}

    public Stock() {
    	
    }

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantiteEnStock() {
		return quantiteEnStock;
	}

	public void setQuantiteEnStock(int quantiteEnStock) {
		this.quantiteEnStock = quantiteEnStock;
	}

	public Date getDateDisponibilite() {
		return dateDisponibilite;
	}

	public void setDateDisponibilite(Date dateDisponibilite) {
		this.dateDisponibilite = dateDisponibilite;
	}
    
}
