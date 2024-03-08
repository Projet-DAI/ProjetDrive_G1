package Model.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.metier.Magasin;
import Model.metier.Produit;
import Model.metier.Stock;


public class StockDAO {
	
	// afficher tous les magasins dans BD
	public static List<Magasin> loadMagasins() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<Magasin> magasins = null;
        try {
            Query<Magasin> query = session.createQuery("FROM Magasin", Magasin.class);
            magasins = query.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } 
        return magasins;
    }
    
	 // afficher detaille de stocks
	public static List<Object[]> getProductsAndStocksForMagasin(int magasinId) {
        List<Object[]> results = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            
            Query<Stock> query = session.createQuery("FROM Stock s WHERE s.magasin.idMagasin = :magasinId", Stock.class);
            query.setParameter("magasinId", magasinId);
            List<Stock> stocks = query.getResultList();

            for (Stock stock : stocks) {
                Produit produit = stock.getProduit();
                double predictedStock = calculatePredictedStock(produit.getVente());
                
                String stockStatus = determineStockStatus(stock.getQuantiteEnStock(), predictedStock);  
               
                results.add(new Object[]{produit.getIdProduit(), produit.getNomProduit(), produit.getEan(), stock.getQuantiteEnStock(), predictedStock, stockStatus});
               
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        return results;
    }


    private static double calculatePredictedStock(int vente) {
        return vente / 90.0 * 15;
    }
	
    private static String determineStockStatus(int currentStock, double predictedStock) {
        if (currentStock > predictedStock * 3) {
            return "Promotions nécessaires";
        } else if (currentStock < predictedStock) {
            return "Besoin de réapprovisionnement";
        } else {
            return "normalité";
        }
    }
    
    // obtenir stock du produit
    public static int obtenirQuantiteProduitStock(String magasinName, int produitId) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
    	try {
            Query<Stock> query = session.createQuery(
                    "FROM Stock s " +
                            "JOIN FETCH s.magasin m " +
                            "JOIN FETCH s.produit p " +
                            "WHERE m.nomMagasin = :magasinName AND p.idProduit = :produitId",
                    Stock.class
            );
            query.setParameter("magasinName", magasinName);
            query.setParameter("produitId", produitId);
            Stock stock = query.uniqueResult();
            int stokDeProduit = stock.getQuantiteEnStock();
            return stokDeProduit;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return -1;
    	} 
    }
        
	
	// test
	public static void main(String[] args) {
		
		
		  List ListMagasin = loadMagasins(); System.out.println("ListMagasin: " +
		  ListMagasin);
		  
		  int magasinId = 1; List<Object[]> productsAndStocks =
		  StockDAO.getProductsAndStocksForMagasin(magasinId); if (productsAndStocks !=
		  null && !productsAndStocks.isEmpty()) { for (Object[] row :
		  productsAndStocks) { System.out.println(row[0] + "\t" + row[1] + "\t" +
		  row[2] + "\t" + row[3] + "\t" + row[4] + "\t" + row[5]); } } else {
		  System.out.println("Aucune information"); }
		 
		
        
		/*
		 * List stocks = getProductsAndStocksForMagasin(1);
		 * System.out.println("stocks: " + stocks);
		 */
	}
	
}
