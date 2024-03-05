package Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PanierDAO panierDAO = new PanierDAO();
        int panierId = 1;
        Panier panier = panierDAO.getPanierById(panierId);
        if (panier != null) {
            // Afficher les détails du panier
            System.out.println("ID Panier: " + panier.getIdPanier());
            System.out.println("Date de création: " + panier.getDateCreation());
            // Afficher les lignes de panier
            System.out.println("Contenu du panier:");
            for (LignePanier lignePanier : panier.getLignesPanier()) {
                System.out.println("Produit: " + lignePanier.getProduit().getNomProduit() + ", Quantité: "
                        + lignePanier.getQuantite());
                // Afficher d'autres détails de la ligne de panier selon vos besoins
            }
            // Ajouter le panier à la requête avec le nom correct
            request.setAttribute("panier", panier);
        } else {
            System.out.println("Le panier est introuvable.");
        }
        // Rediriger vers Panier.jsp
        request.getRequestDispatcher("/Panier.jsp").forward(request, response);
    }

    /**List<Demande> ld = (List<Demande>) q.list();
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
