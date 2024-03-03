package Model.metier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Set<Produit> produits = new HashSet<>();
	
	public Fournisseur() {
		
	}
	
	
	
	public Fournisseur(int idFournisseur, String nomFournisseur, Set<Produit> produits) {
		super();
		this.idFournisseur = idFournisseur;
		this.nomFournisseur = nomFournisseur;
		this.produits = produits;
	}



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

	public Set<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
	
	
	
	
	
	
}
