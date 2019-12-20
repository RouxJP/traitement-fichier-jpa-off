package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Marque;



public interface MarqueDao {

	public Marque getMarque( EntityManager em, String libelle);
	
	public void insererMarque( EntityManager em, Marque marque);

}
