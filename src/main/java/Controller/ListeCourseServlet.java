package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.hibernate.SessionFactory;

import Model.DAO.ChoisirProduitDao;
import Model.DAO.HibernateUtil;
import Model.DAO.PostitDao;
import Model.metier.PostIt;
import Model.metier.Produit;

@WebServlet("/listCourse")
public class ListeCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;
    
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createKeyword(request, response);
                break;
            case "delete":
                deleteKeyword(request, response);
                break;
            case "clearAll":
                clearAllKeywords(request, response);
                break;
            case "choisir":
                choisirProduit(request, response);
                break;
            default:
                // Handle invalid action
                break;
        }
    }

   

    private void createKeyword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.isEmpty()) {
        	 PostIt postit = new PostIt();
        	
        	 PostitDao postitDao = new PostitDao(sessionFactory); 
             postit.setContenu(keyword);
             postit.setDateCreation(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())); 
       //    System.out.println(postit);
             postitDao.save(postit); 

            HttpSession session = request.getSession();
            String sessionKeyword = (String) session.getAttribute("keyword");
            if (sessionKeyword != null && !sessionKeyword.isEmpty()) {
                sessionKeyword += ", " + keyword;
            } else {
                sessionKeyword = keyword;
            }
            session.setAttribute("keyword", sessionKeyword);    

            // 构建 HTML 表格行
            String htmlResponse = "<tr>"
                                    + "<td>" + keyword + "</td>"
                                    + "<td><a href=\"#\" class=\"keyword-link\" onclick=\"openProductModal('" + keyword + "')\">Choisir mon produit</a></td>"
                                    + "<td><button class=\"btn btn-danger btn-sm\" onclick=\"deleteKeyword('" + keyword + "')\"><i class=\"bi bi-x\"></i></button></td>"
                                 + "</tr>";

            // 将 HTML 表格行作为响应返回给前端
            response.getWriter().write(htmlResponse);
        } else {
            response.getWriter().write("Veuillez entrer un mot-clé valide !");
        }
    }




    private void deleteKeyword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keywordToDelete = request.getParameter("keyword");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
        HttpSession session = request.getSession();
        String sessionKeyword = (String) session.getAttribute("keyword");
        
        if (sessionKeyword != null && !sessionKeyword.isEmpty()) {
            String[] keywords = sessionKeyword.split(",");
            StringBuilder newKeywords = new StringBuilder();
            boolean deleted = false;
            for (String keyword : keywords) {
                if (!keyword.trim().equals(keywordToDelete)) {
                    newKeywords.append(keyword.trim());
                    newKeywords.append(",");
                } else {
                    deleted = true;
                }
            }
            
            // 删除末尾的逗号
            if (newKeywords.length() > 0) {
                newKeywords.setLength(newKeywords.length() - 1);
            }
            
            session.setAttribute("keyword", newKeywords.toString());
            
            if (deleted) {
                // 检查 sessionFactory 是否为 null
                if (sessionFactory != null) {
                    // 初始化 PostitDao 并调用 deleteByContenu 方法
                    PostitDao postitDao = new PostitDao(sessionFactory);
                    postitDao.deleteByContenu(keywordToDelete);
                    response.getWriter().write("Mot-clé supprimé avec succès !");
                } else {
                    response.getWriter().write("Erreur: sessionFactory est null !");
                }
            } else {
                response.getWriter().write("Mot-clé non trouvé !");
            }
        } else {
            response.getWriter().write("Aucun mot-clé trouvé !");
        }
    }

    private void clearAllKeywords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession();
    	    String sessionKeyword = (String) session.getAttribute("keyword");
    	    
    	    if (sessionKeyword != null && !sessionKeyword.isEmpty()) {
    	        // 在数据库中删除所有关键字对应的数据
    	        String[] keywords = sessionKeyword.split(",");
    	        PostitDao postitDao = new PostitDao(sessionFactory);
    	        for (String keyword : keywords) {
    	            postitDao.deleteByContenu(keyword.trim());
    	        }
    	        
    	        session.removeAttribute("keyword");
    	        response.getWriter().write("Tous les mots-clés ont été effacés avec succès !");
    	    } else {
    	        response.getWriter().write("Aucun mot-clé trouvé !");
    	    }
    }
    
    
    private void choisirProduit(HttpServletRequest request, HttpServletResponse response) {
    	
    	String keyword = request.getParameter("keyword");
    	List<Produit> listP = new ArrayList<Produit>();
    	try {
			listP = ChoisirProduitDao.recherchekeywords(keyword);
		} catch (Exception e) {
 
            e.printStackTrace();
		}
		
		request.setAttribute("listP", listP);
		request.setAttribute("motcle", keyword);
		
		
	    String selectedProductId = request.getParameter("productId");
	    System.out.println("Selected Product ID: " + selectedProductId); 
		
		
	}
}
