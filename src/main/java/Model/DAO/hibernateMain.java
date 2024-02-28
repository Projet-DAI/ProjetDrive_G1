package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import Model.metier.Categories;
import Model.metier.Produit;
import Model.metier.Rayon;
import Model.metier.Client;

public class hibernateMain {
    public static void main(String[] args) {
        System.out.println("Hibernate!");

        // Client
        try (Session ses = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction tc = ses.beginTransaction();

            Client c1 = new Client("Dupont", "Chloe", "chloe.dupont@hotmail.com", "chloe", 10, null);

            ses.save(c1);
            tc.commit();
        }

        // Rayoon
        try (Session session0 = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session0.beginTransaction();

            try {
                Rayon rayon = new Rayon("Épicerie Sucrée");
                session0.save(rayon);
                transaction.commit();
                System.out.println("Rayon ajouté avec succès à la base de données!");
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session0.close();
            }
        }
        
        // categories
        try (Session session1 = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session1.beginTransaction();

            try {
            	// csv
            	List<Categories> categoriesList = readCsvFileCategories("C:\\Users\\mazhu\\Downloads\\categorie.csv", session1);
                for (Categories category : categoriesList) {
                    session1.save(category);
                }
                transaction.commit();
                System.out.println("categories ajouté avec succès à la base de données!");
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session1.close();
            }
        }
        
        // produits
        try (Session session2 = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session2.beginTransaction();

            try 
            {
            	// csv
            	List<Produit> produits = readCsvFileProduit("C:\\Users\\mazhu\\Downloads\\produit.csv", session2);
            	for (Produit produit : produits) {
                    session2.save(produit);
                }
                transaction.commit();
                System.out.println("produits ajouté avec succès à la base de données!");
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session2.close();
            }
        }
    }

    private static List<Categories> readCsvFileCategories(String csvFilePath, Session session) {
        List<Categories> categoriesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Sauter la ligne d'en-tête d'un fichier CSV
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                Categories category = new Categories();
                category.setNomCategorie(fields[1].trim());
                // faire line avec rayon
                int rayonId = Integer.parseInt(fields[2]);
                Rayon rayon = session.get(Rayon.class, rayonId);
                category.setRayon(rayon);

                categoriesList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoriesList;
    }

    private static List<Produit> readCsvFileProduit(String csvFilePath, Session session) {
        List<Produit> produits = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Sauter la ligne d'en-tête d'un fichier CSV
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                // Création d'un objet produit et définition des propriétés
                Produit produit = new Produit();
                produit.setNomProduit(fields[3]);
                produit.setPrixProduit(Double.parseDouble(fields[5].trim()));

                produit.setMarqueProduit(fields[2]);
                produit.setPromotion(Boolean.parseBoolean(fields[6]));
                produit.setPourcentagePromotion(Double.parseDouble(fields[5]));
                produit.setAdresseImageProduit(fields[1]);
                produit.setNutriscore(fields[4]);

                // faire lien avec produit
                int categoryId = Integer.parseInt(fields[7]);
                Categories categorie = session.get(Categories.class, categoryId);
                produit.setCategorie(categorie);

                produits.add(produit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produits;
    }
}
