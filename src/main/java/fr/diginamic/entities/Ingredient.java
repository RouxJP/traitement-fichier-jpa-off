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
@Table( name="INGREDIENT")
public class Ingredient {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int		id;
	
	private String	libelle;

	@ManyToMany
	@JoinTable(	name="produit_ingredient", 
	joinColumns = @JoinColumn(name="nom_ingredient", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="nom_produit", referencedColumnName="nom_produit"))	
	private Set<Produit>	lstProduits = new HashSet<Produit>();
	
	
	public Ingredient() {
		
	}
	
	public Ingredient ( String libelle){
		this.libelle = libelle ;
	}
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter
	 * @return the lstProduits
	 */
	public Set<Produit> getLstProduits() {
		return lstProduits;
	}

	/** Setter
	 * @param lstProduits the lstProduits to set
	 */
	public void setLstProduits(Set<Produit> lstProduits) {
		this.lstProduits = lstProduits;
	}
}
