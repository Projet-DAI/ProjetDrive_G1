// ServletPanier.java
package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.PanierDAO;
import Model.metier.Panier;
import Model.metier.Produit;
import Model.metier.Stock;
import Model.metier.LignePanier;
import Model.metier.Magasin;
import Model.metier.TempsRetait;
import Model.DAO.MagasinDao;





@WebServlet("/Panier")
public class ServletPanier extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletPanier() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Integer clientId = (Integer) session.getAttribute("clientId");
        Integer panierId = (Integer) session.getAttribute("panierId");

        if (clientId != null && panierId != null && panierId != 0) {
            PanierDAO panierDAO = new PanierDAO();
            Panier panier = panierDAO.getPanierById(panierId);
            
            if (panier != null) {
                double total = panierDAO.calculerTotalPanier(panier);
                request.setAttribute("totalPanier", total);
                session.setAttribute("Panier", panier);
                panierDAO.afficherDetailsPanier(panier);

                // Obtenir les lignes de panier
                List<LignePanier> lignesPanier = panier.getLignesPanier(); // À adapter selon votre implémentation

                // Parcourir toutes les lignes de panier pour obtenir le stock de chaque produit
                for (LignePanier lignePanier : lignesPanier) {
                    Produit produit = lignePanier.getProduit(); // Obtenir le produit de la ligne de panier

                    // Vérifier si le produit n'est pas null
                    if (produit != null) {
                        Stock stock = produit.getStock(); // Obtenir le stock à partir du produit

                        // Vérifier si le stock n'est pas null
                        if (stock != null) {
                        	MagasinDao magasinDao=new MagasinDao();
                            int magasinId = magasinDao.getMagasinIdFromStock(stock);

                            // Récupérer les créneaux disponibles pour le magasin choisi
                            List<String> creneauxDisponibles = magasinDao.getTempsRetraitForMagasin(magasinId); 
                            request.setAttribute("creneauxDisponibles", creneauxDisponibles);
                        } else {
                            System.out.println("Le stock du produit " + produit.getIdProduit() + " est introuvable.");
                        }
                    } else {
                        System.out.println("Le produit de la ligne de panier est introuvable.");
                    }
                }

                request.getRequestDispatcher("/Panier.jsp").forward(request, response);
            } else {
                System.out.println("Le panier est introuvable.");
                response.sendRedirect("index.jsp");
            }
        } else {
            System.out.println("Le client ou le panier est introuvable dans la session.");
            response.sendRedirect("index.jsp");
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        // Récupérer le créneau sélectionné par l'utilisateur

        String creneau = request.getParameter("creneau");

    }
}
