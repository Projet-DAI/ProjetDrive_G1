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
	
	// test
	public static void main(String[] args) {
		List<Magasin> mag = choisirMagasins("compance");
		
        for (Magasin magasin : mag) {
            System.out.println("Nom: " + magasin.getNomMagasin() + ", Aress: " + magasin.getAdresseMagasin());
        }
	}
}
