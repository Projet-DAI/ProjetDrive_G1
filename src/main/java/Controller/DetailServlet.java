package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.ProduitDAO;
import Model.metier.Produit;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productIdStr = request.getParameter("produitId");
		System.out.println("productIdStr:"+ productIdStr);
    	if(productIdStr != null && !productIdStr.isEmpty()) {
    		try {
    			int produitId = Integer.parseInt(productIdStr);
    			Produit produit = ProduitDAO.getProductById(produitId);
    			System.out.println("produit:"+ produit);
    			request.setAttribute("produit", produit);
                request.getRequestDispatcher("detailProduct.jsp").forward(request, response);
        	}catch(Exception ex){
                // chainage vers "index.jsp"
                request.setAttribute("msg_erreur", ex.getMessage());
                request.getRequestDispatcher("Index").forward(request, response);
            }
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
