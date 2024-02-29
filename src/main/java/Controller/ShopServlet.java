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
 * Servlet implementation class AffichierProduitServlet
 */
@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // lire la liste des messages
            List<Produit> promotedProducts = ProduitDAO.getProduitsProm();                   
            // chainage vers la vue "Afficher.jsp" avec la liste 
            request.setAttribute("liste_msg", promotedProducts);
            // Page d'affichage des informations
            request.getRequestDispatcher("/shop.jsp").forward(request, response);
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
