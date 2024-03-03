package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.ProduitDAO;
import Model.metier.Client;
import Model.metier.Panier;
import Model.DAO.PanierDAO;

import Model.metier.Produit;


/**
 * Servlet implementation class AjouterPanierServlet
 */
@WebServlet("/AjouterPanierServlet")
public class AjouterPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public AjouterPanierServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    
    private PanierDAO panierDAO;

    public void init() throws ServletException {
        panierDAO = new PanierDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Vérifiez si le client est connecté en vérifiant s'il existe une session utilisateur
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("client") != null) {
            // Le client est connecté, récupérez ses informations de session
            Client client = (Client) session.getAttribute("client");
            int clientId = client.getIdClient();
            
            Panier panier = panierDAO.getPanierByClientId(clientId);
            
         // Vérifier si un panier a été trouvé
            if (panier != null) {
                // Récupérer l'identifiant du panier
                int panierId = panier.getIdPanier();

				 /*int panierId = Integer.parseInt(request.getParameter("panierId"));*/
			     int produitId = Integer.parseInt(request.getParameter("produitId"));
			     int quantite = Integer.parseInt(request.getParameter("quantite"));

			     panierDAO.addToCart(panierId, produitId, quantite);

			     response.sendRedirect(request.getHeader("referer"));
	    
		 } else {
	         // Le client n'est pas connecté, redirigez-le vers la page de connexion
	         response.sendRedirect("login.jsp");
	     }
 }

	
	}
}


