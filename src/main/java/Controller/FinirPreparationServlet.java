package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Commande;
import Model.metier.LigneCommande;
import Model.metier.StatutCommande;
import Model.metier.Stock;
import emailGenerator.SendMyEmail;
import pdfGenerator.GiveMEPDF;
import pdfGenerator.Product;

/**
 * Servlet implementation class FinirPreparationServlet
 */
@WebServlet("/FinirPreparationServlet")
public class FinirPreparationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinirPreparationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("FinirPreparationServlet");
		
		//1. obtenir listeStock et listeLigneCom a partir de session
		HttpSession s = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer> listeStock = (ArrayList<Integer>)s.getAttribute("listeStock");
		
		@SuppressWarnings("unchecked")
		List<LigneCommande> listeLigneCom = (List<LigneCommande>)s.getAttribute("listeLigneCom");
		
		// 2. changer le statut de ce commande : en cours -> preparé
		int idc = listeLigneCom.get(0).getCommande().getIdCommande();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tr = session.beginTransaction();
		
		// obtenir objet commande idc
		Commande c = session.get(Commande.class, idc);
		
		// obtenir objet statutCommande id = 3
		StatutCommande newStatut = session.get(StatutCommande.class, 3);
		
		c.setStatutCommande(newStatut);
		
		// faire un Update
		session.update(c);
		
		// list product pour PDF
		List<Product> listeProduct = new ArrayList<Product>();
		
		// 3. pour chaque produit dans la liste , on modif le numbre de stock dans BD
		for (LigneCommande lc : listeLigneCom) {
			// pour chaque produit, chercher son stockage par idProduit et idMagasin
			Query<Stock> queryModifStock = session.createQuery("From Stock s where s.produit.idProduit = :idP AND s.magasin.idMagasin = :idM", Stock.class);
			queryModifStock.setParameter("idP", lc.getProduit().getIdProduit());
			queryModifStock.setParameter("idM", lc.getCommande().getMagasin().getIdMagasin());
			
			// modif stock
			Stock stock = queryModifStock.getSingleResult();
			int stockAct = stock.getQuantiteEnStock() - lc.getQuantite();
			stock.setQuantiteEnStock(stockAct);
			
			// update
			session.update(stock);
			
			// construire le objet product pour creer PDF
			Product p = new Product(lc.getProduit().getNomProduit(), lc.getQuantite(), "Preparé");
			listeProduct.add(p);
		}
		
		// 4. creer un PDF pour les produits qui sont preparés
		int idC = listeLigneCom.get(0).getCommande().getIdCommande();
		Date dateC = listeLigneCom.get(0).getCommande().getDateCommande();
		
		// Changer date -> String
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateCString = dateFormat.format(dateC);
        
		String nomU = listeLigneCom.get(0).getCommande().getClient().getNomUtilisateurClient();
		String nomM = listeLigneCom.get(0).getCommande().getMagasin().getNomMagasin();
		String tempsR = listeLigneCom.get(0).getCommande().getTempsRetaitCom();
		String adresseM = listeLigneCom.get(0).getCommande().getMagasin().getAdresseMagasin();
		String filePath = "C:\\Users\\mazhu\\Downloads\\commande-No" + idC + ".pdf";

		//String orderNumber, String orderDate, String userName, String storeName, String storeAdress,
        // String pickupTime, List<Product> productList, String filePath
		GiveMEPDF.generatePDF(idC, dateCString, nomU, nomM, adresseM, tempsR, listeProduct, filePath);
		
		
		// 5. Envoyer un email a client
		// String receiveMail, String pdfPath, String nomU, String idC
		String emailCli = listeLigneCom.get(0).getCommande().getClient().getEmailClient();
		String idCommande = String.valueOf(idC);
		try {
			SendMyEmail.createMimeMessage(emailCli, filePath, nomU, idCommande);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 6. redirect
		tr.commit();
		session.close();
		response.sendRedirect("preparationPreloadServlet");
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
