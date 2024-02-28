package Model.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.metier.Client;
import Model.metier.LignePanier;

public class ClientDAO {
	
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

	    public List<LignePanier> getProduitsDansPanier(Client client) {
	        List<LignePanier> produits = null;
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        Transaction transaction = session.beginTransaction();

	        try {
	            // Requête pour récupérer les lignes de panier associées au client spécifié
	            Query<LignePanier> query = session.createQuery("FROM LignePanier lp WHERE lp.panier.client = :client", LignePanier.class);
	            query.setParameter("client", client);
	            produits = query.list();

	            transaction.commit();
	        } catch (Exception e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }

	        return produits;
	    }
}
