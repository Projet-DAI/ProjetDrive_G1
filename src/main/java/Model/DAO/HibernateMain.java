package Model.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Model.metier.Categories;
import Model.metier.Client;
import Model.metier.LignePanier;
import Model.metier.Panier;
import Model.metier.Produit;
import Model.metier.Rayon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class HibernateMain {

    public static void main(String[] args) {
        System.out.println("Hibernate !");

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            // Client
            Client client = new Client("Dupont", "Chloe", "chloe.dupont@hotmail.com", "123", "chloe", 10, null);
            session.save(client);

            // Rayon
            Rayon rayon = new Rayon("Épicerie Sucrée");
            session.save(rayon);
            System.out.println("Rayon ajouté avec succès à la base de données!");

            // Categories
            List<Categories> categoriesList = readCsvFileCategories("C:\\Users\\widad\\Downloads\\categorie.csv", session);
            for (Categories category : categoriesList) {
                session.save(category);
            }
            System.out.println("Categories ajoutées avec succès à la base de données!");

            // Produits
            List<Produit> produits = readCsvFileProduit("C:\\Users\\widad\\Downloads\\produit.csv", session);
            for (Produit produit : produits) {
                session.save(produit);
            }
            System.out.println("Produits ajoutés avec succès à la base de données!");

            // Panier
            Client c2 = session.get(Client.class, 1);
            Panier panier = new Panier();
            Date dateCreation = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.of("Europe/Paris")).toInstant());
            panier.setDateCreation(dateCreation);
            panier.setClient(c2);
            session.save(panier);
            System.out.println("Panier ajouté avec succès à la base de données!");

            // Récupération d'un produit par son identifiant
            Produit produit = session.get(Produit.class, 1);
            Produit produit2 = session.get(Produit.class, 2);
            Produit produit3 = session.get(Produit.class, 3);

            // Ajout d'une ligne au panier
            addLignePanier(panier, produit, 5, session);
            addLignePanier(panier, produit2, 3, session);
            addLignePanier(panier, produit3, 2, session);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Categories> readCsvFileCategories(String csvFilePath, Session session) {
        List<Categories> categoriesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Sauter la ligne d'en-tête d'un fichier CSV
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                Categories category = new Categories();
                category.setNomCategorie(fields[1].trim());

                // Faire lien avec le rayon
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

                Produit produit = new Produit();
                produit.setNomProduit(fields[3]);
                produit.setPrixProduit(Double.parseDouble(fields[5].trim()));
                produit.setMarqueProduit(fields[2]);
                produit.setPromotion(Boolean.parseBoolean(fields[6]));
                produit.setPourcentagePromotion(Double.parseDouble(fields[5]));
                produit.setAdresseImageProduit(fields[1]);
                produit.setNutriscore(fields[4]);

                // Faire lien avec la catégorie
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

    private static void addLignePanier(Panier panier, Produit produit, int quantite, Session session) {
        LignePanier lignePanier = new LignePanier();
        lignePanier.setPanier(panier);
        lignePanier.setProduit(produit);
        lignePanier.setQuantite(quantite);
        session.save(lignePanier);
    }
}
