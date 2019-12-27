package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Ingredient;

public interface IngredientDao {

	/** Récupérer l'ingrédient
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Ingredient getIngredient( EntityManager em, String libelle);
	
	/** Insérer l'ingrédient
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererIngredient( EntityManager em, Ingredient ingredient);

}
