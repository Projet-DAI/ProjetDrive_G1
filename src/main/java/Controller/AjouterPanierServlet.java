package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.DAO.ProduitDAO;
import Model.metier.Client;
import Model.metier.LignePanier;
import Model.metier.Panier;
import Model.DAO.HibernateUtil;
import Model.DAO.PanierDAO;

import Model.metier.Produit;



/**
 * Servlet implementation class AjouterPanierServlet
 */
@WebServlet("/AjouterPanierServlet")
public class AjouterPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public AjouterPanierServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    
    private PanierDAO panierDAO;

    public void init() throws ServletException {
        panierDAO = new PanierDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifiez si le client est connecté en vérifiant s'il existe une session utilisateur
        
        }
    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String productIdString = request.getParameter("productId");
        String quantiteString = request.getParameter("quantite");
        int quantiteDemandee = Integer.parseInt(request.getParameter("quantite"));
        int productIdDemandee = Integer.parseInt(request.getParameter("productId"));

        Produit product = ProduitDAO.getProductById(productIdDemandee);
        System.out.println("Product ID: " + productIdString); // Ajout d'un message de débogage
        System.out.println("Quantité: " + quantiteString); // Ajout d'un message de débogage

        if (productIdString != null && quantiteString != null) {
            int productId = Integer.parseInt(productIdString);
            int quantite = Integer.parseInt(quantiteString);
            int quantiteEnStock = ProduitDAO.getQuantiteEnStock(productId);

            System.out.println("Product ID: " + productId);
            System.out.println("Quantité: " + quantite);
            if (quantiteDemandee <= quantiteEnStock) {
            // Récupérer le panier du client depuis la session
            HttpSession session = request.getSession(false);
            System.out.println("Session récupérée: " + session);

            if (session != null && session.getAttribute("clientId") != null) {
                System.out.println("Attribut 'clientId' dans la session : " + session.getAttribute("clientId"));

                int clientId = (int) session.getAttribute("clientId"); // Récupérez l'ID du client de la session

                System.out.println("ID du client: " + clientId);

                // Récupérer le panier du client depuis la base de données
                Panier panier = panierDAO.getPanierByClientId(clientId);

                System.out.println("Panier récupéré: " + panier);
                
                Session session1 = HibernateUtil.getSessionFactory().openSession(); // Assurez-vous d'importer la classe Session de Hibernate
                String hql = "SELECT p FROM Panier p JOIN FETCH p.lignesPanier WHERE p.client.id = :clientId";
                Query<Panier> query = session1.createQuery(hql, Panier.class);
                query.setParameter("clientId", clientId);
                Panier panier1 = query.uniqueResult();
                session1.close(); // N'oubliez pas de fermer la session après avoir récupéré les données

                System.out.println("hql resultat : " + hql);
                System.out.println("query resultat : " + query);


                if (panier != null) {
                    //Hibernate.initialize(panier.getLignesPanier());

                    // Récupérer le produit à partir de la base de données
                    Produit produit = ProduitDAO.getProductById(productId);
                    System.out.println("Produit récupéré: " + produit);
                /*}else {
                        // Si aucun panier n'est trouvé, gérer l'erreur
                        System.out.println("Aucun panier trouvé pour le client: " + clientId);

                        // Créer un nouveau panier pour le client
                        panier = new Panier();
                        panier.setClient(new Client(clientId));
                        
                        // Enregistrer le nouveau panier dans la base de données
                        panierDAO.createPanier(panier);

                        // Récupérer le produit à partir de la base de données
                        Produit produit = ProduitDAO.getProductById(productId);
                        System.out.println("Produit récupéré: " + produit);
                 	*/
                    if (produit != null) {
                        // Créer une nouvelle instance de LignePanier
                        LignePanier lignePanier = new LignePanier();
                        lignePanier.setQuantite(quantite);
                        lignePanier.setPanier(panier);
                        lignePanier.setProduit(produit); // Vous devez récupérer le produit ici

                        System.out.println("Ligne de panier créée: " + lignePanier); // Ajout d'un message de débogage

                        // Enregistrer la nouvelle lignePanier dans la base de données
                        //panier.getLignesPanier().add(lignePanier); // Ajoutez la ligne au panier
                        panierDAO.addToCart(panier.getIdPanier(), productId, quantite); // Utilisez votre méthode addToCart

                        System.out.println("Produit ajouté au panier."); // Ajout d'un message de débogage

                        // Rediriger vers une page appropriée
                        response.sendRedirect(request.getHeader("referer"));
                    } else {
                        List<Produit> produitsDeRemplacement = ProduitDAO.getProduitsDeRemplacement(product.getCategorie(),productId );

                        request.setAttribute("produitsDeRemplacement", produitsDeRemplacement);

                        response.sendRedirect("produitsDeRemplacement.jsp");
                    }                    
                    } else {
                        // Si le produit n'est pas trouvé, gérer l'erreur
                        System.out.println("Produit non trouvé pour l'ID: " + productId);
                        response.sendRedirect("shop.jsp");
                    }
                }
            }
        }
        
    }
}
               
            
        
    


