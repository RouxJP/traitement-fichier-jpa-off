package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Ingredient;

public interface IngredientDao {

	public Ingredient getIngredient( EntityManager em, String libelle);
	
	public void insererIngredient( EntityManager em, Ingredient ingredient);

}
