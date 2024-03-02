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
import Model.metier.Commande;
import Model.metier.ListeCourse;

/**
 * Servlet implementation class TransactionPreloadServlet
 */
@WebServlet("/TransactionPreloadServlet")
public class TransactionPreloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionPreloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. obtenir les info de client a partir de session
		HttpSession s = request.getSession();
		String username = (String) s.getAttribute("username");
		String emailCli = (String) s.getAttribute("emailCli");
		
		System.out.println(emailCli);
		
		// 2. Recherche tous les commandes en utilisant emailCli
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		Query<Commande> query = session.createQuery("SELECT c FROM Commande c WHERE c.client.emailClient = :email ORDER BY c.dateCommande DESC", Commande.class);
		query.setParameter("email", emailCli);
		
		// 3. getResult
		List<Commande> listeCommandes = query.list();
		
		// 4. tester res
//		for (Commande c : listeCommande) {
//			System.out.println(c.getIdCommande());
//			System.out.println(c.getClient().getNomUtilisateurClient());
//		}
		
		request.setAttribute("listeCommandes", listeCommandes);
		request.getRequestDispatcher("transaction.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
