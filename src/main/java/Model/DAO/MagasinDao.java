package Model.DAO;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.metier.Magasin;
import Model.metier.Stock;
import Model.DAO.MagasinDao;

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
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;
	    List<String> tempsRetraitIds = null;
	    
	    try {
	        transaction = session.beginTransaction();
	        
	        String hql = "SELECT tr.tempsDeRetrait " +
	                     "FROM TempsRetait tr " +
	                     "JOIN tr.magasins mag " +
	                     "WHERE mag.idMagasin = :magasinId";
	        
	        Query<String> query = session.createQuery(hql, String.class);
	        query.setParameter("magasinId", magasinId);
	        
	        tempsRetraitIds = query.getResultList();
	        
	        transaction.commit();
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    
	    return tempsRetraitIds;
	}
	
	public static int getMagasinIdFromStock(Stock stock) {
	    int magasinId = -1; // Valeur par défaut en cas d'erreur ou si aucun magasin n'est trouvé

	    // Vérifiez si le stock n'est pas null
	    if (stock != null && stock.getMagasin() != null) {
	        // Obtenez l'identifiant du magasin à partir du stock
	        magasinId = stock.getMagasin().getIdMagasin();
	    }

	    return magasinId;
	}


	
	
	
	
	// test
	public static void main(String[] args) {
		/*
		 * List<Magasin> mag = choisirMagasins("Compans"); for (Magasin magasin : mag) {
		 * System.out.println("Nom: " + magasin.getNomMagasin() + ", Aress: " +
		 * magasin.getAdresseMagasin()); }
		 */
		/*int magasinID = getMagasinIdByName("magasin GOOD");
		System.out.println("magasinID: "+magasinID);
		
		 int magasinId = 1; // Exemple
	        
	        MagasinDao magasinDAO = new MagasinDao(); // Assume que vous avez une classe DAO pour les magasins
	        List<String> tempsRetraitIds = magasinDAO.getTempsRetraitForMagasin(magasinId);
	        
	        if (tempsRetraitIds != null && !tempsRetraitIds.isEmpty()) {
	            System.out.println("Identifiants des temps de retrait associés au magasin " + magasinId + ":");
	            for (String tempsRetraitId : tempsRetraitIds) {
	                System.out.println(tempsRetraitId);
	            }
	        } else {
	            System.out.println("Aucun temps de retrait trouvé pour le magasin " + magasinId);
	        }
	    }*/
		 // Appel de la méthode getMagasinIdFromStock avec le stock fictif
        
		 Magasin magasin = new Magasin();
	        magasin.setIdMagasin(1);
	        Stock stock = new Stock();
		        stock.setMagasin(magasin);

		int magasinId = getMagasinIdFromStock(stock);

        // Affichage du résultat
        System.out.println("Identifiant du magasin associé au stock : " + magasinId);
    }

	
	}



