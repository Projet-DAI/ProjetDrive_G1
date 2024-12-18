package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.PanierDAO;
import Model.metier.Panier;

/**
 * Servlet implementation class SupprimerLignePanierServlet
 */
@WebServlet("/SupprimerLignePanierServlet")
public class SupprimerLignePanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerLignePanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres de la requête
        int idPanier = Integer.parseInt(request.getParameter("idPanier"));
        int idProduit = Integer.parseInt(request.getParameter("idProduit"));

        // Créer une instance de PanierDAO
        PanierDAO panierDAO = new PanierDAO();
        Panier panier = panierDAO.getPanierById(idPanier);


        // Appeler la méthode pour supprimer la ligne de panier
        panierDAO.supprimerLignePanier(idPanier, idProduit);

	     // Récupérer à nouveau le panier mis à jour depuis la base de données
	     panier = panierDAO.getPanierById(idPanier);
        // Mettre à jour le panier dans la session
        HttpSession session = request.getSession();
        session.setAttribute("panier", panier);
        
        response.getWriter().write("Item removed successfully");

    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    }
}