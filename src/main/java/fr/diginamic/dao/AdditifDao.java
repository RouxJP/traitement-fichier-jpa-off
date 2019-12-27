package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Additif;

public interface AdditifDao {
	/** Recupérer l'additif
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Additif getAdditif( EntityManager em, String libelle);
	
	/** Insérer l'additif
	 * 
	 * @param em
	 * @param additif
	 */
	public void insererAdditif( EntityManager em, Additif additif);

}
