package Model.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.DAO.HibernateUtil;
import Model.metier.Categories;
import Model.metier.Client;
import Model.metier.LignePanier;
import Model.metier.Panier;
import Model.metier.Produit;
import Model.metier.Rayon;


import Model.metier.Commande;
import Model.metier.LigneCommande;
import Model.metier.Magasin;


import Model.metier.StatutCommande;
import Model.metier.Stock;
import Model.metier.TempsRetait;
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

         // Client 1
            Client client1 = new Client();
            client1.setNomCompletClient("Dupont Chloe");
            client1.setEmailClient("chloe.dupont@hotmail.com");
            client1.setPwdClient("123");
            client1.setNomUtilisateurClient("chloe");
            client1.setPointFideliteClient(10);
            client1.setTelephoneClient("12345678");
            session.save(client1);

            // Client 2
            Client client2 = new Client();
            client2.setNomCompletClient("BELBEKRI Widad");
            client2.setEmailClient("widad.belbekri@gmail.com");
            client2.setPwdClient("345677893");
            client2.setNomUtilisateurClient("widad");
            client2.setPointFideliteClient(15);
            client2.setTelephoneClient("12345678");
            session.save(client2);

            // Client 3
            Client client3 = new Client();
            client3.setNomCompletClient("Han Pang");
            client3.setEmailClient("ph@gmail.com");
            client3.setPwdClient("123");
            client3.setNomUtilisateurClient("PH");
            client3.setPointFideliteClient(0);
            client3.setTelephoneClient("12345678");
            session.save(client3);

            // Client 4
            Client client4 = new Client();
            client4.setNomCompletClient("Marc Marc");
            client4.setEmailClient("marc@stuff.com");
            client4.setPwdClient("marc");
            client4.setNomUtilisateurClient("Marc");
            client4.setPointFideliteClient(0);
            client4.setTelephoneClient("12345678");
            session.save(client4);

            // Client 5
            Client client5 = new Client();
            client5.setNomCompletClient("Adam Adam");
            client5.setEmailClient("adam@stuff.com");
            client5.setPwdClient("adam");
            client5.setNomUtilisateurClient("Adam");
            client5.setPointFideliteClient(0);
            client5.setTelephoneClient("12345678");
            session.save(client5);

//            // Rayon
//            Rayon rayon = new Rayon("Épicerie Sucrée");
//            session.save(rayon);
//            System.out.println("Rayon ajouté avec succès à la base de données!");

            // Categories
//            List<Categories> categoriesList = readCsvFileCategories("X:\\Telechargement\\categorie.csv", session);
//            for (Categories category : categoriesList) {
//                session.save(category);
//            }
//            System.out.println("Categories ajoutées avec succès à la base de données!");

            // Produits
//            List<Produit> produits = readCsvFileProduit("X:\\Telechargement\\produit.csv", session);
//            for (Produit produit : produits) {
//                session.save(produit);
//            }
//            System.out.println("Produits ajoutés avec succès à la base de données!");

            // Panier
//            Client c2 = session.get(Client.class, 2);
//            Panier panier = new Panier();
//            Date dateCreation = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.of("Europe/Paris")).toInstant());
//            panier.setDateCreation(dateCreation);
//            panier.setClient(c2);
//            session.save(panier);
//            System.out.println("Panier ajouté avec succès à la base de données!");
//
//            // Récupération d'un produit par son identifiant
//            Produit produit = session.get(Produit.class, 1);
//            Produit produit2 = session.get(Produit.class, 2);
//            Produit produit3 = session.get(Produit.class, 3);
//
//            // Ajout d'une ligne au panier
//            addLignePanier(panier, produit, 5, session);
//            addLignePanier(panier, produit2, 3, session);
//            addLignePanier(panier, produit3, 2, session);
            
//            StatutCommande sc1 = new StatutCommande();
//			sc1.setLibelleStatut("Payé");
//			
//			StatutCommande sc2 = new StatutCommande();
//			sc2.setLibelleStatut("En cours de préparation");
//			
//			StatutCommande sc3 = new StatutCommande();
//			sc3.setLibelleStatut("Préparé");
//			
//			StatutCommande sc4 = new StatutCommande();
//			sc4.setLibelleStatut("Livrée");
//			
//			StatutCommande sc5 = new StatutCommande();
//			sc5.setLibelleStatut("Rufusée");
//			
//			session.save(sc1);
//			session.save(sc2);
//			session.save(sc3);
//			session.save(sc4);
//			session.save(sc5);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    private static List<Categories> readCsvFileCategories(String csvFilePath, Session session) {
        List<Categories> categoriesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Skip the header line of the CSV file
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                Categories category = new Categories();
                category.setNomCategorie(fields[1].trim());

                // Link with the rayon
                int rayonId = Integer.parseInt(fields[2]); // Change index to 1
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
                String[] fields = line.split(",");
        
                Produit produit = new Produit();
                produit.setNomProduit(fields[4].trim());
                produit.setPrixProduit(Double.parseDouble(fields[7].trim()));
                produit.setMarqueProduit(fields[3]);
                produit.setPromotion(Boolean.parseBoolean(fields[8]));
                produit.setPourcentagePromotion(Double.parseDouble(fields[6]));
                produit.setAdresseImageProduit(fields[1]);
                produit.setNutriscore(fields[5]);
                produit.setDescription(fields[2]);

                // Link with the category
                int categoryId = Integer.parseInt(fields[9]);
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
