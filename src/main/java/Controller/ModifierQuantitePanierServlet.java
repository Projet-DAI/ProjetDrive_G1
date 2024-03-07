package Controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Model.DAO.PanierDAO;
import Model.DAO.ProduitDAO;
import Model.metier.Panier;
import Model.metier.Produit;

@WebServlet("/ModifierQuantitePanierServlet")
public class ModifierQuantitePanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifierQuantitePanierServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres de la requête
        int panierId = Integer.parseInt(request.getParameter("panierId"));
        int produitId = Integer.parseInt(request.getParameter("produitId"));
        int nouvelleQuantite = Integer.parseInt(request.getParameter("nouvelleQuantite"));
        
        // Récupérer le panier à partir de l'ID
        PanierDAO panierDAO = new PanierDAO();
        Panier panier = panierDAO.getPanierById(panierId);
        panierDAO.modifierQuantiteProduit(panier, produitId, nouvelleQuantite);

        

        // Mettre à jour le panier dans la session
        HttpSession session = request.getSession();
        session.setAttribute("Panier", panier);
        // Construire une réponse JSON avec les nouvelles données du panier
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("total", panierDAO.calculerTotalPanier(panier));
        // Ajoutez d'autres données si nécessaire

        // Envoyer la réponse JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }

    }
