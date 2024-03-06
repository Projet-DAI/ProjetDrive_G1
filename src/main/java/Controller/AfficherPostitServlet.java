package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.DAO.ClientDAO;
import Model.DAO.HibernateUtil;
import Model.DAO.PostitDao;
import Model.DAO.ProduitDAO;
import Model.DAO.ListDAO;

import Model.metier.ListeCourse;
import Model.metier.PostIt;
import Model.metier.Produit;
import Model.metier.Client;


@WebServlet("/afficherPostitServlet")
public class AfficherPostitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		 // get listCourse
		String listeCourseIdStr = request.getParameter("listeCourseId");
		System.out.println("listeCourseIdStr:" + listeCourseIdStr);
		int listeCourseId = Integer.parseInt(listeCourseIdStr);
		
		String listeCourseName = request.getParameter("listeCourseName");
		
	    PostitDao postitDao = new PostitDao();
	    
	    List<PostIt> postits = postitDao.findAllByListeCourseId(listeCourseId);
	    System.out.println(postits.size());
	    
	    request.setAttribute("postits", postits);
	    request.setAttribute("listeCourseId", listeCourseId);
	    request.setAttribute("listeCourseName", listeCourseName);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/postit.jsp");
	    dispatcher.forward(request, response);

	}
}