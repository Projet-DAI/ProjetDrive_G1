package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;
import Model.metier.Client;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("nom "+request.getParameter("nomUtilisateurClient"));
		String identifiant = request.getParameter("nomUtilisateurClient");
        String motDePasse = request.getParameter("pwdClient");

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            Query<Client> query = session.createQuery("FROM Client WHERE nomUtilisateurClient = :identifiant",Client.class);
            query.setParameter("identifiant", identifiant);
            query.setParameter("motDePasse", motDePasse);

            List<Client> clients = query.list();

            if (!clients.isEmpty() && clients.get(0).verifierConnexion(identifiant, motDePasse)) {
                // Utilisateur connecté avec succès
                response.sendRedirect("shop.jsp");
                System.out.println("Id : " + clients.get(0).getNomUtilisateurClient());
            } else {
                // Échec de la connexion
                System.out.println("Connexion echouée");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String nomComplet = request.getParameter("nomComplet");
        String adresseEmail = request.getParameter("adresseEmail");
        String telephone = request.getParameter("telephone");
        String nomUtilisateur = request.getParameter("nomUtilisateur");
        String motDePasse = request.getParameter("motDePasse");

        // Créer une nouvelle instance de Client
        Client client = new Client(nomComplet, nomUtilisateur, nomUtilisateur, telephone, motDePasse, 0, null);

        System.out.println(client);
        // Obtenir la session Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Début de la transaction
        Transaction transaction = session.beginTransaction();

        // Enregistrer le client dans la base de données
        session.save(client);

        // Fin de la transaction
        transaction.commit();

        // Fermer la session
        session.close();

        // Rediriger ou effectuer d'autres actions après l'inscription
        response.sendRedirect("shop.jsp");
	}

}
