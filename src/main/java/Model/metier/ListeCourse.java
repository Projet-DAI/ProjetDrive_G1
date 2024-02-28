package Model.metier;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    
    @OneToMany(mappedBy = "listeCourse", cascade = CascadeType.ALL)
    private List<PostIt> postits;

    @Column(name = "NomListeCourse")
    private String nomListeCourse;

    @Column(name = "DateCreation")
    private Date dateCreation;

	public ListeCourse(Client client, String nomListeCourse, Date dateCreation) {
		super();
		this.client = client;
		this.nomListeCourse = nomListeCourse;
		this.dateCreation = dateCreation;
	}
	
	
	public ListeCourse() {
		
	}

	public int getIdListeCourse() {
		return idListeCourse;
	}

	public void setIdListeCourse(int idListeCourse) {
		this.idListeCourse = idListeCourse;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNomListeCourse() {
		return nomListeCourse;
	}

	public void setNomListeCourse(String nomListeCourse) {
		this.nomListeCourse = nomListeCourse;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

    
    
}
