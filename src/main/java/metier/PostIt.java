package metier;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PostIt")
public class PostIt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPostIt")
    private int idPostIt;

    @ManyToOne
    @JoinColumn(name = "IdClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "IdListeCourse")
    private ListeCourse listeCourse;

    @Column(name = "Titre")
    private String titre;

    @Column(name = "Contenu")
    private String contenu;

    @Column(name = "DateCreation")
    private Date dateCreation;

    // Getters and setters
}
