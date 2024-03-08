package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import Model.DAO.HibernateUtil;
import Model.metier.ListeCourse;
import Model.DAO.ListDAO;
/**
 * Servlet implementation class SupprimerListCourseServlet
 */
@WebServlet("/SupprimerListCourseServlet")
public class SupprimerListCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerListCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// obtenir nomListe de form
		int listeId = Integer.parseInt(request.getParameter("listeId"));
		
		ListDAO listDAO = new ListDAO();
		boolean isDeleted = listDAO.supprimerListe(listeId);
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
	    PrintWriter out = response.getWriter();
	    
		if (isDeleted) {
			out.print("{\"success\": true, \"message\": \"Liste supprimée avec succès.\"}");
	    } else {
	    	out.print("{\"success\": false, \"message\": \"Échec de la suppression de la liste.\"}");
	    }
		
		out.flush();
	    out.close();
	}

}
