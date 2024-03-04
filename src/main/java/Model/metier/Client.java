package Model.metier;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;

import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdClient")
    private int idClient;

    @Column(name = "NomCompletClient")
    private String nomCompletClient;

    @Column(name = "NomUtilisateurClient")
    private String nomUtilisateurClient;

    @Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nomCompletClient=" + nomCompletClient + ", nomUtilisateurClient="
				+ nomUtilisateurClient + ", emailClient=" + emailClient + ", telephoneClient=" + telephoneClient
				+ ", pwdClient=" + pwdClient + ", pointFideliteClient=" + pointFideliteClient + ", commandes="
				+ commandes + "]";
	}


	@Column(name = "emailClient")
    private String emailClient;
    
    @Column(name = "telephoneClient")
    private String telephoneClient;

    @Column(name = "PwdClient")
    private String pwdClient;

    @Column(name = "PointFideliteClient")
    private int pointFideliteClient;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;
    
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Panier panier;

	public Client(String nomCompletClient, String nomUtilisateurClient, String emailClient, String telephoneClient, String pwdClient,
			int pointFideliteClient, List<Commande> commandes) {
		super();
		this.telephoneClient = telephoneClient;
		this.nomCompletClient = nomCompletClient;
		this.nomUtilisateurClient = nomUtilisateurClient;
		this.emailClient = emailClient;
		this.pwdClient = pwdClient;
		this.pointFideliteClient = pointFideliteClient;
		this.commandes = commandes;
	}
    
    
    public Client() {
    	
    }

   /* public boolean verifierConnexion(String nomUtilisateur, String motDePasse) {
        return this.nomUtilisateurClient.equals(nomUtilisateur) && this.pwdClient.equals(motDePasse);
    }*/

	public Client(int clientId) {
		this.idClient=idClient;
		// TODO Auto-generated constructor stub
	}


	public int getIdClient() {
		return idClient;
	}


	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}


	public String getNomCompletClient() {
		return nomCompletClient;
	}


	public void setNomCompletClient(String nomCompletClient) {
		this.nomCompletClient = nomCompletClient;
	}


	public String getNomUtilisateurClient() {
		return nomUtilisateurClient;
	}


	public void setNomUtilisateurClient(String nomUtilisateurClient) {
		this.nomUtilisateurClient = nomUtilisateurClient;
	}


	public String getTelephoneClient() {
		return telephoneClient;
	}


	public void setTelephoneClient(String telephoneClient) {
		this.telephoneClient = telephoneClient;
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
	
	
	 public static boolean verifierConnexion(String nomUtilisateur, String motDePasse) {
	        boolean isValidUser = false;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        try {
	          
	            Query<Client> query = session.createQuery("FROM Client WHERE nomUtilisateurClient = :username AND pwdClient = :password");
	            query.setParameter("username", nomUtilisateur);
	            query.setParameter("password", motDePasse);

	            Client client = query.uniqueResult();

	            if (client != null) {
	                isValidUser = true;
	            }

	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }

	        return isValidUser;
    
}
}
