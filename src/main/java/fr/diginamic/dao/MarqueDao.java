package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Marque;



public interface MarqueDao {

	/** Récupérer la marque
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Marque getMarque( EntityManager em, String libelle);
	
	/** Insérer la marque
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererMarque( EntityManager em, Marque marque);

}
