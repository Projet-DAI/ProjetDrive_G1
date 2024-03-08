package Controller;

import java.io.IOException;
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

import Model.DAO.ProduitDAO;
import Model.DAO.HibernateUtil;
import Model.DAO.PanierDAO;
import Model.metier.Client;
import Model.metier.LignePanier;
import Model.metier.Panier;
import Model.metier.Produit;

@WebServlet("/AjouterPanierServlet")
public class AjouterPanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PanierDAO panierDAO;

    public void init() throws ServletException {
        panierDAO = new PanierDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les données du formulaire
        String productIdString = request.getParameter("produitId");
        String quantiteString = request.getParameter("quantite");

        System.out.println("Product ID: " + productIdString); // Ajout d'un message de débogage
        System.out.println("Quantité: " + quantiteString); // Ajout d'un message de débogage

        if (productIdString != null && quantiteString != null) {
            int productId = Integer.parseInt(productIdString);
            int quantite = Integer.parseInt(quantiteString);

            System.out.println("Product ID: " + productId);
            System.out.println("Quantité: " + quantite);

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

                if (panier != null) {
                	try {

                    Produit produit = ProduitDAO.getProductById(productId);
                    System.out.println("Produit récupéré: " + produit);

                    if (produit != null) {
                    	System.out.println("Panier trouvé: " + panier);

                        int quantiteEnStock = ProduitDAO.getQuantiteEnStock(productId);
                    	System.out.println("Quantité trouvé du : " + quantiteEnStock);

                        if (quantite <= quantiteEnStock) {
                            LignePanier lignePanier = new LignePanier();
                            lignePanier.setQuantite(quantite);
                            lignePanier.setPanier(panier);
                            lignePanier.setProduit(produit);
                            System.out.println("Ligne de panier créée: " + lignePanier); // Ajout d'un message de débogage


                            panierDAO.addToCart(panier.getIdPanier(), productId, quantite);
                            System.out.println("Produit ajouté au panier."); // Ajout d'un message de débogage


                            // Mettre à jour le panier dans la session pour que head.jsp puisse afficher le panier
                            Session sessionHib = HibernateUtil.getSessionFactory().getCurrentSession();
                            Transaction tr = sessionHib.beginTransaction();
                            Panier panierUpdate = (Panier) sessionHib.get(Panier.class, panier.getIdPanier());
                            session.setAttribute("panier", panierUpdate);
                            tr.commit();
                            sessionHib.close();

                            response.sendRedirect(request.getHeader("referer"));
                        } else {
                            // Si la quantité demandée est supérieure à la quantité en stock, redirigez vers une autre page
                            List<Produit> produitsDeRemplacement = ProduitDAO.getProduitsDeRemplacement(
                                    produit.getCategorie(), productId);
                            request.setAttribute("produitsDeRemplacement", produitsDeRemplacement);
                            request.getRequestDispatcher("produitsDeRemplacement.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("Produit non trouvé pour l'ID: " + productId);
                        response.sendRedirect("shop.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur en cas d'exception
                }
                }else {
                    // Si aucun panier n'est trouvé pour le client, créez un nouveau panier et ajoutez le produit
                    System.out.println("Aucun panier trouvé pour le client: " + clientId);
                    panier = new Panier();
                    panier.setClient(new Client(clientId));
                    panierDAO.createPanier(panier);
                    response.sendRedirect(request.getHeader("referer"));
                }
            } else {
                // Si l'utilisateur n'est pas connecté, redirigez-le vers la page de connexion
                System.out.println("Le client n'est pas connecté.");
                response.sendRedirect("login.jsp");
            }
            } 
            }
        }
    
        
    

