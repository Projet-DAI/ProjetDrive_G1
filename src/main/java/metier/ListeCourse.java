package metier;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ListeCourse")
public class ListeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdListeCourse")
    private int idListeCourse;

    @ManyToOne
    @JoinColumn(name = "IdClient")
    private Client client;

    @Column(name = "NomListeCourse")
    private String nomListeCourse;

    @Column(name = "DateCreation")
    private Date dateCreation;

    // Getters and setters
}
