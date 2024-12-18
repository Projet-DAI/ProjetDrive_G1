package Model.metier;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Rayon")
public class Rayon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRayon")
    private int idRayon;

    @Column(name = "NomRayon")
    private String nomRayon;
    
    @OneToMany(mappedBy = "rayon", cascade = CascadeType.ALL)
    private List<Categories> categories;

	public Rayon(String nomRayon) {
		super();
		this.nomRayon = nomRayon;
	}
	
	
	public Rayon() {
		
	}

	public int getIdRayon() {
		return idRayon;
	}

	public void setIdRayon(int idRayon) {
		this.idRayon = idRayon;
	}

	public String getNomRayon() {
		return nomRayon;
	}

	public void setNomRayon(String nomRayon) {
		this.nomRayon = nomRayon;
	}
    
    

}
