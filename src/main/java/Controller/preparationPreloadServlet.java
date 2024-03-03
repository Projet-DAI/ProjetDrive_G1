package Controller;

import java.io.IOException;
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
import Model.metier.Magasin;

/**
 * Servlet implementation class preparationPreloadServlet
 */
@WebServlet("/preparationPreloadServlet")
public class preparationPreloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public preparationPreloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction transaction = session.beginTransaction();
			
			Query<Magasin> query = session.createQuery("From Magasin m", Magasin.class);
			
			List<Magasin> listeM = query.list();
			
			for (Magasin m : listeM) {
				System.out.println(m.getNomMagasin());	
			}
			
			transaction.commit();
			session.close();
			
			request.setAttribute("listeM", listeM);
			request.getRequestDispatcher("preparation.jsp").forward(request, response);
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
