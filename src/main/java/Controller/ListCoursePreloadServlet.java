package Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;

import Model.DAO.HibernateUtil;
import Model.metier.ListeCourse;
import Model.metier.PostIt;

/**
 * Servlet implementation class ListCoursePreloadServlet
 */
@WebServlet("/ListCoursePreloadServlet")
public class ListCoursePreloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCoursePreloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. get info client from session
//		HttpSession s = request.getSession();
//		
//		
//		String username = (String) s.getAttribute("username");
//		String emailCli = (String) s.getAttribute("emailCli");
//		
//		System.out.println(emailCli);
//		
//		// 2. search all listecourse with emailCli
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		Session session = factory.openSession();
//		
//		
//		Query<ListeCourse> query = session.createQuery("SELECT lc FROM ListeCourse lc WHERE lc.client.emailClient = :email ORDER BY lc.dateCreation DESC", ListeCourse.class);
//		query.setParameter("email", emailCli);
//
//		// 3. getResult
//		List<ListeCourse> listeCourse = query.list();
//		
//		System.out.println("fini hiber");
//
//        
//        // verification de donnees
//        for (ListeCourse l : listeCourse) {
//        	System.out.println("------");
//        	System.out.println(l.getNomListeCourse());
//        }
//        
//        
//        s.setAttribute("listCourse", listeCourse);
//		
//		response.sendRedirect("List.jsp");
		
		String action = request.getParameter("action");
		HttpSession s = request.getSession();
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();

		try {
		    switch (action) {
		        case "ajouter":
		            String username = (String) s.getAttribute("username");
		            String emailCli = (String) s.getAttribute("emailCli");
		            System.out.println(emailCli);
		            
		            Query<ListeCourse> query = session.createQuery("SELECT lc FROM ListeCourse lc WHERE lc.client.emailClient = :email ORDER BY lc.dateCreation DESC", ListeCourse.class);
		            query.setParameter("email", emailCli);
		            List<ListeCourse> listeCourse = query.list();
		            System.out.println("fini hiber");

		            for (ListeCourse l : listeCourse) {
		                System.out.println("------");
		                System.out.println(l.getNomListeCourse());
		            }
		            
		            s.setAttribute("listCourse", listeCourse);
		            
		            // 关闭Session，然后执行重定向
		            session.close();
		            response.sendRedirect("List.jsp");
		            break;
		            
		        case "supprimerById":
		            int listeId = Integer.parseInt(request.getParameter("listeId"));
		            Transaction transaction = null;
		            boolean supprimeStstu = false;
		            
		            try {
		                transaction = (Transaction) session.beginTransaction();
		                ListeCourse lc = session.get(ListeCourse.class, listeId);
		                
		                if (lc != null) {
		                    session.delete(lc);
		                    transaction.commit();
		                    System.out.println("Liste supprimée avec succès." + listeId);
		                    response.getWriter().write("Success");
		                } else {
		                    System.out.println("Liste introuvable.");
		                    response.getWriter().write("Liste introuvable");
		                }
		            } catch (Exception e) {
		                if (transaction != null) {
		                    transaction.rollback();
		                }
		                e.printStackTrace();
		                System.out.println("Échec de la suppression de la liste.");
		                response.getWriter().write("Échec de la suppression de la liste");
		            } finally {
		                session.close();
		            }
		            break;
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		    // 在可能的错误发生时，添加日志记录器记录错误信息
		    
		    response.getWriter().write("An error occurred while processing the request");
		} finally {
		    // 在最终结束时，确保关闭Session
		    if (session != null && session.isOpen()) {
		        session.close();
		    
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
