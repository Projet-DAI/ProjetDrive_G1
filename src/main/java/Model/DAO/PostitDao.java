package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mysql.cj.xdevapi.Client;

import Model.metier.ListeCourse;
import Model.metier.Panier;
import Model.metier.PostIt;

import java.time.LocalDateTime;
import java.util.List;

public class PostitDao {
	
	// function de verifier si posit déjà dans BD
	public boolean postitExists(String contenu, int clientId, int listitCourseId) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        String hql = "FROM PostIt p WHERE p.contenu = :contenu AND p.client.idClient = :clientId AND p.listeCourse.idListeCourse = :listitCourseId";
	        Query query = session.createQuery(hql);
	        query.setParameter("contenu", contenu);
	        query.setParameter("clientId", clientId);
	        query.setParameter("listitCourseId", listitCourseId);
	        List result = query.list();
	        return !result.isEmpty();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	// function de afficher tout les postits dans une specifique listCourse
	public List<PostIt> findAllByListeCourseId(int listeCourseId) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        String hql = "FROM PostIt p WHERE p.listeCourse.idListeCourse = :listeCourseId";
	        Query<PostIt> query = session.createQuery(hql, PostIt.class);
	        query.setParameter("listeCourseId", listeCourseId);
	        return query.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	// function de save postit dans BD
	public void save(PostIt postit) {
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
	
	// find ListeCourse objet par listeCourseName
	public ListeCourse findListeCourseByName(String listeCourseName) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        String hql = "FROM ListeCourse lc WHERE lc.nomListeCourse = :listeCourseName";
	        Query<ListeCourse> query = session.createQuery(hql, ListeCourse.class);
	        query.setParameter("listeCourseName", listeCourseName);
	        return query.uniqueResultOptional().orElse(null);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	// function pour retour List ID
	public int getListeId(String listeCourseName) { 
		  try (Session session = HibernateUtil.getSessionFactory().openSession()) { 
			  String hql = "SELECT lc.idListeCourse FROM ListeCourse lc WHERE lc.nomListeCourse = :listeCourseName"; 
			  Query<Integer> query = session.createQuery(hql, Integer.class);
			  query.setParameter("listeCourseName", listeCourseName); return
					  query.uniqueResultOptional().orElse(-1); 
			  } catch (Exception e) {
				  e.printStackTrace(); 
				  return -1; 
			  } 
	  }
	
	// supprimer Postit By postitId
	
	public static boolean supprimerPostitById(int postitId) {
		
		Transaction transaction = null;
		boolean supprimeStstu = false;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) { 
			transaction = session.beginTransaction();        

			PostIt postit = session.get(PostIt.class, postitId);
			if (postit != null) {
	            session.remove(postit); 
	            supprimeStstu = true;
	        }

	        transaction.commit();
	    } catch (RuntimeException e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback(); 
	        }
	        e.printStackTrace();
	    } 
	    return supprimeStstu;
	}

	public static void main(String[] args) {
	    //String listeCourseName = "example";
	    System.out.println("supprimerPostitById:" + supprimerPostitById(1));
	    //PostitDao postitDao = new PostitDao(HibernateUtil.getSessionFactory());
	    //int listeId = postitDao.getListeId(listeCourseName);
	    // 打印列表ID
	    //System.out.println("Liste ID for \"" + listeCourseName + "\": " + listeId);
	}

}
