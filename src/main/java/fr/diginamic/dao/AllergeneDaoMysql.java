package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Allergene;

public class AllergeneDaoMysql implements AllergeneDao {

	@Override
	/** Récupérer l'allergene
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Allergene getAllergene(EntityManager em, String libelle) {
		Allergene Allergene = null;
		String sqlReq = "select f from Allergene f where libelle = :lib ";		
		TypedQuery<Allergene> query1 = em.createQuery( sqlReq, Allergene.class);
		query1.setParameter( "lib", libelle);
		try {
			Allergene = query1.getSingleResult();
			
		}catch( NoResultException e) {
			return null;
		}
		return Allergene;
	}

	@Override
	/** Insérer l'allergene
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererAllergene(EntityManager em, Allergene allergene) {
		em.persist( allergene);

	}

}
