package metier;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPanier")
    private int idPanier;

    @ManyToOne
    @JoinColumn(name = "IdClient")
    private Client client;

    @Column(name = "DateCreation")
    private Date dateCreation;

    // Getters and setters
}
