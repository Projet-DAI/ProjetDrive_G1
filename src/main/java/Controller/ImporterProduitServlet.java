package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.DAO.ImporterProduitDAO;


@WebServlet("/ImporterProduitServlet")
@MultipartConfig
public class ImporterProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ImporterProduitServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	Part filePart = request.getPart("file"); 
       
    	if (filePart != null) {
        	
            ImporterProduitDAO dao = new ImporterProduitDAO();
            dao.importCSV(filePart.getInputStream());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/importerProduitReussir.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File part not found.");
        }
    }
  
}
