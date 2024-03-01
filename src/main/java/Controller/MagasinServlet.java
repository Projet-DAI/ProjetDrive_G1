package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.DAO.HibernateUtil;
import Model.DAO.MagasinDao;
import Model.metier.Magasin;
import java.util.List;

/**
 * Servlet implementation class MagasinSerclet
 */
@WebServlet("/MagasinServlet")
public class MagasinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagasinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userLocation = request.getParameter("userLocation");	
	        List<Magasin> mags = MagasinDao.choisirMagasins(userLocation);
	        System.out.println(mags);
	        request.setAttribute("mags", mags);
	        // Page d'affichage des informations
	        request.getRequestDispatcher("/magasin.jsp").forward(request, response);
		}catch(Exception ex){
            // chainage vers "index.jsp"
            request.setAttribute("msg_erreur", ex.getMessage());
            response.sendRedirect("index.html");
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
