package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Additif;


public class AdditifDaoMysql implements AdditifDao {

	@Override
	/** Recupérer l'additif
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Additif getAdditif(EntityManager em, String libelle) {
		Additif additif = null;
		String sqlReq = "select f from Additif f where libelle = :lib ";		
		TypedQuery<Additif> query1 = em.createQuery( sqlReq, Additif.class);
		query1.setParameter( "lib", libelle);
		try {
			additif = query1.getSingleResult();
			
		}catch( NoResultException e) {
			return null;
		}
		return additif;
	}

	@Override
	/** Insérer l'additif
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererAdditif(EntityManager em, Additif additif) {
		em.persist( additif);

	}

}
