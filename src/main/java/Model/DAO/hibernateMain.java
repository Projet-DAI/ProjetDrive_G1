package Model.DAO;

import org.hibernate.Criteria;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import Model.metier.Client;
import Model.metier.Rayon;


public class hibernateMain {
	public static void main (String[] args)
	{
	System.out.println("Hibernate !");
	
	Session ses = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tc = ses.beginTransaction();
	
	Client c1 = new Client("Dupont","Chloe","chloe.dupont@hotmail.com","chloe",10, null);
	
	ses.save(c1);
	tc.commit();
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction transaction = session.beginTransaction();

	try {
	    Rayon rayon = new Rayon("Épicerie Sucrée");
	    session.save(rayon);
	    transaction.commit();
	    System.out.println("Rayon ajouté avec succès à la base de données!");
	} catch (Exception e) {
	    transaction.rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}

	

	}

}
