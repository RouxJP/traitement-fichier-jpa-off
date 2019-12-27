package fr.diginamic.dao;

import javax.persistence.EntityManager;


import fr.diginamic.entities.Produit;

public interface ProduitDao {

	/** Récupérer le produit
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Produit getProduit( EntityManager em, String libelle);
	
	/** Insérer le produit
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererProduit( EntityManager em, Produit produit);

}
