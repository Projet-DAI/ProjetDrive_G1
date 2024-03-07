package Model.DAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;

import Model.metier.Produit;
import Model.metier.Categories;

public class ProduitDAO {

    public static List<Produit> getToutsLesProduits() {
        List<Produit> products = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Produit> query = session.createQuery("FROM Produit", Produit.class);
            products = query.list();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public static List<Produit> rechercheParMotCle(String motcle) {
        List<Produit> listP = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Use HQL (Hibernate Query Language) to retrieve products
            Query<Produit> query = session.createQuery("FROM Produit where NomProduit like '%" + motcle + "%'", Produit.class);
            listP = query.list();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listP;
    }

    public static List<Produit> getProduitsProm() {
        List<Produit> productsProm = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Produit> query = session.createQuery("FROM Produit WHERE promotion = true", Produit.class);
            productsProm = query.list();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productsProm;
    }

    public List<String> getCategoriesByRayon(int rayonId) {
        List<String> categories = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "SELECT DISTINCT P.categorie.nomCategorie FROM Produit P WHERE P.categorie.rayon.idRayon = :rayonId";
            Query<String> query = session.createQuery(hql);
            query.setParameter("rayonId", rayonId);
            categories = query.list();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public static List<Produit> getProduitsByRayonAndCategory(int rayonId, String categorie) {
        List<Produit> produits = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "SELECT p FROM Produit p " +
                    "JOIN FETCH p.categorie " +
                    "WHERE p.categorie.rayon.idRayon = :rayonId " +
                    "AND p.categorie.nomCategorie = :categorie";
            Query<Produit> query = session.createQuery(hql);
            query.setParameter("rayonId", rayonId);
            query.setParameter("categorie", categorie);
            List<Produit> rawProducts = query.list();

            transaction.commit();

            // Utiliser un HashSet pour éliminer les doublons
            HashSet<Produit> uniqueProducts = new HashSet<>(rawProducts);
            produits = new ArrayList<>(uniqueProducts);

            // Ajoutez des messages de débogage ici
            System.out.println("Produits récupérés pour le rayon " + rayonId + " et la catégorie " + categorie + ": " + produits);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produits;
    }
    public static List<Produit> getProduitsByRayon(int rayonId) {
        List<Produit> produits = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Produit P WHERE P.categorie.rayon.idRayon = :rayonId";
            Query<Produit> query = session.createQuery(hql);
            query.setParameter("rayonId", rayonId);
            produits = query.list();

            transaction.commit();

            // Ajoutez des messages de débogage ici
            System.out.println("Produits récupérés pour le rayon " + rayonId + ": " + produits);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produits;
    }

    public static String getNomRayonById(int rayonId) {
        switch (rayonId) {
            case 1:
                return "Boucherie";
            case 2:
                return "Brasserie";
            case 3:
                return "Charcuterie";
            case 4:
                return "Crémerie";
            case 5:
                return "Épicerie Salée";
            case 6:
                return "Épicerie Sucrée";
            case 7:
                return "Fruits et Légumes";
            case 8:
                return "Marée";
            case 9:
                return "Spiritueux";
            case 10:
                return "Surgelé";
            case 11:
                return "Traiteur";
            case 12:
                return "Vin et Champagne";
            case 13:
                return "Entretien et Nettoyage";

            default:
                return "Rayon inconnu";
        }
    }
    
    public static int getQuantiteEnStock(int productId) {
        int quantiteEnStock = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "SELECT s.quantiteEnStock FROM Stock s WHERE s.produit.idProduit = :productId";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("productId", productId);

            List<Integer> results = query.getResultList();
            if (!results.isEmpty()) {
                quantiteEnStock = results.get(0);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quantiteEnStock;
    }
    
    public static List<Produit> getProduitsDeRemplacement(Categories categorie, int productId) {
        List<Produit> produitsDeRemplacement = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Utilisez une requête HQL pour récupérer les produits de remplacement
            String hql = "FROM Produit p WHERE p.categorie = :categorie AND p.idProduit != :productId";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("categorie", categorie);
            query.setParameter("productId", productId);

            // Exécutez la requête et récupérez les produits de remplacement
            produitsDeRemplacement = query.list();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produitsDeRemplacement;
    }


    public static List<Produit> getProduitsPromParIdMagasin(int magasinId) {
        Session session = null;
        List<Produit> produits = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            // Adjusted HQL query to join through Stock
            String hql = "SELECT s.produit FROM Stock s " +
                    "WHERE s.magasin.idMagasin = :magasinId " +
                    "AND s.produit.promotion = true " +
                    "AND s.quantiteEnStock > 0";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("magasinId", magasinId);
            produits = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return produits;
    }
    
	// Afficher un produit
	public static Produit getProductById(int productId) {
	    Produit product = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        product = session.get(Produit.class, productId);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	    }
	    return product;
	}
    
    // Test
 	public static void main(String[] args) {
 		
 		Produit p = getProductById(1);
 		System.out.println("Produit：" + p);
 	
 	}
 	
 	/* Fin de la test */ 
    
    
}