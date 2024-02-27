package metier;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "IdMagasin")
    private Magasin magasin;

    // Getters and setters
}

