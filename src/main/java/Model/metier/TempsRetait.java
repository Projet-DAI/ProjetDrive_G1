package Model.metier;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.DAO.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TempsRetait")
public class TempsRetait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTempsRetrait")
    private int idTempsRetrait;
    
    @Column(name = "TempsDeRetrait")
    private String tempsDeRetrait;
    
    @ManyToMany(mappedBy = "tempsRetaits")
    private Set<Magasin> magasins;
    
    public TempsRetait() {}

	public TempsRetait(int idTempsRetrait, String tempsDeRetrait, Set<Magasin> magasins) {
		super();
		this.idTempsRetrait = idTempsRetrait;
		this.tempsDeRetrait = tempsDeRetrait;
		this.magasins = magasins;
	}

	public int getIdTempsRetrait() {
		return idTempsRetrait;
	}

	public void setIdTempsRetrait(int idTempsRetrait) {
		this.idTempsRetrait = idTempsRetrait;
	}

	public String getTempsDeRetrait() {
		return tempsDeRetrait;
	}

	public void setTempsDeRetrait(String tempsDeRetrait) {
		this.tempsDeRetrait = tempsDeRetrait;
	}

	public Set<Magasin> getMagasins() {
		return magasins;
	}

	public void setMagasins(Set<Magasin> magasins) {
		this.magasins = magasins;
	}
    
}

