package Model.DAO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.metier.Produit;
import Model.metier.Rayon;
import Model.metier.Fournisseur;
import Model.metier.Categories;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class ImporterProduitDAO {
	
	public void importCSV(InputStream fileContent) {
        // creer Hibernate Session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader,  CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            
            transaction = session.beginTransaction();
            
            for (CSVRecord record : csvParser) {
                if (!record.isMapped("EAN")) continue;
                int ean = Integer.parseInt(record.get("EAN"));
                
                Produit produit = findAndCreateProduit(ean, record, session);
            	
                if (record.isMapped("NomFournisseur")) {
                    String nomFournisseur = record.get("NomFournisseur");
                    Fournisseur fournisseur = findAndCreateFournisseur(nomFournisseur, session);
                    if (produit.getFournisseurs() == null) {
                        produit.setFournisseurs(new HashSet<>());
                    }
                    produit.getFournisseurs().add(fournisseur);
                }
                
                if (record.isMapped("NomCategorie")) {
                    String nomCategorie = record.get("NomCategorie");
                    String nomRayon = record.get("NomRayon");
                    Rayon rayon = findAndCreateRayon(nomRayon, session);
                    Categories categorie = findAndCreateCategorie(nomCategorie, rayon, session);
                    produit.setCategorie(categorie);
                }

                session.saveOrUpdate(produit);
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 
    }
	
	private Produit findAndCreateProduit(int ean, CSVRecord record, Session session) {
	    Produit produit = (Produit) session.createQuery("FROM Produit WHERE ean = :ean")
	                        .setParameter("ean", ean)
	                        .uniqueResult();
	    
	    if (produit == null) {
	        produit = new Produit();
	        produit.setEan(ean);
	        produit.setNomProduit(record.get("NomProduit"));
	        produit.setAdresseImageProduit(record.get("AdresseImageProduit"));
	        produit.setMarqueProduit(record.get("MarqueProduit"));
	        produit.setPrixProduit(Double.parseDouble(record.get("PrixProduit").replace(',', '.')));
	        produit.setPromotion(Boolean.parseBoolean(record.get("Promotion")));
	        produit.setPourcentagePromotion(Double.parseDouble(record.get("PourcentagePromotion").replace(',', '.')));
	        produit.setNutriscore(record.get("Nutriscore"));
	        produit.setDescription(record.get("Description"));
	        produit.setLabel(record.get("Label"));
	        produit.setVente(Integer.parseInt(record.get("Vente")));
	        // on peut pas asave Produit，apres faire Categorie relation avec produit on peut save
	    }
	    
	    return produit;
	}


	
	private Categories findAndCreateCategorie(String nomCategorie, Rayon rayon, Session session) {
	    Categories categorie = (Categories) session.createQuery("FROM Categories WHERE nomCategorie = :nomCategorie")
	                            .setParameter("nomCategorie", nomCategorie)
	                            .uniqueResult();
	    if (categorie == null) {
	        categorie = new Categories();
	        categorie.setNomCategorie(nomCategorie);
	        categorie.setRayon(rayon); 
	        session.save(categorie);
	    }
	    return categorie;
	}

	
	private Fournisseur findAndCreateFournisseur(String nomFournisseur, Session session) {
	    Fournisseur fournisseur = (Fournisseur) session.createQuery("FROM Fournisseur WHERE nomFournisseur = :nomFournisseur")
	                                .setParameter("nomFournisseur", nomFournisseur)
	                                .uniqueResult();
	    if (fournisseur == null) {
	        fournisseur = new Fournisseur();
	        fournisseur.setNomFournisseur(nomFournisseur);
	        session.save(fournisseur);
	    }
	    return fournisseur;
	}

	
	private Rayon findAndCreateRayon(String nomRayon, Session session) {
	    Rayon rayon = (Rayon) session.createQuery("FROM Rayon WHERE nomRayon = :nomRayon")
	                            .setParameter("nomRayon", nomRayon)
	                            .uniqueResult();
	    if (rayon == null) {
	        rayon = new Rayon();
	        rayon.setNomRayon(nomRayon);
	        session.save(rayon); 
	    }
	    return rayon;
	}

    
}
