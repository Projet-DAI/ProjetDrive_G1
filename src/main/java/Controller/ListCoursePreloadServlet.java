package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.ListeCourse;

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
		HttpSession s = request.getSession();
		String username = (String) s.getAttribute("username");
		String emailCli = (String) s.getAttribute("emailCli");
		
		System.out.println(emailCli);
		
		// 2. search all listecourse with emailCli
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		
		Query<ListeCourse> query = session.createQuery("SELECT lc FROM ListeCourse lc WHERE lc.client.emailClient = :email ORDER BY lc.dateCreation DESC", ListeCourse.class);
		query.setParameter("email", emailCli);

		// 3. getResult
		List<ListeCourse> listeCourse = query.list();
		
		System.out.println("fini hiber");

        
        // verification de donnees
        for (ListeCourse l : listeCourse) {
        	System.out.println("------");
        	System.out.println(l.getNomListeCourse());
        }
        
        
        s.setAttribute("listCourse", listeCourse);
		
		response.sendRedirect("List.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
