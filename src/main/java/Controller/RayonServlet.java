package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.ProduitDAO;
import Model.metier.Produit;

/**
 * Servlet implementation class RayonServlet
 */
@WebServlet("/RayonServlet")
public class RayonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RayonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int rayonId = Integer.parseInt(request.getParameter("rayonId"));

            List<Produit> listeProduits = ProduitDAO.getProduitsByRayon(rayonId);

            request.setAttribute("listeProduits", listeProduits);
            request.getRequestDispatcher("/rayon.jsp").forward(request, response);
        } catch (NumberFormatException | ServletException | IOException e) {
            e.printStackTrace(); // Gérez l'exception correctement en fonction de votre logique
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
