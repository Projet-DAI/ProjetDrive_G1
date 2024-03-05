package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Commande;
import Model.metier.LigneCommande;
import Model.metier.StatutCommande;

/**
 * Servlet implementation class MagasinCommmandeDetailServlet
 */
@WebServlet("/MagasinCommmandeDetailServlet")
public class MagasinCommmandeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagasinCommmandeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idc = (String)request.getParameter("idc");
		
		System.out.println(idc);
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			
			// Changer l'etat de commande paye -> en cours de preparation
			Query<Commande> queryUpdate = session.createQuery("From Commande c where c.idCommande = :idc", Commande.class);
			queryUpdate.setParameter("idc", idc);
			
			Commande c = queryUpdate.getSingleResult();
			
			// prendre objet statut id= 2
			StatutCommande newStatut = session.get(StatutCommande.class, 2);

			// update c.statut
			c.setStatutCommande(newStatut);
			
			session.update(c);
			
			// Info detail de commmande
			
			Query<LigneCommande> query = session.createQuery("From LigneCommande lc where lc.commande.idCommande = :idc",LigneCommande.class);
			query.setParameter("idc", idc);
			
			List<LigneCommande> listeLigneCom = query.list();
			
			ArrayList<Integer> listeStock = new ArrayList<Integer>();
			
			for (LigneCommande lc : listeLigneCom) {
				int idP = lc.getProduit().getIdProduit();
				Query<Integer> query2 = session.createQuery("Select s.quantiteEnStock From Stock s where s.produit.idProduit = :idP",Integer.class);
				query2.setParameter("idP", idP);
				int stockP = query2.getSingleResult();
				
				System.out.println(stockP);
				listeStock.add(stockP);
			}
			
			
			session.close();
			
			HttpSession s = request.getSession();
			s.setAttribute("listeStock", listeStock);
			s.setAttribute("listeLigneCom", listeLigneCom);
			
			//System.out.println(s.getAttribute("listeStock").toString());
			
			response.sendRedirect("preparationCom.jsp");
			
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
