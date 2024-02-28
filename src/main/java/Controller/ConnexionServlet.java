package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Client;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Post");
        String identifiant = request.getParameter("nomUtilisateurClient");
        String motDePasse = request.getParameter("pwdClient");

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        Query<Client> q = session.createQuery("FROM Client WHERE nomUtilisateurClient = :nomUtilisateurClient AND pwdClient = :pwdClient", Client.class);
        q.setParameter("nomUtilisateurClient", identifiant);
        q.setParameter("pwdClient", motDePasse);

        List<Client> clients = q.list();

        if (!clients.isEmpty()) {
            // Utilisateur connecté avec succès
            response.sendRedirect("index.html");
            System.out.println("Id : " + clients.get(0).getNomUtilisateurClient());
        } else {
            // Échec de la connexion
            response.sendRedirect("login.html?error=true");
        }

	}
}
