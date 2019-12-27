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
@Table(name="ADDITIF")
/**
 * Additif de produit
 * @author acer
 *
 */
public class Additif {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	/** Identifiant généré par Mariadb */
	private int		id;
	
	/** Libellé */
	private String libelle ;

	
	@ManyToMany
	@JoinTable(	name="produit_additif", 
	joinColumns = @JoinColumn(name="id_additif", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"))	
	private Set<Produit>	lstProduits = new HashSet<Produit>();

	public Additif(){
		
	}
	
	public Additif( String libelle) {
		this.libelle 		= libelle ;
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
