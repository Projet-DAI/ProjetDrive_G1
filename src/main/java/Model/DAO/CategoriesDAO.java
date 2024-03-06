package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.metier.Categories;
import Model.metier.Produit;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class CategoriesDAO {

    public static List<Produit> getProduitsByCategorie(int categorieId) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            String hql = "FROM Produit p WHERE p.categorie.id = :categorieId";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("categorieId", categorieId);

            List<Produit> produits = query.getResultList();

            session.getTransaction().commit();
            return produits;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

