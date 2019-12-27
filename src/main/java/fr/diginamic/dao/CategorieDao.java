package fr.diginamic.dao;

import javax.persistence.EntityManager;


import fr.diginamic.entities.Categorie;

public interface CategorieDao {

	/** Récupérer la catégorie
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Categorie getCategorie( EntityManager em, String libelle);
	
	/** Insérer la Catégorie
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererCategorie( EntityManager em, Categorie categorie);
	
}
