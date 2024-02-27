package metier;

import javax.persistence.*;

@Entity
@Table(name = "LignesCommande")
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLigneCommande")
    private int idLigneCommande;

    @ManyToOne
    @JoinColumn(name = "IdCommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "Quantite")
    private int quantite;

    @Column(name = "PrixUnitaire")
    private double prixUnitaire;

    // Getters and setters
}

