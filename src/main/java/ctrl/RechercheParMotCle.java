package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.hibernateMethode;
import Model.metier.Produit;

/**
 * Servlet implementation class RechercheParMotCle
 */
@WebServlet("/RechercheParMotCle")
public class RechercheParMotCle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheParMotCle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mot = request.getParameter("motcle");
		
		List<Produit> listP = new ArrayList<Produit>();
		try {
			listP = hibernateMethode.rechercheParMotCle(mot);
//			System.out.println(listP.size());
		
//		for (Produit p : listP) {
//			System.out.println(p.getNomProduit());
//		}
		} catch (Exception e) {
 
            e.printStackTrace();
		}
		
		request.setAttribute("listP", listP);
		request.setAttribute("motcle", mot);
		request.getRequestDispatcher("/resRechercheParMotCle.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
