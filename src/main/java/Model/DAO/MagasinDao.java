package Model.DAO;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.metier.Magasin;


public class MagasinDao {
	
	public static List<Magasin> choisirMagasins(String userLocation) {
        List<Magasin> resultMagasins = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            String hql = "FROM Magasin M WHERE M.nomMagasin LIKE :userLocation OR M.adresseMagasin LIKE :userLocation";
            Query<Magasin> query = session.createQuery(hql, Magasin.class);
            query.setParameter("userLocation", "%" + userLocation + "%");

            resultMagasins = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMagasins;
    }
	
	// get magasinID par magasinName
	public static int getMagasinIdByName(String selectedMagasin) {
		int selectedMagasinId = -1;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT M.idMagasin FROM Magasin M WHERE M.nomMagasin = :selectedMagasin";
            Query query = session.createQuery(hql);
            query.setParameter("selectedMagasin", selectedMagasin);

            Object result = query.uniqueResult();
            if (result != null) {
                selectedMagasinId = (int) result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return selectedMagasinId;
	}
	
	 public List<String> getTempsRetraitForMagasin(int magasinId) {
	        Session session = null;
	        Transaction transaction = null;
	        List<String> tempsRetraitIds = null;
	        
	        try {
	            session = HibernateUtil.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            
	            String hql = "SELECT tr.tempsDeRetrait " +
	                         "FROM TempsRetait tr " +
	                         "JOIN tr.magasins mag " +
	                         "WHERE mag.idMagasin = :idMagasin";
	            
	            Query<String> query = session.createQuery(hql, String.class);
	            query.setParameter("idMagasin", magasinId);
	            
	            tempsRetraitIds = query.getResultList();
	            
	            transaction.commit();
	        } catch (HibernateException e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            if (session != null) {
	                session.close();
	            }
	        }
	        
	        return tempsRetraitIds;
	    }
	 

		    public static int getIdMagasinByLocation(String userLocation) {
		        int magasinId = -1; // Valeur par défaut en cas d'échec de récupération de l'ID du magasin

		        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		            session.beginTransaction();

		            // Requête pour récupérer l'ID du magasin en fonction de la localisation
		            String hql = "SELECT M.idMagasin FROM Magasin M WHERE M.nomMagasin LIKE :userLocation OR M.adresseMagasin LIKE :userLocation";
		            Query<Integer> query = session.createQuery(hql, Integer.class);
		            query.setParameter("userLocation", "%" + userLocation + "%");

		            Integer result = query.uniqueResult();
		            if (result != null) {
		                magasinId = result;
		            }

		            session.getTransaction().commit();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		        return magasinId;
		    }
		

	
	
	// test
		    
		    public static void main(String[] args) {
		        // Définir la localisation à utiliser pour la recherche du magasin
		        String userLocation = "compans"; // Remplacez par une localisation réelle

		        // Appeler la méthode pour récupérer l'ID du magasin en fonction de la localisation
		        int magasinId = getIdMagasinByLocation(userLocation);

		        // Afficher le résultat
		        if (magasinId != -1) {
		            System.out.println("L'ID du magasin pour la localisation '" + userLocation + "' est : " + magasinId);
		        } else {
		            System.out.println("Aucun magasin trouvé pour la localisation '" + userLocation + "'");
		        }
		    }
}

	 /*public static void main(String[] args) {
	        MagasinDao magasinDao = new MagasinDao();
	        int magasinId = 3; // ID du magasin pour lequel vous souhaitez récupérer les créneaux de retrait
	        
	        // Appel de la méthode pour récupérer les créneaux de retrait
	        List<String> creneauxDisponibles = magasinDao.getTempsRetraitForMagasin(magasinId);
	        
	        // Affichage des créneaux de retrait disponibles
	        System.out.println("Créneaux disponibles pour le magasin " + magasinId + ":");
	        if (creneauxDisponibles != null && !creneauxDisponibles.isEmpty()) {
	            for (String creneau : creneauxDisponibles) {
	                System.out.println("- " + creneau);
	            }
	        } else {
	            System.out.println("Aucun créneau de retrait disponible pour ce magasin.");
	        }
	    }
	}*/