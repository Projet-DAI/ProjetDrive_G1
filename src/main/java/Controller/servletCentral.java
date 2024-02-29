package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.ProduitDAO;
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
                    List<Produit> promotedProducts = ProduitDAO.getProduitsProm();                   
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
            			Produit product = ProduitDAO.getProductById(productId);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
