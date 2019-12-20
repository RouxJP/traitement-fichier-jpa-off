package fr.diginamic.dao;

import javax.persistence.EntityManager;


import fr.diginamic.entities.Produit;

public interface ProduitDao {

	public Produit getProduit( EntityManager em, String libelle);
	
	public void insererProduit( EntityManager em, Produit produit);

}
