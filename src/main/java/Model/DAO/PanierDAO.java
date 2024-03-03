package Model.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Model.metier.LignePanier;
import Model.metier.Panier;
import Model.metier.Produit;

public class PanierDAO {

	public Panier getPanierById(int panierId) {
	    try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
	        session.beginTransaction();
	        Panier panier = session.get(Panier.class, panierId);
	        // Charger les lignes de panier associées au panier
	        if (panier != null) {
	            // Forcer le chargement des lignes de panier
	            panier.getLignesPanier().size();
	        }
	        session.getTransaction().commit();
	        return panier;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	/* public void ajouterProduitAuPanier(int panierId, Produit produit, int quantite) {
	        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
	            Transaction transaction = session.beginTransaction();
	            Panier panier = session.get(Panier.class, panierId);
	            // Vérifier si le panier existe
	            if (panier != null) {
	                // Créer une nouvelle ligne de panier
	                LignePanier nouvelleLigne = new LignePanier();
	                nouvelleLigne.setProduit(produit);
	                nouvelleLigne.setQuantite(quantite);
	                // Ajouter la nouvelle ligne de panier au panier
	                panier.getLignesPanier().add(nouvelleLigne);
	                // Mettre à jour le panier dans la base de données
	                session.update(panier);
	                transaction.commit();
	            } else {
	                System.out.println("Le panier avec l'ID " + panierId + " n'existe pas.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 */
	    public void addToCart(int panierId, int produitId, int quantite) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            Transaction transaction = session.beginTransaction();

	            // Récupérer le panier
	            Panier panier = session.get(Panier.class, panierId);
	            
	            if (panier != null) {
	            // Récupérer le produit
		            Produit produit = session.get(Produit.class, produitId);
	
		            // Ajouter le produit au panier en créant une nouvelle ligne de panier
		            LignePanier lignePanier = new LignePanier();
		            lignePanier.setPanier(panier);
		            lignePanier.setProduit(produit);
		            lignePanier.setQuantite(quantite);
		            
		            panier.getLignesPanier().add(lignePanier);
	
		            session.saveOrUpdate(lignePanier);
	
		            transaction.commit();
	        }  else {
	            System.out.println("Le panier avec l'ID " + panierId + " n'existe pas.");
	        }
	    }catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	 // Modifier la méthode getPanierIdByClientId pour retourner un Integer
	 /*   public Integer getPanierIdByClientId(int clientId) {
	        Integer panierId = null;

	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            session.beginTransaction();
	            // Créez la requête HQL pour récupérer l'ID du panier par l'ID du client
	            String hql = "SELECT idPanier FROM Panier WHERE client.idClient = :clientId"; // Assurez-vous que le nom de la colonne est correct
	            Query<Integer> query = session.createQuery(hql, Integer.class);
	            query.setParameter("clientId", clientId);
	            
	            panierId = query.uniqueResult();

	            session.getTransaction().commit();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return panierId;
	    }*/
	    
	    public Panier getPanierByClientId(int clientId) {
	        	Panier panier = null;
	            Transaction transaction = null;

	    	 	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	 		transaction = session.beginTransaction();
		            // Créez la requête HQL pour récupérer le panier par l'ID du client
		            String hql = "SELECT p \r\n"
		            		+ "FROM Panier p \r\n"
		            		+ "JOIN p.client c \r\n"
		            		+ "WHERE c.idClient = :clientId\r\n"
		            		+ "";
		            Query<Panier> query = session.createQuery(hql,Panier.class);
		            query.setParameter("clientId", clientId);
		            
		            panier = query.uniqueResult();
		            
		            transaction.commit();

		          
		            //session.getTransaction().commit();
	
		        } catch (Exception e) {
		        	  if (transaction != null) {
		                  transaction.rollback();
		              }
		              e.printStackTrace();
		        }
		        return panier;
		    }


	    public static void createPanier(Panier panier) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            session.beginTransaction();

	            session.save(panier);

	            session.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	

	    private Panier getPanier(int panierId) {
	        try {
	            PanierDAO panierDAO = new PanierDAO();
	            return panierDAO.getPanierById(panierId);
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    public void afficherDetailsPanier(Panier panier) {
	        System.out.println("ID Panier: " + panier.getIdPanier());
	        System.out.println("Date de création: " + panier.getDateCreation());
	        System.out.println("Contenu du panier:");
	        
	        for (LignePanier lignePanier : panier.getLignesPanier()) {
	            System.out.println("Produit: " + lignePanier.getProduit().getNomProduit() + ", Quantité: " + lignePanier.getQuantite());
	            // Afficher d'autres détails de la ligne de panier selon vos besoins
	        }
	    }

	
	 	
	



	    public static void main(String[] args) {
	        // Création de l'instance de PanierDAO
	        PanierDAO panierDAO = new PanierDAO();

	        // Spécifiez l'identifiant du client pour lequel vous souhaitez récupérer le panier
	        int clientId = 2;

	        // Récupérez l'ID du panier pour le client spécifié
	        	Panier panier = panierDAO.getPanierByClientId(clientId);

	        // Vérifiez si un panier a été trouvé pour le client
	        if (panier != null) {
	          
	            // Affichez les détails du panier
	            System.out.println("Panier récupéré avec succès: "+ clientId);
	            System.out.println("ID Panier: " + panier.getIdPanier());
	            System.out.println("Date de création: " + panier.getDateCreation());
	            // Ajoutez d'autres détails du panier si nécessaire
	        } else {
	            // Affichez un message si aucun panier n'a été trouvé pour le client spécifié
	            System.out.println("Aucun panier trouvé pour le client avec l'ID: " + clientId);
	        }
	    }
	    }


		/*
		    // Appelez la méthode getPanierById pour récupérer le panier
		    Panier panier = panierDAO.getPanierById(panierId);
		
		    // Vérifiez si le panier retourné n'est pas null
		    if (panier != null) {
		        // Affichez les détails du panier
		        System.out.println("ID Panier: " + panier.getIdPanier());
		        System.out.println("Date de création: " + panier.getDateCreation());
		        // Affichez d'autres détails du panier selon vos besoins
		    } else {
		        System.out.println("Le panier est introuvable.");
		    }
		}*/
		