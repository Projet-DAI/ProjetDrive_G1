package metier;

import javax.persistence.*;

@Entity
@Table(name = "ProduitRemplacement")
public class ProduitRemplacement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProduitRemplacement")
    private Long idProduitRemplacement;
    
    @ManyToOne
    @JoinColumn(name = "IdProduitPrincipal", referencedColumnName = "IdProduit")
    private Produit produitPrincipal;
    
    @ManyToOne
    @JoinColumn(name = "IdProduitRemplacement", referencedColumnName = "IdProduit")
    private Produit produitRemplacement;

    
    
    public ProduitRemplacement(Produit produitPrincipal, Produit produitRemplacement) {
		super();
		this.produitPrincipal = produitPrincipal;
		this.produitRemplacement = produitRemplacement;
	}
    
    public ProduitRemplacement() {
    	
    }

	public Long getIdProduitRemplacement() {
        return idProduitRemplacement;
    }

    public void setIdProduitRemplacement(Long idProduitRemplacement) {
        this.idProduitRemplacement = idProduitRemplacement;
    }

    public Produit getProduitPrincipal() {
        return produitPrincipal;
    }

    public void setProduitPrincipal(Produit produitPrincipal) {
        this.produitPrincipal = produitPrincipal;
    }

    public Produit getProduitRemplacement() {
        return produitRemplacement;
    }

    public void setProduitRemplacement(Produit produitRemplacement) {
        this.produitRemplacement = produitRemplacement;
    }
}
