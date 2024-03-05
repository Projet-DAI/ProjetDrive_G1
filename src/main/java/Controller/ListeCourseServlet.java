package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.metier.LigneListeCourse;
import Model.metier.Produit;
import antlr.collections.List;

/**
 * Servlet implementation class ListeCourseServlet
 */
@WebServlet("/shoppingList")
public class ListeCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method != null && method.equals("ajouterList")) {
            // 获取产品 ID
            int productId = Integer.parseInt(request.getParameter("productId"));
            
            // 假设这里有一个 ProductService 类来处理购物清单操作
            LigneListeCourse productService = new LigneListeCourse();
            Produit product = productService.getProduit();
            
            // 获取当前会话对象
            HttpSession session = request.getSession();
            
            // 从会话中获取购物清单列表
            List shoppingList = (List) session.getAttribute("shoppingList");
            
            
            
            // 将产品添加到购物清单列表中
            shoppingList.add(product);
            
            // 更新会话中的购物清单列表
            session.setAttribute("shoppingList", shoppingList);
            
            response.getWriter().write("Product added to shopping list successfully");
        }
    }
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
