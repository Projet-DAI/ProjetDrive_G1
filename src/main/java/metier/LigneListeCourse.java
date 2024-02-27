package metier;

import javax.persistence.*;

@Entity
@Table(name = "LigneListeCourse")
public class LigneListeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLigneListeCourse")
    private int idLigneListeCourse;

    @ManyToOne
    @JoinColumn(name = "IdListeCourse")
    private ListeCourse listeCourse;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    private Produit produit;

    @Column(name = "Quantite")
    private int quantite;

    // Getters and setters
}
