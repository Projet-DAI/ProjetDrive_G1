package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Client;

/**
 * Servlet implementation class ServeletVerifierEmail
 */
@WebServlet("/ServeletVerifierEmail")
public class ServeletVerifierEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletVerifierEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println("commencer servlet" + email);
		
		 try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
	            session.beginTransaction();

	            // 使用 HQL 查询
	            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Client WHERE emailClient = :email", Long.class);
	            query.setParameter("email", email);

	            Long count = query.getSingleResult();

	            session.getTransaction().commit();
	            
	            if (count > 0) {
	            	response.getWriter().write("1");
	            } else {
	            	response.getWriter().write("0");
	            }

	           
	        } catch (Exception e) {
	            e.printStackTrace();
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
