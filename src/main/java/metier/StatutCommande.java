package metier;

import javax.persistence.*;

@Entity
@Table(name = "StatutCommande")
public class StatutCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdStatutCommande")
    private int idStatutCommande;

    @Column(name = "LibelleStatut")
    private String libelleStatut;

    // Getters and setters
}
