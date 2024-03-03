package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.metier.Commande;
import Model.metier.LigneCommande;
import Model.metier.Produit;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {
    private Connection connection;

    public CommandeDAO(Connection connection) {
        this.connection = connection;
    }

    public int insererCommande(Commande commande) {
        int commandeId = -1;

        try {

        	String query = "INSERT INTO commande (id_client, date_commande, montant_total) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, commande.getClient().getIdClient());
                preparedStatement.setDate(2, new java.sql.Date(commande.getDateCommande().getTime()));
                preparedStatement.setDouble(3, commande.getMontantTotal());

                int rowsAffected = preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        commandeId = generatedKeys.getInt(1);
                    }
                }

                if (rowsAffected == 0) {
                    throw new SQLException("L'insertion de la commande a échoué, aucune ligne affectée.");
                }
            }

            query = "INSERT INTO ligne_commande (id_commande, id_produit, quantite, prix_unitaire) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (LigneCommande ligneCommande : commande.getLignesCommande()) {
                    preparedStatement.setInt(1, commandeId);
                    preparedStatement.setInt(2, ligneCommande.getProduit().getIdProduit());
                    preparedStatement.setInt(3, ligneCommande.getQuantite());
                    preparedStatement.setDouble(4, ligneCommande.getPrixUnitaire());
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commandeId;
    }
}
