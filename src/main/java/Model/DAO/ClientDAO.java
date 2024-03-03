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

	    public Client getClientByUserName(String nomUtilisateur) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = null;
	        Client client = null;

	        try {
	            tx = session.beginTransaction();
	            Query<Client> query = session.createQuery("FROM Client WHERE nomUtilisateurClient = :username");
	            query.setParameter("username", nomUtilisateur);

	            client = query.uniqueResult();

	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) {
	                tx.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }

	        return client;
	    }

	    public Client getClientById(int clientId) {
	        Client client = null;
	        
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            client = session.get(Client.class, clientId);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return client;
	    }
	    
	    
	   public int getPointsFideliteById(int clientId) {
		    int pointsFidelite = 0;
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        Client client = session.get(Client.class, clientId);

		        if (client != null) {
		        	
		            pointsFidelite = client.getPointFideliteClient();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return pointsFidelite;
		}	 
	   	    
	    /*public List<LignePanier> getProduitsDansPanier(Client client) {
	        List<LignePanier> produits = null;
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        Transaction transaction = session.beginTransaction();

	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();

	            // Récupérer le client par son ID
	            Client client = session.get(Client.class, clientId);

	            if (client != null) {
	                // Récupérer les points de fidélité du client
	                pointsFidelite = client.getPointFideliteClient();
	            }

	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        
	        return pointsFidelite;}

	    public int getPointsFideliteById(int clientId) {
	        int pointsFidelite = 0;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            Client client = session.get(Client.class, clientId);

	            if (client != null) {
	                pointsFidelite = client.getPointFideliteClient();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return pointsFidelite;
	    }*/
}