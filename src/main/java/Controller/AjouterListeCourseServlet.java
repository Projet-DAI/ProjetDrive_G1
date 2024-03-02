package Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
import Model.metier.Client;
import Model.metier.ListeCourse;

/**
 * Servlet implementation class AjouterListeCourseServlet
 */
@WebServlet("/AjouterListeCourseServlet")
public class AjouterListeCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterListeCourseServlet() {
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
		String nomListe = request.getParameter("nomListe");
		
		// chercher les idClient avec emailCli
		HttpSession s = request.getSession();
		String emailCli = (String) s.getAttribute("emailCli");
		
		// open hibernate session
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		// create query de recherche
		Query<Client> query = session.createQuery("SELECT c FROM Client c WHERE c.emailClient = :email", Client.class);
		query.setParameter("email", emailCli);
		
		Client client = query.uniqueResult();

	    session.getTransaction().commit();
		
		// get date creation
        LocalDateTime currentDateTime = LocalDateTime.now();

        Date dateCreation = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());


        System.out.println("Current System Time: " + dateCreation);
        System.out.println(client.getIdClient());
        
        
        // enregistrer dans la BD
        session.beginTransaction();
        ListeCourse l = new ListeCourse();
        l.setClient(client);
        l.setNomListeCourse(nomListe);
        l.setDateCreation(dateCreation);
        
        
        session.save(l);
        
        // commit et close session
        session.getTransaction().commit();
        session.close();
        

		response.sendRedirect("ListCoursePreloadServlet");
		
	}

}
