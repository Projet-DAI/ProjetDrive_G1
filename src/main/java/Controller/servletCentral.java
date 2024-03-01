package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.ProduitDAO;
import Model.metier.Panier;
import Model.DAO.PanierDAO;

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
        
			/*
			 * case "shop": try { // lire la liste des messages List<Produit>
			 * promotedProducts = hibernateMethode.getProduitsProm(); // chainage vers la
			 * vue "Afficher.jsp" avec la liste request.setAttribute("liste_msg",
			 * promotedProducts); // Page d'affichage des informations
			 * request.getRequestDispatcher("shop").forward(request, response);
			 * }catch(Exception ex){ // chainage vers "index.jsp"
			 * request.setAttribute("msg_erreur", ex.getMessage());
			 * request.getRequestDispatcher("Index").forward(request, response); } break;
			 */
                
			/*
			 * case "detailProduct": String productIdStr =
			 * request.getParameter("productId"); if(productIdStr != null &&
			 * !productIdStr.isEmpty()) { try { int productId =
			 * Integer.parseInt(productIdStr); Produit product =
			 * hibernateMethode.getProductById(productId); request.setAttribute("product",
			 * product); request.getRequestDispatcher("detailProduct.jsp").forward(request,
			 * response); }catch(Exception ex){ // chainage vers "index.jsp"
			 * request.setAttribute("msg_erreur", ex.getMessage());
			 * request.getRequestDispatcher("Index").forward(request, response); } }
			 * 
			 * break;
			 */
            case "addToCart":
                String productIdStr1 = request.getParameter("productId");
                String quantityStr = request.getParameter("quantity");
                HttpSession session = request.getSession();
                if (session.getAttribute("clientId") == null) {
                	response.sendRedirect("login.jsp");
                    return;
                }
                
                
                if (productIdStr1 != null && quantityStr != null && !productIdStr1.isEmpty() && !quantityStr.isEmpty()) {
                    try {
                        int productId = Integer.parseInt(productIdStr1);
                        int quantity = Integer.parseInt(quantityStr);
                        Produit product = ProduitDAO.getProductById(productId);
                        
                        int clientId = (int) session.getAttribute("clientId");

//                        HttpSession session1 = request.getSession(true);
//                        Panier panier = (Panier) session1.getAttribute("panier");
//                        if (panier == null) {
//                            panier = new Panier();
//                        }
                        PanierDAO panierDAO = new PanierDAO();
                        panierDAO.ajouterProduitAuPanier(1, product, quantity); // Utilisez l'ID du panier correct ici

                        
                       // panierDAO.ajouterProduitAuPanier(panier.getIdPanier(), product, quantity);

                        response.sendRedirect("detailProduct.jsp?productId=" + productId + "&addedToCart=true");
                    } catch (Exception ex) {
                        // Gérer les erreurs ici
                        ex.printStackTrace();
                        request.setAttribute("error", "Une erreur s'est produite lors de l'ajout au panier: " + ex.getMessage());
                        request.getRequestDispatcher("detailProduct.jsp?productId=" + productIdStr1).forward(request, response);
                    }
                }
                break;

        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  }}
	  
	  
