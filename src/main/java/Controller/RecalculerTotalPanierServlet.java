package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.PanierDAO;
import Model.metier.Panier;

@WebServlet("/RecalculerTotalPanierServlet")
public class RecalculerTotalPanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer le panier depuis la session
        Panier panier = (Panier) request.getSession().getAttribute("panier");

        // Vérifier si le panier existe
        if (panier != null) {
            // Calculez le nouveau total du panier
            double nouveauTotal = PanierDAO.calculerTotalPanier(panier);

            // Convertir le nouveau total en chaîne de caractères pour l'envoyer en réponse
            String nouveauTotalStr = String.valueOf(nouveauTotal);

            // Envoyer le nouveau total en réponse
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(nouveauTotalStr);
            out.flush();
        } else {
            // Si le panier est vide, renvoyer 0 en réponse
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print("0");
            out.flush();
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
    }
}
