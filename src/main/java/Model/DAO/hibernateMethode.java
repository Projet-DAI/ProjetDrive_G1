package Model.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.metier.Produit;

public class hibernateMethode {
	
	public static List<Produit> getProductsFromDatabase() {
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
	
	// test
	public static void main(String[] args) {
		 List<Produit> res = getProductsFromDatabase();
		// 打印产品信息或进行其他操作
        for (Produit product : res) {
            System.out.println("Product ID: " + product.getIdProduit());
            System.out.println("Product Name: " + product.getNomProduit());
            System.out.println("Product Price: " + product.getPrixProduit());
            System.out.println("Product Brand: " + product.getMarqueProduit());
            System.out.println("Product Promotion: " + product.isPromotion());
            System.out.println("Product Promotion Percentage: " + product.getPourcentagePromotion());
            System.out.println("Product Image Address: " + product.getAdresseImageProduit());
            System.out.println("Product Nutriscore: " + product.getNutriscore());
            System.out.println("Product Category ID: " + product.getCategorie().getIdCategorie());
            System.out.println("------------------------------");
        }
	}
}

