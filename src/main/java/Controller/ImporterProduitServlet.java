package Controller;

import java.io.IOException;

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
        Part filePart = request.getPart("file"); // Assumes <input type="file" name="file">.
        if (filePart != null) {
        	// 获取上传文件的文件名
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE修复后的文件名
            
            // 解析CSV文件
            //parseCSVFile(filePart.getInputStream());
            // 可选：设置响应类型和消息
        	
        	// 获取DAO实例并调用importCSV方法
            ImporterProduitDAO dao = new ImporterProduitDAO();
            dao.importCSV(filePart.getInputStream());
            
            response.setContentType("text/html");
            response.getWriter().print("File uploaded and processed successfully.");
        } else {
            // 处理错误情况
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File part not found.");
        }
    }
    
	/*
	 * private void parseCSVFile(InputStream fileContent) { // 实现CSV文件解析和数据插入逻辑 //
	 * 示例：使用Apache Commons CSV解析CSV文件内容 try (BufferedReader reader = new
	 * BufferedReader(new InputStreamReader(fileContent, StandardCharsets.UTF_8));
	 * CSVParser csvParser = new CSVParser(reader,
	 * CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
	 * )) {
	 * 
	 * for (CSVRecord record : csvParser) { // 假设我们有三个列名：Column1, Column2, Column3
	 * String AdresseImageProduit = record.get("AdresseImageProduit"); String
	 * NomProduit = record.get("NomProduit"); String MarqueProduit =
	 * record.get("MarqueProduit"); // 根据您的CSV文件结构调整列名
	 * 
	 * // 打印每条记录的数据到控制台 System.out.println("AdresseImageProduit: " +
	 * AdresseImageProduit + ", NomProduit: " + NomProduit + ", MarqueProduit: " +
	 * MarqueProduit); } } catch (Exception e) { e.printStackTrace(); } }
	 */
}
