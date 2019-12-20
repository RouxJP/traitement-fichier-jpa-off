package fr.diginamic.dao;

import javax.persistence.EntityManager;


import fr.diginamic.entities.Categorie;

public interface CategorieDao {

	public Categorie getCategorie( EntityManager em, String libelle);
	
	public void insererCategorie( EntityManager em, Categorie categorie);
	
}
