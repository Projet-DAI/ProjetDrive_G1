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

	    public int getPointsFideliteById1(int clientId) {
	        int pointsFidelite = 0;
	        Transaction transaction = null;

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
	    }
	    
	    // obtenir clientID par mail
	    public int getClienteIdByEmail(String email) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;
	        int clientId = -1;

	        try {
	            transaction = session.beginTransaction();
	           
	            Query query = session.createQuery("SELECT c.idClient FROM Client c WHERE c.emailClient = :emailClient");
	            query.setParameter("emailClient", email);
	            
	            Object result = query.uniqueResult();
	            
	            if (result != null) {
	                clientId = (int) result;
	            }
	            
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }

	        return clientId;
	    }
	    
	    // obtenie objet client par mail
	    public Client findClientByEmail(String email) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Client client = null;
	        try {
	            String hql = "FROM Client C WHERE C.emailClient = :emailClient";
	            Query query = session.createQuery(hql);
	            query.setParameter("emailClient", email);
	            client = (Client) query.uniqueResult();
	        } catch (Exception e) {
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
	
	}

