package Model.metier;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;

import java.util.List;

@Entity
@Table(name = "TempsRetait")
public class TempsRetait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTempsRetrait")
    private int idTempsRetrait;

    @ManyToOne
    @JoinColumn(name = "IdCommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "IdMagasin")
    private Magasin magasin;
    
    @Column(name = "TempsDeRetrait")
    private String tempsDeRetrait;
    
    public TempsRetait(Commande commande, Magasin magasin, String tempsDeRetrait) {
        this.commande = commande;
        this.magasin = magasin;
        this.tempsDeRetrait = tempsDeRetrait;
    }
    
    public TempsRetait() {}
    
    public int getIdTempsRetrait() {
        return idTempsRetrait;
    }

    public void setIdTempsRetrait(int idTempsRetrait) {
        this.idTempsRetrait = idTempsRetrait;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public String getTempsDeRetrait() {
        return tempsDeRetrait;
    }

    public void setTempsDeRetrait(String tempsDeRetrait) {
        this.tempsDeRetrait = tempsDeRetrait;
    }
}

