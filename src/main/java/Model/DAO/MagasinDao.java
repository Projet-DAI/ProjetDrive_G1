package Model.DAO;

import java.util.*;


import org.hibernate.Session;
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
	
	// test
	public static void main(String[] args) {
		/*
		 * List<Magasin> mag = choisirMagasins("Compans"); for (Magasin magasin : mag) {
		 * System.out.println("Nom: " + magasin.getNomMagasin() + ", Aress: " +
		 * magasin.getAdresseMagasin()); }
		 */
		int magasinID = getMagasinIdByName("magasin GOOD");
		System.out.println("magasinID: "+magasinID);
	}
}
