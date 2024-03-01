package Model.DAO;


import java.sql.Connection;
import java.sql.SQLException;

import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;

import Model.metier.LigneListeCourse;
import Model.metier.Panier;

public class ListDAO {
	
	public static boolean addToShoppingList(int productId) throws IllegalStateException, SystemException {
        Session session = null;
        Transaction transaction = null;
        boolean success = false;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = (Transaction) session.beginTransaction();
            
            // 创建购物清单项
            LigneListeCourse item = new LigneListeCourse();
            
            // 保存购物清单项到数据库
            session.save(item);
            
            // 提交事务
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return success;
    }



		public static void main(String[] args) {
		    // Création de l'instance de PanierDAO
		    PanierDAO panierDAO = new PanierDAO();
		
		    // Spécifiez l'identifiant du panier que vous souhaitez récupérer pour le test
		    int panierId = 1;
		
		    // Appelez la méthode getPanierById pour récupérer le panier
		    Panier panier = panierDAO.getPanierById(panierId);
		
		    // Vérifiez si le panier retourné n'est pas null
		    if (panier != null) {
		        // Affichez les détails du panier
		        System.out.println("ID Panier: " + panier.getIdPanier());
		        System.out.println("Date de création: " + panier.getDateCreation());
		        // Affichez d'autres détails du panier selon vos besoins
		    } else {
		        System.out.println("Le panier est introuvable.");
		    }
		}

}
