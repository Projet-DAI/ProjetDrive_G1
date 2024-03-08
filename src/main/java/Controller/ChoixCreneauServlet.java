package Controller;

import java.io.IOException;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.MagasinDao;

/**
 * Servlet implementation class ChoixCreneauServlet
 */
@WebServlet("/ChoixCreneauServlet")
public class ChoixCreneauServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoixCreneauServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Récupérer l'ID du magasin à partir de la session
		HttpSession session = request.getSession();
		 Integer magasinId = 3; // ID du magasin pour lequel vous souhaitez récupérer les créneaux de retrait
	        System.out.println("le magasin choisi est "+ magasinId);
		// Vérifiez si l'ID du magasin est null ou non
		if (magasinId != null) {
		    // Convertissez Integer en int
	       
		    
		    // Utilisez l'ID du magasin pour récupérer les créneaux disponibles, afficher et rediriger
		    MagasinDao magasinDao = new MagasinDao();
		    List<String> creneauxDisponibles = magasinDao.getTempsRetraitForMagasin(magasinId);
		    
		    // Afficher les créneaux récupérés
		    System.out.println("Créneaux disponibles :");
		    for (String creneau : creneauxDisponibles) {
		        System.out.println("- " + creneau);
		    }
		 
		    
		    request.setAttribute("creneauxDisponibles", creneauxDisponibles);
		} else {
		    // Gérer le cas où l'attribut "idMagasin" n'est pas défini dans la session
		    System.out.println("L'attribut 'idMagasin' n'est pas défini dans la session.");
		    // Vous pouvez rediriger vers une page d'erreur ou effectuer d'autres actions appropriées ici
		}
		// Récupérer le créneau choisi depuis la requête
	    String creneauChoisi = request.getParameter("creneau");
	    
	 // Afficher le créneau choisi dans la console pour vérification
	    System.out.println("Créneau choisi : " + creneauChoisi);

	

	    // Enregistrer le créneau choisi dans la session
	    session.setAttribute("creneauChoisi", creneauChoisi);
	    
		 

		// Rediriger vers la page Checkout.jsp
		request.getRequestDispatcher("Checkout.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Récupérer l'ID du magasin à partir de la session
		HttpSession session = request.getSession();
		 Integer magasinId = 3; // ID du magasin pour lequel vous souhaitez récupérer les créneaux de retrait
	        System.out.println("le magasin choisi est "+ magasinId);		System.out.println("le magasin choisi est "+ magasinId);
		// Vérifiez si l'ID du magasin est null ou non
		if (magasinId != null) {
		    // Convertissez Integer en int
	       
		    
		    // Utilisez l'ID du magasin pour récupérer les créneaux disponibles, afficher et rediriger
		    MagasinDao magasinDao = new MagasinDao();
		    List<String> creneauxDisponibles = magasinDao.getTempsRetraitForMagasin(magasinId);
		    
		    // Afficher les créneaux récupérés
		    System.out.println("Créneaux disponibles :");
		    for (String creneau : creneauxDisponibles) {
		        System.out.println("- " + creneau);
		    }
		 
		    
		    request.setAttribute("creneauxDisponibles", creneauxDisponibles);
		} else {
		    // Gérer le cas où l'attribut "idMagasin" n'est pas défini dans la session
		    System.out.println("L'attribut 'idMagasin' n'est pas défini dans la session.");
		    // Vous pouvez rediriger vers une page d'erreur ou effectuer d'autres actions appropriées ici
		}
		// Récupérer le créneau choisi depuis la requête
	    String creneauChoisi = request.getParameter("creneau");
	    
	 // Afficher le créneau choisi dans la console pour vérification
	    System.out.println("Créneau choisi : " + creneauChoisi);

	

	    // Enregistrer le créneau choisi dans la session
	    session.setAttribute("creneauChoisi", creneauChoisi);
	    
		 

		// Rediriger vers la page Checkout.jsp
		request.getRequestDispatcher("Checkout.jsp").forward(request, response);
	}
}



