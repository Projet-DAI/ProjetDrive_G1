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

/**
 * Servlet implementation class GestionServlet
 */
@WebServlet("/GestionServlet")
public class GestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        if ("loadMagasins".equals(action)) {
            List<Magasin> magasins = StockDAO.loadMagasins();
            String json = new Gson().toJson(magasins);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else if ("showStocks".equals(action)) {
            int magasinId = Integer.parseInt(request.getParameter("magasinId"));
            List<Stock> stocks = StockDAO.getStocksForMagasin(magasinId);
            String json = new Gson().toJson(stocks);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
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
