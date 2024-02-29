package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.hibernateMethode;
import Model.metier.Panier;
import Model.metier.Produit;

/**
 * Servlet implementation class servletIndex
 */
@WebServlet("/servletCentral")
public class servletCentral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCentral() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupérer le paramètre "method"
        String m = request.getParameter("method");
        
     // traitement 
        switch(m){
	        case "accueil":
	            request.getRequestDispatcher("index").forward(request, response);
	            break;
        
            case "shop":
                try {
                    // lire la liste des messages
                    List<Produit> promotedProducts = hibernateMethode.getProduitsProm();                   
                    // chainage vers la vue "Afficher.jsp" avec la liste 
                    request.setAttribute("liste_msg", promotedProducts);
                    // Page d'affichage des informations
                    request.getRequestDispatcher("shop").forward(request, response);
                }catch(Exception ex){
                    // chainage vers "index.jsp"
                    request.setAttribute("msg_erreur", ex.getMessage());
                    request.getRequestDispatcher("Index").forward(request, response);
                }
                break;
                
            case "detailProduct":
            	String productIdStr = request.getParameter("productId");
            	if(productIdStr != null && !productIdStr.isEmpty()) {
            		try {
            			int productId = Integer.parseInt(productIdStr);
            			Produit product = hibernateMethode.getProductById(productId);
            			request.setAttribute("product", product);
                        request.getRequestDispatcher("detailProduct.jsp").forward(request, response);
                	}catch(Exception ex){
                        // chainage vers "index.jsp"
                        request.setAttribute("msg_erreur", ex.getMessage());
                        request.getRequestDispatcher("Index").forward(request, response);
                    }
            	}
            	
            	break;
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productIdStr = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");
		
		if (productIdStr != null && quantityStr != null && !productIdStr.isEmpty() && !quantityStr.isEmpty()) {
		        try {
		            // Convertir les chaînes en entiers
		            int productId = Integer.parseInt(productIdStr);
		            int quantity = Integer.parseInt(quantityStr);

		            // Récupérer le produit depuis la base de données
		            Produit product = hibernateMethode.getProductById(productId);
		            
		            // Vérifier si le produit existe
		            if (product != null) {
		                // Créer ou récupérer la session utilisateur
		                HttpSession session = request.getSession(true);

		                // Récupérer le panier de l'utilisateur depuis la session
		                Panier panier = (Panier) session.getAttribute("panier");
		                if (panier == null) {
		                    // Si l'utilisateur n'a pas de panier, créer un nouveau panier
		                    panier = new Panier();
		                }

		                // Ajouter le produit au panier
		                panier.ajouterProduit(product, quantity);

		                // Mettre à jour la session avec le nouveau panier
		                session.setAttribute("panier", panier);

		                // Redirection vers une page de confirmation d'ajout au panier
		                response.sendRedirect("confirmation.jsp");
		            } else {
		                // Gérer le cas où le produit n'existe pas
		                request.setAttribute("error", "Product not found.");
		                request.getRequestDispatcher("error.jsp").forward(request, response);
		            }
		        } catch (NumberFormatException e) {
		            // Gérer les erreurs de conversion des paramètres en entiers
		            request.setAttribute("error", "Invalid product ID or quantity.");
		            request.getRequestDispatcher("error.jsp").forward(request, response);
		        }
		    } else {
		        // Gérer le cas où les paramètres sont manquants
		        request.setAttribute("error", "Product ID or quantity is missing.");
		        request.getRequestDispatcher("index.jsp").forward(request, response);
		    }
		}

}
