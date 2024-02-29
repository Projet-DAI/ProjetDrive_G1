package Model.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.metier.Client;
import Model.metier.LignePanier;

public class ClientDAO {
	
	   public boolean verifierConnexion(String nomUtilisateur, String motDePasse) {
	        boolean isValidUser = false;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();
	          
	            Query<Client> query = session.createQuery("FROM Client WHERE nomUtilisateurClient = :username AND pwdClient = :password");
	            query.setParameter("username", nomUtilisateur);
	            query.setParameter("password", motDePasse);

	            Client client = query.uniqueResult();

	            if (client != null) {
	                isValidUser = true;
	            }

	            tx.commit();
	        } catch (Exception e) {
	        	 if (tx != null) {
	                 tx.rollback();
	             }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }

	        return isValidUser;
	    }

	   public List<LignePanier> getProduitsDansPanier(Client client) {
		    List<LignePanier> produits = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction transaction = null;
		    try {
		        transaction = session.beginTransaction();
		        Query<LignePanier> query = session.createQuery("FROM LignePanier lp WHERE lp.panier.client = :client", LignePanier.class);
		        query.setParameter("client", client);
		        produits = query.list();

		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        session.close();
		    }

		    return produits;
		}
}