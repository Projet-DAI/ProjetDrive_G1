package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.DAO.HibernateUtil;
import Model.metier.Panier;

/**
 * Servlet implementation class ServletPanier
 */
@WebServlet("/Panier")
public class ServletPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    Transaction transaction = null;
		    Panier panier = null;
		    
		    try {
		        transaction = session.beginTransaction();
		        int panierId = 1; // Remplacez 1 par l'ID du panier que vous souhaitez récupérer
		        panier = (Panier) session.get(Panier.class, panierId);
		        Hibernate.initialize(panier.getLignesPanier()); // Assurez-vous que les lignes de panier sont chargées
		        transaction.commit();
		    } catch (HibernateException e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        session.close();
		    }
		    
		    request.setAttribute("panier", panier);
		    request.getRequestDispatcher("panier.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
