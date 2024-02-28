package metier;

import javax.persistence.*;

@Entity
@Table(name = "StatutCommande")
public class StatutCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdStatutCommande")
    private int idStatutCommande;

    @Column(name = "LibelleStatut")
    private String libelleStatut;

	public StatutCommande(String libelleStatut) {
		super();
		this.libelleStatut = libelleStatut;
	}
    
    
    public StatutCommande() {
    	
    }


	public int getIdStatutCommande() {
		return idStatutCommande;
	}


	public void setIdStatutCommande(int idStatutCommande) {
		this.idStatutCommande = idStatutCommande;
	}


	public String getLibelleStatut() {
		return libelleStatut;
	}


	public void setLibelleStatut(String libelleStatut) {
		this.libelleStatut = libelleStatut;
	}
    
    

}
