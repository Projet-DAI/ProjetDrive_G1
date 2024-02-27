package metier;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdClient")
    private int idClient;

    @Column(name = "NomClient")
    private String nomClient;

    @Column(name = "PrenomClient")
    private String prenomClient;

    @Column(name = "emailClient")
    private String emailClient;

    @Column(name = "PwdClient")
    private String pwdClient;

    @Column(name = "PointFideliteClient")
    private int pointFideliteClient;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;

	public Client(String nomClient, String prenomClient, String emailClient, String pwdClient,
			int pointFideliteClient, List<Commande> commandes) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.pwdClient = pwdClient;
		this.pointFideliteClient = pointFideliteClient;
		this.commandes = commandes;
	}
    
    
    public Client() {
    	
    }

    // Getters and setters
}
