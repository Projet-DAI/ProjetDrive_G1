package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.metier.Panier;
import Model.metier.PostIt;

import java.util.List;

public class PostitDao {

	   private final SessionFactory sessionFactory;

	    public PostitDao(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public static void save(PostIt postit) {
	        Transaction transaction = null;
	            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            	transaction = session.beginTransaction();
	            
	            session.save(postit);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	    
	    public int getListeId(String listeCourseName) {
	        int listeId = -1; // 默认值，如果未找到匹配的列表ID，则返回-1
	        
	        // 获取Hibernate会话
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            // 执行查询，检索与列表名称匹配的列表ID
	            // 假设列表名称是唯一的
	            String hql = "SELECT lc.IdListeCourse FROM ListeCourse lc WHERE lc.nomListeCourse = :listeCourseName";
	            System.out.println("Executing HQL query: " + hql);
	            Integer result = (Integer) session.createQuery(hql)
	                .setParameter("listeCourseName", listeCourseName)
	                .uniqueResult();
	            
	            // 如果找到匹配的列表ID，则将其赋值给列表ID变量
	            if (result != null) {
	                listeId = result;
	                System.out.println("List ID found: " + listeId);
	            } else {
	                System.out.println("No matching list ID found for course name: " + listeCourseName);
	            }
	            transaction.commit(); // 提交事务
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback(); // 发生异常时回滚事务
	            }
	            e.printStackTrace();
	        }
	        
	        return listeId;
	    }

	
	    
	    public void deleteByContenu(String contenu) {
	        Transaction transaction = null;
	        try (Session session = sessionFactory.openSession()) {
	            transaction = session.beginTransaction();
	            Query query = session.createQuery("DELETE FROM Model.metier.PostIt WHERE contenu = :contenu");
	            query.setParameter("contenu", contenu);
	            int rowsAffected = query.executeUpdate();
	            transaction.commit();
	            System.out.println("Rows affected: " + rowsAffected);
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    
	    public void deleteByListeCourseId(int idListeCourse) {
	        Transaction transaction = null;
	        try (Session session = sessionFactory.openSession()) {
	            transaction = session.beginTransaction();
	            Query query = session.createQuery("DELETE FROM postit WHERE listeCourse.idListeCourse = :idListeCourse");
	            query.setParameter("idListeCourse", idListeCourse);
	            query.executeUpdate();
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    
	    }
	    
	    
	    public static void main(String[] args) {
	        String listeCourseName = "example";
	        PostitDao postitDao = new PostitDao(HibernateUtil.getSessionFactory());
	        int listeId = postitDao.getListeId(listeCourseName);
	       
	        
	        // 打印列表ID
	 
	        System.out.println("Liste ID for \"" + listeCourseName + "\": " + listeId);
	    }

		 
		
	    
}
