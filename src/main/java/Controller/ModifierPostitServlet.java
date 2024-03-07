package Controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Model.DAO.ClientDAO;
import Model.DAO.ListDAO;
import Model.DAO.PostitDao;
import Model.metier.PostIt;
import Model.metier.Client;
import Model.metier.ListeCourse;


/**
 * Servlet implementation class ModifierPostitServlet
 */
@WebServlet("/ModifierPostitServlet")
public class ModifierPostitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierPostitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		// get Client objet par email dans session
		
		String emailCli = (String) session.getAttribute("emailCli"); 
		
		ClientDAO clientDAO = new ClientDAO(); 
		Client client = clientDAO.findClientByEmail(emailCli); 
		System.out.println("client:" + client);

		ListDAO ListeDao = new ListDAO(); 
		
		PostitDao postitDao = new PostitDao();
        ListDAO listDao = new ListDAO();
	    
        switch (action) {
			
        case "ajouter":
        	String postitContent = request.getParameter("postit");
            int listeCourseId = Integer.parseInt(request.getParameter("listeCourseId"));

            ListeCourse listeCourse = listDao.getListeCourseById(listeCourseId);

            boolean exists = postitDao.postitExists(postitContent, client.getIdClient(), listeCourseId);

            JSONObject json = new JSONObject();
            if (!exists) {
                PostIt postit = new PostIt();
                postit.setContenu(postitContent);
                postit.setDateCreation(LocalDateTime.now());
                postit.setClient(client);
                postit.setListeCourse(listeCourse);

                postitDao.save(postit);

                json.put("content", postit.getContenu());
                json.put("id", postit.getIdPostIt());
                json.put("creationDate", postit.getDateCreation().toString());
            } else {
                json.put("message", "Ce PostIt existe déjà.");
                json.put("status", "error");
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        	
            break;
            
        case "supprimer":
            int postitId = Integer.parseInt(request.getParameter("postitId"));

            boolean isDeleted = postitDao.supprimerPostitById(postitId);

            JSONObject jsonResponse = new JSONObject();
            if(isDeleted) {
                jsonResponse.put("status", "success");
            } else {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "ne peut être supprimée PostIt");
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse.toString());
            break;
	    }
	    
	}

}
