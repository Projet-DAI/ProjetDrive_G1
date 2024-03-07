package Controller;

import java.io.IOException;
import java.util.Date;
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

import Model.DAO.ClientDAO;
import Model.DAO.HibernateUtil;
import Model.DAO.PanierDAO;
import Model.metier.Client;
import Model.metier.Panier;

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
	    doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
	    String motDePasse = request.getParameter("password");

	    SessionFactory factory = HibernateUtil.getSessionFactory();
	    Session session = factory.openSession();

	    try {
	        Query<Client> query = session.createQuery("FROM Client WHERE emailClient = :email AND pwdClient = :motDePasse", Client.class);
	        query.setParameter("email", email);
	        query.setParameter("motDePasse", motDePasse);

	        List<Client> clients = query.list();

	        if (!clients.isEmpty()) {// Utilisateur connecté avec succès
	        	
	        	String username = clients.get(0).getNomUtilisateurClient();
	        	
	            int clientId = clients.get(0).getIdClient();

	            HttpSession s = request.getSession();
	            
	            s.setAttribute("emailCli", email);
	            s.setAttribute("username", username);
	            
	            if (email.equals("marc@stuff.com") || email.equals("adam@stuff.com")) {
	            	
	            	response.sendRedirect("employee.jsp");
	            	
	            } else {
	            	
	            	s.setAttribute("clientId", clientId);        
	            	
		            // Vérifier si le client a déjà un panier
	            	Query<Panier> query2 = session.createQuery("From Panier p where p.client.idClient=:clientId", Panier.class);
	            	query2.setParameter("clientId", clientId);
	            	
	            	List<Panier> p = query2.list();
	            	
	            	if (p.size() < 1) {
	            		
	            		Panier panierNew = new Panier();
	            		
	            		Client c = session.get(Client.class, clientId);
	            		
	            		panierNew.setClient(c);
	            		panierNew.setDateCreation(new Date());
	            		
	            		session.save(panierNew);
	            		
	            		Query<Panier> query3 = session.createQuery("From Panier p where p.client.idClient=:clientId", Panier.class);
		            	query3.setParameter("clientId", clientId);
		            	
		            	Panier panierCherche = query3.getSingleResult();
		            	
		            	s.setAttribute("panierId", panierCherche.getIdPanier());
		                
			            s.setAttribute("panier", panierCherche);
	            	
	            	} else {	
	            		s.setAttribute("panierId", p.get(0).getIdPanier());
	                    
			            s.setAttribute("panier", p.get(0));
	            	}
	            	
	            	response.sendRedirect("index.jsp");
	            }        
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
}
