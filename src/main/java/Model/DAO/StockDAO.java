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
            // 第一步：查询指定Magasin的所有Stock记录
            Query<Stock> query = session.createQuery("FROM Stock s WHERE s.magasin.idMagasin = :magasinId", Stock.class);
            query.setParameter("magasinId", magasinId);
            List<Stock> stocks = query.getResultList();

            // 第二步：对于每个Stock记录，获取Produit信息并计算预测库存量
            for (Stock stock : stocks) {
                Produit produit = stock.getProduit();
                double predictedStock = calculatePredictedStock(produit.getVente());
                
                String stockStatus = determineStockStatus(stock.getQuantiteEnStock(), predictedStock);  
                // 组装结果：产品ID，产品名称，产品EAN，当前库存量，预测库存量，Action
                results.add(new Object[]{produit.getIdProduit(), produit.getNomProduit(), produit.getEan(), stock.getQuantiteEnStock(), predictedStock, stockStatus});
               
                // 组装结果：产品ID，产品名称，产品EAN，当前库存量，预测库存量
                //results.add(new Object[]{produit.getIdProduit(), produit.getNomProduit(), produit.getEan(), stock.getQuantiteEnStock(), predictedStock});
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

    // 计算预测库存量的辅助方法
    private static double calculatePredictedStock(int vente) {
        // 根据过去90天的销量计算未来15天的预测库存量
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
	
	// test
	public static void main(String[] args) {
		
		List ListMagasin = loadMagasins();
		System.out.println("ListMagasin: " + ListMagasin);
		
		int magasinId = 1; // 假设存在的Magasin ID，需要根据实际情况调整

        // 调用方法获取信息
        List<Object[]> productsAndStocks = StockDAO.getProductsAndStocksForMagasin(magasinId);

        // 打印结果
        if (productsAndStocks != null && !productsAndStocks.isEmpty()) {
			/*
			 * System.out.println("产品ID\t产品名称\tEAN\t当前库存量\t预测库存量"); for (Object[] row :
			 * productsAndStocks) { System.out.println(row[0] + "\t" + row[1] + "\t" +
			 * row[2] + "\t" + row[3] + "\t" + row[4]); }
			 */
        	System.out.println("产品ID\t产品名称\tEAN\t当前库存量\t预测库存量\t库存状态");
            for (Object[] row : productsAndStocks) {
                System.out.println(row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3] + "\t" + row[4] + "\t" + row[5]);
            }
        } else {
            System.out.println("没有找到指定商店的库存信息。");
        }
		
        
		/*
		 * List stocks = getProductsAndStocksForMagasin(1);
		 * System.out.println("stocks: " + stocks);
		 */
	}
	
}
