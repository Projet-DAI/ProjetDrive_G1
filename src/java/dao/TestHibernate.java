package dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import metier.Demande;
import metier.Employe;
import metier.EmployeDemandeReq;

/**
 * Classe de test pour Hibernate.
 */
public class TestHibernate
{
	/**
	 * Programme de test.
	 */
	public static void main (String[] args)
		{
		System.out.println("Hibernate !");
        // Enregistrement d'employés
		Session ses = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc = ses.beginTransaction();
		
		Employe e = new Employe("Dupond", "Pierre"); 
		Employe e1 = new Employe("Smith", "John"); 
		Employe e2 = new Employe("Belbekri", "Widad"); 


		
		ses.save(e1);
		ses.save(e2);
		ses.save(e);
		tc.commit();
        // Mise à jour d'un employé
	    Session ses1 = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tc1 = ses1.beginTransaction();
		e.setNomE("Dupont"); 
        ses1.update(e);
        tc1.commit();
		
        // Chargement d'un employé et affichage de ses propriétés

		Session ses2 = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc2 = ses2.beginTransaction();
		Employe e4 = (Employe)ses2.get(Employe.class, 1);

		System.out.println("nom :"+ e4.getNomE());
		System.out.println("prenom :"+ e4.getPrenomE());
		tc2.commit();

        // Enregistrement de demandes
		
		Session ses3 = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc3 = ses3.beginTransaction();
		Demande d = new Demande("08/02/2024","15","01/03/2024",e2);
		Demande d1 = new Demande("09/02/2024","7","03/03/2024",e1);
		Demande d4= new Demande ("10/02/2024","7","01/05/2024",e);
		Demande d3 = new Demande("10/02/2024","10","08/03/2024",e);
		Demande d6 = new Demande("09/02/2024","12","25/04/2024",e1);
		Demande d7 = new Demande("09/02/2024","10","11/07/2024",e1);
		
		ses3.save(d);
		ses3.save(d1);
		ses3.save(d4);
		ses3.save(d3);
		ses3.save(d7);
		ses3.save(d6);
		tc3.commit();

		
        // Affichage des propriétés d'une demande avec le nom de l'employé
		
		Session ses4 = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc4 = ses4.beginTransaction();
		Demande d2= ses4.get(Demande.class,1);
		Employe e5= d2.getEmploye();
		
		System.out.println("5----Demande:----");
	    System.out.println("DateD: " + d2.getDateD());
	    System.out.println("NbJours: " + d2.getNbJours());
	    System.out.println("DateDebut: " + d2.getDateDebut());
	    System.out.println("Nom Employe: " + e5.getNomE());
	    System.out.println("Prenom Employe: " + e5.getPrenomE());
	    tc4.commit();
		
        // Chargement des demandes d'un employé
	
		Session ses6 = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc6 = ses6.beginTransaction();
		Employe e3 = ses6.get(Employe.class, 3);
		Hibernate.initialize(e3.getDemandes());

		tc6.commit();
		

        // Enregistrement d'une demande pour un employé
		Session ses7 = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tc7 = ses7.beginTransaction();
		Demande d5 = new Demande("10/02/2024","10","08/03/2024",e3);
		Set<Demande>demandes1= e3.getDemandes();
		Hibernate.initialize(demandes1);
		ses7.save(d5);
		demandes1.add(d5);
		
		ses7.update(e3);
		System.out.println("9----L'employé " + e3.getNomE() + " " + e3.getPrenomE() + " a "+ demandes1.size() + " demandes-----");
		
		tc7.commit();
		
        HQL();}
		
//		Session ses7 = HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction tc7 = ses7.beginTransaction();
//		
//		Query q = ses7.createQuery("SELECT e FROM Employe e Demande d WHERE d.employe = e AND e.demandes.size > 2");
//		List<Employe> employes = (List<Employe>) q.list();
//
//		for (Employe employe : employes) {
//		    int nombreDemandes = employe.getDemandes().size();
//
//		    System.out.println("Employé: " + employe.getNomE() + ", Nombre de demandes: " + nombreDemandes);
//		}
//		
//		tc7.commit();
//		ses7.close();
		
//		
//		//Requête HQL a.Pour les employés ayant fait plus de 2 demandes, retournez le nom et le nombre de demandes.
//			
//		Session ses8=HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction tc8 = ses8.beginTransaction();
//
//		List<EmployeDemandeReq> q = ses8.createQuery(
//				"select EmployeDemandeReq(e.nomE, count(d)) " +
//				"from Employe e join e.demandes d " +
//		        "group by e.nomE " +
//		        "having count(d) > 2", EmployeDemandeReq.class)
//		        .list();
//
//		for (EmployeDemandeReq result : q) {
//		    System.out.println("a. Employé : " + result.getNomE() + ", Nombre de demandes : " + result.getDemandeCount());
//		}
//
//		tc8.commit();
//		ses8.close();

		
		
//		//Requête HQL b. Modifiez la requête précédente afin que la réponse soit retournée sous la forme d'une classe d'objet.
//
//		Session ses9 = HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction tc9 = ses9.beginTransaction();
//		List<Object[]> q1=ses9.createQuery("Select e.NomE,count(*) from Demande d,Employe e where d.CodeEmp=e.CodeE group by e.CodeE,e.NomE having count(*) >=2").list();
//		
//		for (Object[] result : q1) {
//		    String nomEmploye = (String) result[0];
//		    Long nombreDemandes = (Long) result[1];
//		    System.out.println("a. Employé : " + nomEmploye + ", Nombre de demandes : " + nombreDemandes);
//		}
//
//		
//		tc9.commit();
//		ses9.close();}

		public static void HQL() {
			try (Session session =HibernateUtil.getSessionFactory().getCurrentSession())
				{ session.beginTransaction();
				//Requête HQL a.Pour les employés ayant fait plus de 2 demandes, retournez le nom et le nombre de demandes.
				
				List<Object[]> listeA=session.createQuery("Select e.NomE,count(*) "+ 
												 "from Demande d, Employe e "+ 
												 "where d.employe.CodeE = e.CodeE "+ 
												 "group by e.CodeE, e.NomE "+
												 "having count(*) >=2").list();
				System.out.println("-----Requete 11a-----" );
				for (Object[] row : listeA) {
				    String nomEmploye = (String) row[0];
				    Long nombreDemandes = (Long) row[1];
				    System.out.println("Employé : " + nomEmploye + ", Nombre de demandes : " + nombreDemandes);
				}
				//Requête HQL b. Modifiez la requête précédente afin que la réponse soit retournée sous la forme d'une classe d'objet.
				
				List<EmployeDemandeReq>listeB= session.createQuery("select new miage.dao.EmployeDemandeReq(e.NomE, count(d)) " +
					    											"from metier.Demande d, metier.Employe e " +
					    											"where d.employe = e " +
					    											"group by e.CodeE, e.NomE " +
					    											"having count(d) >= 2", EmployeDemandeReq.class)
					    											.list();
				System.out.println("-----Requete 11b-----"+ listeB);
					
	            session.getTransaction().commit();

				}
		}
			
			
		
		
		
		//Requête HQL c. Modifiez la requête précédente afin que le 2 soit passé en paramètre.

		
	
		//Requête HQL d. Affichez la liste des employés.

		
		
		//Requête HQL e. Affichez les demandes de plus de 6 jours.

		
		
		//Requête HQL f. Affichez le nom et prénom des employés ayant un "er" dans leur nom.

				
				
		}		

		

 /*----- Fin de la classe TestHibernate -----*/