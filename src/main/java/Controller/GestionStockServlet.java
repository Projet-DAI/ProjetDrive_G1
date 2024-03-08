package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.Gson;
import Model.DAO.StockDAO;
import Model.metier.Magasin;
import Model.metier.Stock;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class GestionServlet
 */
@WebServlet("/gestionStockServlet")
public class GestionStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionStockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	        
			String action = request.getParameter("action");
	        System.out.print("action: " + action);
	        
	        if ("loadMagasins".equals(action)) {
	            List<Magasin> magasins = StockDAO.loadMagasins();
	            System.out.print("loadMagasins: " + magasins);
	            request.setAttribute("magasins", magasins);
	            request.getRequestDispatcher("gestionStocks.jsp").forward(request, response);
	            
	        } else if ("showStocks".equals(action)) {
	            
	        	int magasinId = Integer.parseInt(request.getParameter("magasinId"));
	            System.out.print("magasinId: " + magasinId);
	            //List<Map<String, Object>> productsAndStocks = StockDAO.getProductsAndStocksForMagasin(magasinId);
	            List<Object[]> productsAndStocks = StockDAO.getProductsAndStocksForMagasin(magasinId);
	            System.out.print("productsAndStocks: " + productsAndStocks);
	            for (Object[] stock : productsAndStocks) {
	                StringBuilder stockDetails = new StringBuilder("Stock Details: ");
	                for (Object property : stock) {
	                    stockDetails.append(property).append(", ");
	                }
	                System.out.println(stockDetails.toString());
	            }
	            
	            String json = new Gson().toJson(productsAndStocks);
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().write(json);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        //response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error processing request");
	        System.err.println("Exception message: " + e.getMessage());
	        System.err.println("Exception cause: " + e);
	        e.printStackTrace();
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
