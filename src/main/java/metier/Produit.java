package metier;

import javax.persistence.*;

@Entity
@Table(name = "Produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProduit")
    private int idProduit;

    @Column(name = "NomProduit")
    private String nomProduit;

    @Column(name = "PrixProduit")
    private double prixProduit;

    @Column(name = "MarqueProduit")
    private String marqueProduit;

    @Column(name = "Promotion")
    private boolean promotion;

    @Column(name = "PourcentagePromotion")
    private double pourcentagePromotion;

    @Column(name = "AdresseImageProduit")
    private String adresseImageProduit;

    @Column(name = "Nutriscore")
    private String nutriscore;

    @ManyToOne
    @JoinColumn(name = "IdCategorie")
    private Categories categorie;

    // Getters and setters
}
