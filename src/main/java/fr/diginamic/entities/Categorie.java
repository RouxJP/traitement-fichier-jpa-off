package fr.diginamic.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="CATEGORIE")
/** Catégorie de produit
 * 
 * @author acer
 *
 */
public class Categorie {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	/** Identifiant généré par Mariadb */
	private int		id;
	
	/** Libellé */
	private String  libelle ;
	
	@OneToMany( mappedBy ="categorie")
	private Set<Produit> lstProduit;
	
	public Categorie() {
		
	}
	public Categorie( String libelle) {
		this.libelle = libelle;
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
	 * @return the lstProduit
	 */
	public Set<Produit> getLstProduit() {
		return lstProduit;
	}
	/** Setter
	 * @param lstProduit the lstProduit to set
	 */
	public void setLstProduit(Set<Produit> lstProduit) {
		this.lstProduit = lstProduit;
	}

}
