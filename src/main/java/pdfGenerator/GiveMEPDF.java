package pdfGenerator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class GiveMEPDF {

	    public static void main(String[] args) {
//	        int orderNumber = 12345;
//	        String orderDate = "2024-02-28 10:30:00";
//	        String userName = "John Doe";
//	        String storeName = "My Store";
//	        String pickupTime = "2024-03-01 12:00:00";
//	        String storeAdress = "zzzzzzzzzzzzzzzz";
//	        
//	        Product p = new Product("niurou",12,"aaa");
//	        Product p2 = new Product("jirou",22,"111");
//	        
//	        // Sample product list
//	        List<Product> productList = new ArrayList<Product>();
//	        productList.add(p);
//	        productList.add(p2);
//	        
//	        try {
//	            generatePDF(orderNumber, orderDate, userName, storeName, storeAdress, pickupTime, productList, "X:\\output.pdf");
//	            System.out.println("finish");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	    }

	    public static void generatePDF(int orderNumber, String orderDate, String userName, String storeName, String storeAdress,
	            String pickupTime, List<Product> productList, String filePath) throws IOException {
	        try (PDDocument document = new PDDocument()) {
	            PDPage page = new PDPage(PDRectangle.A4);
	            document.addPage(page);

	            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	                contentStream.beginText();
	                contentStream.newLineAtOffset(50, PDRectangle.A4.getHeight() - 50);
	                contentStream.showText("Commande NO : " + orderNumber);
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Date Commande : " + orderDate);
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Nom de Utilisateur : " + userName);
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Nom de Magasin : " + storeName);
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Adresse Magasin : " + storeAdress);
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Temps de retait : " + pickupTime);
	                contentStream.endText();

	                // Draw product table
	                float margin = 50;
	                float yStart = PDRectangle.A4.getHeight() - 200;
	                float tableWidth = PDRectangle.A4.getWidth() - 2 * margin;
	                float yPosition = yStart;
	                float tableHeight = 20f;
	                float rowHeight = 20f;
	                int rows = productList.size();
	                int cols = 3;
	                //float colWidth = tableWidth / (float) cols;
	                float cellMargin = 5f;
	                 
	                float colWidth1 = 0.7f * tableWidth;  // La largeur de la première colonne est égale à la moitié de la largeur du tableau.
	                float colWidth2 = 0.15f * tableWidth; // La largeur de la deuxième colonne est égale à un quart de la largeur du tableau.
	                float colWidth3 = 0.15f * tableWidth; // La largeur de la troisième colonne est égale à un quart de la largeur du tableau.

	                // Draw table headers
	                float tableTopY = yPosition;
	                float tableBottomY = yPosition - tableHeight;
	                contentStream.setLineWidth(1f);
	                contentStream.moveTo(margin, tableTopY);
	                contentStream.lineTo(margin + tableWidth, tableTopY);
	                contentStream.moveTo(margin, tableBottomY);
	                contentStream.lineTo(margin + tableWidth, tableBottomY);

	                float nextX = margin; // Position de départ de x lorsque vous êtes prêt à commencer à tracer des lignes verticales
	                float headerTextOffset = 14; // Ajuster la position du texte de l'en-tête
	                for (int i = 1; i <= cols; i++) {
	                	
	                	if (i == 1) {
	                    contentStream.moveTo(nextX, tableTopY);
	                    contentStream.lineTo(nextX, tableBottomY);
	                	}

	                    // Ajouter du texte pour chaque colonne
	                    contentStream.beginText();
	                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	                    contentStream.newLineAtOffset(nextX + cellMargin, tableTopY - headerTextOffset); // Ajuster la position du texte de l'en-tête
	                    switch (i) {
	                        case 1:
	                            contentStream.showText("Nom de Produit");
	                            break;
	                        case 2:
	                            contentStream.showText("Quantité");
	                            break;
	                        case 3:
	                            contentStream.showText("Etat");
	                            break;
	                        default:
	                            break;
	                    }
	                    contentStream.endText();
	                    // Ajouter la ligne de cadre droite
	                    if (i == 1) {
	                    	nextX += colWidth1;
	                    } else if (i == 2) {
	                    	nextX += colWidth2;
	                    } else if (i == 3) {
	                    	nextX += colWidth3;
	                    }

	                    contentStream.moveTo(nextX, tableTopY);
	                    contentStream.lineTo(nextX, tableBottomY);
    
	                }

	                contentStream.stroke();

	                // Draw table content
	                float contentStartY = yPosition - tableHeight;
	                float textx = margin + cellMargin;
	                float texty = contentStartY - 15;

	                for (Product product : productList) {
	                    contentStream.beginText();
	                    contentStream.newLineAtOffset(textx, texty);
	                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
	                    contentStream.showText(product.getProductName());
	                    contentStream.newLineAtOffset(colWidth1, 0); // Utilisation des nouvelles largeurs de colonnes
	                    contentStream.showText(String.valueOf(product.getQuantity()));
	                    contentStream.newLineAtOffset(colWidth2, 0); // Utilisation des nouvelles largeurs de colonnes
	                    contentStream.showText(product.getStatus());
	                    contentStream.endText();

	                    texty -= rowHeight;
	                }



	                // Draw creation time
	                float creationTimeY = yPosition - rows * rowHeight - 40;
	                contentStream.beginText();
	                contentStream.newLineAtOffset(margin, creationTimeY);
	                contentStream.showText("Commande prête à : " + getCurrentDateTime());
	                contentStream.endText();
	            }

	            document.save(filePath);
	        }
	    }

	    public static String getCurrentDateTime() {
	        LocalDateTime currentDateTime = LocalDateTime.now();

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        String formattedDateTime = currentDateTime.format(formatter);
	        return formattedDateTime;
	    }

	    
}