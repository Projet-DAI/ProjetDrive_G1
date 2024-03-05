package Controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.ProduitDAO;
import Model.DAO.MagasinDao;
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
		String selectedMagasin = request.getParameter("selectedMagasin");
		System.out.println("selectedMagasin : " + selectedMagasin);

		try {
			
			if (selectedMagasin != null && !selectedMagasin.isEmpty()) {
	            int magasinId = MagasinDao.getMagasinIdByName(selectedMagasin);
	            List<Produit> promotedProducts = ProduitDAO.getProduitsPromParIdMagasin(magasinId);
	            request.setAttribute("liste_msg", promotedProducts);
	        } 
			
            // lire la liste des messages
            List<Produit> promotedProducts = ProduitDAO.getProduitsProm();                   
            // chainage vers la vue "Afficher.jsp" avec la liste 
            request.setAttribute("liste", promotedProducts);
            // Page d'affichage des informations
            request.getRequestDispatcher("/shop.jsp").forward(request, response);
        }catch(Exception ex){
            // chainage vers "index.jsp"
            request.setAttribute("msg_erreur", ex.getMessage());
            response.sendRedirect("index.jsp");
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
