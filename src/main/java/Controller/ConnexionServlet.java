package Controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	    String email = request.getParameter("email");
	    String motDePasse = request.getParameter("password");

	    SessionFactory factory = HibernateUtil.getSessionFactory();
	    Session session = factory.openSession();

	    try {
	        Query<Client> query = session.createQuery("FROM Client WHERE emailClient = :email AND pwdClient = :motDePasse", Client.class);
	        query.setParameter("email", email);
	        query.setParameter("motDePasse", motDePasse);

	        List<Client> clients = query.list();
	        RequestDispatcher rd;

	        if (!clients.isEmpty()) {// Utilisateur connecté avec succès
	        	String username = clients.get(0).getNomUtilisateurClient();

	            HttpSession s = request.getSession();
	            s.setAttribute("username", username);

	            //request.getRequestDispatcher("ShopServlet").forward(request, response);
	            //response.sendRedirect("servletCentral?method=shop");
	            request.getRequestDispatcher("shop").forward(request, response);


	        	//request.("servletCentral?method=shop").forward(request, response);
	        } else {
	            // Échec de la connexion
	            System.out.println("Connexion échouée");
	            String msg = "Nom d'utilisateur ou mot de passe incorrect.";

	            request.setAttribute("msgE", msg);
	            request.getRequestDispatcher("/login.jsp").forward(request, response);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
}
