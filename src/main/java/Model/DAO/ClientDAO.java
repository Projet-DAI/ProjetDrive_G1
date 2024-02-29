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

	   
	    public int getPointsFideliteById(int clientId) {
	        int pointsFidelite = 0;
	        Transaction transaction = null;

	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();

	            String sql = "SELECT pointFideliteClient FROM Client WHERE idClient = :clientId";
	            Query<Integer> query = session.createQuery(sql, Integer.class);
	            query.setParameter("clientId", clientId);

	            pointsFidelite = query.uniqueResult();

	            transaction.commit();
	        } catch (Exception e) {

	            e.printStackTrace();
	        }
	        return pointsFidelite;
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
