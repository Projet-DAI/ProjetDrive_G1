package Model.metier;

import java.util.List;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ManyToAny;



@Entity
@Table(name = "Fournisseur")
public class Fournisseur {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFournisseur")
    private int idFournisseur;
	
	@Column(name = "NomFournisseur")
    private String nomFournisseur;
	
	@ManyToMany(mappedBy = "fournisseurs")
	private List<Produit> produits;
	
	public Fournisseur(int idFournisseur, String nomFournisseur, List<Produit> produits) {
		super();
		this.idFournisseur = idFournisseur;
		this.nomFournisseur = nomFournisseur;
		this.produits = produits;
	}
	
	public Fournisseur() {
		
	}
	
	/* getters and setters */
	public int getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(int idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

	public String getNomFournisseur() {
		return nomFournisseur;
	}

	public void setNomFournisseur(String nomFournisseur) {
		this.nomFournisseur = nomFournisseur;
	}
	
	
	
}
