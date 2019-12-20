package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Ingredient;


public class IngredientDaoMysql implements IngredientDao {

	@Override
	public Ingredient getIngredient(EntityManager em, String libelle) {
		Ingredient Ingredient = null;
		String sqlReq = "select f from Ingredient f where libelle = :lib ";		
		TypedQuery<Ingredient> query1 = em.createQuery( sqlReq, Ingredient.class);
		query1.setParameter( "lib", libelle);
		try {
			Ingredient = query1.getSingleResult();
			
		}catch( NoResultException e) {
			return null;
		}
		return Ingredient;
	}

	@Override
	public void insererIngredient(EntityManager em, Ingredient ingredient) {
		em.persist( ingredient);

	}

}
