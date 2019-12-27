package fr.diginamic.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ALLERGENE")
/**
 * Allergene contenu dans un produit
 * @author acer
 *
 */
public class Allergene {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	/** Identifiant généré par Mariadb */
	private int		id;
	
	/** Libellé */
	private String 	libelle ;

	@ManyToMany
	@JoinTable(	name="produit_allergene", 
	joinColumns = @JoinColumn(name="id_allergene", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"))	
	private Set<Produit>	lstProduits = new HashSet<Produit>();
	
	public Allergene() {
		
	}
	
	public Allergene( String libelle) {
		this.libelle 		= libelle ;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
}
