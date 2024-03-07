package Model.DAO;


import java.sql.Connection;
import java.sql.SQLException;

import javax.transaction.SystemException;


import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.metier.LigneListeCourse;
import Model.metier.ListeCourse;
import Model.metier.Panier;
import Model.metier.PostIt;

public class ListDAO {
	
	public static boolean addToShoppingList(int productId) throws IllegalStateException, SystemException {
        Session session = null;
        Transaction transaction = null;
        boolean success = false;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = (Transaction) session.beginTransaction();
            
            LigneListeCourse item = new LigneListeCourse();
            
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return success;
    }
	
	// get listCourse By ID
	public static ListeCourse getListeCourseById(int listeCourseId) {
        Transaction transaction = null;
		ListeCourse listeCourse = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	transaction = (Transaction) session.beginTransaction();
            listeCourse = session.get(ListeCourse.class, listeCourseId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
            	transaction.rollback();
            }
            e.printStackTrace();
        }
        return listeCourse;
    }
	
	
	public static boolean supprimerListe(String listeId) {
	    Transaction transaction = null;
	    boolean supprimeStstu = false;
	    
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) { 
	        transaction = session.beginTransaction(); 
	        ListeCourse listeCourse = session.get(ListeCourse.class, Integer.parseInt(listeId));

	        if (listeCourse != null) {
	            // 删除列表
	            session.delete(listeCourse);
	            transaction.commit();
	            supprimeStstu = true;
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return supprimeStstu;
	}
	

		public static void main(String[] args) {
			 
			 System.out.println("getListeCourseById: " + getListeCourseById(1)); 
		    // Création de l'instance de PanierDAO
			/*
			 * PanierDAO panierDAO = new PanierDAO();
			 * 
			 * // Spécifiez l'identifiant du panier que vous souhaitez récupérer pour le
			 * test int panierId = 1;
			 * 
			 * // Appelez la méthode getPanierById pour récupérer le panier Panier panier =
			 * panierDAO.getPanierById(panierId);
			 * 
			 * // Vérifiez si le panier retourné n'est pas null if (panier != null) { //
			 * Affichez les détails du panier System.out.println("ID Panier: " +
			 * panier.getIdPanier()); System.out.println("Date de création: " +
			 * panier.getDateCreation()); // Affichez d'autres détails du panier selon vos
			 * besoins } else { System.out.println("Le panier est introuvable."); }
			 */
		    
		   
		}

}
