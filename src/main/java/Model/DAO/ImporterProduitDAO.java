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

public class ImporterProduitDAO {
	
	public void importCSV(InputStream fileContent) {
        // creer Hibernate Session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            
            transaction = session.beginTransaction();
            
            for (CSVRecord record : csvParser) {
                // Sur noms de colonnes de CSV, déterminez la table à laquelle les données appartiennent 
            	// et ajouter donnees dans BD
                
                if (record.isMapped("NomProduit") && record.isMapped("AdresseImageProduit")) {
                	// Obtenir la valeur EAN dans CSV
                    int ean = Integer.parseInt(record.get("EAN")); 

                    // Utiliser l'EAN pour interroger un produit existant
                    Produit existingProduit = (Produit) session.createQuery("FROM Produit WHERE ean = :ean")
                                            .setParameter("ean", ean) 
                                            .uniqueResult();
                    
                	if (existingProduit == null) {
                		 Produit produit = new Produit();
                         produit.setNomProduit(record.get("NomProduit"));
                         produit.setAdresseImageProduit(record.get("AdresseImageProduit"));
                         produit.setMarqueProduit(record.get("MarqueProduit"));
                         produit.setPrixProduit(Double.parseDouble(record.get("PrixProduit")));
                         produit.setPromotion(Boolean.parseBoolean(record.get("Promotion")));
                         produit.setPourcentagePromotion(Double.parseDouble(record.get("PourcentagePromotion")));
                         produit.setNutriscore(record.get("Nutriscore"));
                         produit.setDescription(record.get("Description"));
                         produit.setLabel(record.get("Label"));
                         produit.setVente(Integer.parseInt(record.get("Vente")));
                         session.save(produit);
                	}
                }
                
                if (record.isMapped("NomFournisseur")) {
                	
                	String nomFournisseur = record.get("NomFournisseur");
	            	Fournisseur existingFournisseur = (Fournisseur) session.createQuery("FROM Fournisseur WHERE nomFournisseur = :nomFournisseur")
	                         .setParameter("nomFournisseur", nomFournisseur)
	                         .uniqueResult();
	            	
	            	if (existingFournisseur == null) {
	                   
	                    Fournisseur fournisseur = new Fournisseur();
	                    fournisseur.setNomFournisseur(nomFournisseur);
	                    
	                    session.save(fournisseur); 
	                }
	            	//produit.getFournisseurs().add(fournisseur);
                }
                
                if (record.isMapped("NomCategorie")) {
                	
                	String nomCategorie = record.get("NomCategorie");
                	Categories existingCategorie = (Categories) session.createQuery("FROM Categories WHERE nomCategorie = :nomCategorie")
                            .setParameter("nomCategorie", nomCategorie)
                            .uniqueResult();
                	
                	if (existingCategorie == null) {
                		
                        String nomRayon = record.get("NomRayon");
                        Rayon rayon = findOrCreateRayon(nomRayon, session); 

                        Categories categorie = new Categories();
                        categorie.setNomCategorie(nomCategorie);
                        categorie.setRayon(rayon); 
                        
                        session.save(categorie);
                    }
                	//produit.getCategories().add(categorie);
                }
                
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
	
	private Categories findOrCreateCategorie(String nomCategorie, Rayon rayon, Session session) {
	    Categories categorie = (Categories) session.createQuery("FROM Categories WHERE nomCategorie = :nomCategorie")
	                            .setParameter("nomCategorie", nomCategorie)
	                            .uniqueResult();
	    if (categorie == null) {
	        categorie = new Categories();
	        categorie.setNomCategorie(nomCategorie);
	        categorie.setRayon(rayon); // Assume Rayon is already defined or created
	        session.save(categorie);
	    }
	    return categorie;
	}

	
	private Fournisseur findOrCreateFournisseur(String nomFournisseur, Session session) {
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

	
	private Rayon findOrCreateRayon(String nomRayon, Session session) {
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
