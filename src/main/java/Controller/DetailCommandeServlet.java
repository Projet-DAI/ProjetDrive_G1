package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Commande;
import Model.metier.LigneCommande;

/**
 * Servlet implementation class DetailCommandeServlet
 */
@WebServlet("/DetailCommandeServlet")
public class DetailCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailCommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// obtenir idcommande from button
		String idc = request.getParameter("idc");
		int idCommande = Integer.valueOf(idc);
		
		//System.out.println("idcommande" + idCommande);
		
		// Obtenir les informations sur le client pour la session en cours.
		HttpSession s = request.getSession();
		String emailCli = (String)s.getAttribute("emailCli");
		
		// Utilisez idcommande pour vérifier les détails de la commande
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		Query<LigneCommande> query = session.createQuery("SELECT lc From LigneCommande lc WHERE lc.commande.idCommande=:idc ",LigneCommande.class);
		query.setParameter("idc", idCommande);
		
		
		
		// getResult
		List<LigneCommande> ligneCommandes = query.list();
		
//		for (LigneCommande l : ligneCommandes) {
//			System.out.println(l.getPrixUnitaire());
//			System.out.println(l.getIdLigneCommande());
//			System.out.println(l.getProduit().getNomProduit());
//			System.out.println(l.getQuantite());
//		}
		
		// Construire response
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Double prixProduits = 0.0;
		
		// adresse magasin split 
		String adresseM = ligneCommandes.get(0).getCommande().getMagasin().getAdresseMagasin();
		String[] adMEtCodeP = adresseM.split(",");
		
		
		String txtHTML =  
			    "<div class=\"modal-header\">" +
			        "<h5 class=\"modal-title\" id=\"exampleModalLabel\">Commande NO." + idCommande + " : </h5>" +
			        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">" + 
			            "<span aria-hidden=\"true\">&times;</span></button></div>" +
			    "<div class=\"modal-body\">" +
			        "<div class=\"row\">" +
			            "<div class=\"col-md-6\">" +
			                "<p>" +
			                    "<strong>Magasin Detail:</strong><br>"+ ligneCommandes.get(0).getCommande().getMagasin().getNomMagasin() +"<br>" +
			                    adMEtCodeP[0] +"<br>" + adMEtCodeP[1] + "<br>" +
			                "</p>" +
			            "</div>" +
			            "<div class=\"col-md-6\">" +
			                "<p>" +
			                    "<strong>Date de Création</strong><br>" + ligneCommandes.get(0).getCommande().getDateCommande() + "<br>" + 
			                "</p>" +
			                "<p>" +
			                    "<strong>Temps de Retait</strong><br> " + ligneCommandes.get(0).getCommande().getTempsRetaitCom() +
			                "</p>" +
			            "</div>" +
			        "</div>" +
			        "<div class=\"row\">" +
			            "<div class=\"col-md-12\">" +
			                "<p>" +
			                    "<strong>Votre Commande :</strong>" +
			                "</p>" +
			                "<div class=\"table-responsive\">" +
			                    "<table class=\"table\">" +
			                        "<thead>" +
			                            "<tr>" +
			                                "<th>Produits</th>" +
			                                "<th class=\"text-right\">Sous-total</th>" +
			                            "</tr>" +
			                        "</thead>" +
			                        "<tbody>";
		for (LigneCommande l : ligneCommandes) {
			txtHTML += "<tr>" +
                    "<td>" + l.getProduit().getNomProduit() + "  x" + l.getQuantite() + "</td>" +
                    "<td class=\"text-right\">" + l.getPrixUnitaire() + "€</td>" +
                    "</tr>";
			prixProduits += l.getPrixUnitaire();
		}

		Double prixTotal = prixProduits + 10;
		txtHTML +=      
			                       "</tbody>" +
			                        "<tfooter>" +
			                            "<tr>" +
			                                "<td><strong>Cart Subtotal</strong></td>" +
			                                "<td class=\"text-right\">"+ prixProduits +"€</td>" +
			                            "</tr>" +
			                            "<tr>" +
			                                "<td><strong>Shipping</strong></td>" +
			                                "<td class=\"text-right\">0€</td>" +
			                            "</tr>" +
			                            "<tr>" +
			                                "<td><strong>ORDER TOTAL</strong></td>" +
			                                "<td class=\"text-right\"><strong>"+ prixTotal +"€</strong></td>" +
			                            "</tr>" +
			                        "</tfooter>" +
			                    "</table>" +
			                "</div>" +
			            "</div>" +
			        "</div>" +
			    "</div>" +
			    "<div class=\"modal-footer\">" +
			        "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>" +
			    "</div>";
		
		out.print(txtHTML);

				
		
		/*<div class="modal-header">
		<h5 class="modal-title" id="exampleModalLabel">No. Pesanan :
			AL121N8H2XQB47</h5>
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-6">
				<p>
					<strong>Billing Detail:</strong><br> Teguh Rianto<br>
					Jl. Petani No. 159, Cibabat<br> Cimahi Utara<br> Kota
					Cimahi<br> Jawa Barat 40513
				</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>Payment Method:</strong><br> Direct Transfer to<br>
					Bank: BCA<br> No Rek.: 72133236179
				</p>
				<p>
					<strong>Batas Pembayaran</strong><br> 14-12-2017 17:55
					GMT+7
				</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<p>
					<strong>Your Order:</strong>
				</p>
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Products</th>
								<th class="text-right">Subtotal</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Ikan Segar x1</td>
								<td class="text-right">Rp 30.000</td>
							</tr>
							<tr>
								<td>Sirloin x1</td>
								<td class="text-right">Rp 120.000</td>
							</tr>
							<tr>
								<td>Mix Vegetables x1</td>
								<td class="text-right">Rp 30.000</td>
							</tr>
						</tbody>
						<tfooter>
						<tr>
							<td><strong>Cart Subtotal</strong></td>
							<td class="text-right">Rp 180.000</td>
						</tr>
						<tr>
							<td><strong>Shipping</strong></td>
							<td class="text-right">Rp 20.000</td>
						</tr>
						<tr>
							<td><strong>ORDER TOTAL</strong></td>
							<td class="text-right"><strong>Rp 200.000</strong></td>
						</tr>
						</tfooter>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
