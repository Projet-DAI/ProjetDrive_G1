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
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traiter les requêtes POST si nécessaire
        doGet(request, response);
    }
}
