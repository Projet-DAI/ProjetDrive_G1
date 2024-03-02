package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
	    
}
