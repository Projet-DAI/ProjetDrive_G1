package Model.DAO;

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

	
	 public void ajouterProduitAuPanier(int panierId, Produit produit, int quantite) {
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
	



		public static void main(String[] args) {
		    // Création de l'instance de PanierDAO
		    PanierDAO panierDAO = new PanierDAO();
		
		    // Spécifiez l'identifiant du panier que vous souhaitez récupérer pour le test
		    int panierId = 1;
		    
		    
		
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
		}
		}