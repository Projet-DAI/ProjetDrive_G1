package metier;

import javax.persistence.*;

@Entity
@Table(name = "Rayon")
public class Rayon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRayon")
    private int idRayon;

    @Column(name = "NomRayon")
    private String nomRayon;

    // Getters and setters
}
