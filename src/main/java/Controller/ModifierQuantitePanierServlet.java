package Controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.PanierDAO;
import Model.DAO.ProduitDAO;
import Model.metier.Panier;
import Model.metier.Produit;

/**
 * Servlet implementation class ModifierQuantitePanierServlet
 */
@WebServlet("/ModifierQuantitePanierServlet")
public class ModifierQuantitePanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierQuantitePanierServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	        HttpSession session = request.getSession(false);
	        if (session != null && session.getAttribute("clientId") != null) {
	            int clientId = (int) session.getAttribute("clientId");
	            PanierDAO panierDAO = new PanierDAO();
	            Panier panier = panierDAO.getPanierByClientId(clientId);
	            if (panier != null) {
	                Map<Integer, Integer> nouvellesQuantites = new HashMap<>();
	                Enumeration<String> parameterNames = request.getParameterNames();
	                while (parameterNames.hasMoreElements()) {
	                    String paramName = parameterNames.nextElement();
	                    if (paramName.startsWith("quantite_")) {
	                        int productId = Integer.parseInt(paramName.split("_")[1]);
	                        int nouvelleQuantite = Integer.parseInt(request.getParameter(paramName));
	                        nouvellesQuantites.put(productId, nouvelleQuantite);
	                    }
	                }
	                for (Map.Entry<Integer, Integer> entry : nouvellesQuantites.entrySet()) {
	                    int productId = entry.getKey();
	                    int nouvelleQuantite = entry.getValue();
	                    Produit produit = ProduitDAO.getProductById(productId); // Récupérer le produit par son identifiant

	                    if (nouvelleQuantite == 0) {
	                        // Supprimer le produit du panier si la quantité est mise à zéro
	                        panierDAO.removeProduit(panier,produit);
	                    } else {
	                        // Mettre à jour la quantité du produit dans le panier
	                        panierDAO.updateQuantiteProduit(panier, produit, nouvelleQuantite);
	                    }
	                }
	                // Mettre à jour le panier dans la base de données
	                panierDAO.updatePanier(panier);
	            }
	            // Rediriger vers la page du panier
	            response.sendRedirect("Panier.jsp");
	        } else {
	            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
	            response.sendRedirect("login.jsp");
	        }
	    }
	
	}


