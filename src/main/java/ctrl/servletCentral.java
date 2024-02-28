package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.hibernateMethode;
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
            case "shop":
                try {
                    // lire la liste des messages
                    List<Produit> promotedProducts = hibernateMethode.getProduitsProm();                   
                    // chainage vers la vue "Afficher.jsp" avec la liste 
                    request.setAttribute("liste_msg", promotedProducts);
                    // 传入的页面
                    request.getRequestDispatcher("shop").forward(request, response);
                }catch(Exception ex){
                    // chainage vers "index.jsp"
                    request.setAttribute("msg_erreur", ex.getMessage());
                    request.getRequestDispatcher("Index").forward(request, response);
                }
                break;
                
            case "accueil":
                request.getRequestDispatcher("index").forward(request, response);
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
