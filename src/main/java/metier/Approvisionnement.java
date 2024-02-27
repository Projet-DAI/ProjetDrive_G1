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

    // Getters and setters
}
