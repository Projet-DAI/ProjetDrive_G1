package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Produit;

/**
 * Servlet implementation class TableauDeBordPreloadServlet
 */
@WebServlet("/TableauDeBordPreloadServlet")
public class TableauDeBordPreloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableauDeBordPreloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// obtenir email client à partir de la session
		HttpSession s = request.getSession();
		String emailCli = (String)s.getAttribute("emailCli");
		
		// Creer Hibernate session pour la requete
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			// Query HQL
			String hql = "SELECT lc.produit, SUM(lc.quantite), MAX(lc.commande.dateCommande) " +
	                 "FROM LigneCommande lc " +
	                 "WHERE lc.commande.client.emailClient = :email " +
	                 "GROUP BY lc.produit " +
	                 "ORDER BY SUM(lc.quantite) DESC";
			
			Query<Object[]> query = session.createQuery(hql, Object[].class);
			query.setParameter("email", emailCli);
			
			// get result
			List<Object[]> results = query.list();
			
			// tester res
//		    for (Object[] result : results) {
//		        Produit produit = (Produit) result[0];
//		        Long totalQuantity = (Long) result[1];
//		        Date lastPurchaseDate = (Date) result[2];
//
//		        System.out.println("Produit: " + produit.getNomProduit() + 
//		                           ", Quantité totale: " + totalQuantity +
//		                           ", Dernière date d'achat: " + lastPurchaseDate);
//		    }

		    // commit()
		    session.getTransaction().commit();
		    
		    request.setAttribute("listRes", results);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			// fermer la session
		    session.close();
		}
		
		request.getRequestDispatcher("TableauDeBord.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
