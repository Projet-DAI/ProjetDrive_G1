package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.DAO.HibernateUtil;
import Model.DAO.MagasinDao;
import Model.metier.Magasin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class MagasinSerclet
 */
@WebServlet("/MagasinServlet")
public class MagasinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagasinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	        String userLocation = request.getParameter("userLocation");
	        
	        // Récupérer l'ID du magasin en fonction de la localisation
	        int idMagasin = MagasinDao.getIdMagasinByLocation(userLocation); // Remplacez cette ligne par votre logique pour obtenir l'ID du magasin
	        
	        // Stoker l'ID du magasin dans la session
	        HttpSession session = request.getSession();
	        session.setAttribute("idMagasin", idMagasin);
	        List<Magasin> mags = MagasinDao.choisirMagasins(userLocation);
			System.out.println("le magasin choisi est "+ idMagasin);
			
	        List<Map<String, Object>> magasinsData = new ArrayList<>();
	        for (Magasin magasin : mags) {
	            Map<String, Object> magasinData = new HashMap<>();
	            magasinData.put("idMagasin", magasin.getIdMagasin());
	            magasinData.put("nomMagasin", magasin.getNomMagasin());
	            magasinData.put("adresseMagasin", magasin.getAdresseMagasin());
	            magasinsData.add(magasinData);
	        }

	        ObjectMapper mapper = new ObjectMapper();
	        String json = mapper.writeValueAsString(magasinsData);

	        response.setContentType("application/json");
	        response.getWriter().write(json);
	    } catch(Exception ex) {
	        response.setContentType("application/json");
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("{\"error\":\"" + ex.getMessage() + "\"}");
	    }
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
