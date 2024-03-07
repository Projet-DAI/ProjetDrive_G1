package Model.DAO;

import java.util.ArrayList;
import java.util.List;


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

	
	   
	    public void modifierQuantiteProduit(Panier panier, int produitId, int nouvelleQuantite) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();

	            // Trouver la ligne de panier correspondant au produit dans le panier
	            for (LignePanier ligne : panier.getLignesPanier()) {
	                if (ligne.getProduit().getIdProduit() == produitId) {
	                    // Mettre à jour la quantité du produit dans la ligne de panier
	                    ligne.setQuantite(nouvelleQuantite);
	                    session.update(ligne);
	                    break;
	                }
	            }
	            session.update(panier);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }



	    public void updateQuantiteProduit(int panierId, int produitId, int nouvelleQuantite) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();

	            String hql = "UPDATE LignePanier lp SET lp.quantite = :nouvelleQuantite " +
	                         "WHERE lp.panier.idPanier = :panierId AND lp.produit.idProduit = :produitId";

	            // Exécuter la requête HQL
	            int lignesModifiees = session.createQuery(hql)
	                                        .setParameter("nouvelleQuantite", nouvelleQuantite)
	                                        .setParameter("panierId", panierId)
	                                        .setParameter("produitId", produitId)
	                                        .executeUpdate();

	            if (lignesModifiees > 0) {
	                System.out.println("La quantité du produit a été mise à jour avec succès dans le panier.");
	            } else {
	                System.out.println("Aucune ligne de panier mise à jour.");
	            }

	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    
	    public void removeProduit(Panier panier, Produit produit) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();

	            // Supprimer la ligne de panier correspondant au produit du panier
	            LignePanier ligneToRemove = null;
	            for (LignePanier ligne : panier.getLignesPanier()) {
	                if (ligne.getProduit().equals(produit)) {
	                    ligneToRemove = ligne;
	                    break;
	                }
	            }

	            if (ligneToRemove != null) {
	                panier.getLignesPanier().remove(ligneToRemove);
	                session.delete(ligneToRemove);
	                session.update(panier);
	                transaction.commit();
	            }
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    public void updatePanier(Panier panier) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();

	            session.update(panier);

	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	    
	    
	    public void supprimerLignePanier(int idPanier, int idProduit) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            // Charger les entités associées avant de les supprimer
	            Panier panier = session.load(Panier.class, idPanier);
	            Produit produit = session.load(Produit.class, idProduit);
	            
	            // Trouver la ligne de panier à supprimer
	            LignePanier lignePanier = panier.getLignesPanier().stream()
	                .filter(lp -> lp.getProduit().getIdProduit() == idProduit)
	                .findFirst()
	                .orElse(null);
	            
	            // Supprimer la ligne de panier
	            if (lignePanier != null) {
	                panier.getLignesPanier().remove(lignePanier);
	                session.delete(lignePanier);
	                System.out.println("Ligne de panier supprimée avec succès.");
	            } else {
	                System.out.println("Ligne de panier non trouvée.");
	            }
	            
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    
	    }
	    
	    public static double calculerTotalPanier(Panier panier) {
	        List<LignePanier> lignesPanier = panier.getLignesPanier();
	        double total = 0.0;
	        
	        for (LignePanier lignePanier : lignesPanier) {
	        	total += lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite();
	        }
	        
	        return total;
	    }


	    public static void main(String[] args) {
	    	
    	  Session session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          
          try {
		      int clientId = 1;
		
		      // 创建查询，获取关联到给定 clientId 的 Panier 对象
		      Query<Panier> query2 = session.createQuery("FROM Panier p WHERE p.client.idClient = :clientId", Panier.class);
		      query2.setParameter("clientId", clientId);
		
		      // 获取单一结果，如果没有结果会抛出 NoResultException，如果有多个结果会抛出 NonUniqueResultException
		      List<Panier> p = query2.list();
		
		      // 在这里使用获取到的 Panier 对象
		      if (p.size() == 0) {
		          System.out.println("No Panier found: " + p);
		      } else {
		          System.out.println(p.get(0).getIdPanier());
		      }

	      } catch (Exception e) {
	          e.printStackTrace();
	      } 
	    }
}
          
	    	
	    	
	    	
	    	
	    	
	    	
        	 /*PanierDAO panierDAO = new PanierDAO();
	         
	         // Récupération d'un panier avec ses lignes de panier depuis la base de données (vous devez ajuster cela en fonction de votre DAO)
	         int panierId = 1; // ID du panier à récupérer depuis la base de données
	         Panier panier = panierDAO.getPanierById(panierId);
	         
	         // Vérification si le panier existe dans la base de données
	         if (panier != null) {
	             // Appel de la méthode calculerTotalPanier pour calculer le total du panier
	             double totalPanier = calculerTotalPanier(panier);
	             
	             // Affichage du total du panier
	             System.out.println("Total du panier: " + totalPanier + "€");
	         } else {
	             System.out.println("Le panier avec l'ID " + panierId + " n'existe pas dans la base de données.");
	         }
	     }*/
	    	
	    	
	    	
	        // Création de l'instance de PanierDAO
	   /* 	int panierId = 7;
	    	int produitId = 3;

	        PanierDAO panierDAO = new PanierDAO();
	        Panier panier = panierDAO.getPanierById(panierId);
	        //Produit produit = panier.getLignesPanier().get(0).getProduit();
	        int nouvelleQuantite = 4;
	        panierDAO.modifierQuantiteProduit(panier, produitId, nouvelleQuantite);
	        System.out.println("La quatité du panier"+ panierId+" du produit"+produitId+ "la nvelle quantite"+nouvelleQuantite);
	       // panierDAO.updateQuantiteProduit(panierId, produitId, nouvelleQuantite);

	        //ProduitDAO produitDAO = new ProduitDAO();
	        
	        
	        
	        
	        


	       /* // Spécifiez l'identifiant du panier que vous souhaitez modifier
	        int panierId = 7;

	        // Spécifiez l'identifiant du produit que vous souhaitez modifier
	        int produitId = 3;

	        // Spécifiez la nouvelle quantité pour le produit
	        int nouvelleQuantite = 5;
	        System.out.println("Test de la méthode modifierQuantiteProduit...");

	        // Test de la méthode modifierQuantiteProduit
	        Panier panier = panierDAO.getPanierById(panierId);
	        Produit produit = produitDAO.getProductById(produitId);// récupérez le produit correspondant à produitId depuis la base de données
	        panierDAO.modifierQuantiteProduit(panier, produit, nouvelleQuantite);
	        System.out.println("La quantité du produit a été modifiée avec succès.");

	        System.out.println("Test de la méthode updateQuantiteProduit...");
	        
	        
	        // Test de la méthode updateQuantiteProduit
	        panierDAO.updateQuantiteProduit(panierId, produitId, nouvelleQuantite);
	        System.out.println("La quantité du produit a été mise à jour avec succès dans le panier.");
	        
	        System.out.println("Test de la méthode removeProduit...");

	    /*    // Test de la méthode removeProduit
	        Produit produitToRemove = produitDAO.getProductById(produitId);
	        panierDAO.removeProduit(panier, produitToRemove);
	        System.out.println("Le produit a été retiré avec succès du panier.");
	        
	        System.out.println("Test de la méthode updatePanier...");
*/
	        // Test de la méthode updatePanier
	        /*panierDAO.updatePanier(panier);
	        System.out.println("Le panier a été mis à jour avec succès.");

	        
	        System.out.println("Test de la méthode supprimerLignePanier...");*/

	        /*// Test de la méthode supprimerLignePanier
	        panierDAO.supprimerLignePanier(panierId, produitId);
	        System.out.println("La ligne de panier a été supprimée avec succès.");
*/
	 


	   /* public static void main(String[] args) {
	        // Création de l'instance de PanierDAO
	        PanierDAO panierDAO = new PanierDAO();

	        // Spécifiez l'identifiant du client pour lequel vous souhaitez récupérer le panier
	        int clientId = 1;

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
	    }*/


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
		