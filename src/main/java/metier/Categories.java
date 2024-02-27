package metier;

import javax.persistence.*;

@Entity
@Table(name = "Categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategorie")
    private int idCategorie;

    @Column(name = "NomCategorie")
    private String nomCategorie;

    @ManyToOne
    @JoinColumn(name = "IdRayon")
    private Rayon rayon;

    // Getters and setters
}
