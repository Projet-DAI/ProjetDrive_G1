package Model.DAO;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.transaction.Synchronization;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import Model.metier.Categories;
import Model.metier.Client;
import Model.metier.Commande;
import Model.metier.LigneCommande;
import Model.metier.Magasin;
import Model.metier.Produit;
import Model.metier.Rayon;
import Model.metier.StatutCommande;
import Model.metier.Stock;
import Model.metier.TempsRetait;

public class dataTEST {
	
	public static void insertDataClient() {
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			
	        Transaction transaction = session.beginTransaction();
	      // ---------Client-------------  
	        // Client 1 data
	        Client c1 = new Client();
	        c1.setEmailClient("ph@gmail.com");
	        c1.setNomCompletClient("Han Pang");
	        c1.setNomUtilisateurClient("PH");
	        c1.setPointFideliteClient(0);
	        c1.setPwdClient("123");
	        c1.setTelephoneClient("12345678");
	        
	        session.save(c1);
	        
	        // Client 2 data
	        Client c2 = new Client();
	        c2.setEmailClient("marc@stuff.com");
	        c2.setNomCompletClient("Marc Marc");
	        c2.setNomUtilisateurClient("Marc");
	        c2.setPointFideliteClient(0);
	        c2.setPwdClient("marc");
	        c2.setTelephoneClient("12345678");
	        
	        session.save(c2);
	        
	        // client 3 data
	        Client c3 = new Client();
	        c3.setEmailClient("adam@stuff.com");
	        c3.setNomCompletClient("Adam Adam");
	        c3.setNomUtilisateurClient("Adam");
	        c3.setPointFideliteClient(0);
	        c3.setPwdClient("adam");
	        c3.setTelephoneClient("12345678");
	        
	        session.save(c3);

	        transaction.commit();
	        session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertDataStatutCommande() {
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction transaction = session.beginTransaction();
			
			// Data statutCommande
			StatutCommande sc1 = new StatutCommande();
			sc1.setLibelleStatut("Payé");
			
			StatutCommande sc2 = new StatutCommande();
			sc2.setLibelleStatut("En cours de préparation");
			
			StatutCommande sc3 = new StatutCommande();
			sc3.setLibelleStatut("Préparé");
			
			StatutCommande sc4 = new StatutCommande();
			sc4.setLibelleStatut("Livrée");
			
			StatutCommande sc5 = new StatutCommande();
			sc5.setLibelleStatut("Rufusée");
			
			session.save(sc1);
			session.save(sc2);
			session.save(sc3);
			session.save(sc4);
			session.save(sc5);
			
			transaction.commit();
		    session.close();
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insertDataCommande() {
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction transaction = session.beginTransaction();
			
			// create magasin
			Magasin m = new Magasin();
			m.setAdresseMagasin("8 Esp Compans Caffarelli Immeuble Atria, 31000 Toulouse");
			m.setNomMagasin("Magasin-HelloWorld");
			
			session.save(m);
			
			// Data Commande 1
			Commande c1 = new Commande();
			
			// get current date
			Date d = new Date();
			Timestamp timestamp = new Timestamp(d.getTime());	
			
			// get client
			Query<Client> query = session.createQuery("From Client c where c.idClient = 3", Client.class);
			Client c = query.getSingleResult();
			
			// get idStatutCommande
			Query<StatutCommande> query2 = session.createQuery("From StatutCommande sc where sc.idStatutCommande = 1", StatutCommande.class);
			StatutCommande sc = query2.getSingleResult();
			
			c1.setDateCommande(timestamp);
			c1.setMontantTotal(33.0);
			c1.setTempsRetaitCom("2024-03-04 14:00:00.000");
			c1.setClient(c);
			c1.setMagasin(m);
			c1.setStatutCommande(sc);
			
			// Data Commande 2
			Commande c2 = new Commande();
			
			c2.setDateCommande(timestamp);
			c2.setMontantTotal(55.0);
			c2.setTempsRetaitCom("2024-03-04 14:00:00.000");
			c2.setClient(c);
			c2.setMagasin(m);
			c2.setStatutCommande(sc);
			
			session.save(c1);
			session.save(c2);
			
			transaction.commit();
		    session.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void insertLigneCommande() {
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction transaction = session.beginTransaction();
			
			// get commande 1 
			Query<Commande> query1 = session.createQuery("From Commande c where c.idCommande = 1", Commande.class);
			Commande c1 = query1.getSingleResult();
			
			// get commande 1 
			Query<Commande> query2 = session.createQuery("From Commande c where c.idCommande = 2", Commande.class);
			Commande c2 = query2.getSingleResult();
			
			// get commande 1 
			Query<Produit> query3 = session.createQuery("From Produit p where p.idProduit between 1 And 5", Produit.class);
			List<Produit> listP = query3.list();
			
			Produit pfish = listP.get(0);
			Produit pfruit = listP.get(1);
			Produit pboeuf = listP.get(2);
			Produit plegume = listP.get(3);
			Produit pverme = listP.get(4);
			
			
			LigneCommande l1 = new LigneCommande();
			l1.setPrixUnitaire(15.0);
			l1.setQuantite(5);
			l1.setCommande(c1);
			l1.setProduit(pfish);
			
			LigneCommande l2 = new LigneCommande();
			l2.setPrixUnitaire(8.0);
			l2.setQuantite(5);
			l2.setCommande(c1);
			l2.setProduit(pfruit);
			
			LigneCommande l3 = new LigneCommande();
			l3.setPrixUnitaire(10.0);
			l3.setQuantite(5);
			l3.setCommande(c1);
			l3.setProduit(pboeuf);
			
			LigneCommande l4 = new LigneCommande();
			l4.setPrixUnitaire(15.0);
			l4.setQuantite(5);
			l4.setCommande(c2);
			l4.setProduit(pfish);
			
			LigneCommande l5 = new LigneCommande();
			l5.setPrixUnitaire(24.0);
			l5.setQuantite(10);
			l5.setCommande(c2);
			l5.setProduit(plegume);
			
			
			LigneCommande l6 = new LigneCommande();
			l6.setPrixUnitaire(16.0);
			l6.setQuantite(5);
			l6.setCommande(c2);
			l6.setProduit(pverme);
			
			session.save(l1);
			session.save(l2);
			session.save(l3);
			session.save(l4);
			session.save(l5);
			session.save(l6);
			
			transaction.commit();
		    session.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public static void insertCSV() {
	    try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
	        Transaction transaction = session.beginTransaction();
	        
	        // Lecture du fichier CSV TempsRetrait et insertion des données dans la base de données

	        List<TempsRetait> tempsRetraitList = readCsvFileTempRetrait("C:\\Users\\mazhu\\Downloads\\TempsRetrairsRetrait.csv", session);
	        for (TempsRetait tempsRetrait : tempsRetraitList) {
	            session.save(tempsRetrait);
	        }
	        
	        // Lecture du fichier CSV Magasin et insertion des données dans la base de données
	        List<Magasin> magasinList = readCsvFileMagasin("C:\\Users\\mazhu\\Downloads\\Magasin.csv", session);
	        for (Magasin magasin : magasinList) {
	            session.save(magasin);
	        }
	        
	        List<Rayon> rayonList = readCsvFileRayon("C:\\Users\\mazhu\\Downloads\\rayon.csv", session);
	        for (Rayon rayon : rayonList) {
	            session.save(rayon);
	        }
	        List<Categories> cateList = readCsvFileCate("C:\\Users\\mazhu\\Downloads\\categorie.csv", session);
	        for (Categories cate : cateList) {
	            session.save(cate);
	        }
	        
	        List<Produit> produitList = readCsvFileProduit("C:\\Users\\mazhu\\Downloads\\gestion_produit.csv", session);
	        for (Produit produit : produitList) {
	            session.save(produit);


//	        List<TempsRetait> tempsRetraitList = readCsvFileTempRetrait("X:\\Telechargement\\TempsRetrairsRetrait.csv", session);

	        //List<TempsRetait> tempsRetraitList = readCsvFileTempRetrait("C:\\Users\\Imane\\Downloads\\TempsRetrairsRetrait.csv", session);
//
//	        for (TempsRetait tempsRetrait : tempsRetraitList) {
//	            session.save(tempsRetrait);
//	        }
//	        
//	        // Lecture du fichier CSV Magasin et insertion des données dans la base de données
//
////	        List<Magasin> magasinList = readCsvFileMagasin("X:\\Telechargement\\Magasin.csv", session);
//
//	        //List<Magasin> magasinList = readCsvFileMagasin("C:\\Users\\Imane\\Downloads\\Magasin.csv", session);
//
//	        for (Magasin magasin : magasinList) {
//	            session.save(magasin);
//	        }
//	        
//
//	    
//	       
//
//	        List<Rayon> rayonList = readCsvFileRayon("C:\\Users\\LUO\\Downloads\\rayon.csv", session);
//	        for (Rayon rayon : rayonList) {
//	            session.save(rayon);
//	        }
//	        List<Categories> cateList = readCsvFileCate("C:\\Users\\LUO\\Downloads\\categorie.csv", session);
//
//	        for (Categories cate : cateList) {
//	            session.save(cate);
//	        }
//	        
//
//	       
//
//	        List<Produit> produitList = readCsvFileProduit("C:\\Users\\LUO\\Downloads\\gestion_produit.csv", session);
//
//	        for (Produit produit : produitList) {
//	            session.save(produit);
//	        }
	        
	        List<Stock> stockList = readCsvFilestock("C:\\Users\\LUO\\Downloads\\stock.csv", session);
	        for (Stock stock : stockList) {
	            session.save(stock);

	        }
	       
	        
	       
	       
	        
	        // Validation des transactions et confirmation de l'ajout des données
	        transaction.commit();
	        System.out.println("Données ajoutées avec succès à la base de données!");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	 private static List<Stock> readCsvFilestock(String csvFilePath, Session session)  {
		 List<Stock> stockList = new ArrayList<>();
		 try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
	            // Skip the header line of the CSV file
	            reader.readLine();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] fields = line.split(",");

	                Stock stock = new Stock();
	                stock.setQuantiteEnStock(Integer.parseInt(fields[2]));
	                
	                int IdMagasin = Integer.parseInt(fields[3]); 
	                Magasin magasin = session.get(Magasin.class, IdMagasin);
	                stock.setMagasin(magasin);
	                
	                int IdProduit = Integer.parseInt(fields[4]); 
	                Produit produit = session.get(Produit.class, IdProduit);
	                stock.setProduit(produit);


	                stockList.add(stock);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return stockList;
		 
		 
		 
		
	}


	
	 private static List<Rayon> readCsvFileRayon(String csvFilePath, Session session)  {
		 List<Rayon> rayonList = new ArrayList<>();
		 try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
	            // Skip the header line of the CSV file
	            reader.readLine();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] fields = line.split(",");

	                Rayon rayon = new Rayon();
	                rayon.setNomRayon(fields[1].trim());


	                rayonList.add(rayon);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return rayonList;
		 
		 
		 
		
	}

	private static List<Categories> readCsvFileCate(String csvFilePath, Session session) {
		 List<Categories> cateList = new ArrayList<>();

		    try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
		        // Skip the header line of the CSV file
		        reader.readLine();
		        String line;
		        while ((line = reader.readLine()) != null) {
	                String[] fields = line.split(",");

	                Categories categories = new Categories();
	                categories.setNomCategorie(fields[1].trim());
	                
	                
	                
	                int rayonId = Integer.parseInt(fields[2]); 
	                Rayon rayon = session.get(Rayon.class, rayonId);
	                categories.setRayon(rayon);
	                cateList.add(categories);

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return cateList;
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
	                produit.setNomProduit(fields[3].trim());
	                produit.setPrixProduit(Double.parseDouble(fields[8]));
	                produit.setMarqueProduit(fields[2]);
	             // 将字符串转换为布尔值
	                boolean promotion = fields[6].equals("1") || fields[6].equalsIgnoreCase("true");

	                // 设置产品的促销属性
	                produit.setPromotion(promotion);

	                produit.setAdresseImageProduit(fields[1]);
	                produit.setNutriscore(fields[5]);
	                produit.setDescription(fields[4]);
	                produit.setLabel(fields[10].trim());
	                produit.setKiloProduit(fields[9]);
	             // 在设置pourcentagePromotion之前添加检查
	                if (!fields[7].isEmpty()) {
	                    produit.setPourcentagePromotion(Double.parseDouble(fields[7]));
	                } else {
	                    // 如果字符串为空，设置为默认值或采取其他适当的处理方式
	                    produit.setPourcentagePromotion(0.0); // 或者设置为其他默认值
	                }

	                produit.setVente(Integer.parseInt(fields[11]));
	                
	                
	                
	                int cateID = Integer.parseInt(fields[15]); 
	                Categories cate = session.get(Categories.class, cateID);
	                produit.setCategorie(cate);

	        

	                    produits.add(produit);
	           
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return produits;
	    }

	private static List<TempsRetait> readCsvFileTempRetrait(String csvFilePath, Session session) {
	        List<TempsRetait> TempList = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
	            // Skip the header line of the CSV file
	            reader.readLine();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] fields = line.split(",");

	                TempsRetait tempsretait = new TempsRetait();
	                tempsretait.setTempsDeRetrait(fields[1].trim());


	                TempList.add(tempsretait);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return TempList;
	    }
	 
	 private static List<Magasin> readCsvFileMagasin(String csvFilePath, Session session) {
		    List<Magasin> magasinList = new ArrayList<>();

		    try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
		        // Skip the header line of the CSV file
		        reader.readLine();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            String[] fields = line.split(";");

		            Magasin magasin = new Magasin();
		            // Assurez-vous que vous avez au moins trois champs dans chaque ligne
		            if (fields.length >= 3) {
		                magasin.setAdresseMagasin(fields[1].trim());
		                magasin.setNomMagasin(fields[2].trim());
		                // Ajoutez l'objet Magasin à la liste
		                magasinList.add(magasin);
		            } else {
		                // Gérer les lignes avec moins de champs que prévu
		                System.out.println("Erreur: la ligne du fichier CSV ne contient pas assez de champs.");
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return magasinList;
		}
	 
	 public static void insertMagasinTempRetrait() {
		    try {
		        Transaction transaction = null;
		        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		            transaction = session.beginTransaction();

		            // 读取CSV文件
		           // try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\mazhu\\Downloads\\magasin_tempsretait.csv"))) {


//		            try (BufferedReader reader = new BufferedReader(new FileReader("X:\\Telechargement\\magasin_tempsretait.csv"))) {
//
		            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Imane\\Downloads\\magasin_tempsretait.csv"))) {

		                String line;
		                boolean isFirstLine = true; // 标记是否是第一行
		                while ((line = reader.readLine()) != null) {
		                    if (isFirstLine) { // 如果是第一行，则跳过
		                        isFirstLine = false;
		                        continue;
		                    }
		                    // 解析CSV行数据
		                    String[] fields = line.split(";"); // 假设CSV文件中使用逗号作为分隔符

		                    // 将字符串转换为整数
		                    int idMagasin = Integer.parseInt(fields[0]);
		                    int idTempsRetrait = Integer.parseInt(fields[1]);

		                    // 创建插入数据的SQL语句
		                    String sql = "INSERT INTO magasin_tempsretait (IdMagasin, IdTempsRetrait) VALUES (:field1, :field2)";

		                    // 创建SQLQuery对象
		                    SQLQuery query = session.createSQLQuery(sql);

		                    // 设置参数值
		                    query.setParameter("field1", idMagasin); // 设置第一个字段的值
		                    query.setParameter("field2", idTempsRetrait); // 设置第二个字段的值

		                    // 执行SQL语句插入数据
		                    query.executeUpdate();
		                }
		            }

		            // 提交事务
		            transaction.commit();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}



	


	
	
	public static void main(String[] args) {
//		dataTEST.insertDataClient();
//		dataTEST.insertDataStatutCommande();
//		dataTEST.insertDataCommande();
//		dataTEST.insertLigneCommande();
		dataTEST.insertCSV();
		//insertMagasinTempRetrait();
		
		
		
	}
	
	
	
}
