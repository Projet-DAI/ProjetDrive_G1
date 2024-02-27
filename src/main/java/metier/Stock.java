package metier;

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

    // Getters and setters
}
