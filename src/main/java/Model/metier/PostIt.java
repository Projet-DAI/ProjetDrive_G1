package Model.metier;

import javax.persistence.*;

import java.time.LocalDateTime;
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

    @Column(name = "Contenu")
    private String contenu;

    @Column(name = "DateCreation")
    private LocalDateTime  dateCreation;

	public PostIt() {
		
	}

	public PostIt(int idPostIt, Client client, ListeCourse listeCourse, String contenu, LocalDateTime dateCreation) {
		super();
		this.idPostIt = idPostIt;
		this.client = client;
		this.listeCourse = listeCourse;
		this.contenu = contenu;
		this.dateCreation = dateCreation;
	}

	public int getIdPostIt() {
		return idPostIt;
	}

	public void setIdPostIt(int idPostIt) {
		this.idPostIt = idPostIt;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ListeCourse getListeCourse() {
		return listeCourse;
	}

	public void setListeCourse(ListeCourse listeCourse) {
		this.listeCourse = listeCourse;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
	
}
