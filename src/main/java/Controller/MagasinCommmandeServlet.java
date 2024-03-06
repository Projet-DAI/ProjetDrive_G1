package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Commande;

/**
 * Servlet implementation class MagasinCommmandeServlet
 */
@WebServlet("/MagasinCommmandeServlet")
public class MagasinCommmandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagasinCommmandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idm = (String) request.getParameter("idm");
		
		//System.out.println(idm);
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction tr = session.beginTransaction();
			
			Query<Commande> query = session.createQuery("From Commande c where c.magasin.idMagasin = :idm AND c.statutCommande.idStatutCommande between 1 and 3 Order By c.dateCommande DESC", Commande.class);
			query.setParameter("idm", idm);
			
			List<Commande> listC = query.list();
			
			/* 
			[
			  {
			  "1":{"TempsRetaitCom":123456789, "Status":"En cours"},
			  
			  "2":{"TempsRetaitCom":987654321, "Status":"Livré"}
			  }
			]
			*/
			
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			
			
			for (Commande c : listC) {
				
//				System.out.println(c.getIdCommande());
//				System.out.println(c.getTempsRetaitCom());
//				System.out.println(c.getStatutCommande().getLibelleStatut());

				sb.append("\"" + c.getIdCommande() + "\":{\"TempsRetaitCom\":\"" + c.getTempsRetaitCom() + "\", \"Status\":\"" + c.getStatutCommande().getLibelleStatut() + "\"},");
			}
			
			if (sb.length() > 1) {
				sb.deleteCharAt(sb.length() - 1);
			}
			
			sb.append("}");
			
			
			//System.out.println(sb);
			//System.out.println("finish json");
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(sb);
			
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
