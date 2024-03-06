package Model.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;


import Model.metier.Produit;

public class ProduitDAO{
	
	// Afficher touts les produits
	public static List<Produit> getToutsLesProduits() {
        List<Produit> products = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Use HQL (Hibernate Query Language) to retrieve products
            Query<Produit> query = session.createQuery("FROM Produit", Produit.class);
            products = query.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
	}
	
	public static List<Produit> rechercheParMotCle(String motcle) {
		
		String[] listeMot = motcle.split("");
		
		if (listeMot.length > 1) {
			for (String mot : listeMot) {
				rechercheParMotCle(mot);
			}
		}
		
        List<Produit> listP = new ArrayList<>();


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Use HQL (Hibernate Query Language) to retrieve products
            Query<Produit> query = session.createQuery("FROM Produit	 where NomProduit like '%" + motcle + "%'", Produit.class);
            listP = query.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listP;
	}
  	
	//Afficher produits promotion
	public static List<Produit> getProduitsProm() {
        List<Produit> productsProm = new ArrayList<>();
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
               Query<Produit> query = session.createQuery("FROM Produit WHERE promotion = 1", Produit.class);
              productsProm = query.list();
                } catch (Exception e) {
            e.printStackTrace();
        }

        return productsProm;
	}
	
	//Afficher produits promotion Par IdMagasin
	public static List<Produit> getProduitsPromParIdMagasin(int magasinId) {
	    Session session = null;
	    List<Produit> produits = new ArrayList<>();
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        // Adjusted HQL query to join through Stock
	        String hql = "SELECT s.produit FROM Stock s " +
                    "WHERE s.magasin.idMagasin = :magasinId " +
                    "AND s.produit.promotion = true " +
                    "AND s.quantiteEnStock > 0";
	        Query<Produit> query = session.createQuery(hql, Produit.class);
	        query.setParameter("magasinId", magasinId);
	        produits = query.getResultList();
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
	
	// test
	public static void main(String[] args) {
		
//		List<Produit> produits = getProduitsPromParIdMagasin(1); // Assuming magasinId = 1 for testing
//	    for (Produit produit : produits) {
//	        System.out.println("Produit ID: " + produit.getIdProduit() + ", Nom: " + produit.getNomProduit() + ", Promotion: " + produit.isPromotion());
//	        // If you wish to display stock, ensure you fetch it accordingly
//	    }
		
		String s = "kdk";
    	String[] ls = s.split(" ");
    	
    	for (String m : ls) {
    		System.out.println(ls.length);
    		System.out.println(m);
    	}
    	
		
		/*
		 * List<Produit> res = getProduitsProm();
		 * 
		 * for (Produit product : res) { System.out.println("Product ID: " +
		 * product.getIdProduit()); System.out.println("Product Name: " +
		 * product.getNomProduit()); System.out.println("Product Price: " +
		 * product.getPrixProduit()); System.out.println("Product Brand: " +
		 * product.getMarqueProduit()); System.out.println("Product Promotion: " +
		 * product.isPromotion()); System.out.println("Product Promotion Percentage: " +
		 * product.getPourcentagePromotion());System.out.println("Product description: "
		 * + product.getDescription()); System.out.println("Product Image Address: " +
		 * product.getAdresseImageProduit()); System.out.println("Product Nutriscore: "
		 * + product.getNutriscore()); System.out.println("Product Category ID: " +
		 * product.getCategorie().getIdCategorie());
		 * System.out.println("------------------------------"); }
		 */
		
		/*
		 * int testProductId = 1; Produit product = getProductById(testProductId);
		 * 
		 * if (product != null) { System.out.println("Product ID: " +
		 * product.getIdProduit()); System.out.println("Product Name: " +
		 * product.getNomProduit()); System.out.println("Product Price: " +
		 * product.getPrixProduit()); System.out.println("Product Brand: " +
		 * product.getMarqueProduit()); System.out.println("Product Promotion: " +
		 * product.isPromotion()); System.out.println("Product Promotion Percentage: " +
		 * product.getPourcentagePromotion());
		 * System.out.println("Product Image Address: " +
		 * product.getAdresseImageProduit()); System.out.println("Product Nutriscore: "
		 * + product.getNutriscore()); System.out.println("Product Category ID: " +
		 * (product.getCategorie() != null ? product.getCategorie().getIdCategorie() :
		 * "N/A")); } else { System.out.println("No product found with ID: " +
		 * testProductId); }
		 */
	}
	
	/* Fin de la test */ 
	
	
	public class Main {
	    public void main(String[] args) {
//	        // Spécifiez l'ID du produit que vous souhaitez récupérer
//	        int productId = 1; // Remplacez 1 par l'ID du produit que vous souhaitez récupérer
//
//	        // Appelez la méthode getProductById pour récupérer le produit
//	        Produit product = ProduitDAO.getProductById(productId);
//
//	        // Vérifiez si le produit a été trouvé
//	        if (product != null) {
//	            // Affichez les détails du produit
//	            System.out.println("Product ID: " + product.getIdProduit());
//	            System.out.println("Product Name: " + product.getNomProduit());
//	            System.out.println("Product Price: " + product.getPrixProduit());
//	            System.out.println("Product Brand: " + product.getMarqueProduit());
//	            System.out.println("Product Promotion: " + product.isPromotion());
//	            System.out.println("Product Promotion Percentage: " + product.getPourcentagePromotion());
//	            System.out.println("Product Image Address: " + product.getAdresseImageProduit());
//	            System.out.println("Product Nutriscore: " + product.getNutriscore());
//	            System.out.println("Product Category ID: " + (product.getCategorie() != null ? product.getCategorie().getIdCategorie() : "N/A"));
//	        } else {
//	            // Si le produit n'est pas trouvé, affichez un message
//	            System.out.println("No product found with ID: " + productId);
//	        }
	    	
	    	
	    }
	}

}

