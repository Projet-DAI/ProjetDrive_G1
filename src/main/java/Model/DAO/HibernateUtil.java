package Model.DAO;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.source.internal.hbm.ModelBinder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            /**
             * Ajout des classes entités.
             */
            configuration.addAnnotatedClass(Model.metier.Client.class);
            configuration.addAnnotatedClass(Model.metier.Commande.class);
            configuration.addAnnotatedClass(Model.metier.LigneCommande.class);
            configuration.addAnnotatedClass(Model.metier.Magasin.class);
            configuration.addAnnotatedClass(Model.metier.Produit.class);
            configuration.addAnnotatedClass(Model.metier.StatutCommande.class);
            configuration.addAnnotatedClass(Model.metier.Stock.class);
            configuration.addAnnotatedClass(Model.metier.Categories.class);
            configuration.addAnnotatedClass(Model.metier.Rayon.class);
            configuration.addAnnotatedClass(Model.metier.Approvisionnement.class);
            configuration.addAnnotatedClass(Model.metier.ListeCourse.class);
            configuration.addAnnotatedClass(Model.metier.LigneListeCourse.class);
            configuration.addAnnotatedClass(Model.metier.PostIt.class);
            configuration.addAnnotatedClass(Model.metier.Panier.class);
            configuration.addAnnotatedClass(Model.metier.LignePanier.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            /*----- Exception -----*/
            System.err.println("Initial SessionFactory creation failed.\n" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
