package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Allergene;

public interface AllergeneDao {

	/** Recupérer l'allergène
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Allergene getAllergene( EntityManager em, String libelle);
	
	/** Insérer l'allergene
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererAllergene( EntityManager em, Allergene Allergene);

}
