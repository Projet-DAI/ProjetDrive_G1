package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.DAO.ProduitDAO;
import Model.metier.Client;
import Model.metier.LignePanier;
import Model.metier.Panier;
import Model.DAO.HibernateUtil;
import Model.DAO.PanierDAO;

import Model.metier.Produit;



/**
 * Servlet implementation class AjouterPanierServlet
 */
@WebServlet("/AjouterPanierServlet")
public class AjouterPanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PanierDAO panierDAO;

    public void init() throws ServletException {
        panierDAO = new PanierDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdString = request.getParameter("productId");
        String quantiteString = request.getParameter("quantite");

        if (productIdString != null && quantiteString != null) {
            int productId = Integer.parseInt(productIdString);
            int quantiteDemandee = Integer.parseInt(quantiteString);

            Produit produit = ProduitDAO.getProductById(productId);

            if (produit != null) {
                int quantiteEnStock = ProduitDAO.getQuantiteEnStock(productId);

                HttpSession session = request.getSession(false);

                if (session != null && session.getAttribute("clientId") != null) {
                    int clientId = (int) session.getAttribute("clientId");

                    Panier panier = panierDAO.getPanierByClientId(clientId);

                    if (panier != null) {
                        if (quantiteDemandee <= quantiteEnStock) {
                            LignePanier lignePanier = new LignePanier();
                            lignePanier.setQuantite(quantiteDemandee);
                            lignePanier.setPanier(panier);
                            lignePanier.setProduit(produit);

                            panierDAO.addToCart(panier.getIdPanier(), productId, quantiteDemandee);

                            response.sendRedirect(request.getHeader("referer"));
                        } else {
                            List<Produit> produitsDeRemplacement = ProduitDAO.getProduitsDeRemplacement(produit.getCategorie(), productId);
                            request.setAttribute("produitsDeRemplacement", produitsDeRemplacement);

                            request.getRequestDispatcher("produitsDeRemplacement.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("Aucun panier trouvé pour le client: " + clientId);

                        panier = new Panier();
                        panier.setClient(new Client(clientId));

                        panierDAO.createPanier(panier);

                        response.sendRedirect(request.getHeader("referer"));
                    }
                }
            } else {
                System.out.println("Produit non trouvé pour l'ID: " + productId);
                response.sendRedirect("shop.jsp");
            }
        }
    }
}
