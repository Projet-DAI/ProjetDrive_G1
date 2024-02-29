package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.DAO.HibernateUtil;
import Model.metier.Client;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// donnees de page register.jsp
		String nomComplet = (String) request.getParameter("nomComplet");
		String adresseEmail = (String) request.getParameter("adresseEmail");
		String phone = (String) request.getParameter("telephone");
		String nomU = (String) request.getParameter("nomUtilisateur");
		String motDePasse = (String) request.getParameter("motDePasse");
		
		System.out.println("Nom Complet: " + nomComplet);
        System.out.println("Adresse Email: " + adresseEmail);
        System.out.println("Phone: " + phone);
        System.out.println("Nom Utilisateur: " + nomU);
        System.out.println("Mot de Passe: " + motDePasse);
	
		
		
		// separer nomComplet par nom et prenom
//		String[] nomList = nomComplet.split("");
//		String prenom = nomList[0];
//		String nom = nomList[-1];
				// open session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		// creer un client
		Client client = new Client();
		// mise a jour les donnes
		client.setEmailClient(adresseEmail);
		client.setNomCompletClient(nomComplet);
		client.setNomUtilisateurClient(nomU);
		client.setPwdClient(motDePasse);
		client.setTelephoneClient(phone);
		client.setPointFideliteClient(0);
		
		
		// save client
		session.save(client);
		// commit
		transaction.commit();
		
		session.close();
		
		// mettre en session le nomU et email pour profil
		HttpSession s = request.getSession();
		s.setAttribute("emailClient", adresseEmail);
		s.setAttribute("username", nomU);
		
		// redirect
		request.getRequestDispatcher("/InscriptionReussi.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
