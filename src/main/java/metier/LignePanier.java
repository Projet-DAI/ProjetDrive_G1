package metier;

import javax.persistence.*;

@Entity
@Table(name = "LignePanier")
public class LignePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLignePanier")
    private int idLignePanier;

    @ManyToOne
    @JoinColumn(name = "IdPanier")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "Quantite")
    private int quantite;

    // Getters and setters
}
