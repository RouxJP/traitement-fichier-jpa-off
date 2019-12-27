package fr.diginamic.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MARQUE")
/** Marque d'un  produit */
public class Marque {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	/** Identifiant généré par Mariadb */
	private int		id;
	
	/** Nom de la marque */
	private String	nom ;

	@OneToMany( mappedBy ="marque")
	private Set<Produit> lstProduit;
	
	public Marque() {
		
	}
	
	public Marque( String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
