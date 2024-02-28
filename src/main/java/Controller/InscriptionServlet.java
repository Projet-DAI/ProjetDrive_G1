package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
        Client client = new Client();
        client.setNomCompletClient(nomComplet);
        client.setEmailClient(adresseEmail);
        client.setTelephoneClient(telephone);
        client.setNomUtilisateurClient(nomUtilisateur);
        client.setPwdClient(motDePasse);

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
