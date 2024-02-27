package metier;

import javax.persistence.*;

@Entity
@Table(name = "Magasin")
public class Magasin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMagasin")
    private int idMagasin;

    @Column(name = "NomMagasin")
    private String nomMagasin;

    @Column(name = "AdresseMagasin")
    private String adresseMagasin;

    // Getters and setters
}

