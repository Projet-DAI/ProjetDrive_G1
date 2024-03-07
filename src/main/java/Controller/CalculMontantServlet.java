package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.ClientDAO;
import Model.DAO.PanierDAO;
import Model.metier.Client;
import Model.metier.LignePanier;
import Model.metier.Panier;

@WebServlet("/CalculMontantServlet")
public class CalculMontantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer la session
        HttpSession session = request.getSession();

        // Récupérer l'ID du client connecté
        Integer clientId = (Integer) session.getAttribute("clientId");

        if (clientId != null) {
            // Récupérer le panier depuis la session
            Panier panier = (Panier) session.getAttribute("panier");

            if (panier != null && !panier.getLignesPanier().isEmpty()) {
                // Calculer le montant total du panier
                double totalPanier = 0.0;
                for (LignePanier lignePanier : panier.getLignesPanier()) {
                    totalPanier += lignePanier.getProduit().getPrixProduit() * lignePanier.getQuantite();
                }

                // Récupérer les points de fidélité du client depuis la base de données
                ClientDAO clientDAO = new ClientDAO();
                Client client = clientDAO.getClientById(clientId);
                int pointsFidelite = clientDAO.getPointsFideliteById(clientId);

                // Calculer le montant total après réduction
                double reductionEnEuros = (double) pointsFidelite / 10.0;
                double nouveauTotalPanier = totalPanier - reductionEnEuros;

                // Mettre à jour les attributs de la requête
                request.setAttribute("totalPanier", totalPanier);
                request.setAttribute("pointsFidelite", pointsFidelite);
                request.setAttribute("nouveauTotalPanier", nouveauTotalPanier);

                // Rediriger vers la page appropriée pour afficher les informations
                request.getRequestDispatcher("/Panier.jsp").forward(request, response);
            } else {
                // Le panier est vide ou introuvable
                response.sendRedirect("index.jsp"); // Rediriger vers la page appropriée
            }
        } else {
            // L'utilisateur n'est pas connecté
            response.sendRedirect("login.jsp"); // Rediriger vers la page de connexion
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traiter les requêtes POST si nécessaire
        doGet(request, response);
    }
}
