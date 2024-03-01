package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.ProduitDAO;
import Model.metier.Panier;
import Model.DAO.PanierDAO;
import Model.DAO.*;
import Model.metier.*;



import Model.metier.Produit;

/**
 * Servlet implementation class servletIndex
 */
//Importez les classes nécessaires

@WebServlet("/servletCentral")
public class servletCentral extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        switch(method) {
            case "accueil":
                request.getRequestDispatcher("index").forward(request, response);
                break;
                
            case "addToCart":
                String productIdStr = request.getParameter("productId");
                String quantityStr = request.getParameter("quantity");

                if (Objects.isNull(productIdStr) || Objects.isNull(quantityStr)) {
                    request.setAttribute("error", "Les paramètres de produit ou de quantité sont manquants.");
                    request.getRequestDispatcher("shop.jsp").forward(request, response);
                    return;
                }

                int productId = Integer.parseInt(productIdStr); // Déplacez la déclaration de productId ici

                try {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity <= 0) {
                        request.setAttribute("error", "La quantité doit être un entier positif.");
                        response.sendRedirect("detailProduct.jsp?productId=" + productId + "&addedToCart=true");

                    }

                    Produit product = ProduitDAO.getProductById(productId);
                    if (product == null) {
                        request.setAttribute("error", "Le produit avec l'ID " + productId + " n'existe pas.");
                        response.sendRedirect("detailProduct.jsp?productId=" + productId + "&addedToCart=true");

                    }

                    // Vous pouvez ajouter ici la logique pour ajouter le produit au panier
                    // Par exemple, appeler une méthode de votre classe PanierDAO pour ajouter le produit au panier

                    // Rediriger vers la page de confirmation ou une autre page appropriée
                    response.sendRedirect("detailProduct.jsp?productId=\" + productId + \"&addedToCart=true");
         
                } catch (Exception ex) {
                    request.setAttribute("error", "Une erreur s'est produite lors de l'ajout au panier: " + ex.getMessage());
                    response.sendRedirect("detailProduct.jsp?productId=" + productId + "&addedToCart=true");

                }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

