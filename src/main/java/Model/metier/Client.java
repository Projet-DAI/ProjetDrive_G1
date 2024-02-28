package Model.metier;

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
    
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Panier panier;

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


	public int getIdClient() {
		return idClient;
	}


	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}


	public String getNomClient() {
		return nomClient;
	}


	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}


	public String getPrenomClient() {
		return prenomClient;
	}


	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}


	public String getEmailClient() {
		return emailClient;
	}


	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}


	public String getPwdClient() {
		return pwdClient;
	}


	public void setPwdClient(String pwdClient) {
		this.pwdClient = pwdClient;
	}


	public int getPointFideliteClient() {
		return pointFideliteClient;
	}


	public void setPointFideliteClient(int pointFideliteClient) {
		this.pointFideliteClient = pointFideliteClient;
	}


	public List<Commande> getCommandes() {
		return commandes;
	}


	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
    
    

}
