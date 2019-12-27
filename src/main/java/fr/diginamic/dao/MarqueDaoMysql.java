package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Marque;

public class MarqueDaoMysql implements MarqueDao {

	@Override
	/** Récupérer la marque
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Marque getMarque(EntityManager em, String libelle) {
		Marque Marque = null;
		String sqlReq = "select f from Marque f where nom = :lib ";		
		TypedQuery<Marque> query1 = em.createQuery( sqlReq, Marque.class);
		query1.setParameter( "lib", libelle);
		try {
			Marque = query1.getSingleResult();
			
		}catch( NoResultException e) {
			return null;
		}
		return Marque;
	}

	@Override
	/** Insérer la marque
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererMarque(EntityManager em, Marque marque) {
		em.persist( marque);
	}

}
