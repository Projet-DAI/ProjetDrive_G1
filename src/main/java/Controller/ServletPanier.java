package Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.DAO.HibernateUtil;
import Model.DAO.PanierDAO;
import Model.metier.LignePanier;
import Model.metier.Panier;

/**
 * Servlet implementation class ServletPanier
 */
@WebServlet("/Panier")
public class ServletPanier extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Vérifier si les attributs de session sont présents
        Integer clientId = (Integer) session.getAttribute("clientId");
        Integer panierId = (Integer) session.getAttribute("panierId");

        if (clientId != null && panierId != null && panierId != 0) {
        	PanierDAO panierDAO = new PanierDAO();
            Panier panier = panierDAO.getPanierById(panierId);
            
            if (panier != null) {
            	
                // Afficher les détails du panier
                panierDAO.afficherDetailsPanier(panier);
                
                // Ajouter le panier à la requête avec le nom correct
                session.setAttribute("Panier", panier);
                
                // Rediriger vers Panier.jsp
                request.getRequestDispatcher("/Panier.jsp").forward(request, response);
                
            } else {
                System.out.println("Le panier est introuvable.");
                response.sendRedirect("index.jsp");
            }
        } else {
            System.out.println("Le client ou le panier est introuvable dans la session.");
            response.sendRedirect("referer");
        }
    }

    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
