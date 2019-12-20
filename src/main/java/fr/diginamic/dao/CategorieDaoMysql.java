package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Categorie;

public class CategorieDaoMysql implements CategorieDao{

	@Override
	public Categorie getCategorie( EntityManager em, String libelle) {
		Categorie categorie = null;
		String sqlReq = "select f from Categorie f where libelle = :lib ";		
		TypedQuery<Categorie> query1 = em.createQuery( sqlReq, Categorie.class);
		query1.setParameter( "lib", libelle);
		try {
			categorie = query1.getSingleResult();
			
		}catch( NoResultException e) {
			return null;
		}
		return categorie;
	}

	@Override
	public void insererCategorie(EntityManager em, Categorie categorie) {
		em.persist( categorie);
		
	}

}
