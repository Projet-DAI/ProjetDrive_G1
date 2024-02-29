package Model.DAO;

import org.hibernate.Session;

import Model.metier.Panier;

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